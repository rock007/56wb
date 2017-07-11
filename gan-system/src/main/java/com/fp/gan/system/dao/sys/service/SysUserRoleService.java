package com.fp.gan.system.dao.sys.service;


import com.fp.gan.system.comm.base.BaseService;
import com.fp.gan.system.dao.sys.model.SysUserRole;
import com.fp.gan.system.dao.sys.model.SysUserRoleExample;

import java.util.List;

/**
* SysUserRoleService接口
*/
public interface SysUserRoleService extends BaseService<SysUserRole, SysUserRoleExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

    public List<SysUserRole> selectUserRoleByUserId(Integer userId);
}