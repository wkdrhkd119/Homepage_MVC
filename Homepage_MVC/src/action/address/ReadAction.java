package action.address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.address.AddressDAO;
import model.address.AddressDTO;

public class ReadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int no = Integer.parseInt(request.getParameter("no"));
		String col = request.getParameter("col");
		String word = request.getParameter("word");
		String nowPage = request.getParameter("nowPage");
		
		AddressDAO dao = new AddressDAO();
		AddressDTO dto = dao.read(no);
		
		request.setAttribute("dto", dto);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		
		return "/views/address/read.jsp";
	}

}
