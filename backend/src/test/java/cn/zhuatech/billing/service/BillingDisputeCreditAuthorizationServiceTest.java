/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BillingDisputeCreditAuthorizationServiceTest {
    private final BillingDisputeCreditAuthorizationService service = new BillingDisputeCreditAuthorizationService();

    @Test void authorizesEvidenceBackedCredit() {
        var result = service.assess(request(true, true, true));
        assertThat(result.decision()).isEqualTo(BillingDisputeCreditAuthorizationService.Decision.AUTHORIZE);
        assertThat(result.blockers()).isEmpty();
    }

    @Test void reviewsCreditWithOperationalActions() {
        var result = service.assess(request(false, false, false));
        assertThat(result.decision()).isEqualTo(BillingDisputeCreditAuthorizationService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(3);
    }

    @Test void blocksUnsupportedCredit() {
        var result = service.assess(new BillingDisputeCreditAuthorizationService.Request("CASE-003", false, false,
                false, false, false, false, false, false, true, true, true, false, false));
        assertThat(result.decision()).isEqualTo(BillingDisputeCreditAuthorizationService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(10);
    }

    private BillingDisputeCreditAuthorizationService.Request request(boolean payment, boolean reason, boolean notice) {
        return new BillingDisputeCreditAuthorizationService.Request("CASE-001", true, true, true, true, true,
                true, true, true, payment, reason, notice, true, true);
    }
}
