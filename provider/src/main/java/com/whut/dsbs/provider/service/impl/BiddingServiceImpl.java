package com.whut.dsbs.provider.service.impl;

import com.whut.dsbs.common.dto.Bidding;
import com.whut.dsbs.common.service.ActivitiService;
import com.whut.dsbs.common.service.BiddingService;
import com.whut.dsbs.provider.dao.BiddingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 报价投标服务层的实现
 *
 * Created by zyb on 2017-05-10.
 */
@Service("biddingService")
@Transactional
public class BiddingServiceImpl implements BiddingService{

    @Resource
    private ActivitiService activitiService;

    @Autowired
    private BiddingDao biddingDao;

    public List<Bidding> selectAll() {
        return null;
    }

    public List<Bidding> selectByPage(int i, String s) {
        return null;
    }

    public Bidding select(Bidding bidding) {
        return null;
    }

    public Bidding insert(Bidding bidding) {
        String processInstanceId = activitiService.startProcessInstance(bidding.getUserId());
        bidding.setProcessInstanceId(processInstanceId);

        //查到新建的任务
        List<Bidding> biddings = activitiService.findRoleCurrentTask(2);//决策者角色id
        Bidding result = biddings.get(0);

        //创建投标报价单
        bidding.setAssigneeDepartment("技术部门");
        bidding.setAssigneeRoleId(6);
        bidding.setProcessInstanceId(result.getProcessInstanceId());
        bidding.setTaskId(result.getTaskId());
        bidding.setTaskName(result.getTaskName());
        bidding.setCreateTime(new Date());
        biddingDao.insert(bidding);

        //完成任务
        activitiService.completeTask(bidding);
        return bidding;
    }

    public Bidding delete(Bidding bidding) {
        return null;
    }

    public Bidding update(Bidding bidding) {
        return null;
    }

    public boolean deleteBatch(String s) {
        return false;
    }

    public List<Bidding> selectBiddingByRoleId(int roleId) {

        List<Bidding> activitiBidding = activitiService.findRoleCurrentTask(roleId);

        List<Bidding> biddings = biddingDao.selectByRoleId(roleId);
        Map<String, Bidding> map = new HashMap<String, Bidding>();
        for(Bidding bidding : biddings){
            map.put(bidding.getProcessInstanceId(), bidding);
        }

        List<Bidding> result = new ArrayList<Bidding>();
        for(Bidding bidding : activitiBidding){
            Bidding item = map.get(bidding.getProcessInstanceId());

            item.setTaskId(bidding.getTaskId());
            item.setTaskName(bidding.getTaskName());

            result.add(item);
        }

        return result;
    }
}
