package com.jenkin.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.jenkin.springboot.helper.BaseJsonSchemaValidatorTest;
import com.jenkin.springboot.pojo.Plan;
import com.jenkin.springboot.service.PlanService;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: jenkinwang
 * @description:
 */
@RestController
public class PlanController {

    @Autowired
    public PlanService planService;

    /**
     * GET
     * @return
     */
    @RequestMapping("/plan")
    public Map<String, Plan> getAll() {
        return planService.getAll();
    }

    /**
     * GET
     * @return
     */
    @RequestMapping("/plan/{id}")
    public Plan getPlanById(@PathVariable String id) {
        return planService.getPlanById(id);
    }

    /**
     * POST
     */
    @RequestMapping(value = "/plan", method = RequestMethod.POST)
    public ResponseEntity<?> addPlan(@RequestBody String content) throws IOException {
        BaseJsonSchemaValidatorTest validator = new BaseJsonSchemaValidatorTest();
        JsonNode node = validator.getJsonNodeFromStringContent(content);
        JsonSchema schema = validator.getJsonSchemaFromClasspath("F:\\springboot-redis-restful-api\\src\\main\\resources\\schema.json");
        Set<ValidationMessage> errors = schema.validate(node);

        if (errors.size() > 0) {
            JSONObject result = new JSONObject();
            List<String> error_ = new ArrayList<>();
            for (ValidationMessage error : errors) {
                error_.add(error.getMessage());
            }
            result.put("errors", error_);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        Plan plan = JSONObject.parseObject(content, Plan.class);
        if (plan.getObjectId() != null && !plan.getObjectId().equals("")) {
            if (planService.getPlanById(plan.getObjectId()) != null) {
                JSONObject result = new JSONObject();
                result.put("message", "Plan already exists");
                result.put("objectId", plan.getObjectId());
                return new ResponseEntity<>(result, HttpStatus.CONFLICT);
            }
            planService.addPlan(plan);
        }
        JSONObject result = new JSONObject();
        result.put("message", "success");
        result.put("objectId", plan.getObjectId());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * DELETE
     */
    @RequestMapping(value = "/plan/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePlanById(@PathVariable String id) {
        if (planService.getPlanById(id) == null) {
            JSONObject result = new JSONObject();
            result.put("message", "Object does not exists");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        planService.deletePlanById(id);
        JSONObject result = new JSONObject();
        result.put("message", "delete success");
        result.put("objectId", id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

}
