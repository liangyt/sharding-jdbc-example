package com.test.info.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * <p>
 * 会员表
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-16
 */
@Data
@TableName("user")
public class User {
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 级别
     */
    private Long levelId;

    @TableField(exist = false)
    private Level level;

    /**
     * 时间
     */
    private Date time;
}
