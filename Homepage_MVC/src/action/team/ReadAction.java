package action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.team.TeamDAO;
import model.team.TeamDTO;

public class ReadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int no = Integer.parseInt(request.getParameter("no"));
				
		TeamDAO dao = new TeamDAO();
		TeamDTO dto = dao.read(no);
		
		request.setAttribute("dto", dto);
			
		return "/views/team/read.jsp";
	}

}
