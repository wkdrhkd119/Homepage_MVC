package action.images;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.images.ImagesDAO;

public class DeleteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		 int no=Integer.parseInt(request.getParameter("no"));
		
		 ImagesDAO dao = new ImagesDAO();
		 boolean flag = dao.checkRefno(no);
		 
		 request.setAttribute("flag", flag);
		 //request.setAttribute("no", no);
		
		
		return "/views/images/deleteForm.jsp";
	}

}
