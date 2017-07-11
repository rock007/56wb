package com.fp.gan.system.dao.sys.service.imp;

import com.fp.gan.system.comm.annotation.BaseService;
import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysSystemMapper;
import com.fp.gan.system.dao.sys.model.SysSystem;
import com.fp.gan.system.dao.sys.model.SysSystemExample;
import com.fp.gan.system.dao.sys.service.SysSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* SysSystemService实现
*/
@Service
@Transactional
@BaseService
public class SysSystemServiceImpl extends BaseServiceImpl<SysSystemMapper, SysSystem, SysSystemExample> implements SysSystemService {

    private static Logger _log = LoggerFactory.getLogger(SysSystemServiceImpl.class);

    @Autowired
    SysSystemMapper sysSystemMapper;

}