/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class EnterpriseBillingApiTests {@Autowired MockMvc mvc;
 @Test void graduatedTiersDiscountTaxAndReceiptAreCalculated() throws Exception {mvc.perform(post("/api/enterprise/billing/price").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"usage":150,"fixedFee":100,"discountRate":0.1,"taxRate":0.06,"paidAmount":100,"tiers":[{"upTo":100,"unitPrice":2},{"unitPrice":1.5}]}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.usageCharge").value(275.0)).andExpect(jsonPath("$.data.total").value(357.75)).andExpect(jsonPath("$.data.outstanding").value(257.75));}
 @Test void incompleteTiersAreRejected() throws Exception {mvc.perform(post("/api/enterprise/billing/price").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"usage":150,"fixedFee":0,"discountRate":0,"taxRate":0,"paidAmount":0,"tiers":[{"upTo":100,"unitPrice":2}]}
 """)).andExpect(status().isBadRequest());}
}
