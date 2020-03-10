package com.test.member.info.mapper;

import com.test.member.info.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-06
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    Member selectForUpdate(Long id);
}
