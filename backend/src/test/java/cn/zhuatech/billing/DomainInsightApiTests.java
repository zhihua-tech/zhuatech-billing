/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class DomainInsightApiTests {
    @Autowired MockMvc mvc;
    @Test void domainInsightProducesAuditableDecision() throws Exception {
        mvc.perform(post("/api/insights/billing").with(httpBasic("operator","operator123"))
            .contentType(MediaType.APPLICATION_JSON).content("{\"quantity\":10,\"unitPrice\":1000,\"discountRate\":0.1,\"taxRate\":0.06,\"paidAmount\":5000}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.data.outstanding").value(4540));
    }
}
