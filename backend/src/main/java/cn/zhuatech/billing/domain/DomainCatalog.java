/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.billing.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String,WorkflowAction> actions=new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog(){
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交开票", List.of("草稿"), "待开票"));
actions.put("ISSUE", new WorkflowAction("ISSUE", "确认开票", List.of("待开票"), "已开票"));
actions.put("RECEIVE", new WorkflowAction("RECEIVE", "登记回款", List.of("已开票"), "部分回款"));
actions.put("SETTLE", new WorkflowAction("SETTLE", "完成核销", List.of("部分回款"), "已结清"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName(){return "知华科技企业计费与应收管理系统";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene(){return "产品计费、账单生成、发票申请、应收跟踪、收款核销和退款管理";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus(){return "草稿";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel(){return "客户/合同";} /**
                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                 */
public String amountLabel(){return "账单金额";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel(){return "计费项";} /**
                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                  */
public String dueLabel(){return "到期日";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules(){return List.of(
        new ModuleDefinition("PRICING","计费规则","维护一次性、周期、阶梯和用量计费规则"),
    new ModuleDefinition("INVOICE","账单与开票","生成账单、校验税率并提交开票"),
    new ModuleDefinition("RECEIPT","收款与核销","登记回款并匹配客户、合同和发票"),
    new ModuleDefinition("REFUND","退款与红冲","处理退款申请、红字发票和余额调整")
    );}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String,WorkflowAction> actions(){return Collections.unmodifiableMap(actions);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to){}
}
