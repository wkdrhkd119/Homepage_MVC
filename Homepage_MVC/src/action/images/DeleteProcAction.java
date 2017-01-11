package action.images;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.images.ImagesDAO;
import utility.UploadSave;

public class DeleteProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int no = Integer.parseInt(request.getParameter("no"));
		String passwd = request.getParameter("passwd");
		String oldfile = request.getParameter("oldfile");
		String upDir = request.getRealPath("/views/images/storage");
		
		Map map = new HashMap();
		map.put("no", no);
		map.put("passwd", passwd);
		
		ImagesDAO dao = new ImagesDAO();
		boolean pflag = dao.passCheck(map);
		boolean flag= false;
		
		if(pflag){
		flag=dao.delete(no);
		}
		if(flag){
		UploadSave.deleteFile(upDir, oldfile);
		}
		

		request.setAttribute("flag", flag);
		request.setAttribute("pflag", pflag);
		
		return  "/views/images/deleteProc.jsp";
	}

}
