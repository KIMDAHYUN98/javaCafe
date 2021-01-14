package jCafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostProductServlet")
public class PostProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String itemNo = request.getParameter("itemNo");
		String item = request.getParameter("item");
		String category = request.getParameter("category");
		String price = request.getParameter("price");
		String content = request.getParameter("content");
		String likeIt = request.getParameter("likeIt");
		String image = request.getParameter("image");
		

		productVO vo = new productVO();
		
		vo.setItemNo(itemNo);
		vo.setItem(item);
		vo.setCategory(category);
		vo.setPrice(Integer.parseInt(price));
		vo.setContent(content);
		vo.setLikeIt(Integer.parseInt(likeIt));
		vo.setImage(image);
		
		productDAO dao = new productDAO();
		dao.insertProduct(vo);
		
		String script = "<script>location.href='jCafe/cafeList.html'</script>";
		response.getWriter().append(script);
		
		System.out.println(itemNo);
		System.out.println(item);
		System.out.println(category);
		System.out.println(price);
		System.out.println(content);
		System.out.println(likeIt);
		System.out.println(image);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
