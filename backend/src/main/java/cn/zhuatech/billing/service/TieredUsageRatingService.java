/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
@Service public class TieredUsageRatingService{
 public RatingResult rate(@Valid RatingRequest request){List<Tier> tiers=request.tiers().stream().sorted(Comparator.comparing(Tier::upToQuantity)).toList();BigDecimal previous=BigDecimal.ZERO,remaining=request.usageQuantity().subtract(request.includedQuantity()).max(BigDecimal.ZERO),variable=BigDecimal.ZERO;List<RatedTier> rated=new ArrayList<>();
  for(Tier tier:tiers){if(tier.upToQuantity().compareTo(previous)<=0)continue;BigDecimal width=tier.upToQuantity().subtract(previous),quantity=remaining.min(width);if(quantity.signum()>0){BigDecimal amount=quantity.multiply(tier.unitPrice()).setScale(2,RoundingMode.HALF_UP);rated.add(new RatedTier(previous,tier.upToQuantity(),quantity,tier.unitPrice(),amount));variable=variable.add(amount);remaining=remaining.subtract(quantity);}previous=tier.upToQuantity();if(remaining.signum()==0)break;}
  BigDecimal subtotal=request.fixedFee().add(variable).setScale(2,RoundingMode.HALF_UP),tax=subtotal.multiply(request.taxRate()).setScale(2,RoundingMode.HALF_UP),total=subtotal.add(tax);List<String>warnings=remaining.signum()>0?List.of("有 "+remaining+" 用量未配置计费阶梯"):List.of();
  return new RatingResult(remaining.signum()>0?"RATE_CARD_GAP":"RATED",request.accountNo(),request.usageQuantity(),request.includedQuantity(),variable.setScale(2,RoundingMode.HALF_UP),request.fixedFee(),subtotal,tax,total,rated,warnings);
 }
 public record RatingRequest(@NotBlank String accountNo,@NotNull @DecimalMin("0") BigDecimal usageQuantity,@NotNull @DecimalMin("0") BigDecimal includedQuantity,@NotNull @DecimalMin("0") BigDecimal fixedFee,@NotNull @DecimalMin("0") @DecimalMax("1") BigDecimal taxRate,@NotEmpty List<@Valid Tier> tiers){}
 public record Tier(@NotNull @DecimalMin("0.01") BigDecimal upToQuantity,@NotNull @DecimalMin("0") BigDecimal unitPrice){}
 public record RatedTier(BigDecimal fromExclusive,BigDecimal toInclusive,BigDecimal quantity,BigDecimal unitPrice,BigDecimal amount){}
 public record RatingResult(String status,String accountNo,BigDecimal usageQuantity,BigDecimal includedQuantity,BigDecimal variableCharge,BigDecimal fixedFee,BigDecimal subtotal,BigDecimal tax,BigDecimal invoiceTotal,List<RatedTier> ratedTiers,List<String>warnings){}
}
