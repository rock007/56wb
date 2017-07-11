package com.fp.gan.db.entity.sys;

import javax.persistence.*;

/**
 * Created by sam on 2017/7/7.
 */
@Entity
@Table(name = "sys_organization")
public class Organization {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="organization_id")
    private Long organizationId;

    private Long pid;

    private String name;

    private String description;

    private Long ctime;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}
