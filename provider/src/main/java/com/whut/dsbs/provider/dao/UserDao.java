package com.whut.dsbs.provider.dao;

import com.whut.dsbs.common.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户类的dao层
 * Created by zyb on 2017-04-29.
 */
@Mapper
public interface UserDao {
    @Select("select * from dsbs_user")
    List<User> selectAll();

    User selectById(@Param("id")int id);

    /**
     * 用于验证登录的方法
     * @param user
     * @return
     */
    User selectByUser(User user);
}
