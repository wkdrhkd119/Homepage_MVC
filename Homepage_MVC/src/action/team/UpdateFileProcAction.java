package action.team;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import action.Action;
import model.team.TeamDAO;
import utility.UploadSave;

public class UpdateFileProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String upDir = request.getRealPath("/views/team/storage");
		String tempDir = request.getRealPath("/views/team/temp");

		UploadSave upload = new UploadSave(request, -1, -1, tempDir);
		
		int no = Integer.parseInt(upload.getParameter("no"));
		String col = upload.getParameter("col");
		String nowPage = upload.getParameter("nowPage");
		String word = UploadSave.encode(upload.getParameter("word"));
		String oldfile = UploadSave.encode(upload.getParameter("oldfile"));
		
		//수정할 사진 받기
		FileItem fileItem = upload.getFileItem("filename");
		int size = (int)fileItem.getSize();
		String filename = null;
		if(size>0){
			UploadSave.deleteFile(upDir, oldfile);
			filename = UploadSave.saveFile(fileItem, upDir);
		}
		
		Map map = new HashMap();
		map.put("no", no);
		map.put("filename", filename);
		
		TeamDAO dao = new TeamDAO();
		boolean flag = dao.updateFile(map);
		
		request.setAttribute("flag", flag);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("no", no);
		
		return "/views/team/updateFileProc.jsp";
	}

}
