package action.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.bbs.BbsDAO;
import model.bbs.BbsDTO;

public class UpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int bbsno = Integer.parseInt(request.getParameter("bbsno"));
		
		BbsDAO dao = new BbsDAO();
		BbsDTO dto = dao.read(bbsno);
		
		request.setAttribute("dto", dto);
		
		
		return "/views/bbs/updateForm.jsp";
	}

}
