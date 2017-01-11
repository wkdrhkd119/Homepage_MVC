package action.images;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import action.Action;
import model.images.ImagesDAO;
import model.images.ImagesDTO;
import utility.UploadSave;

public class CreateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String tempDir = request.getRealPath("/views/images/temp");
		String upDir = request.getRealPath("/views/images/storage");

		UploadSave upload = new UploadSave(request, -1, -1, tempDir);

		ImagesDTO dto = new ImagesDTO();
		dto.setWname(UploadSave.encode(upload.getParameter("wname")));
		dto.setTitle(UploadSave.encode(upload.getParameter("title")));
		dto.setContent(UploadSave.encode(upload.getParameter("content")));
		dto.setPasswd(UploadSave.encode(upload.getParameter("passwd")));

		FileItem fileItem = upload.getFileItem("fname");
		int size = (int) fileItem.getSize();
		String fname = null;
		if(size > 0){
			fname = upload.saveFile(fileItem, upDir);
		}else{
			fname = "member.jpg";
		}

		dto.setFname(fname);
		
		ImagesDAO dao = new ImagesDAO();
		boolean flag = dao.create(dto);
		
		request.setAttribute("flag", flag);
		
		return "/views/images/createProc.jsp";
	}

}
