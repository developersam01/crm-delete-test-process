package com.fitness.crm.bpmn.endpoint;

import com.fitness.bpm.engine.services.AbstractBpmProcessClient;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Path("/performDelete")
public class CrmDeleteTestProcessEndpoint extends AbstractBpmProcessClient<String, Serializable> {

    private static final Logger LOG = Logger.getLogger(CrmDeleteTestProcessEndpoint.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("unchecked")
    public Response crmDeleteTestProcess(final String inputRequest) throws Exception {
        if (isInputKeyValid(inputRequest)) {
            try {
                Serializable processResult = runProcess(inputRequest);
                return prepareResponse(processResult);
            } catch (Exception e) {
                LOG.error("An error occurred executing the process", e);
                return buildErrorMessage(Response.Status.INTERNAL_SERVER_ERROR, Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
        } else {
            return buildErrorMessage(Response.Status.BAD_REQUEST, "Invalid Input Request");
        }
    }

    private boolean isInputKeyValid(final String key) {
        return key != null && key.trim().length() != 0;
    }

    private Response prepareResponse(final Serializable result) {
        if (result instanceof Map) {
            Map<String, Object> response = (Map<String, Object>) result;
            if ((boolean) response.getOrDefault("success", true)) {
                return Response.ok(response.get("data")).build();
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("errorCode", response.getOrDefault("errorCode", "UNKNOWN_ERROR"));
                errorResponse.put("errorMessage", response.get("errorMessage"));
                switch ((String) errorResponse.get("errorCode")) {
                    // Add cases for other error codes
                    default: {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
                    }
                }
            }
        }
        return Response.status(Response.Status.OK).entity(result).build();
    }

    private Response buildErrorMessage(final Response.Status statusCode, String errorMessage) {
        Map<String, Serializable> errorResponse = new HashMap<>();
        errorResponse.put("errorMessage", errorMessage);
        errorResponse.put("errorCode", statusCode.name());
        Response.ResponseBuilder responseBuilder = Response.status(statusCode).entity(errorResponse);
        return responseBuilder.build();
    }

    @Override
    protected String getProcessDefinitionKey() {
        return "crm-delete-test-process";
    }

    @Override
    protected String getInputParameterKey() {
        return "inputRequest";
    }

    @Override
    protected String getOutputParameterKey() {
        return "outputResponse";
    }
}
