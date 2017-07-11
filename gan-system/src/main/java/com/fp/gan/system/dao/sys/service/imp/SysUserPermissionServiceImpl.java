package com.fp.gan.system.dao.sys.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.fp.gan.system.comm.annotation.BaseService;
import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysUserPermissionMapper;
import com.fp.gan.system.dao.sys.model.SysUserPermission;
import com.fp.gan.system.dao.sys.model.SysUserPermissionExample;
import com.fp.gan.system.dao.sys.service.SysUserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* SysUserPermissionService实现
*/
@Service
@Transactional
@BaseService
public class SysUserPermissionServiceImpl extends BaseServiceImpl<SysUserPermissionMapper, SysUserPermission, SysUserPermissionExample> implements SysUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(SysUserPermissionServiceImpl.class);

    @Autowired
    SysUserPermissionMapper sysUserPermissionMapper;

    @Override
    public int permission(JSONArray datas, int id) {
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (json.getBoolean("checked")) {
                // 新增权限
                SysUserPermission sysUserPermission = new SysUserPermission();
                sysUserPermission.setUserId(id);
                sysUserPermission.setPermissionId(json.getIntValue("id"));
                sysUserPermission.setType(json.getByte("type"));
                sysUserPermissionMapper.insertSelective(sysUserPermission);
            } else {
                // 删除权限
                SysUserPermissionExample sysUserPermissionExample = new SysUserPermissionExample();
                sysUserPermissionExample.createCriteria()
                        .andPermissionIdEqualTo(json.getIntValue("id"))
                        .andTypeEqualTo(json.getByte("type"));
                sysUserPermissionMapper.deleteByExample(sysUserPermissionExample);
            }
        }
        return datas.size();
    }

}