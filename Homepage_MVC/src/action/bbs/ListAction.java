package action.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.bbs.BbsDAO;
import model.bbs.BbsDTO;
import utility.Utility;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8"); //한글 깨짐 방지
		
		//검색
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		
		if(col.equals("tot")){
			word="";
		}
		//페이지관련-------------------------------
		int nowPage = 1; 	//현재 보고있는 페이지
			if(request.getParameter("nowPage")!=null){
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
			}
		int recordPerPage = 5;	//한페이지당 보여줄 레코드갯수
		
		
		//DB에서 가져올 순번
		int sno = ((nowPage-1)*recordPerPage) +1;
		int eno = nowPage*recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		BbsDAO dao = new BbsDAO();
		List<BbsDTO> list = dao.list(map);
		int total = dao.total(col, word);
	
		
		String paging =  Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);
		
		return "/views/bbs/list.jsp";
	}

}
