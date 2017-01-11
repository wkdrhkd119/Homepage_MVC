package action.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.bbs.BbsDAO;
import model.bbs.BbsDTO;

public class DeleteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int bbsno = Integer.parseInt(request.getParameter("bbsno"));
		
		BbsDAO dao = new BbsDAO();
		boolean flag = dao.checkRefno(bbsno);
		
		request.setAttribute("flag", flag);
		
		return "/views/bbs/deleteForm.jsp";
	}

}
