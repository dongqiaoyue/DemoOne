package com.jenkin.springboot.pojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Configuration
public class Plan implements Serializable {
    @Id
    private String objectId;
    private String objectType;
    private String planType;
    private String creationDate;
    private String _org;
    private List<Map<String, Object>> linkedPlanServices;
    private Map<String, Object> planCostShares;

    public Plan() {};

    public Plan(String objectId, String objectType, String planType, String creationDate, String _org, List<Map<String, Object>> linkedPlanServices, Map<String, Object> planCostShares) {
        this.objectId = objectId;
        this.objectType = objectType;
        this.planType = planType;
        this.creationDate = creationDate;
        this._org = _org;
        this.linkedPlanServices = linkedPlanServices;
        this.planCostShares = planCostShares;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String get_org() {
        return _org;
    }

    public void set_org(String _org) {
        this._org = _org;
    }

    public List<Map<String, Object>> getLinkedPlanServices() {
        return linkedPlanServices;
    }

    public void setLinkedPlanServices(List<Map<String, Object>> linkedPlanServices) {
        this.linkedPlanServices = linkedPlanServices;
    }

    public Map<String, Object> getPlanCostShares() {
        return planCostShares;
    }

    public void setPlanCostShares(Map<String, Object> planCostShares) {
        this.planCostShares = planCostShares;
    }
}
