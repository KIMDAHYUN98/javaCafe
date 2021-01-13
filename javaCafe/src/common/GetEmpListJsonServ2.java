package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getEmpList2")
public class GetEmpListJsonServ2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GetEmpListJsonServ2() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		EmpDAO dao = new EmpDAO();
		List<EmployeeVO> list = dao.getEmpList();
		PrintWriter out = response.getWriter();
		String json = "[";
		int cnt = 1;
		
		for(EmployeeVO emp : list) {
			json += "{";
			json += "\"empId\":\"" + emp.getEmployeeId() + "\"";
			json += ",\"firstName\":\"" + emp.getFirstName() + "\"";
			json += ",\"lastName\":\"" + emp.getLastName() + "\"";
			json += ",\"email\":\"" + emp.getEmail() + "\"";
			json += ",\"phoneNumber\":\"" + emp.getPhoneNumber() + "\"";
			json += ",\"hire_date\":\"" + emp.getHireDate() + "\"";
			json += ",\"job_id\":\"" + emp.getJobId() + "\"";
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
