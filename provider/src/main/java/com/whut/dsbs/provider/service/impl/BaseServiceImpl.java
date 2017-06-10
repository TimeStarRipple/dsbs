package com.whut.dsbs.provider.service.impl;

import java.util.List;

/**
 * 基础业务逻辑实现层
 *
 * 用于防止实现接口时出现太多的无用方法所设计的实现类
 *
 * Created by zyb on 2017-05-30.
 */
public class BaseServiceImpl<T> {

    public List<T> selectAll(){
        return null;
    }

    public List<T> selectByPage(int page, String filter){
        return null;
    }

    public T select(T t){
        return null;
    }

    public T insert(T t){
        return null;
    }

    public T delete(T t){
        return null;
    }

    public T update(T t){
        return null;
    }

    public boolean deleteBatch(String ids){
        return false;
    }
}
