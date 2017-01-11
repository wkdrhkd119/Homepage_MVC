package action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.team.TeamDAO;
import model.team.TeamDTO;
import utility.UploadSave;

public class CreateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		return "/views/team/createForm.jsp";
	}

}
