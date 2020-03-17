package com.test.info.service;

import com.test.info.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-06
 */
public interface IUserService extends IService<User> {

    User getUserWithLevelById(Long id);
}
