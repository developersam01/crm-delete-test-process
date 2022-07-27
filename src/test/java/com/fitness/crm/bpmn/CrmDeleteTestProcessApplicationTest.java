package com.fitness.crm.bpmn;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareAssertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fitness.bpm.test.runner.CamundaJUnitRunner;

@RunWith(CamundaJUnitRunner.class)
public class CrmDeleteTestProcessApplicationTest {

    @Resource
    private RuntimeService runtimeService;
    
    @Test
    @Deployment(resources = {"crm-delete-test-process.bpmn"})
    public void testCrmDeleteTestProcessProcess() {

        Map<String, Object> variables = new HashMap<>();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("crm-delete-test-process", variables);

        assertThat(processInstance).isEnded();
        assertThat(processInstance).hasPassedInOrder(
            "StartEvent_Template",
            "Set_Response",
            "EndEvent_Template"
        );
    }
}
