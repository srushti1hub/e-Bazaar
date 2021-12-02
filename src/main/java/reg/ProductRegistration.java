package reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

@WebServlet("/ProductRegistration")
public class ProductRegistration extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();


//		
		String productName = req.getParameter("productName");
		double productPrice = Double.parseDouble(req.getParameter("productPrice"));
		int productUnit = Integer.parseInt(req.getParameter("productUnit"));
		//String serialID = req.getParameter("productSerial");
		String[] productQualities = req.getParameter("productQualities").split(",");
		String productCategory = req.getParameter("category");// Fetch Category from the form
		String productSubCategory=req.getParameter("subCategory");
		String mfg=req.getParameter("mfg");
		
		Product product = new Product();
		
		product.setCategory(productCategory);
		product.setMfg(mfg);
		product.setName(productName);
		product.setSubCategory(productSubCategory);
		product.setProductPrice(productPrice);
		product.setProductUnit(productUnit);
		product.setProductQualities(productQualities);
		
		
		
//        /**************************Method to check whether Product created correctly"*************************
		out.println("Hello");
		out.println("<br>");

		out.println("productName : " + product.getName());
		out.println("<br>");
		out.println("productCategory : " + product.getCategory());
		out.println("<br>");
		out.println("productID : " + product.getProductID());
		out.println("<br>");
		out.println("productPrice : " + product.getProductPrice());
		out.println("<br>");
		out.println("productUnit : " + product.getProductUnit());
		out.println("<br>");
		out.println("productQualities : " + product.getProductQualities().toString());
		out.println("<br>");
		dbConnection db=new dbConnection();
		try {
			boolean result=db.addProductToDB(product);
			if(result) {
				out.println("Product added");
			}
			else {
				out.println("Product could not be added");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        *****************************************************************************"*************************/
//        req.setAttribute("product", product);
//        resp.sendRedirect(productCategory);
//        
//		

//		dbConnection db=new dbConnection();
//		try {
//			boolean res=db.addProductToDB(product);
//			if (res) {
//				out.println("Product Added SuccessFully");				
//			}
//			else {
//				out.println("Product Could not be added");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}