/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.controller;import cn.zhuatech.billing.common.ApiResponse;import cn.zhuatech.billing.service.EnterpriseBillingService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/billing") public class EnterpriseBillingController {private final EnterpriseBillingService service;/**
                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                     */
public EnterpriseBillingController(EnterpriseBillingService service){this.service=service;}/**
                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                */
@PostMapping("/price") ApiResponse<EnterpriseBillingService.Invoice> price(@Valid @RequestBody EnterpriseBillingService.PricingRequest request){return ApiResponse.ok(service.price(request));}}
