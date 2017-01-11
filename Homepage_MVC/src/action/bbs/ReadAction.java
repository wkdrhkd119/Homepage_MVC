package action.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.bbs.BbsDAO;
import model.bbs.BbsDTO;

public class ReadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int bbsno = Integer.parseInt(request.getParameter("bbsno"));
		
		BbsDAO dao = new BbsDAO();
		dao.upViewcnt(bbsno);
		
		BbsDTO dto = dao.read(bbsno);
		
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");
		dto.setContent(content);
		
		request.setAttribute("dto", dto);
		
		return "/views/bbs/read.jsp";
	}

}
