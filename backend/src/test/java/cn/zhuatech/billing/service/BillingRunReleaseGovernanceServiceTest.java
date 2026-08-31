/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.service;
import org.junit.jupiter.api.Test;import static org.assertj.core.api.Assertions.assertThat;
class BillingRunReleaseGovernanceServiceTest{private final BillingRunReleaseGovernanceService service=new BillingRunReleaseGovernanceService();
 @Test void issuesControlledBillingRun(){var r=service.assess(new BillingRunReleaseGovernanceService.Request("RUN-001",80,true,true,true,0,true,true,true,true,true));assertThat(r.decision()).isEqualTo(BillingRunReleaseGovernanceService.Decision.ISSUE);}
 @Test void holdsInvalidBillingRun(){var r=service.assess(new BillingRunReleaseGovernanceService.Request("RUN-002",80,false,false,false,3,false,false,false,false,false));assertThat(r.decision()).isEqualTo(BillingRunReleaseGovernanceService.Decision.HOLD);assertThat(r.blockers()).hasSize(6);assertThat(r.actions()).hasSize(3);}}
