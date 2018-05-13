package com.cts.loan.LoanProcess;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class LoanCalculator implements JavaDelegate{
	private long salary;
	private long loanAmountReqd;
	private String job;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		
				
				salary=Long.parseLong(execution.getVariable("salary").toString());
				loanAmountReqd=Long.parseLong(execution.getVariable("loanamount").toString());
				job=execution.getVariable("jobCategory").toString();
				
				if(job.equals("Salaried"))
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
