package com.jenkin.springboot.service;

import com.jenkin.springboot.pojo.Plan;

import java.util.Map;

/**
 * @author: jenkinwang
 * @description:
 */
public interface PlanService {
    Map<String, Plan> getAll();

    void addPlan(Plan plan);

    void deletePlanById(String id);

    Plan getPlanById(String id);
}
