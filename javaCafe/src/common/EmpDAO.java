package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection conn = null;

	public EmpDAO() {
		try {
			String user = "hr";
			String pw = "hr";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}

	}// end of 생성자.
	
     //insert 기능 생성
	public EmployeeVO insertEmp(EmployeeVO vo) {
		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "select * from emp_temp where employee_id = ?";
		String sql = "insert into emp_temp (employee_id, first_name, last_name, email, hire_date, job_id)"
				+ "values(?,?,?,?,sysdate,?)";
		int r =0;
		String newSeq = null;
		EmployeeVO newVo = new EmployeeVO();
		    try {
			PreparedStatement psmt = conn.prepareStatement(sql1);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				newSeq = rs.getString(1);
			}
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, newSeq);
			psmt.setString(2, vo.getFirstName());
			psmt.setString(3, vo.getLastName());
			psmt.setString(4, vo.getEmail());
			psmt.setString(5, vo.getJobId());
			r = psmt.executeUpdate();
			System.out.println(r+ "건 입력됨.");
			
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, newSeq);
			rs = psmt.executeQuery();
			if (rs.next()) {
				newVo.setEmail(rs.getString("email"));
				newVo.setEmployeeId(rs.getInt("employee_id"));
				newVo.setFirstName(rs.getString("first_name"));
				newVo.setHireDate(rs.getString("hire_date"));
				newVo.setJobId(rs.getString("job_id"));
				newVo.setLastName(rs.getString("last_name"));
				newVo.setPhoneNumber(rs.getString("phone_number"));
				newVo.setSalary(rs.getInt("salary"));
			}
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return newVo;
	}

	// delete 기능 생성
	public boolean deleteEmp(EmployeeVO vo) {
		String sql = "delete from emp_temp where employee_id = ?";
		int r = 0;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getEmployeeId());

			r = psmt.executeUpdate();
			System.out.println(r + "건 삭제됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r == 1 ? true : false; // 한 건 지워졌으면 트루 아니면 false..
	}
	
	//update 기능 생성?
	public EmployeeVO updateEmp(EmployeeVO vo) {
		String sql = "update emp_temp set first_name = ?, last_name = ?, email = ?, job_id = ?"
				+ "where employee_id = ?";
		String sql2 = "select * from emp_temp";
		int r = 0;
		EmployeeVO newVo = new EmployeeVO();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getFirstName());
			psmt.setString(2, vo.getLastName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getJobId());
			psmt.setInt(5, vo.getEmployeeId());
			System.out.println(vo.getEmployeeId());
			
			r = psmt.executeUpdate();
			System.out.println(r + "건 수정됨.");
			
			psmt = conn.prepareStatement(sql2);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				
				newVo.setEmail(rs.getString("email"));
				newVo.setEmployeeId(rs.getInt("employee_id"));
				newVo.setFirstName(rs.getString("first_name"));
				newVo.setHireDate(rs.getString("hire_date"));
				newVo.setJobId(rs.getString("job_id"));
				newVo.setLastName(rs.getString("last_name"));
				newVo.setPhoneNumber(rs.getString("phone_number"));
				newVo.setSalary(rs.getInt("salary"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return newVo;
	}

	public List<EmployeeVO> getEmpList() {
		String sql = "select * from emp_temp order by 1 desc";
		List<EmployeeVO> list = new ArrayList<>();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoneNumber(rs.getString("phone_number"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setSalary(rs.getInt("salary"));

				list.add(vo);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	}// end of getEmpList()

}