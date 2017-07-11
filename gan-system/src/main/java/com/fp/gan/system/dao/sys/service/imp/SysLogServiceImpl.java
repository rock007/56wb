package com.fp.gan.system.dao.sys.service.imp;

import com.fp.gan.system.comm.annotation.BaseService;
import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysLogMapper;
import com.fp.gan.system.dao.sys.model.SysLog;
import com.fp.gan.system.dao.sys.model.SysLogExample;
import com.fp.gan.system.dao.sys.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* SysLogService实现
* sam is here  2017/3/20.
*/
@Service
@Transactional
@BaseService
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog, SysLogExample> implements SysLogService {

    private static Logger _log = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Autowired
    SysLogMapper sysLogMapper;

}