# ZhuaTech Billing｜知华科技企业计费与应收管理系统

## 企业级增强：账单批次开票治理

新增计量、价格、税务、重复风险、客户主数据、期间、贷项和收入复核门禁，详见 [账单批次治理](docs/ENTERPRISE_BILLING_RUN_RELEASE.md)。

        > 从计费规则、账单、开票到收款核销形成完整收入链路。

        [![Java 21](https://img.shields.io/badge/Java-21-315a70)](backend/pom.xml) [![Vue 3](https://img.shields.io/badge/Vue-3-42b883)](frontend/package.json) [![MySQL 8](https://img.shields.io/badge/MySQL-8-4479a1)](compose.yaml) [![个人非商用](https://img.shields.io/badge/license-personal%20non--commercial-b47b3a)](LICENSE)

        ZhuaTech Billing 是知华科技（上海如静知华信息科技有限公司）发布的前后端分离企业应用社区源码版，面向产品计费、账单生成、发票申请、应收跟踪、收款核销和退款管理。官网：[https://www.zhuatech.cn/](https://www.zhuatech.cn/)。

        ## 企业版 V2.0

        在原有四大业务模块基础上，新增企业控制中心：支持组织与账期维度、幂等防重、经办/管理员职责分离、审批闭环、附件 SHA-256 元数据、办结凭证门槛、外部适配器回执、乐观锁和全程审计；并实现固定费、阶梯用量、折扣、税额、已收款与未结应收计算。详见[企业版能力说明](docs/ENTERPRISE.md)。

        ## 业务闭环

        ```text
        提交开票 → 确认开票 → 登记回款 → 完成核销
        ```

        ## 主要模块

        | 模块 | 已实现能力 |
        | --- | --- |
        | 计费规则 | 维护一次性、周期、阶梯和用量计费规则 |
| 账单与开票 | 生成账单、校验税率并提交开票 |
| 收款与核销 | 登记回款并匹配客户、合同和发票 |
| 退款与红冲 | 处理退款申请、红字发票和余额调整 |
        | 运营总览 | 状态结构、模块负荷、金额指标、风险关注和最近业务 |
        | 领域计算 | 账单税额与应收计算，提供可解释计算结果和处理建议 |
| 操作审计 | 创建、修改、删除、流程动作和设置变更均保留操作人及时间 |
        | 系统设置 | 核心业务参数持久化，管理员权限隔离 |

        管理端支持业务记录查询、新增、修改、删除、状态流转、越级操作拦截和审计追踪；响应式界面可在电脑和移动浏览器使用。演示数据全部为虚构数据。

        ## 技术架构

        - 后端：Java 21、Spring Boot 4、Spring Security、Spring Data JPA、MySQL 8
        - 前端：Vue 3、Vite，管理端与业务工作台响应式布局
        - 测试：H2 隔离数据库、MockMvc 接口与权限集成测试
        - 部署：Docker Compose、Nginx 反向代理、健康检查和环境变量
        - Java 工程包：`cn.zhuatech.billing`

        ## 快速启动

        ```bash
        cp .env.example .env
        docker compose up --build
        ```

        浏览器打开 `http://localhost:8107`。演示账号：`admin / admin123`、`operator / operator123`。默认密码只能用于本地演示，上线前必须修改。

        本地开发：

        ```bash
        cd backend && mvn test
        cd ../frontend && npm install && npm run build
        ```

        更多资料参见 [API 文档](docs/API.md)、[架构说明](docs/ARCHITECTURE.md)、[安全政策](SECURITY.md)和[贡献指南](CONTRIBUTING.md)。

        ## 使用范围

        本工程仅允许个人非商业性的学习、研究和技术交流，**不得商用**。商用、二次销售、SaaS 服务、企业部署及深度定制须取得上海如静知华信息科技有限公司书面授权。

        商业授权、企业信息化、AI 转型、软件外包、项目实施和深度定制请访问[知华科技官网](https://www.zhuatech.cn/)，或扫描微信二维码咨询。

        <p align="center"><img src="docs/images/zhuatech-wechat-consulting.png" alt="知华科技微信咨询二维码一" width="230"><img src="docs/images/zhuatech-wechat-consulting-2.png" alt="知华科技微信咨询二维码二" width="230"></p>

        SEO 关键词：计费系统、应收管理、账单管理、开票管理、收款核销、知华科技、上海软件开发、企业信息化、软件项目外包。
