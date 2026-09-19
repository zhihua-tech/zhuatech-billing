/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.controller;

import cn.zhuatech.billing.common.ApiResponse;
import cn.zhuatech.billing.service.BillingDisputeCreditAuthorizationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/billing")
public class BillingDisputeCreditAuthorizationController {
    private final BillingDisputeCreditAuthorizationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public BillingDisputeCreditAuthorizationController(BillingDisputeCreditAuthorizationService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/dispute-credit-authorization")
    public ApiResponse<BillingDisputeCreditAuthorizationService.Assessment> assess(
            @Valid @RequestBody BillingDisputeCreditAuthorizationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
