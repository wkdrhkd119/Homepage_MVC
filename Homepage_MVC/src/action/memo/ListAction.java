package action.memo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.memo.MemoDAO;
import model.memo.MemoDTO;
import utility.Utility;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		//검색
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		if(col.equals("tot")){
			word="";
		}
		
		//페이지관련-------------------------------
		int nowPage = 1; //현재페이지(변경가능해야함)
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordePerPage = 5;	//한페이지다아 보여줄 페이지 레코드갯수
		
		//DB에서 읽어올 시작순번과 끝순번 만들기
		int sno = ((nowPage-1)*recordePerPage)+1;
		int eno = nowPage*recordePerPage;
		
		
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		MemoDAO dao = new MemoDAO();
		List<MemoDTO> list = dao.list(map);
		int total = dao.total(col,word);
		
		String paging = Utility.paging3(total, nowPage, recordePerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);
		
		return "/views/memo/list.jsp";
	}

}
