/**
 * 
 */
package action.images;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

/**
 * @author Soldesk
 *
 */
public class CreateFormtAction implements Action {

	/* (non-Javadoc)
	 * @see action.Action#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		return "/views/images/createForm.jsp";
	}

}
