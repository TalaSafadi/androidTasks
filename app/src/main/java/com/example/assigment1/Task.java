package com.example.assigment1;

import java.io.Serializable;

public class Task implements Serializable {
    private String TaskName;
    private String description;
    private Boolean status;





    public Task(String taskName, String description, Boolean status) {
        TaskName = taskName;
        this.description = description;
        this.status = status;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return TaskName;
    }

}
