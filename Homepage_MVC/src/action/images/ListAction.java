package action.images;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.images.ImagesDAO;
import model.images.ImagesDTO;
import utility.Utility;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//검색============================================
		String col= Utility.checkNull(request.getParameter("col"));
		String word= Utility.checkNull(request.getParameter("word"));


		if(col.equals("total")){
		word="";
		}
		//paging 관련=======================================
		int nowPage= 1;//현재 보고 있는 페이지
		if(request.getParameter("nowPage")!=null){
		nowPage=Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage= 5;//한 페이지 보여줄 갯수

		//DB에서 가져올 순번=====================================
		int sno= ((nowPage-1)*recordPerPage)+1;
		int eno= nowPage*recordPerPage;


		Map map= new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		ImagesDAO dao = new ImagesDAO();
		int total=dao.total(col, word);
		List<ImagesDTO> list= dao.list(map);
		
		
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		
		return "/views/images/list.jsp";
	}

}
