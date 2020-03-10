package com.test.member.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.member.coupon.entity.Coupon;
import com.test.member.coupon.mapper.CouponMapper;
import com.test.member.coupon.service.ICouponService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-06
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

}
