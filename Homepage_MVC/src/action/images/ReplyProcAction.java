package action.images;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import action.Action;
import model.images.ImagesDAO;
import model.images.ImagesDTO;
import utility.UploadSave;

public class ReplyProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String upDir = request.getRealPath("/views/images/storage");
		String tempDir = request.getRealPath("/views/images/temp");
		
		// beans
		UploadSave upload = new UploadSave(request,-1,-1,tempDir);
		
		// paging
		String col = upload.getParameter("col");
		String word = UploadSave.encode(upload.getParameter("word"));
		int nowPage = Integer.parseInt(upload.getParameter("nowPage"));
		
		// 답변을 위한 부모 값
		ImagesDTO dto = new ImagesDTO();
		dto.setGrpno(Integer.parseInt(upload.getParameter("grpno")));
		dto.setIndent(Integer.parseInt(upload.getParameter("indent")));
		dto.setAnsnum(Integer.parseInt(upload.getParameter("ansnum")));
		
		// 부모글 삭제 확인
		dto.setNo(Integer.parseInt(upload.getParameter("no")));
		
		// form의 입력된 값 가져오기
		dto.setWname(UploadSave.encode(upload.getParameter("wname")));
		dto.setTitle(UploadSave.encode(upload.getParameter("title")));
		dto.setContent(UploadSave.encode(upload.getParameter("content")));
		dto.setPasswd(UploadSave.encode(upload.getParameter("passwd")));
		
		// filename 가져오기
		FileItem fileItem = upload.getFileItem("fname");
		int filesize = (int)fileItem.getSize();
		String fname = null;
		if(filesize>0) fname = UploadSave.saveFile(fileItem, upDir);
		
		dto.setFname(fname);
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		
		ImagesDAO dao = new ImagesDAO();
		dao.upAnsnum(map);
		
		boolean flag = dao.createReply(dto);
		
		request.setAttribute("flag", flag);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);

		
		return "/views/images/replyProc.jsp";
	}

}
