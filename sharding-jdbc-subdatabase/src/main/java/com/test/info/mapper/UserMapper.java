package com.test.info.mapper;

import com.test.info.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getUserWithLevelById(Long id);
}
