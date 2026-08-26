/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.http.HttpStatus;import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import java.math.*;import java.util.*;
@Service public class EnterpriseBillingService {
 public Invoice price(@Valid PricingRequest req){
  BigDecimal remaining=req.usage(),lower=BigDecimal.ZERO,usageCharge=BigDecimal.ZERO;List<TierCharge> charges=new ArrayList<>();
  for(int i=0;i<req.tiers().size()&&remaining.signum()>0;i++){
   var tier=req.tiers().get(i);if(tier.upTo()!=null&&tier.upTo().compareTo(lower)<=0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"阶梯上限必须递增");
   BigDecimal capacity=tier.upTo()==null?remaining:tier.upTo().subtract(lower);BigDecimal quantity=remaining.min(capacity);
   BigDecimal amount=quantity.multiply(tier.unitPrice());usageCharge=usageCharge.add(amount);remaining=remaining.subtract(quantity);
   charges.add(new TierCharge(lower,tier.upTo(),quantity,money(amount)));if(tier.upTo()!=null)lower=tier.upTo();
  }
  if(remaining.signum()>0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"缺少覆盖剩余用量的无上限阶梯");
  BigDecimal subtotal=req.fixedFee().add(usageCharge);BigDecimal discount=subtotal.multiply(req.discountRate());BigDecimal taxable=subtotal.subtract(discount);
  BigDecimal tax=taxable.multiply(req.taxRate()),total=taxable.add(tax),outstanding=total.subtract(req.paidAmount()).max(BigDecimal.ZERO);
  return new Invoice(money(usageCharge),money(subtotal),money(discount),money(tax),money(total),money(outstanding),charges,outstanding.signum()==0?"SETTLED":"RECEIVABLE");
 }
 private BigDecimal money(BigDecimal v){return v.setScale(2,RoundingMode.HALF_UP);}
 public record PricingRequest(@NotNull @DecimalMin("0") BigDecimal usage,@NotNull @DecimalMin("0") BigDecimal fixedFee,
  @NotNull @DecimalMin("0") @DecimalMax("1") BigDecimal discountRate,@NotNull @DecimalMin("0") @DecimalMax("1") BigDecimal taxRate,
  @NotNull @DecimalMin("0") BigDecimal paidAmount,@NotEmpty List<@Valid Tier> tiers){}
 public record Tier(@DecimalMin("0") BigDecimal upTo,@NotNull @DecimalMin("0") BigDecimal unitPrice){}
 public record TierCharge(BigDecimal fromExclusive,BigDecimal upToInclusive,BigDecimal quantity,BigDecimal amount){}
 public record Invoice(BigDecimal usageCharge,BigDecimal subtotal,BigDecimal discount,BigDecimal tax,BigDecimal total,BigDecimal outstanding,List<TierCharge> tiers,String decision){}
}
