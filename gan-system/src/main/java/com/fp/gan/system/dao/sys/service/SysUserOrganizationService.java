package com.fp.gan.system.dao.sys.service;


import com.fp.gan.system.comm.base.BaseService;
import com.fp.gan.system.dao.sys.model.SysUserOrganization;
import com.fp.gan.system.dao.sys.model.SysUserOrganizationExample;

/**
* SysUserOrganizationService接口
*/
public interface SysUserOrganizationService extends BaseService<SysUserOrganization, SysUserOrganizationExample> {

    /**
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);

}