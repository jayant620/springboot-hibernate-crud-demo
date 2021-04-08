package com.throttling.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.util.concurrent.RateLimiter;



@Controller
public class GuavaHelloWorldController {
    private Logger logger = LoggerFactory.getLogger(GuavaHelloWorldController.class);
 
    private static final Double PERMITS_PER_SECONDS = 1d;
    private static final int    PERMITS_CONSUMED 	= 20;
	
    private RateLimiter rateLimiter = RateLimiter.create(PERMITS_PER_SECONDS);
	
    private AtomicInteger index = new AtomicInteger(0);
 
    @GetMapping("/hello-world")
    @ResponseBody
    public String sayHello(@RequestParam(name="name") String name) {
    	
        Date start = Calendar.getInstance().getTime();
    	
        int id = index.incrementAndGet();
        logger.debug( "Serving request id " + id );
    	
        double slept = rateLimiter.acquire(PERMITS_CONSUMED);
        logger.debug( "Acquired resources for request id " + id );
    	
        Date end = Calendar.getInstance().getTime();
        logger.debug( 
            String.format("Request id is %d served %d ms, after a sleep of %.0f ms to acquire the resources", 
            id, end.getTime()-start.getTime(), slept*1000 ) );
    	
        return "Hello " + name + "from request id " + id;
    }
}
