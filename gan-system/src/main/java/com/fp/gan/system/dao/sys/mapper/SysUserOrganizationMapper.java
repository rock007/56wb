package com.fp.gan.system.dao.sys.mapper;


import java.util.List;

import com.fp.gan.system.dao.sys.model.SysUserOrganization;
import com.fp.gan.system.dao.sys.model.SysUserOrganizationExample;
import org.apache.ibatis.annotations.Param;

public interface SysUserOrganizationMapper {
    long countByExample(SysUserOrganizationExample example);

    int deleteByExample(SysUserOrganizationExample example);

    int deleteByPrimaryKey(Integer userOrganizationId);

    int insert(SysUserOrganization record);

    int insertSelective(SysUserOrganization record);

    List<SysUserOrganization> selectByExample(SysUserOrganizationExample example);

    SysUserOrganization selectByPrimaryKey(Integer userOrganizationId);

    int updateByExampleSelective(@Param("record") SysUserOrganization record, @Param("example") SysUserOrganizationExample example);

    int updateByExample(@Param("record") SysUserOrganization record, @Param("example") SysUserOrganizationExample example);

    int updateByPrimaryKeySelective(SysUserOrganization record);

    int updateByPrimaryKey(SysUserOrganization record);
}