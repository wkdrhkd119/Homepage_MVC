package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class UpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");//관리자가
		if(id==null){//사용자가
			id = (String)session.getAttribute("id");
		}
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.read(id);
		
		request.setAttribute("dto", dto);
		request.setAttribute("id", id);
		
		
		return "/views/member/updateForm.jsp";
	}

}
