package com.test.member.info.service.impl;

import com.test.member.info.entity.Member;
import com.test.member.info.mapper.MemberMapper;
import com.test.member.info.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shardingsphere.api.hint.HintManager;
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

    /**
     * 不带事务观察添加再查询，数据源切换情况
     * 结果：添加主库 查询从库
     */
    public void saveToSelectNotransactional() {
        Member m = new Member();
        m.setUsername("saveToSelect");
        this.save(m);
        this.list();
    }

    /**
     * 带事务先查询后添加再查询，观察数据源切换情况
     * 结果：先查从库  添加主库 再查询还是主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void selectToSaveToSelect() {
        this.list();

        Member m = new Member();
        m.setUsername("selectToSaveToSelect");
        this.save(m);
        this.list();
    }

    /**
     * 带事务先查询后添加再查询，观察数据源切换情况
     * 结果：先查从库  添加主库 再查询还是主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void selectToSaveToSelectException() {
        this.list();
        Member m = new Member();
        m.setUsername("异常");
        this.save(m);
        this.list();
        throw new RuntimeException("异常");
    }

    /**
     * 强制走主库：查询操作强制走主库
     */
    @Transactional(rollbackFor = Exception.class)
    public void hintSelectToSaveToSelect() {
        try(HintManager manager = HintManager.getInstance()) {
            manager.setMasterRouteOnly();
            this.list();
        }

        Member m = new Member();
        m.setUsername("selectToSaveToSelect");
        this.save(m);
        this.list();
    }
}
