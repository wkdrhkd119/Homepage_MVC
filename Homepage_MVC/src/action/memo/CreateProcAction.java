package action.memo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.memo.MemoDAO;
import model.memo.MemoDTO;

public class CreateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MemoDTO dto = new MemoDTO();
		request.setCharacterEncoding("utf-8");//한글처리
		
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		MemoDAO dao = new MemoDAO();
		boolean flag = dao.create(dto);
		
		request.setAttribute("flag", flag);
		
		return "/views/memo/createProc.jsp";
	}

}
