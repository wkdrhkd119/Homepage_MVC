package action.address;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.address.AddressDAO;
import model.address.AddressDTO;
import utility.Utility;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		if(col.equals("tot")){
			word = "";
		}
		
		int nowPage = 1;
			if(request.getParameter("nowPage")!=null){
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
			}
		int recordPerPage= 5;
		int sno = ((nowPage-1)*recordPerPage)+1;
		int eno = nowPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		AddressDAO dao = new AddressDAO();
		List<AddressDTO> list = dao.list(map);
		Iterator<AddressDTO> iter = list.iterator();
		int total = dao.total(col,word);
		
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("iter", iter);
		
		
		return "/views/address/list.jsp";
	}

}
