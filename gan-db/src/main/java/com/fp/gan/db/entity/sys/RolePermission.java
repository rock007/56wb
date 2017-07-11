package com.fp.gan.db.entity.sys;

import javax.persistence.*;

/**
 * Created by sam on 2017/7/7.
 */
@Entity
@Table(name = "sys_role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long role_permission_id;

    private Long role_id;

    private Long permission_id;


    public Long getRole_permission_id() {
        return role_permission_id;
    }

    public void setRole_permission_id(Long role_permission_id) {
        this.role_permission_id = role_permission_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }
}
