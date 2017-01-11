package action.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.member.MemberDAO;
import model.member.MemberDTO;
import utility.Utility;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8"); //post방식의 한글처리 일반적인 getParameter로 받아올때 한글이 깨지므로
		
		//검색관련
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if(col.equals("total")){
			word="";
		}
		
		//페이징 관련
		int nowPage = 1;
		int recordPerPage = 5;
		if(request.getParameter("nowPage")!=null){ //getParameter문자열로 받아온다
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		//DB에서 가져올 순번
		int sno = ((nowPage-1)*recordPerPage)+1;
		int eno = nowPage*recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		MemberDAO dao = new MemberDAO();
		int total = dao.total(col,word);
		List<MemberDTO> list = dao.list(map);
		Iterator<MemberDTO> iter = list.iterator();
		
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);
		
		return "/views/member/list.jsp";
	}

}
