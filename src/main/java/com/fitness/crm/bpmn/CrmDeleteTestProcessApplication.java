package com.fitness.crm.bpmn;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.ProcessApplicationInterface;

import com.fitness.bpm.engine.services.application.AbstractBpmProcessApplication;

@Startup
@Singleton
@Local(ProcessApplicationInterface.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@ProcessApplication("crm-delete-test-process")
public class CrmDeleteTestProcessApplication extends AbstractBpmProcessApplication {

}
