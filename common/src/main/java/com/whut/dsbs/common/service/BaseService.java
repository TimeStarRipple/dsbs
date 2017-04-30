package com.whut.dsbs.common.service;

import java.util.List;

/**
 * Created by zyb on 2017-04-30.
 */
public interface BaseService<T> {

    List<T> selectAll();

    List<T> selectByPage(int page, String filter);

    T select(T t);

    T insert(T t);

    T delete(T t);

    T update(T t);

    boolean deleteBatch(String ids);
}
