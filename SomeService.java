package com.cts.loan.LoanProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SomeService {
	private static Logger logger = LoggerFactory.getLogger(SomeService.class);

	   public String process() {
	       logger.info("Doing some processing...");
	       return "Finished processing!";
	   }
}
