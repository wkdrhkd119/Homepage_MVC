package controller; 
 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.NullAction; 
 
public class Controller extends HttpServlet { //서버 구동시 객체생성
    private boolean usingTemplate = false; 
    private String templatePage = null; 
    
    //명령어=명령처리Action객체
    private Map commandHandlerMap = new java.util.HashMap(); 
  
   public void init(ServletConfig config) throws ServletException { //ServletConfig
        String configFile =  config.getInitParameter("configFile"); //getInitParameter web.xml파라미터 가져온다.
         
        Properties prop = new Properties(); 
        FileInputStream fis = null; 
        try { 
            fis = new FileInputStream(configFile); 
            prop.load(fis); //파일값 읽어들인다.
        } catch (IOException e) { 
            throw new ServletException(e); 
        } finally { 
            if (fis != null) try { fis.close(); } catch(IOException ex) {} 
        } 
         
        Iterator keyIter = prop.keySet().iterator(); 
        while( keyIter.hasNext() ) { 
            String command = (String)keyIter.next(); 
            System.out.println("command: " + command); 
 
            String handlerClassName = prop.getProperty(command).trim();  
            System.out.println("handlerClassName: " + handlerClassName); 
             
            try { //excute 메소드 호출하기 위해서
                //클래스를 JVM으로 로딩합니다. 
                //mvctem.command.HelloHandler 클래스의 소스를 읽어 들입니다. 
                Class handlerClass = Class.forName(handlerClassName); //객체생성 Class.forName을 통해서
                 
                //읽어들인 클래의 객체를 생성합니다. 
                Object handlerInstance = handlerClass.newInstance(); 
                 
                //MAP에 키와 각 클래스별 객체가 저장합니다. 
                commandHandlerMap.put(command, handlerInstance); 
                
            } catch (ClassNotFoundException e) { 
                throw new ServletException(e); 
            } catch (InstantiationException e) { 
                throw new ServletException(e); 
            } catch (IllegalAccessException e) { 
                throw new ServletException(e); 
            } 
        } 
      
        templatePage = config.getInitParameter("templatePage"); 
        
        if (templatePage != null && !templatePage.equals("")) { 
            usingTemplate = true; // 템플릿 페이지 존재 
        } 
    } 
   
    //1. HTTP 요청 받음   
    //service에 의해서 doget,dopost가 요청된다
    public void doGet( 
        HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException { 
        process(request, response); 
    } 
 
    protected void doPost( 
        HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException { 
        process(request, response); 
    } 
    private void process( 
        HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException { 
    	
    	//2. 클라이언트가 요구하는 기능을 분석(방명록의 경우 글쓰기등)  
        String command = request.getRequestURI(); 
        System.out.println("RequestURI: " + request.getRequestURI()); 
        
        //URI:/www_mvc/mvc/list.do
        if (command.indexOf(request.getContextPath()) == 0) { //URI 잘 가지고 왔는지 확인
            command = command.substring(request.getContextPath().length()); // /mvc/list.do 가지고온다. 키값으로 저장됨
            System.out.println("command: " + command); 
        } 
         
 
        Action action =  //Action을 사용하기 위해3,4,5번 꺼내기
            (Action)commandHandlerMap.get(command); 
         
        // 핸들러가 없는 경우 
        if (action == null) { 
            action = new NullAction(); 
        } 
          
        String viewPage = null; 
        try { 
        	//3,4,5번 사용 execute가 호출되어야 함
            viewPage = action.execute(request, response); //viewPage리턴
        } catch(Throwable e) { 
            throw new ServletException(e); 
        } 
        
        if(viewPage.indexOf("/popup")!=-1){
        	usingTemplate = false;
        }else{
        	usingTemplate = true;
        }
        
         
        if (usingTemplate) { 
            request.setAttribute("CONTENT_PAGE", viewPage); 
        } 
        //6. 뷰로 포워딩(또는 리다이렉트), jsp 페이지로 이동 
        RequestDispatcher dispatcher = //forward를 사용하는 방법 viewPage를 보여주기 위해서
           request.getRequestDispatcher(usingTemplate ? templatePage : viewPage); 
        dispatcher.forward(request, response); 
    } 
} 