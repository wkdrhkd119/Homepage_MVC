package action.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import action.Action;
import model.bbs.BbsDAO;
import model.bbs.BbsDTO;
import utility.UploadSave;

public class CreateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//--업로드용 변수 선언(폴더명) 실제폴더가 존재해야함
		String upDir="/views/bbs/storage";
		String tempDir="/views/bbs/temp";
		
		upDir=request.getRealPath(upDir);  //절대 경로 반환
		tempDir=request.getRealPath(tempDir);
		
		BbsDTO dto = new BbsDTO();
		UploadSave upload = new UploadSave(request, -1, -1, tempDir);
		dto.setWname(UploadSave.encode(upload.getParameter("wname")));
		dto.setTitle(UploadSave.encode(upload.getParameter("title")));
		dto.setContent(UploadSave.encode(upload.getParameter("content")));
		dto.setPasswd(UploadSave.encode(upload.getParameter("passwd")));
		
		FileItem fileItem = upload.getFileItem("filename");
		int size = (int)fileItem.getSize();
		String filename = null;
		if(size>0) filename = UploadSave.saveFile(fileItem, upDir);
		
		dto.setFilename(filename);
		dto.setFilesize(size);
		
		
		BbsDAO dao = new BbsDAO();
		boolean flag = dao.create(dto);
		
		request.setAttribute("flag", flag);
		
		return "/views/bbs/createProc.jsp";
	}

}
