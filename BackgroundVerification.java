package com.cts.loan.LoanProcess;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class BackgroundVerification implements JavaDelegate{
	private String panCardNo;
	private long aadharCardNo;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		panCardNo=execution.getVariable("fpancardno").toString();
		aadharCardNo=Long.parseLong(execution.getVariable("faadharCardNo").toString());
		
		if(panCardNo.equals("ASIPP2786N"))
			execution.setVariable("CIBILScore", 789);
		if(aadharCardNo==43478078897L)
			execution.setVariable("AadharCardVerification", "Verified");
	}

}
