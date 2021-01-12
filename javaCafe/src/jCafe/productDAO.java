package jCafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productDAO {

	Connection conn = null;

	public productDAO() {

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

	}// end of 생성자

	public List<productVO> getProductList() {
		String sql = "select * from product order by 1";
		List<productVO> list = new ArrayList();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery(); // 위의 sql 구문을 실행
			while (rs.next()) { // 가져온 데이터를 true로 리턴
				productVO vo = new productVO();
				vo.setItemNo(rs.getString("item_no")); // 반드시 DB 칼럼명과 같을 것!!
				vo.setItem(rs.getString("item"));
				vo.setCategory(rs.getString("category"));
				vo.setPrice(rs.getInt("price"));
				vo.setLink(rs.getString("link"));
				vo.setContent(rs.getString("content"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setAlt(rs.getString("alt"));
				vo.setImage(rs.getString("image"));

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

	}
}
