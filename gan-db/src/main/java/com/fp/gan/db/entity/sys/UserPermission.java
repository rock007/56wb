package com.fp.gan.db.entity.sys;

import javax.persistence.*;

/**
 * Created by sam on 2017/7/7.
 */
@Entity
@Table(name = "sys_user_permission")
public class UserPermission {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long user_permission_id;

    private Long user_id;

    private Long permission_id;

    private Integer type;

    public Long getUser_permission_id() {
        return user_permission_id;
    }

    public void setUser_permission_id(Long user_permission_id) {
        this.user_permission_id = user_permission_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
