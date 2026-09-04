/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillingDisputeCreditAuthorizationService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.customerVerified()) blockers.add("客户与争议申请人未核验");
        if (!request.invoiceLocked()) blockers.add("争议账单尚未冻结追收与变更");
        if (!request.entitlementEvidence()) blockers.add("合同权益证据不完整");
        if (!request.usageEvidenceReconciled()) blockers.add("用量与计费明细未对账");
        if (!request.taxImpactReviewed()) blockers.add("税务与发票影响未复核");
        if (!request.creditAmountPositive()) blockers.add("贷项金额必须大于零");
        if (!request.creditWithinAuthority()) blockers.add("贷项金额超出当前审批权限");
        if (!request.financeApproved()) blockers.add("财务尚未批准贷项处理");
        if (!request.approverSeparated()) blockers.add("经办人与审批人未职责分离");
        if (!request.auditReady()) blockers.add("争议处理审计材料不完整");
        if (!request.originalPaymentLinked()) actions.add("关联原始支付或应收记录");
        if (!request.reasonCodeAssigned()) actions.add("补充标准争议与贷项原因码");
        if (!request.customerNoticeReady()) actions.add("生成客户处理结果通知");
        Decision decision = !blockers.isEmpty() ? Decision.BLOCKED : !actions.isEmpty() ? Decision.REVIEW : Decision.AUTHORIZE;
        return new Assessment(request.caseId(), decision, List.copyOf(blockers), List.copyOf(actions));
    }

    public record Request(@NotBlank String caseId, boolean customerVerified, boolean invoiceLocked,
                          boolean entitlementEvidence, boolean usageEvidenceReconciled,
                          boolean taxImpactReviewed, boolean creditAmountPositive,
                          boolean creditWithinAuthority, boolean financeApproved,
                          boolean originalPaymentLinked, boolean reasonCodeAssigned,
                          boolean customerNoticeReady, boolean approverSeparated, boolean auditReady) {}
    public record Assessment(String caseId, Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { AUTHORIZE, REVIEW, BLOCKED }
}
