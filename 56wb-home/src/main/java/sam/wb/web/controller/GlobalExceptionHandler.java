package sam.wb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);
	
    @ExceptionHandler(Exception.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
    	
    	logger.error("系统错误", ex);
        return "redirect:/";
    }     
}
