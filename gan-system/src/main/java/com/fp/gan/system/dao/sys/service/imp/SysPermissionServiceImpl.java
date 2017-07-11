package com.fp.gan.system.dao.sys.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.fp.gan.system.comm.annotation.BaseService;
import com.fp.gan.system.comm.base.BaseServiceImpl;
import com.fp.gan.system.dao.sys.mapper.SysPermissionMapper;
import com.fp.gan.system.dao.sys.mapper.SysSystemMapper;
import com.fp.gan.system.dao.sys.mapper.SysUserPermissionMapper;
import com.fp.gan.system.dao.sys.model.*;
import com.fp.gan.system.dao.sys.service.SysApiService;
import com.fp.gan.system.dao.sys.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* SysPermissionService实现
* sam is here  2017/3/20.
*/
@Service
@Transactional
@BaseService
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission, SysPermissionExample> implements SysPermissionService {

    private static Logger _log = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    SysSystemMapper sysSystemMapper;

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Autowired
    SysApiService sysApiService;

    @Autowired
    SysUserPermissionMapper sysUserPermissionMapper;

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        // 角色已有权限
        List<SysRolePermission> rolePermissions = sysApiService.selectSysRolePermisstionBySysRoleId(roleId);

        JSONArray systems = new JSONArray();
        // 系统
        SysSystemExample sysSystemExample = new SysSystemExample();
        sysSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        sysSystemExample.setOrderByClause("orders asc");
        List<SysSystem> SysSystems = sysSystemMapper.selectByExample(sysSystemExample);
        for (SysSystem SysSystem : SysSystems) {
            JSONObject node = new JSONObject();
            node.put("id", SysSystem.getSystemId());
            node.put("name", SysSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                SysPermissionExample SysPermissionExample = new SysPermissionExample();
                SysPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                SysPermissionExample.setOrderByClause("orders asc");
                List<SysPermission> SysPermissions = sysPermissionMapper.selectByExample(SysPermissionExample);
                if (SysPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (SysPermission SysPermission: SysPermissions) {
                    if (SysPermission.getPid().intValue() != 0 || SysPermission.getType() != 1) continue;
                    JSONObject node = new JSONObject();
                    node.put("id", SysPermission.getPermissionId());
                    node.put("name", SysPermission.getName());
                    node.put("open", true);
                    for (SysRolePermission rolePermission : rolePermissions) {
                        if (rolePermission.getPermissionId().intValue() == SysPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (SysPermission SysPermission2: SysPermissions) {
                            if (SysPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || SysPermission2.getType() != 2) continue;
                            JSONObject node2 = new JSONObject();
                            node2.put("id", SysPermission2.getPermissionId());
                            node2.put("name", SysPermission2.getName());
                            node2.put("open", true);
                            for (SysRolePermission rolePermission : rolePermissions) {
                                if (rolePermission.getPermissionId().intValue() == SysPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (SysPermission SysPermission3: SysPermissions) {
                                    if (SysPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || SysPermission3.getType() != 3) continue;
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", SysPermission3.getPermissionId());
                                    node3.put("name", SysPermission3.getName());
                                    node3.put("open", true);
                                    for (SysRolePermission rolePermission : rolePermissions) {
                                        if (rolePermission.getPermissionId().intValue() == SysPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    @Override
    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
        // 角色权限
        SysUserPermissionExample SysUserPermissionExample = new SysUserPermissionExample();
        SysUserPermissionExample.createCriteria()
                .andUserIdEqualTo(usereId)
                .andTypeEqualTo(type);
        List<SysUserPermission> SysUserPermissions = sysUserPermissionMapper.selectByExample(SysUserPermissionExample);

        JSONArray systems = new JSONArray();
        // 系统
        SysSystemExample SysSystemExample = new SysSystemExample();
        SysSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        SysSystemExample.setOrderByClause("orders asc");
        List<SysSystem> SysSystems = sysSystemMapper.selectByExample(SysSystemExample);
        for (SysSystem SysSystem : SysSystems) {
            JSONObject node = new JSONObject();
            node.put("id", SysSystem.getSystemId());
            node.put("name", SysSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                SysPermissionExample SysPermissionExample = new SysPermissionExample();
                SysPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                SysPermissionExample.setOrderByClause("orders asc");
                List<SysPermission> SysPermissions = sysPermissionMapper.selectByExample(SysPermissionExample);
                if (SysPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (SysPermission SysPermission: SysPermissions) {
                    if (SysPermission.getPid().intValue() != 0 || SysPermission.getType() != 1) continue;
                    JSONObject node = new JSONObject();
                    node.put("id", SysPermission.getPermissionId());
                    node.put("name", SysPermission.getName());
                    node.put("open", true);
                    for (SysUserPermission SysUserPermission : SysUserPermissions) {
                        if (SysUserPermission.getPermissionId().intValue() == SysPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (SysPermission SysPermission2: SysPermissions) {
                            if (SysPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || SysPermission2.getType() != 2) continue;
                            JSONObject node2 = new JSONObject();
                            node2.put("id", SysPermission2.getPermissionId());
                            node2.put("name", SysPermission2.getName());
                            node2.put("open", true);
                            for (SysUserPermission SysUserPermission : SysUserPermissions) {
                                if (SysUserPermission.getPermissionId().intValue() == SysPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (SysPermission SysPermission3: SysPermissions) {
                                    if (SysPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || SysPermission3.getType() != 3) continue;
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", SysPermission3.getPermissionId());
                                    node3.put("name", SysPermission3.getName());
                                    node3.put("open", true);
                                    for (SysUserPermission SysUserPermission : SysUserPermissions) {
                                        if (SysUserPermission.getPermissionId().intValue() == SysPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

}