/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
@Service
public class BillingRunReleaseGovernanceService{
 public Assessment assess(Request r){List<String>b=new ArrayList<>();List<String>a=new ArrayList<>();
  if(!r.meteringComplete())b.add("计量或用量数据未完整结转");if(!r.pricingVersionEffective())b.add("计费规则版本未生效");
  if(!r.taxValidated())b.add("税率与开票主体校验未通过");if(r.duplicateInvoiceCount()>0)b.add("账单批次存在重复开票风险");
  if(!r.customerMasterComplete())b.add("客户开票主数据不完整");if(!r.periodOpen())b.add("计费期间已关闭");
  if(!r.creditNotesApproved())a.add("完成红字与贷项通知审批");if(!r.revenueReviewComplete())a.add("完成收入与递延口径复核");if(!r.ownerApproved())a.add("取得计费负责人批准");
  Decision d=!b.isEmpty()?Decision.HOLD:!a.isEmpty()?Decision.REVIEW:Decision.ISSUE;
  return new Assessment(r.runId(),r.invoiceCount(),d,List.copyOf(b),List.copyOf(a));}
 public record Request(@NotBlank String runId,@Min(1)int invoiceCount,boolean meteringComplete,
  boolean pricingVersionEffective,boolean taxValidated,@Min(0)int duplicateInvoiceCount,
  boolean customerMasterComplete,boolean periodOpen,boolean creditNotesApproved,
  boolean revenueReviewComplete,boolean ownerApproved){}
 public record Assessment(String runId,int invoiceCount,Decision decision,List<String> blockers,List<String> actions){}
 public enum Decision{ISSUE,REVIEW,HOLD}
}
