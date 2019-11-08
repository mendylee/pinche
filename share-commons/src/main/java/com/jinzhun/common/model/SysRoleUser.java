package com.jinzhun.common.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 角色与用户关系实体
 */
@Data
@TableName("sys_role_user")
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;

    @Override
    public String toString() {
        return "SysRoleUser{" +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
