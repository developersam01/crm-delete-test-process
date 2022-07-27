package com.fitness.crm.bpmn.delegate;

import com.fitness.bpm.engine.services.delegate.AbstractJavaDelegate;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandlingDelegate extends AbstractJavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        String errorCode = getStringVariable(execution, "errorCode");
        String errorMessage = getStringVariable(execution, "errorMessage");
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("errorCode",  errorCode != null ? errorCode : "UNKNOWN_ERROR");
        result.put("errorMessage", errorMessage);
        execution.setVariable("outputResponse", result);
    }
}
