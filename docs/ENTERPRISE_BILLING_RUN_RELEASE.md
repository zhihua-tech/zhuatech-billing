# 企业级账单批次开票治理

批次开票前检查计量、价格版本、税务、重复风险、客户主数据、期间状态、贷项、收入复核和负责人批准。

`POST /api/enterprise/billing/run-release` 返回 `ISSUE / REVIEW / HOLD`，生产使用应关联计费快照、发票平台回执和审计证据。
