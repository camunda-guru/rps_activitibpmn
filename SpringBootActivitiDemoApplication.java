package com.cts.springbootactivactiviti.SpringBootActivitiDemo;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.activiti.spring.boot.RestApiAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.onlineBankingApplication"})
public class SpringBootActivitiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivitiDemoApplication.class, args);
	}

    @RestController
    public static class TestRestController
    {
    	@Autowired
    	private RuntimeService runTimeService;
    	@Autowired
    	private PersonService personService;
    	@RequestMapping(value="/startprocess", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    	public String startTestProcess()
    	{
    		runTimeService.startProcessInstanceByKey("testprocess");
    		return "We are running now"+runTimeService.createProcessInstanceQuery().count()+"Process instance";
    	}
    	
    	
    	@RequestMapping(value = "/process")
    	public String startProcessInstance(@RequestParam String assignee) {
    		return personService.startProcess(assignee);
    	}
     
    	@RequestMapping(value = "/tasks/{assignee}")
    	public String getTasks(@PathVariable("assignee") String assignee) {
    		List<Task> tasks = personService.getTasks(assignee);
    		return tasks.toString();
    	}
     
    	@RequestMapping(value = "/completetask")
    	public String completeTask(@RequestParam String id) {
    		personService.completeTask(id);
    		return "Task with id " + id + " has been completed!";
    	}
    }

}
