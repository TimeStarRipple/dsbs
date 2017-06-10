package com.whut.dsbs.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.whut.dsbs.common.dto.Bidding;
import com.whut.dsbs.common.service.ActivitiService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流服务实现类
 *
 * Created by zyb on 2017-05-10.
 */
@Service("activitiService")
@Transactional
public class ActivitiServiceImpl implements ActivitiService{

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    //初始化工作流参数
    private Map<String, Object> variables = new HashMap<String, Object>();
    {
        variables.put("role1Id", "2");//决策者创建项目
        variables.put("role2Id", "6");//技术部门-产品拆分
        variables.put("role3Id", "4");//仓库部门-确认数量
        variables.put("role4Id", "5");//生产部门-确定费用
        variables.put("role5Id", "3");//采购部门-确定收购价
        variables.put("role6Id", "2");//查看报价结果
    }

    public String startProcessInstance(int i) {
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("biddingTask", variables);
        return processInstance.getProcessInstanceId();
    }

    public List<Bidding> findRoleCurrentTask(int i, int page, String filter) {

        PageHelper.startPage(page, 10);

        List<Task> tasks = taskService
                .createTaskQuery()
                .taskAssignee(String.valueOf(i))
                .list();
        List<Bidding> result = new ArrayList<Bidding>();

        if (tasks == null || tasks.size() == 0) {
            return result;
        }else{
            Bidding bidding = null;
            for(Task task : tasks){
                bidding = new Bidding();

                bidding.setAssigneeRoleId(i);
                bidding.setProcessInstanceId(task.getProcessInstanceId());
                bidding.setTaskId(task.getId());
                bidding.setTaskName(task.getName());

                result.add(bidding);
            }
        }

        return result;
    }

    public boolean completeTask(Bidding bidding) {
        if(bidding.getTaskId() == null || "".equals(bidding.getTaskId())){
            return false;
        }

        String condition = bidding.getCondition();
        if(condition == null){
            //处理没有条件的任务
            taskService
                    .complete(bidding.getTaskId(), variables);
        } else {
            //处理有条件的任务
            variables.put("condition", condition);
            taskService
                    .complete(bidding.getTaskId(), variables);
        }
        return true;

    }

    public Bidding selectTaskByBidding(Bidding bidding) {
        List<Task> tasks = taskService
                .createTaskQuery()
                .taskAssignee(String.valueOf(bidding.getAssigneeRoleId()))
                .processInstanceId(bidding.getProcessInstanceId())
                .list();
        if (tasks == null || tasks.size() == 0 || tasks.size() > 1) {
            System.out.println("tasks.size()有问题");
        }
        else{
            Task task = tasks.get(0);
            bidding.setTaskId(task.getId());
            bidding.setTaskName(task.getName());
        }
        return bidding;
    }
}
