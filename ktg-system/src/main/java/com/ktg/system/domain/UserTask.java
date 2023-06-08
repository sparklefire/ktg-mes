package com.ktg.system.domain;

import com.ktg.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 所有用户相关的业务单据
 * 用于移动端展示待处理单据和已处理单据
 */
public class UserTask extends BaseEntity {

    /**
     * 单据类型
     */
    private String taskType;

    /**
     * 单据的ID
     */
    private String taskId;

    /**
     * 单据编码
     */
    private String taskCode;

    /**
     * 单据名称
     */
    private String taskName;


    /**
     * 单据的状态
     */
    private String status;

    private String nickName;

    private String userName;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "taskType='" + taskType + '\'' +
                ", taskId='" + taskId + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
