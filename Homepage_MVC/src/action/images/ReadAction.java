package action.images;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.images.ImagesDAO;
import model.images.ImagesDTO;

public class ReadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int no= Integer.parseInt(request.getParameter("no"));
		
		
		ImagesDAO dao =new ImagesDAO();
		
		dao.upViewcnt(no);
		
		ImagesDTO dto= dao.read(no);
		
		
		List<ImagesDTO> list= dao.imgRead(no);
		
		String content= dto.getContent();	
		content= content.replaceAll("\r\n","<br>");
		
		request.setAttribute("list", list);
		request.setAttribute("dto", dto);	
		

				
		return "/views/images/read.jsp";
	}

}
