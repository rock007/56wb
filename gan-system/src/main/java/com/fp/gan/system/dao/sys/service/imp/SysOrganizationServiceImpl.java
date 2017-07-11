package com.fp.gan.system.dao.sys.service.imp;

import com.fp.gan.system.comm.annotation.BaseService;
import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysOrganizationMapper;
import com.fp.gan.system.dao.sys.model.SysOrganization;
import com.fp.gan.system.dao.sys.model.SysOrganizationExample;
import com.fp.gan.system.dao.sys.service.SysOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* SysOrganizationService实现
* sam is here  2017/3/20.
*/
@Service
@Transactional
@BaseService
public class SysOrganizationServiceImpl extends BaseServiceImpl<SysOrganizationMapper, SysOrganization, SysOrganizationExample> implements SysOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(SysOrganizationServiceImpl.class);

    @Autowired
    SysOrganizationMapper sysOrganizationMapper;

}