package com.fp.gan.system.dao.sys.service.imp;

import com.fp.gan.core.annotation.MyBatisService;
import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysRoleMapper;
import com.fp.gan.system.dao.sys.model.SysRole;
import com.fp.gan.system.dao.sys.model.SysRoleExample;
import com.fp.gan.system.dao.sys.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* SysRoleService实现
*/
@Service
@Transactional
@MyBatisService
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole, SysRoleExample> implements SysRoleService {

    private static Logger _log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    SysRoleMapper sysRoleMapper;

}