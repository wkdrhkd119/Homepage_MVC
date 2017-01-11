package action.bbs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import action.Action;
import model.bbs.BbsDAO;
import model.bbs.BbsDTO;
import utility.UploadSave;

public class UpdateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String upDir = "/views/bbs/storage";
		String tempDir = "/views/bbs/temp";		
		
		upDir = request.getRealPath(upDir); //절대경로
		tempDir = request.getRealPath(tempDir); //절대경로

		UploadSave upload = new UploadSave(request, -1, -1, tempDir);
		

		String col = upload.getParameter("col");
		String word = UploadSave.encode(upload.getParameter("word"));
		String nowPage = upload.getParameter("nowPage");
		String oldfile =  UploadSave.encode(upload.getParameter("oldfile"));
		
		BbsDTO dto = new BbsDTO();
		dto.setBbsno(Integer.parseInt(upload.getParameter("bbsno")));
		dto.setWname(UploadSave.encode(upload.getParameter("wname")));
		dto.setTitle(UploadSave.encode(upload.getParameter("title")));
		dto.setContent(UploadSave.encode(upload.getParameter("content")));
		dto.setPasswd(UploadSave.encode(upload.getParameter("passwd")));
		
		FileItem fileItem = upload.getFileItem("filename");
		int filesize = (int)fileItem.getSize();
		String filename = null;
		if(filesize>0){	//새로운 파일을 업로드 했다.
			UploadSave.deleteFile(upDir, oldfile);	//기존파일을 삭제
			filename = UploadSave.saveFile(fileItem, upDir);
		}
		dto.setFilename(filename);
		dto.setFilesize(filesize);

		Map map = new HashMap();
		map.put("bbsno", dto.getBbsno());
		map.put("passwd", dto.getPasswd());
		
		BbsDAO dao = new BbsDAO();
		boolean pflag = dao.passCheck(map);
		//패스워드 검증
		//수정처리
		boolean flag = false;
		if(pflag){
			flag = dao.update(dto);
		}
			
		request.setAttribute("pflag", pflag);	
		request.setAttribute("flag", flag);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		return "/views/bbs/updateProc.jsp";
	}
}


