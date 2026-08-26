/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.service;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.*;
import java.util.*;
@Service
public class DomainInsightService {
    public Map<String,Object> analyze(InsightRequest req){
        Map<String,Object> result=new LinkedHashMap<>();
        BigDecimal listAmount=req.quantity().multiply(req.unitPrice());BigDecimal subtotal=listAmount.multiply(BigDecimal.ONE.subtract(req.discountRate())).setScale(2,RoundingMode.HALF_UP);
BigDecimal tax=subtotal.multiply(req.taxRate()).setScale(2,RoundingMode.HALF_UP);BigDecimal total=subtotal.add(tax);BigDecimal outstanding=total.subtract(req.paidAmount()).max(BigDecimal.ZERO);
result.put("subtotal",subtotal);result.put("tax",tax);result.put("total",total);result.put("outstanding",outstanding);result.put("decision",outstanding.signum()==0?"SETTLED":req.paidAmount().signum()>0?"PARTIAL":"UNPAID");
        return result;
    }
    private BigDecimal rate(long numerator,long denominator){return denominator==0?BigDecimal.ZERO:BigDecimal.valueOf(numerator).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(denominator),2,RoundingMode.HALF_UP);}
    public record InsightRequest(@Positive BigDecimal quantity, @DecimalMin("0.0") BigDecimal unitPrice, @DecimalMin("0.0") @DecimalMax("1.0") BigDecimal discountRate, @DecimalMin("0.0") @DecimalMax("1.0") BigDecimal taxRate, @DecimalMin("0.0") BigDecimal paidAmount){}
}
