package com.jenkin.springboot.service.impl;

import com.jenkin.springboot.pojo.Plan;
import com.jenkin.springboot.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: jenkinwang
 * @description:
 */
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private HashOperations hashOperations;

    @Override
    public Map<String, Plan> getAll() {
        Map<String, Plan> map = hashOperations.entries("PLANS");
        return map;
    }

    @Override
    public void addPlan(Plan plan) {
        hashOperations.put("PLANS", plan.getObjectId(), plan);
    }

    @Override
    public void deletePlanById(String id) {
        hashOperations.delete("PLANS", id);
    }

    @Override
    public Plan getPlanById(String id) {
        return (Plan) hashOperations.get("PLANS", id);
    }

}
