package com.whut.dsbs.customer.controller;

import com.whut.dsbs.common.dto.Permission;
import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.common.service.UserService;
import com.whut.dsbs.customer.constants.JsonResult;
import com.whut.dsbs.customer.utils.DecodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 用户控制层
 *
 * Created by zyb on 2017-04-28.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userServiceImpl;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userServiceImpl.selectAll();
    }

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public JsonResult getPermissions(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Permission> result = (List<Permission>) session.getAttribute("permissions");
        if(result == null){
            throw new RuntimeException("登录超时，请重新登录");
        }else{
            return new JsonResult("200", "查询权限成功", result);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JsonResult login(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");

        //判定请求头中是否存在账号密码
        if(authorization == null || "".equals(authorization) || "Basic".equals(authorization)){
            throw new RuntimeException("请登录后再使用该系统！");
        }

        User user = DecodeUtil.decodeToObject(authorization);

        if(user.getUsername() == null || "".equals(user.getUsername())){
            throw new RuntimeException("账号不能为空！");
        }

        if(user.getPassword() == null || "".equals(user.getPassword())){
            throw new RuntimeException("密码不能为空！");
        }

        User result = userServiceImpl.selectByUser(user);
        if(result == null || result.getUsername() == null){
            throw new RuntimeException("账号或密码错误！");
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", user);
        httpSession.setAttribute("permissions", result.getRole().getPermissions());

        //密码设置为空，返回前台
        result.setPassword(null);

        return new JsonResult("200", "登录成功", result);
    }

    @RequestMapping(value = "/index/{id}", method = RequestMethod.PUT)
    public JsonResult updateUser(@RequestBody User data){
        System.out.println(data);
        userServiceImpl.update(data);
        return new JsonResult("200", "更新成功", data);
    }

    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    public JsonResult getUserById(@PathVariable("id")int id){
        User result = userServiceImpl.selectById(id);
        return new JsonResult("200", "查询成功", result);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult getUserByPage(int page, String search){
        List<User> result = userServiceImpl.selectByPage(page, search);
        return new JsonResult("200", "查询成功", result);
    }

}
