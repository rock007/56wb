package com.fp.gan.system.dao.sys.service;

import com.fp.gan.system.dao.sys.model.*;

import java.util.List;

/**
 * Sys系统接口
 * sam is here  2017/2/11.
 */
public interface SysApiService {

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param SysUserId
     * @return
     */
    List<SysPermission> selectSysPermissionBySysUserId(Integer SysUserId);

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param SysUserId
     * @return
     */
    List<SysPermission> selectSysPermissionBySysUserIdByCache(Integer SysUserId);

    /**
     * 根据用户id获取所属的角色
     * @param SysUserId
     * @return
     */
    List<SysRole> selectSysRoleBySysUserId(Integer SysUserId);

    /**
     * 根据用户id获取所属的角色
     * @param SysUserId
     * @return
     */
    List<SysRole> selectSysRoleBySysUserIdByCache(Integer SysUserId);

    /**
     * 根据角色id获取所拥有的权限
     * @param SysRoleId
     * @return
     */
    List<SysRolePermission> selectSysRolePermisstionBySysRoleId(Integer SysRoleId);

    /**
     * 根据用户id获取所拥有的权限
     * @param SysUserId
     * @return
     */
    List<SysUserPermission> selectSysUserPermissionBySysUserId(Integer SysUserId);

    /**
     * 根据条件获取系统数据
     * @param SysSystemExample
     * @return
     */
    List<SysSystem> selectSysSystemByExample(SysSystemExample SysSystemExample);

    /**
     * 根据条件获取组织数据
     * @param SysOrganizationExample
     * @return
     */
    List<SysOrganization> selectSysOrganizationByExample(SysOrganizationExample SysOrganizationExample);

    /**
     * 根据username获取SysUser
     * @param username
     * @return
     */
    SysUser selectSysUserByUsername(String username);

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    int insertSysLogSelective(SysLog record);

}
