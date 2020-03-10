package com.test.member.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.member.coupon.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 优惠券表 Mapper 接口
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-06
 */
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {

}
