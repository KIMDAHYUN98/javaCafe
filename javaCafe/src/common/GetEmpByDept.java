package common;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getMembersByDept")
public class GetEmpByDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEmpByDept() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		EmpDAO dao = new EmpDAO();
		Map<String, Integer> map = dao.getMemberByDept();
		Set<String> keySet = map.keySet(); // map의 키값만 빼내옴
		
		String json = "[";
		int cnt = 1;
		for(String key : keySet) {
			
			json += "{";
			json += "\"" + key + "\":";
			json += map.get(key);
			json += "}";
			if(keySet.size() != cnt++) {
				json += ", ";
			}
		}
		json += "]";
		
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
