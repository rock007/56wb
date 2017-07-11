package com.fp.gan.system.dao.sys.service.imp;


import com.fp.gan.system.dao.sys.mapper.*;
import com.fp.gan.system.dao.sys.model.*;
import com.fp.gan.system.dao.sys.service.SysApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysApiService实现
 * sam is here  2016/01/19.
 */
@Service
@Transactional
public class SysApiServiceImpl implements SysApiService {

    private static Logger _log = LoggerFactory.getLogger(SysApiServiceImpl.class);

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    SysUserPermissionMapper sysUserPermissionMapper;

    @Autowired
    SysSystemMapper sysSystemMapper;

    @Autowired
    SysOrganizationMapper sysOrganizationMapper;

    @Autowired
    SysLogMapper sysLogMapper;

    /**
     * 根据用户id获取所拥有的权限
     * @param SysUserId
     * @return
     */
    @Override
    public List<SysPermission> selectSysPermissionBySysUserId(Integer SysUserId) {
        // 用户不存在或锁定状态
        SysUser SysUser = sysUserMapper.selectByPrimaryKey(SysUserId);
        if (null == SysUser || 1 == SysUser.getLocked()) {
            _log.info("selectSysPermissionBySysUserId : SysUserId={}", SysUserId);
            return null;
        }
        List<SysPermission> SysPermissions =null; //SysApiMapper.selectSysPermissionBySysUserId(SysUserId);
        return SysPermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param SysUserId
     * @return
     */
    @Override
    @Cacheable(value = "zheng-Sys-rpc-service-ehcache", key = "'selectSysPermissionBySysUserId_' + #SysUserId")
    public List<SysPermission> selectSysPermissionBySysUserIdByCache(Integer SysUserId) {
        return selectSysPermissionBySysUserId(SysUserId);
    }

    /**
     * 根据用户id获取所属的角色
     * @param SysUserId
     * @return
     */
    @Override
    public List<SysRole> selectSysRoleBySysUserId(Integer SysUserId) {
        // 用户不存在或锁定状态
        SysUser SysUser = sysUserMapper.selectByPrimaryKey(SysUserId);
        if (null == SysUser || 1 == SysUser.getLocked()) {
            _log.info("selectSysRoleBySysUserId : SysUserId={}", SysUserId);
            return null;
        }
        List<SysRole> SysRoles =null; //sysApiMapper.selectSysRoleBySysUserId(SysUserId);
        return SysRoles;
    }

    /**
     * 根据用户id获取所属的角色
     * @param SysUserId
     * @return
     */
    @Override
    @Cacheable(value = "zheng-Sys-rpc-service-ehcache", key = "'selectSysRoleBySysUserId_' + #SysUserId")
    public List<SysRole> selectSysRoleBySysUserIdByCache(Integer SysUserId) {
        return selectSysRoleBySysUserId(SysUserId);
    }

    /**
     * 根据角色id获取所拥有的权限
     * @param SysRoleId
     * @return
     */
    @Override
    public List<SysRolePermission> selectSysRolePermisstionBySysRoleId(Integer SysRoleId) {
        SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
        sysRolePermissionExample.createCriteria()
                .andRoleIdEqualTo(SysRoleId);
        List<SysRolePermission> SysRolePermissions = sysRolePermissionMapper.selectByExample(sysRolePermissionExample);
        return SysRolePermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param SysUserId
     * @return
     */
    @Override
    public List<SysUserPermission> selectSysUserPermissionBySysUserId(Integer SysUserId) {
        SysUserPermissionExample sysUserPermissionExample = new SysUserPermissionExample();
        sysUserPermissionExample.createCriteria()
                .andUserIdEqualTo(SysUserId);
        List<SysUserPermission> SysUserPermissions = sysUserPermissionMapper.selectByExample(sysUserPermissionExample);
        return SysUserPermissions;
    }

    /**
     * 根据条件获取系统数据
     * @param SysSystemExample
     * @return
     */
    @Override
    public List<SysSystem> selectSysSystemByExample(SysSystemExample SysSystemExample) {
        return sysSystemMapper.selectByExample(SysSystemExample);
    }

    /**
     * 根据条件获取组织数据
     * @param SysOrganizationExample
     * @return
     */
    @Override
    public List<SysOrganization> selectSysOrganizationByExample(SysOrganizationExample SysOrganizationExample) {
        return sysOrganizationMapper.selectByExample(SysOrganizationExample);
    }

    /**
     * 根据username获取SysUser
     * @param username
     * @return
     */
    @Override
    public SysUser selectSysUserByUsername(String username) {
        SysUserExample SysUserExample = new SysUserExample();
        SysUserExample.createCriteria()
                .andUsernameEqualTo(username);
        List<SysUser> SysUsers = sysUserMapper.selectByExample(SysUserExample);
        if (null != SysUsers && SysUsers.size() > 0) {
            return SysUsers.get(0);
        }
        return null;
    }

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    @Override
    public int insertSysLogSelective(SysLog record) {
        return sysLogMapper.insertSelective(record);
    }

}