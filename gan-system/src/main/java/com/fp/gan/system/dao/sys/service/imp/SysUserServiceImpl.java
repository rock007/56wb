package com.fp.gan.system.dao.sys.service.imp;

import com.fp.gan.system.comm.annotation.BaseService;
import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysUserMapper;
import com.fp.gan.system.dao.sys.model.SysUser;
import com.fp.gan.system.dao.sys.model.SysUserExample;
import com.fp.gan.system.dao.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* SysUserService实现
*/
@Service
@Transactional
@BaseService
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser, SysUserExample> implements SysUserService {

    private static Logger _log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser createUser(SysUser SysUser) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria()
                .andUsernameEqualTo(SysUser.getUsername());
        long count = sysUserMapper.countByExample(sysUserExample);
        if (count > 0) {
            return null;
        }
        sysUserMapper.insert(SysUser);
        return SysUser;
    }

    @Override
    public  SysUser selectUserByUsername(String name){

        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria()
                .andUsernameEqualTo(name);
        List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}