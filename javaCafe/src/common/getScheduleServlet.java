package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getSchedule")
public class getScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getScheduleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		
		EmpDAO dao = new EmpDAO();
		List<Schedule> list = dao.getScheduleList();
		PrintWriter out = response.getWriter();
		String json = "[";
		int cnt = 1;
		for(Schedule sch : list) {
			json += "{";
			json += "\"title\":\"" + sch.getTitle() + "\"";
			json += ",\"start\":\"" + sch.getStartDate() + "\"";
			json += ",\"end\":\"" + sch.getEndDate() + "\"";
			json += ",\"url\":\"" + sch.getUrl() + "\"";
			json += "}";
			if(list.size() != cnt++) {
				json += ", ";
			}
		}
		json += "]";
		out.print(json);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
