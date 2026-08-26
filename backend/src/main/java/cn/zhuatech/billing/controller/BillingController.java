/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.controller;
import cn.zhuatech.billing.common.ApiResponse;
import cn.zhuatech.billing.model.*;
import cn.zhuatech.billing.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api")
public class BillingController {
    private final BillingService service; public BillingController(BillingService service){this.service=service;}
    @GetMapping("/public/about") ApiResponse<Map<String,Object>> about(){return ApiResponse.ok(service.about());}
    @GetMapping("/catalog") ApiResponse<BillingService.CatalogView> catalog(){return ApiResponse.ok(service.catalog());}
    @GetMapping("/dashboard") ApiResponse<BillingService.Dashboard> dashboard(){return ApiResponse.ok(service.dashboard());}
    @GetMapping("/records") ApiResponse<List<BusinessRecord>> records(@RequestParam(required=false) String module){return ApiResponse.ok(service.list(module));}
    @PostMapping("/records") ApiResponse<BusinessRecord> create(@Valid @RequestBody BillingService.RecordRequest request){return ApiResponse.ok(service.create(request));}
    @PutMapping("/records/{id}") ApiResponse<BusinessRecord> update(@PathVariable Long id,@Valid @RequestBody BillingService.RecordRequest request){return ApiResponse.ok(service.update(id,request));}
    @PostMapping("/records/{id}/actions") ApiResponse<BusinessRecord> action(@PathVariable Long id,@Valid @RequestBody BillingService.ActionRequest request){return ApiResponse.ok(service.action(id,request));}
    @DeleteMapping("/records/{id}") ApiResponse<Void> delete(@PathVariable Long id){service.delete(id);return ApiResponse.ok(null);}
    @GetMapping("/admin/audit-logs") ApiResponse<List<AuditLog>> audits(){return ApiResponse.ok(service.auditLogs());}
    @GetMapping("/admin/settings") ApiResponse<Map<String,String>> settings(){return ApiResponse.ok(service.settings());}
    @PutMapping("/admin/settings") ApiResponse<Map<String,String>> updateSettings(@RequestBody Map<String,String> values){return ApiResponse.ok(service.updateSettings(values));}
}
