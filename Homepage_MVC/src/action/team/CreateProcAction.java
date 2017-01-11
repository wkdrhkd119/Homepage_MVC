package action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import action.Action;
import model.team.TeamDAO;
import model.team.TeamDTO;
import utility.UploadSave;

public class CreateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String upDir = request.getRealPath("/views/team/storage");
		String tempDir = request.getRealPath("/views/team/temp");
		
		UploadSave upload = new UploadSave(request, -1, -1, tempDir);
		
		TeamDTO dto = new TeamDTO();//from의 값 가져오기
		dto.setName(UploadSave.encode(upload.getParameter("name")));
		dto.setGender(UploadSave.encode(upload.getParameter("gender")));
		dto.setPhone(upload.getParameter("phone"));
		dto.setZipcode(UploadSave.encode(upload.getParameter("zipcode")));
		dto.setAddress1(UploadSave.encode(upload.getParameter("address1")));
		dto.setAddress2(UploadSave.encode(upload.getParameter("address2")));
		dto.setHobby(UploadSave.encode(upload.getParameter("hobby")));
		dto.setSkill(upload.getParameterValues("skill"));
		
		FileItem fileItem = upload.getFileItem("filename");
		int filesize = (int)fileItem.getSize();
		String filename = null;
			if(filesize>0){
				filename = UploadSave.saveFile(fileItem, upDir);
			}else{
				filename = "member.jpg";
			}
			
		dto.setFilename(filename);	
		TeamDAO dao = new TeamDAO();
		boolean flag = dao.create(dto);
		
		request.setAttribute("flag", flag);
		
		return "/views/team/createProc.jsp";
	}

}
