#### 主从分离，读写分离
配置 application-master-slave.yml:
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
    masterslave:
      load-balance-algorithm-type: round_robin
      name: ds_ms
      master-data-source-name: ds-master
      slave-data-source-names: ds-slave-0,ds-slave-1
    props:
      sql:
        show: true # 显示SQL

```

基本例子，其中还有强制走主库的处理：
```java

/**
     * 带事务观察添加再查询，数据源切换情况
     * 结果： 添加主库 查询主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveToSelect() {
        Member m = new Member();
        m.setUsername("saveToSelect");
        this.save(m);
        this.list();
    }

    /**
     * 不带事务观察添加再查询，数据源切换情况
     * 结果：添加主库 查询从库
     */
    public void saveToSelectNotransactional() {
        Member m = new Member();
        m.setUsername("saveToSelect");
        this.save(m);
        this.list();
    }

    /**
     * 带事务先查询后添加再查询，观察数据源切换情况
     * 结果：先查从库  添加主库 再查询还是主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void selectToSaveToSelect() {
        this.list();

        Member m = new Member();
        m.setUsername("selectToSaveToSelect");
        this.save(m);
        this.list();
    }

    /**
     * 带事务先查询后添加再查询，观察数据源切换情况
     * 结果：先查从库  添加主库 再查询还是主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void selectToSaveToSelectException() {
        this.list();
        Member m = new Member();
        m.setUsername("异常");
        this.save(m);
        this.list();
        throw new RuntimeException("异常");
    }

    /**
     * 强制走主库：查询操作强制走主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void hintSelectToSaveToSelect() {
        try(HintManager manager = HintManager.getInstance()) {
            manager.setMasterRouteOnly();
            this.list();
        }

        Member m = new Member();
        m.setUsername("selectToSaveToSelect");
        this.save(m);
        this.list();
    }
```