/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.controller;
import cn.zhuatech.billing.common.ApiResponse;
import cn.zhuatech.billing.service.DomainInsightService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/insights")
public class DomainInsightController {
    private final DomainInsightService service;public DomainInsightController(DomainInsightService service){this.service=service;}
    @PostMapping("/billing") ApiResponse<Map<String,Object>> analyze(@Valid @RequestBody DomainInsightService.InsightRequest request){return ApiResponse.ok(service.analyze(request));}
}
