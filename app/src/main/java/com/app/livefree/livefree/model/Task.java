package com.app.livefree.livefree.model;

import java.util.List;

/**
 * Created by anujkumars on 8/29/2015.
 */
public class Task {
    int id;
    String description;
    int priority;
    String taskTime;
    String duration;
    String remainingTime;
    String remainingDistance;
    String userId;
    String locationId;
    List<String> tags;


    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getRemainingDistance() {
        return remainingDistance;
    }

    public void setRemainingDistance(String remainingDistance) {
        this.remainingDistance = remainingDistance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
