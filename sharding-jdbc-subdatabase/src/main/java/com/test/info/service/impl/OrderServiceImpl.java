package com.test.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.info.entity.Order;
import com.test.info.mapper.OrderMapper;
import com.test.info.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 表单 服务实现类
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
