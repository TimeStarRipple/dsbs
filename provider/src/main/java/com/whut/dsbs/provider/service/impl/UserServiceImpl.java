package com.whut.dsbs.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.whut.dsbs.common.dto.Role;
import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.common.service.UserService;
import com.whut.dsbs.provider.dao.RoleDao;
import com.whut.dsbs.provider.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务的实现
 * Created by zyb on 2017-04-29.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    public List<User> selectByPage(int i, String s) {
        PageHelper.startPage(i, 10);
        return userDao.selectAll();
    }

    public User selectById(int i) {
        return userDao.selectById(i);
    }

    public User selectByUser(User user) {
        User result = userDao.selectByUser(user);
        if(result == null){
            return null;
        }
        Role resultRole = roleDao.getRoleById(result.getRoleId());
        result.setRole(resultRole);
        return result;
    }

    public User update(User user) {
        userDao.update(user);
        return user;
    }
}
