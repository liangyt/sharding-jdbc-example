package com.test.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.info.entity.Level;
import com.test.info.mapper.LevelMapper;
import com.test.info.service.ILevelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 级别表 服务实现类
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-16
 */
@Service
public class LevelServiceImpl extends ServiceImpl<LevelMapper, Level> implements ILevelService {

}
