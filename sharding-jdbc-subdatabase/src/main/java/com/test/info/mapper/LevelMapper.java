package com.test.info.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.info.entity.Level;
import com.test.info.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 级别 Mapper 接口
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-16
 */
@Mapper
public interface LevelMapper extends BaseMapper<Level> {

    Level getLevelById(Long id);
}
