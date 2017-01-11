package action.images;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.images.ImagesDAO;
import model.images.ImagesDTO;

public class ReplyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int no = Integer.parseInt(request.getParameter("no"));
		
		ImagesDAO dao = new ImagesDAO();
		ImagesDTO dto = dao.readReply(no);
		
		request.setAttribute("dto", dto);
		
		return "/views/images/replyForm.jsp";
	}

}
