package sam.wb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
     
    @ExceptionHandler(Exception.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
    	System.out.println("eccezione globale");
        return "redirect:/";
    }
    
}
