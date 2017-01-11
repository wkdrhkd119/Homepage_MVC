package action; 

import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

public class NullAction implements Action { //NullAction.java 
											//- 아무런 명령어도 일치하지 않으면 생성되는 클래스 

    public String execute( 
        HttpServletRequest request, 
        HttpServletResponse response) 
    throws ServletException { 

        System.out.println("NullAction Loading"); 
         
        // Context Path을 제거한 경로 지정 
        return "/views/nullCommand.jsp"; 
    } 
} 
