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

public class ReplyProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String upDir = request.getRealPath("/views/bbs/storage");
		String tempDir = request.getRealPath("/views/bbs/temp");	

		UploadSave upload = new UploadSave(request, -1, -1, tempDir);
		

		//페이징과 검색을 위한 컬럼
		String col = upload.getParameter("col");
		String word = UploadSave.encode(upload.getParameter("word")); //한글처리
		String nowPage = upload.getParameter("nowPage");
		
		
		BbsDTO dto = new BbsDTO();
		//답변을 위한 값 dto객체가 위에 사용됬기 때문에 dto로 설정
		dto.setGrpno(Integer.parseInt(upload.getParameter("grpno")));
		dto.setIndent(Integer.parseInt(upload.getParameter("indent")));
		dto.setAnsnum(Integer.parseInt(upload.getParameter("ansnum")));
		
		//부모글 삭제 제한을 위해
		dto.setBbsno(Integer.parseInt(upload.getParameter("bbsno")));
		
		//form에 입력된 값 가져오기
		dto.setWname(UploadSave.encode(upload.getParameter("wname")));
		dto.setTitle(UploadSave.encode(upload.getParameter("title")));
		dto.setContent(UploadSave.encode(upload.getParameter("content")));
		dto.setPasswd(UploadSave.encode(upload.getParameter("passwd")));
		
		//filename 가져오기
		FileItem fileItem = upload.getFileItem("filename");
		int filesize = (int)fileItem.getSize();
		String filename = null;
		if(filesize>0){
			filename = UploadSave.saveFile(fileItem, upDir);
		}
		dto.setFilename(filename);
		dto.setFilesize(filesize);

		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		
		BbsDAO dao = new BbsDAO();
		dao.upAnsnum(map);
		boolean flag = dao.createReply(dto);
		
		request.setAttribute("flag",flag);
		request.setAttribute("col",col);
		request.setAttribute("word",word);
		request.setAttribute("nowPage",nowPage);
		
		
		return "/views/bbs/replyProc.jsp";
	}

}
