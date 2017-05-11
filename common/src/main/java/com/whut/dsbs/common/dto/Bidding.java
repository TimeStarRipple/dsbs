package com.whut.dsbs.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 投标报价实例
 *
 * Created by zyb on 2017-05-10.
 */
public class Bidding implements Serializable{

    private int id;

    private String projectName;

    private Date createTime;

    private int userId;

    private String username;

    private String projectDescription;

    private Date deadline;

    /**
     * 工作流内数据，用于关联
     */
    //当前任务id
    private String taskId;

    //当前工作流id
    private String processInstanceId;

    //当前任务办理角色
    private int assigneeRoleId;

    //当前任务办理部门名称
    private String assigneeDepartment;

    //当前任务名称
    private String taskName;

    //当前任务条件
    private String condition;

    public Bidding() {
    }

    public Bidding(int id, String projectName, Date createTime, int userId, String username, String projectDescription, Date deadline, String taskId, String processInstanceId, int assigneeRoleId, String assigneeDepartment, String taskName, String condition) {
        this.id = id;
        this.projectName = projectName;
        this.createTime = createTime;
        this.userId = userId;
        this.username = username;
        this.projectDescription = projectDescription;
        this.deadline = deadline;
        this.taskId = taskId;
        this.processInstanceId = processInstanceId;
        this.assigneeRoleId = assigneeRoleId;
        this.assigneeDepartment = assigneeDepartment;
        this.taskName = taskName;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public int getAssigneeRoleId() {
        return assigneeRoleId;
    }

    public void setAssigneeRoleId(int assigneeRoleId) {
        this.assigneeRoleId = assigneeRoleId;
    }

    public String getAssigneeDepartment() {
        return assigneeDepartment;
    }

    public void setAssigneeDepartment(String assigneeDepartment) {
        this.assigneeDepartment = assigneeDepartment;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bidding{");
        sb.append("id=").append(id);
        sb.append(", projectName='").append(projectName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", projectDescription='").append(projectDescription).append('\'');
        sb.append(", deadline=").append(deadline);
        sb.append(", taskId='").append(taskId).append('\'');
        sb.append(", processInstanceId='").append(processInstanceId).append('\'');
        sb.append(", assigneeRoleId=").append(assigneeRoleId);
        sb.append(", assigneeDepartment='").append(assigneeDepartment).append('\'');
        sb.append(", taskName='").append(taskName).append('\'');
        sb.append(", condition='").append(condition).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
