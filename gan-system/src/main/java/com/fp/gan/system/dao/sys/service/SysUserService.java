package com.fp.gan.system.dao.sys.service;


import com.fp.gan.system.comm.base.BaseService;
import com.fp.gan.system.dao.sys.model.SysRole;
import com.fp.gan.system.dao.sys.model.SysUser;
import com.fp.gan.system.dao.sys.model.SysUserExample;

import java.util.List;

/**
* SysUserService接口
*/
public interface SysUserService extends BaseService<SysUser, SysUserExample> {

    SysUser createUser(SysUser SysUser);

    SysUser  selectUserByUsername(String name);

}