package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class ReadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		
		String id= request.getParameter("id");
		String grade = (String)session.getAttribute("grade");
		
		if(id==null){//관리자가 아닐때 //일반회원이 들어갈떄 확인
			id = (String)session.getAttribute("id");
		}
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.read(id);
		
		request.setAttribute("dto", dto);
		request.setAttribute("id", id);
		request.setAttribute("grade", grade);
		
		return "/views/member/read.jsp";
	}

}
