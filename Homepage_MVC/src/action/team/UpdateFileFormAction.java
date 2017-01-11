package action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.team.TeamDAO;

public class UpdateFileFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		return "/views/team/updateFileForm.jsp";
	}

}
