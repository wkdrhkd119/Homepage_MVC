package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import model.member.MemberDAO;
import utility.UploadSave;

public class DeletProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		
		String upDir = request.getRealPath("/views/member/storage");

		String id = request.getParameter("id");
		String oldfile = request.getParameter("oldfile");
		
		if(oldfile!=null && oldfile.equals("member.jpg")){
			oldfile = null;    //member.jpg이면 oldfile을 null값으로 받아온다.
		}
		
		MemberDAO dao = new MemberDAO();
		boolean flag = dao.delete(id);
		if(flag){
			UploadSave.deleteFile(upDir, oldfile);
			session.invalidate();//회원과 서버간의 연결을 끊는다.
		}
		
		request.setAttribute("flag", flag);
		
		return "/views/member/deleteProc.jsp";
	}

}
