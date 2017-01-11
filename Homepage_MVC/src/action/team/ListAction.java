package action.team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.team.TeamDAO;
import model.team.TeamDTO;
import utility.Utility;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if(col.equals("total")) word="";
		
		int nowPage=1;
			if(request.getParameter("nowPage")!=null){
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
			}
		int recordPerPage=5;
		
		int sno = ((nowPage-1)*recordPerPage)+1;
		int eno = nowPage*recordPerPage;		
		
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		TeamDAO dao = new TeamDAO();
		
		List<TeamDTO> list = dao.list(map);
		int total = dao.total(col, word);
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		
		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);
	
		
		return "/views/team/list.jsp";
	}

}
