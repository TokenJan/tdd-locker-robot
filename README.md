# TDD「Locker Robot」
## Tasking
### 初始化 Locker 系统
1. given: Locker 数量为 -1 when: 初始化 Locker 系统 then: 返回【初始化 Locker 系统】异常

### 存包
1. given: 储物箱未满 when: 存包 then: 出票, 开门, 剩余储物箱数量 - 1
2. given: 储物箱已满 when: 存包 then: 不出票, 不开门, 剩余储物箱数量 = 0

### 取包
1. given: 提供合法凭据when: 取包 then: 打开对应的柜子，剩余储物箱数量 + 1
2. given: 提供已使用凭据 when: 取包 then: 返回【无效票】异常，不开门
3. given: 提供不合法凭据 when: 取包 then: 返回【无效票】异常，不开门