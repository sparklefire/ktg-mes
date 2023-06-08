package com.ktg.system.mapper;

import com.ktg.system.domain.UserTask;

import java.util.List;

public interface UserTaskMapper {

    public List<UserTask> listTodoList(Long userId);

    public List<UserTask> listFinishedList(Long userId);

}
