/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.config;
import cn.zhuatech.billing.model.*;
import cn.zhuatech.billing.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Configuration public class DataInitializer {
    @Bean CommandLineRunner seed(BusinessRecordRepository records,SystemSettingRepository settings){return args->{
        if(records.count()>0)return;
            settings.save(new SystemSetting("currency","CNY"));
    settings.save(new SystemSetting("taxRate","6%"));
    settings.save(new SystemSetting("billingCycle","自然月"));
    settings.save(new SystemSetting("overdueReminder","到期前3天"));
            records.save(new BusinessRecord("BIL-20260826-001","PRICING","私有化软件年度服务计费规则","企业软件产品线","计费管理员","已结清",new BigDecimal("680000"),4,LocalDate.now().plusDays(365),"正常","许可、实施和维保分项计费"));
    records.save(new BusinessRecord("BIL-20260826-002","INVOICE","华东客户八月服务账单","华东制造集团","应收会计","待开票",new BigDecimal("286000"),6,LocalDate.now().plusDays(5),"正常","合同验收资料已齐全"));
    records.save(new BusinessRecord("BIL-20260826-003","RECEIPT","渠道客户回款核销","渠道合作伙伴","资金会计","部分回款",new BigDecimal("180000"),3,LocalDate.now().plusDays(-2),"关注","剩余6万元等待客户付款"));
    records.save(new BusinessRecord("BIL-20260826-004","REFUND","云资源套餐差额退款","成长型客户","客户运营","草稿",new BigDecimal("12800"),1,LocalDate.now().plusDays(3),"正常","因套餐降配退还差额"));
    };}
}
