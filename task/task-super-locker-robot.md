## Tasking
### 存包
1. given: super robot 管理 2 个储物箱, 第一个储物箱共 1 个空格且空置率为 100%, 第二个储物箱共 4 个空格且空置率为 50% when: 交给 super robot 存包 then: 成功把包存入第一个储物箱
2. given: super robot 管理 2 个储物箱, 第一个储物箱共 4 个空格且空置率为 50%, 第二个储物箱共 1 个空格且空置率为 100% when: 交给 super robot 存包 then: 成功把包存入第二个储物箱
3. given: super robot 管理 2 个储物箱, 第一个储物箱共 4 个空格且空置率为 50%, 第二个储物箱共 2 个空格且空置率为 50% when: 交给 super robot 存包 then: 成功把包存入第一个储物箱
4. given: super robot 管理 2 个储物箱, 所有储物箱已满 when: 交给 super robot 存包 then: 存包失败，提示【储物箱已满】

### 取包
1. given: 把包存入第一个储物箱, 提供合法凭据 when: 把票交给 super robot 取包 then: 成功取出我的包
2. given: 把包存入第二个储物箱, 提供合法凭据 when: 把票交给 super robot 取包 then: 成功取出我的包
3. given: 提供不合法凭据 when: 把票交给 super robot 取包 then: 取包失败，提示【无效票】