#### 分库，分表:

准备两个数据源ds0, ds1, 以及对应的表结构
```java
ds0
    - order_item
    - user_0
    - level
ds1
    - user_1
    - level
```

配置 application-subdatabase.yml:
```xml
spring:
  shardingsphere:
    datasource:
      names: ds0,ds1
      ds0:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/db0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 123456
      ds1:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 123456
    sharding:
      tables:
        order_item: # 订单表，只在一个数据源里面存在;注意关键字 order,不能起表名 order
          actual-data-nodes: ds0.order_item # 逻辑表 order 对应的真实表
        user: # 用户表，在两个数据源中各存在一个
          actual-data-nodes: ds0.user_0,ds1.user_1 # 两个表，在两个数据源中各存在一个
          database-strategy:
            inline:
              sharding-column: id
              algorithm-expression: ds$->{id%2}
          table-strategy:
            inline:
              sharding-column: id
              algorithm-expression: user_$->{id%2}
          key-generator:
            column: id
            type: SNOWFLAKE
      binding-tables: order_item,user # 绑定的逻辑表，可以使用逗号分割多表
      broadcast-tables: level # 广播表，在两个数据中存在的一样的表，数据也一样
      default-key-generator: # 默认主键生成策略
        column: id
        type: SNOWFLAKE
    props:
      sql:
        show: true # 显示SQL

```

基本例子： 
```java
   /**
    * 添加级别
    * 结果： 两个数据源的级别表都添加了相同的数据记录
    */
   @GetMapping("/addLevel")
   public void addLevel() {
       Level level = new Level();
       level.setCode("0001");
       level.setName("默认级别");
       levelService.save(level);
   }

   /**
    * 获取级别
    * 结果：从其中一个数据源获取结果
    * @param code
    * @return
    */
   @GetMapping("/getLevelByCode/{code}")
   public Object getLevelByCode(@PathVariable String code) {
       QueryWrapper query = new QueryWrapper();
       query.eq("code", code);
       return levelService.getOne(query);
   }

   /**
    * 添加用户
    * 结果：在两个数据源中轮流插入数据表中
    */
   @GetMapping("/addUser")
   public void addUser() {
       User user = new User();
       user.setName("用户名称");
       user.setTime(new Date());

       QueryWrapper query = new QueryWrapper();
       query.eq("code", "0001");
       Level level = levelService.getOne(query);
       user.setLevelId(level.getId());

       userService.save(user);
   }

   /**
    * 获取用户详情
    * @return
    */
   @GetMapping("/getUserById")
   public Object getUserById() {
       return userService.getUserWithLevelById(1239771025214767106L);
   }

   /**
    * 添加表单
    * 结果：添加进所在数据源的所在表，不需要利用策略
    */
   @GetMapping("/addOrder")
   public void addOrder() {
       Order order = new Order();
       order.setName("新表单");
       order.setTime(new Date());
       orderService.save(order);
   }

   /**
    * 获取表单列表
    * @return
    */
   @GetMapping("orderList")
   public Object orderList() {
       return orderService.list();
   }

```