package com.whut.dsbs.common.service;

import com.whut.dsbs.common.dto.Bidding;

import java.util.List;

/**
 * 工作流服务
 *
 * Created by zyb on 2017-05-10.
 */
public interface ActivitiService {

    String startProcessInstance(int userId);

    List<Bidding> findRoleCurrentTask(int roleId);

    boolean completeTask(Bidding bidding);
}
