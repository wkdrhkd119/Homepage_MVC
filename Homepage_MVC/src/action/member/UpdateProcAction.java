package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class UpdateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setTel(request.getParameter("tel"));
		dto.setEmail(request.getParameter("email"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress1(request.getParameter("address1"));
		dto.setAddress2(request.getParameter("address2"));
		dto.setJob(request.getParameter("job"));
		
		MemberDAO dao = new MemberDAO();
		
		boolean flag = dao.update(dto);
		
		request.setAttribute("flag", flag);
		request.setAttribute("id", dto.getId());
		
		
		return "/views/member/updateProc.jsp";
	}

}
