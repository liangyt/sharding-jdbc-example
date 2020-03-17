package com.test.info.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.info.entity.Level;
import com.test.info.entity.Order;
import com.test.info.entity.User;
import com.test.info.service.impl.LevelServiceImpl;
import com.test.info.service.impl.OrderServiceImpl;
import com.test.info.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-16
 */
@RestController
@RequestMapping("/info")
public class TestController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LevelServiceImpl levelService;
    @Autowired
    private OrderServiceImpl orderService;

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
}

