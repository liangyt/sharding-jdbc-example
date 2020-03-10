package com.test.member.info.service.impl;

import com.test.member.info.entity.Member;
import com.test.member.info.mapper.MemberMapper;
import com.test.member.info.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-06
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    /**
     * 带事务观察添加再查询，数据源切换情况
     * 结果： 添加主库 查询主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveToSelect() {
        Member m = new Member();
        m.setUsername("saveToSelect");
        this.save(m);
        this.list();
    }

    public Member selectForUpdate(Long id) {
        return this.baseMapper.selectForUpdate(id);
    }
}
