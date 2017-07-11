package com.fp.gan.db.entity.sys;

import javax.persistence.*;

/**
 * Created by sam on 2017/7/7.
 */

@Entity
@Table(name = "sys_user_organization")
public class UserOrganization {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long user_organization_id;

    private Long user_id;

    private Long organization_id;


    public Long getUser_organization_id() {
        return user_organization_id;
    }

    public void setUser_organization_id(Long user_organization_id) {
        this.user_organization_id = user_organization_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }
}
