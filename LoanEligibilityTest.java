package com.cts.loan.LoanProcess;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class LoanEligibilityTest implements JavaDelegate{

	private long salary;
	private long loanAmountReqd;
	private String job;
	private int cibilScore;
	private String aadharStatus;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		
		cibilScore=Integer.parseInt(execution.getVariable("CIBILScore").toString());
		aadharStatus=execution.getVariable("AadharCardVerification").toString();
		salary=Long.parseLong(execution.getVariable("fsalary").toString());
		loanAmountReqd=Long.parseLong(execution.getVariable("famount").toString());
		job=execution.getVariable("fjobCategory").toString();
		
		if(job.equals("Salaried") && (cibilScore>500)&&(aadharStatus.equals("Verified")))
		{
			
			double eligibleAmount=salary*.4;
			//if(loanAmountReqd>eligibleAmount)
				//execution.setVariable("outcome", "exceeds eligibility");
			//if(loanAmountReqd<=eligibleAmount)
			//{
			execution.setVariable("result", "Applicable");
			execution.setVariable("EligibleLoanAmount",eligibleAmount );
			execution.setVariable("EMI", eligibleAmount/60);
			//}
		}
		if(job.equals("SelfEmployed"))
		{
			execution.setVariable("result", "NotApplicable");
		}
		
	}

}
