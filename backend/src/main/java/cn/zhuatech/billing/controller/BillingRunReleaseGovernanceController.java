/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.controller;
import cn.zhuatech.billing.common.ApiResponse;import cn.zhuatech.billing.service.BillingRunReleaseGovernanceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/billing")public class BillingRunReleaseGovernanceController{private final BillingRunReleaseGovernanceService service;/**
                                                                                                                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                       */
public BillingRunReleaseGovernanceController(BillingRunReleaseGovernanceService service){this.service=service;}/**
                                                                                                                                                                                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                      */
@PostMapping("/run-release")public ApiResponse<BillingRunReleaseGovernanceService.Assessment> assess(@Valid @RequestBody BillingRunReleaseGovernanceService.Request request){return ApiResponse.ok(service.assess(request));}}
