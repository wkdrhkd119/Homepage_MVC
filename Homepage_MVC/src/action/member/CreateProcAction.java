package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import action.Action;
import model.member.MemberDAO;
import model.member.MemberDTO;
import utility.UploadSave;

public class CreateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String upDir = "/views/member/storage";
		String tempDir = "/views/member/temp";
		
		upDir = request.getRealPath(upDir);
		tempDir = request.getRealPath(tempDir);
		
		UploadSave upload = new UploadSave(request, -1, -1, tempDir);
		
		String id = UploadSave.encode(upload.getParameter("id"));
		String email = upload.getParameter("email");
		
		String str = "";
		String viewPage = "/views/member/prcreateProc.jsp";
		
		MemberDAO dao = new MemberDAO();
		if(dao.duplicateId(id)){			 //prcreateProc로 가야한다 
			str="중복된 아이디"; 				 //prcreateProc로 가야한다 
			request.setAttribute("str", str);
		}else if(dao.duplicateEmail(email)){ //prcreateProc로 가야한다 
			str="중복된 이메일";
			request.setAttribute("str", str);//prcreateProc로 가야한다 
		}else{
			MemberDTO dto = new MemberDTO();
			dto.setId(UploadSave.encode(upload.getParameter("id")));
			dto.setMname(UploadSave.encode(upload.getParameter("mname")));
			dto.setAddress1(UploadSave.encode(upload.getParameter("address1")));
			dto.setAddress2(UploadSave.encode(upload.getParameter("address2")));
			
			dto.setPasswd(upload.getParameter("passwd"));
			dto.setTel(upload.getParameter("tel"));
			dto.setEmail(upload.getParameter("email"));
			dto.setZipcode(upload.getParameter("zipcode"));
			dto.setJob(upload.getParameter("job"));
			
			//form의 선택한 파일 받기
			FileItem fileItem = upload.getFileItem("fname");
			int size = (int)fileItem.getSize();
			String fname = null;
			if(size>0){
				fname = UploadSave.saveFile(fileItem, upDir);
			}else{
				fname = "member.jpg";
			}
			
			dto.setFname(fname);
			
			boolean flag = dao.create(dto);
			
			request.setAttribute("flag", flag);
			
			viewPage = "/views/member/createProc.jsp";
		}
				
		return viewPage;
	}

}
