package com.fp.gan.system.dao.sys.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fp.gan.core.annotation.MyBatisService;

import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysRolePermissionMapper;
import com.fp.gan.system.dao.sys.model.SysRolePermission;
import com.fp.gan.system.dao.sys.model.SysRolePermissionExample;
import com.fp.gan.system.dao.sys.service.SysRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* SysRolePermissionService实现
* sam is here  2017/3/20.
*/
@Service
@Transactional
@MyBatisService
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission, SysRolePermissionExample> implements SysRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(SysRolePermissionServiceImpl.class);

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public int rolePermission(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                SysRolePermission SysRolePermission = new SysRolePermission();
                SysRolePermission.setRoleId(id);
                SysRolePermission.setPermissionId(json.getIntValue("id"));
                sysRolePermissionMapper.insertSelective(SysRolePermission);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            SysRolePermissionExample SysRolePermissionExample = new SysRolePermissionExample();
            SysRolePermissionExample.createCriteria()
                    .andPermissionIdIn(deleteIds)
                    .andRoleIdEqualTo(id);
            sysRolePermissionMapper.deleteByExample(SysRolePermissionExample);
        }
        return datas.size();
    }

}