package action.memo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.memo.MemoDAO;

public class DeleteProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int memono = Integer.parseInt(request.getParameter("memono"));
		
		MemoDAO dao = new MemoDAO();
		
		boolean flag = dao.delete(memono);
		
		request.setAttribute("flag", flag);
		
		return "/views/memo/deleteProc.jsp";
	}

}
