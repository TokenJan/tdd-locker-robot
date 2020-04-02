## Tasking
### 存包
1. given: 储物箱未满 when: 存包 then: 成功存包
2. given: 储物箱已满 when: 存包 then: 存包失败

### 取包
1. given: 我已存包，提供合法凭据 when: 取包 then: 成功取出我的包
2. given: 提供已使用凭据 when: 取包 then: 取包失败，提示【无效票】
3. given: 提供不合法凭据 when: 取包 then: 取包失败，提示【无效票】
