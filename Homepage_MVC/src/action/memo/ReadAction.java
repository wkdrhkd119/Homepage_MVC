package action.memo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.memo.MemoDAO;
import model.memo.MemoDTO;

public class ReadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int memono = Integer.parseInt(request.getParameter("memono"));
		MemoDAO dao = new MemoDAO();
		dao.upViewcnt(memono); /*  조회수 증가  */
		
		/* 		조회		 */
		MemoDTO dto = dao.read(memono);
		
		String content = null;
		if(dto != null){
			content = dto.getContent();
			content = content.replaceAll("\r\n", "<br>");
			dto.setContent(content);
		}
		
		request.setAttribute("dto", dto);
		
		return "/views/memo/read.jsp";
	}

}
