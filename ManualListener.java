package com.cts.loan.LoanProcess;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class ManualListener implements  ActivitiEventListener,ExecutionListener,TaskListener{

	@Override
	public void onEvent(ActivitiEvent event) {
		switch (event.getType()) {
		case PROCESS_STARTED:
			System.out.println("New Process Instance has been started.");
			break;

		case PROCESS_COMPLETED:

			String message = (String) event.getEngineServices().getRuntimeService().getVariable(event.getExecutionId(),
					"message");

			System.out.println("Process (started by \"" + message + "\") has been completed.");
			break;

		case VARIABLE_CREATED:
			System.out.println("New Variable was created.");
			System.out.println(">> All Variables in execution scope: "
					+ event.getEngineServices().getRuntimeService().getVariables(event.getExecutionId()));
			break;

		case TASK_ASSIGNED:
			System.out.println("Task has been assigned.");
			break;

		case TASK_CREATED:
			System.out.println("Task has been created.");
			break;

		case TASK_COMPLETED:
			System.out.println("Task \""
					+ event.getEngineServices().getHistoryService().createHistoricTaskInstanceQuery()
							.orderByHistoricTaskInstanceEndTime().asc().list().get(0).getName()
					+ "\" has been completed.");
			break;

		default:
			break;
		}
		
	}

	@Override
	public boolean isFailOnException() {
		System.out.println("inside isFailOnException()");
		return false;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		//System.out.println(delegateTask.getAssignee());
		//System.out.println(delegateTask.getCreateTime());
	}

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(execution.getCurrentActivityName());
		//System.out.println(execution.getCurrentActivityName());
		execution.setVariable("outcome", "Phone call over");
	}

}
