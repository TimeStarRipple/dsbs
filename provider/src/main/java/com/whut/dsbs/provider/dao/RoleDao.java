package com.whut.dsbs.provider.dao;

import com.whut.dsbs.common.dto.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色dao
 *
 * Created by zyb on 2017-05-09.
 */
@Mapper
public interface RoleDao {
    Role getRoleById(@Param("id")int id);
}
