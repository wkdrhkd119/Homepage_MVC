package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import model.member.MemberDAO;

public class DeletFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		if(id==null){
			id = (String)session.getAttribute("id");
		}
		MemberDAO dao = new MemberDAO();
		String oldfile = dao.getFname(id);
		
		request.setAttribute("id", id);
		request.setAttribute("oldfile", oldfile);
		
		return "/views/member/deleteForm.jsp";
	}

}
