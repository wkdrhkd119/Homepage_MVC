package action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.team.TeamDAO;
import model.team.TeamDTO;

public class UpdateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
				
		TeamDTO dto = new TeamDTO();
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setPhone(request.getParameter("phone"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress1(request.getParameter("address1"));
		dto.setAddress2(request.getParameter("address2"));
		dto.setSkill(request.getParameterValues("skill"));
		dto.setHobby(request.getParameter("hobby"));
		
		TeamDAO dao = new TeamDAO();
		boolean flag = dao.update(dto);
		
		request.setAttribute("flag", flag);
		
		return "/views/team/updateProc.jsp";
	}

}
