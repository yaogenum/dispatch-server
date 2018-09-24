### 系统定义

负责发起对当前missile,trident-user的任务执行

### 问题背景

1. 任务运行 时间长
2. 任务运行 过于集中
3. 任务运行 不及时
4. 任务由于各种原因失败

  槽点多多

### 系统解决的问题

1. missile,trident-user 每天依靠定时任务8点 扫描全部的待执行任务，均在一个时间段内
2. 任务执行是 随机到某一台机器 执行，无法分布式集群作业
3. missile,trident-user任务执行依赖的table表 是否ready不知道，需要运行时才能确认，无法保证任务执行时间的及时性



### 问题解决

设计一个新的方案 专门 做任务执行的发起。

### 方案设计 主体思路

#### 驱动
 1. (table kafka)ES 解决 及时性  保证90%
 2. (local schedule)TASK 补偿/保证稳定 保证正常10%/异常时100% 

#### 接入
 1. missile,trident-user接入 client.jar 
 2. 实现 preExecute（获取所有任务以及任务必要的信息）,execute(任务) 接口
 3. 可配置 定时任务周期 appExpression,应用名称appName
 
#### 路由
 
 定时任务:利用zk协调每台机器负责的任务范围
 
 1. 机器向ZK 注册 临时同步顺序节点 ; 
 
 >  (/zk/trident-dispatch/trident-user/tmp/dispatch,/zk/trident-dispatch/missile/tmp/dispatch)
 
 2. 等待10S(等待机器注册过程完成)
 3. 拉取当前节点/zk/trident-dispatch//tmp/dispatch下所有的机器IP，以及当前任务ID，按照取模方式 派分不同范围的任务
 
 ES：来自 金砖 的表任务消息 ，消息落到当前机器即可执行
 （消息业务定义： 发送 当前 表 存在 日任务并且执行成功的kafka消息）
 1. 注册 kafka监听
 
#### 去重
 
 ES:
 
 1. 消息到达之后，直接运行，无须做去重
 
 定时任务：
 
 1. 任务启动时 ，preExecute接口接入方提供 需要运行的任务（接入方本地可以存储一张表profile_generation_info表记录  任务执行记录），后续定时10分钟进行 更新（可配置时间）
 2. 任务开始发起时，需要 到ZK 注册  zk/trident-dispatch/trident-user/taskId/status(是否发起过),batch(批次),IP(机器 临时节点) (过期时间 48H),snapshot
 3. 任务开始前检查，zk/trident-dispatch/trident-user/taskId/status(0 尚未发起，1已发起，-1发起异常),IP(机器 临时节点) (过期时间 48H),snapshot， 发起标准： 已经发起过（且IP节点不存在）；节点不存在;snapshot比当前时间小;  且节点版本号 一致（分布式锁，由于并发量非常小以及机器数量少，使用乐观锁，不采用悲观锁的实现）
 4. 任务执行完后，接入可以选择性实现接口 afterExecute[可选]，是为了 补偿 在向ZK注册完发起了调用，应用也接受了execute,但是处理异常了，进行一个重复调用（这个时候虽然jar完成了自己的事情，但是 是需要重复调度补偿的。 ） 


### 方案设计 链路

1. 机器注册
2.A 任务取模分配 | 2.B 接受消息
3. 任务依赖表 检查
4. 任务发起去重检查
5. 任务发起
6. 任务写入发起状态以及信息
7. 任务回执结果写入ZK



### 方案设计 实现


1. 引入 ZK
2. 打包client
