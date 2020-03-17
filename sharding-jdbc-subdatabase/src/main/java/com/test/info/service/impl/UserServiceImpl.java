package com.test.info.service.impl;

import com.test.info.entity.User;
import com.test.info.mapper.UserMapper;
import com.test.info.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getUserWithLevelById(Long id) {
        return this.baseMapper.getUserWithLevelById(id);
    }
}
