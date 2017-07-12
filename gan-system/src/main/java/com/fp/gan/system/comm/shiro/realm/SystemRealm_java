package com.fp.gan.system.comm.shiro.realm;

import com.fp.gan.system.dao.sys.model.SysPermission;
import com.fp.gan.system.dao.sys.model.SysRole;
import com.fp.gan.system.dao.sys.model.SysUser;
import com.fp.gan.system.dao.sys.service.SysApiService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SystemRealm extends AuthorizingRealm {

    private static Logger _log = LoggerFactory.getLogger(SystemRealm.class);

    @Autowired
    private SysApiService sysApiService;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SysUser upmsUser = sysApiService.selectSysUserByUsername(username);

        // 当前用户所有角色
        List<SysRole> upmsRoles = sysApiService.selectSysRoleBySysUserId(upmsUser.getUserId());
        Set<String> roles = new HashSet<>();
        for (SysRole upmsRole : upmsRoles) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(upmsRole.getName())) {
                roles.add(upmsRole.getName());
            }
        }

        // 当前用户所有权限
        List<SysPermission> upmsPermissions = sysApiService.selectSysPermissionBySysUserId(upmsUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (SysPermission upmsPermission : upmsPermissions) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // client无密认证
        String upmsType = "";//PropertiesFileUtil.getInstance("zheng-upms-client").get("zheng.upms.type");
        if ("client".equals(upmsType)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }

        // 查询用户信息
        SysUser upmsUser = sysApiService.selectSysUserByUsername(username);

        if (null == upmsUser) {
            throw new UnknownAccountException();
        }
        //MD5Util.MD5(password + upmsUser.getSalt())
        if (!upmsUser.getPassword().equals(password )) {
            throw new IncorrectCredentialsException();
        }
        if (upmsUser.getLocked() == 1) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
