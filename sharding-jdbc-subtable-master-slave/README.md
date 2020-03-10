#### 主从分离，读写分离，分表不分库处理 (一主二从):

把 coupon 逻辑表处理两个真实表 coupon_0 coupon_1。

如果不知道怎么配置主从数据库的话，那就直接使用同一个数据库就可以了，或者参考以前写的一遍
写的 [spring-boot 读写分离处理](https://mp.weixin.qq.com/s/UNOf4HXcPXxyzuxBN-hxIA) ，
里面有教怎么配置主从数据库。  
配置 application-subtable-master-slave.yml:
```xml
spring:
  shardingsphere:
    datasource:
      names: ds-master,ds-slave-0,ds-slave-1
      ds-master:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3307/sharding-jdbc-ms?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 123456
      ds-slave-0:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3308/sharding-jdbc-ms?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 123456
      ds-slave-1:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3308/sharding-jdbc-ms?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 123456
    sharding:
      tables:
        coupon:
          actual-data-nodes: ds0.coupon_${0..1}
          table-strategy:
            inline:
              sharding-column: id
              algorithm-expression: coupon_$->{id%2}
          key-generator:
            column: id
            type: SNOWFLAKE
      binding-tables: coupon
      master-slave-rules:
        ds0:
          master-data-source-name: ds-master
          slave-data-source-names: ds-slave-0, ds-slave-1
    props:
      sql:
        show: true # 显示SQL
```

基本例子： com.test.member.coupon.controller.CouponController
```java
    /**
     * 插入，根据分区键的策略表达式自动插入对应的真实表
     */
    @GetMapping("/add")
    public void add() {
        Coupon coupon = new Coupon();
        coupon.setCode("test1");
        coupon.setName("test1");

        couponService.save(coupon);
    }

    /**
     * 观察怎么读取多表数据
     * @return
     */
    @GetMapping("list")
    public Object list() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

        return couponService.list(queryWrapper);
    }

    /**
     * 单表查询，会根据分区键 自动识别查询哪个表
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Object getById(@PathVariable Long id) {
        return couponService.getById(id);
    }

```