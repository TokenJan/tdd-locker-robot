# TDD「Locker Robot」
## Tasking
### 存包
1. given: 储物箱未满 when: 存包 then: 出票并开门
2. given: 储物箱已满 when: 存包 then: 返回【储物箱已满】不开门

### 取包
1. given: 提供合法凭据when: 取包 then: 打开对应的柜子并作废该凭据
2. given: 提供已使用凭据 when: 取包 then: 返回【无效票】不开门
3. given: 提供不合法凭据 when: 取包 then: 返回【无效票】不开门