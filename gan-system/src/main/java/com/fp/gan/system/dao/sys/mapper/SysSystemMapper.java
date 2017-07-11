package com.fp.gan.system.dao.sys.mapper;

import java.util.List;

import com.fp.gan.system.dao.sys.model.SysSystem;
import com.fp.gan.system.dao.sys.model.SysSystemExample;
import org.apache.ibatis.annotations.Param;

public interface SysSystemMapper {
    long countByExample(SysSystemExample example);

    int deleteByExample(SysSystemExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(SysSystem record);

    int insertSelective(SysSystem record);

    List<SysSystem> selectByExample(SysSystemExample example);

    SysSystem selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") SysSystem record, @Param("example") SysSystemExample example);

    int updateByExample(@Param("record") SysSystem record, @Param("example") SysSystemExample example);

    int updateByPrimaryKeySelective(SysSystem record);

    int updateByPrimaryKey(SysSystem record);
}