/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.controller;import cn.zhuatech.billing.common.ApiResponse;import cn.zhuatech.billing.service.TieredUsageRatingService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/advanced/billing") public class TieredUsageRatingController{private final TieredUsageRatingService service;/**
                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                  */
public TieredUsageRatingController(TieredUsageRatingService service){this.service=service;}/**
                                                                                                                                                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                             */
@PostMapping("/rate") public ApiResponse<TieredUsageRatingService.RatingResult> rate(@Valid @RequestBody TieredUsageRatingService.RatingRequest request){return ApiResponse.ok(service.rate(request));}}
