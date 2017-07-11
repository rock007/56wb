package com.fp.gan.system.dao.sys.mapper;


import java.util.List;

import com.fp.gan.system.dao.sys.model.SysUserPermission;
import com.fp.gan.system.dao.sys.model.SysUserPermissionExample;
import org.apache.ibatis.annotations.Param;

public interface SysUserPermissionMapper {
    long countByExample(SysUserPermissionExample example);

    int deleteByExample(SysUserPermissionExample example);

    int deleteByPrimaryKey(Integer userPermissionId);

    int insert(SysUserPermission record);

    int insertSelective(SysUserPermission record);

    List<SysUserPermission> selectByExample(SysUserPermissionExample example);

    SysUserPermission selectByPrimaryKey(Integer userPermissionId);

    int updateByExampleSelective(@Param("record") SysUserPermission record, @Param("example") SysUserPermissionExample example);

    int updateByExample(@Param("record") SysUserPermission record, @Param("example") SysUserPermissionExample example);

    int updateByPrimaryKeySelective(SysUserPermission record);

    int updateByPrimaryKey(SysUserPermission record);
}