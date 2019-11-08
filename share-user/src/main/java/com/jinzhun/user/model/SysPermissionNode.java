package com.jinzhun.user.model;

import java.util.List;

import com.jinzhun.common.model.SysPermission;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限节点
 */
public class SysPermissionNode extends SysPermission {

    @Getter
    @Setter
    private List<SysPermissionNode> children;
}
