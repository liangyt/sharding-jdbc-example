package com.test.member.coupon.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.member.coupon.entity.Coupon;
import com.test.member.coupon.service.impl.CouponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠券表 前端控制器
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/member.coupon/coupon")
public class CouponController {

    @Autowired
    private CouponServiceImpl couponService;

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
}

