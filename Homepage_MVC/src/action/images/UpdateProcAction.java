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

public class UpdateProcAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String upDir = "/views/images/storage";
		String tempDir = "/views/images/temp";

		upDir = request.getRealPath(upDir);
		tempDir = request.getRealPath(tempDir);

		UploadSave upload = new UploadSave(request, -1, -1, tempDir);

		String col = upload.getParameter("col");
		String word = UploadSave.encode(upload.getParameter("word"));
		String nowPage = upload.getParameter("nowPage");
		String oldfile = UploadSave.encode(upload.getParameter("oldfile"));

		ImagesDTO dto = new ImagesDTO();
		dto.setNo(Integer.parseInt(upload.getParameter("no")));
		dto.setWname(UploadSave.encode(upload.getParameter("wname")));
		dto.setTitle(UploadSave.encode(upload.getParameter("title")));
		dto.setContent(UploadSave.encode(upload.getParameter("content")));
		dto.setPasswd(UploadSave.encode(upload.getParameter("passwd")));

		FileItem fileItem = upload.getFileItem("fname");

		int filesize = (int) fileItem.getSize();
		String fname = null;

		if (filesize > 0) { //새 파일을 업로드했다.
		UploadSave.deleteFile(upDir, oldfile); //기존파일을 삭제
		fname = UploadSave.saveFile(fileItem, upDir);
		}

		dto.setFname(fname);

		Map map = new HashMap();
		map.put("no", dto.getNo());
		map.put("passwd", dto.getPasswd());
		
		ImagesDAO dao = new ImagesDAO();
		boolean pflag = dao.passCheck(map);

		boolean flag = false;
		if (pflag) {
		flag = dao.update(dto);
		}
		
		request.setAttribute("flag", flag);
		request.setAttribute("pflag", pflag);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		
		return "/views/images/updateProc.jsp";
	}

}
