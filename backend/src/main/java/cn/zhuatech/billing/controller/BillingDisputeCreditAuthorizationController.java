/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.controller;

import cn.zhuatech.billing.common.ApiResponse;
import cn.zhuatech.billing.service.BillingDisputeCreditAuthorizationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/billing")
public class BillingDisputeCreditAuthorizationController {
    private final BillingDisputeCreditAuthorizationService service;
    public BillingDisputeCreditAuthorizationController(BillingDisputeCreditAuthorizationService service) { this.service = service; }
    @PostMapping("/dispute-credit-authorization")
    public ApiResponse<BillingDisputeCreditAuthorizationService.Assessment> assess(
            @Valid @RequestBody BillingDisputeCreditAuthorizationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
