package com.fp.gan.db.entity.sys;

import javax.persistence.*;

/**
 * Created by sam on 2017/7/7.
 */
@Entity
@Table(name = "sys_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long user_role_id;

    private Long user_id;

    private Long role_id;

    public Long getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(Long user_role_id) {
        this.user_role_id = user_role_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
