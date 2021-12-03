package UserFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import Connection.MyCon;
import reg.Product;
import try1.User;

public class UserMethods {
// buyItem
// addToCart
// display Cart
//view shopping history
// View login history

	public User getUserDetails(String usertype, String email) {
		User user = new User();
		Connection con = MyCon.dbcon("user_signup_login_DATA_for_admin");
		String tableName = usertype + "RegistrationDetails";
		String sql = "select * from " + tableName + " where userEmailid='" + email + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int userSerialID = rs.getInt(1);
				String username = rs.getString(2);
				String userEmailid = rs.getString(3);
				String userMobNumber = rs.getString(4);
				String userPassword = rs.getString(5);
				String userAddress = rs.getString(6);
				// String registrationTime=rs.getDateTime(7);
				String userUniqueID = rs.getString(8);
				System.out.println(userSerialID);

				user.setAddress(userAddress);
				user.setEmail(userEmailid);
				user.setMobNumber(userMobNumber);
				user.setName(username);
				user.setUserID(userUniqueID);
				user.setPassword(userPassword);
				user.setUserType(usertype);
				return user;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;

	}

	public Product getProductDetails(String ProductID) {
		Product product = new Product();
		Connection con = MyCon.dbcon("product");

		String tableName = ProductID.split("_")[0];
		System.out.println(tableName);

		String sql = "select * from " + tableName + " where productID='" + ProductID + "'";
		System.out.println(sql);
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int serialID = rs.getInt(1);
				String productID = rs.getString(2);
				String subCategory = rs.getString(3);
				String productName = rs.getString(4);
				double productPrice = rs.getDouble(5);
				int productUnit = rs.getInt(6);
				String[] productQualities = rs.getString(7).split(",");
				String mfg = rs.getString(8);
				String sellerID = rs.getString(9);

				product.setCategory(tableName);
				product.setMfg(mfg);
				product.setSubCategory(subCategory);
				product.setProductQualities(productQualities);
				product.setProductID(productID);
				product.setProductUnit(productUnit);
				product.setProductPrice(productPrice);
				product.setName(productName);
				product.setSellerID(sellerID);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return product;
	}

	public boolean updateProduct(String productID, Product productUpdated) {
		Connection con = MyCon.dbcon("product");
		Product productOld = getProductDetails(productID);

		String tableName = productID.split("_")[0];
		String sql = "update " + tableName+" set productName='"+productUpdated.getName()+"',  productPrice='"+productUpdated.getProductPrice()+"', productUnit='"+productUpdated.getProductUnit()+"',productQualities='"+toString(productUpdated.getProductQualities())+"', mfg='"+productUpdated.getMfg()+"'  where productID='"+productID+"'";
		
		try {
			Statement st=con.createStatement();			
			st.executeUpdate(sql);
			System.out.println("updation Successful SuccessFul");		
			System.out.println("***************");
			productOld.printProductDetails();
			System.out.println("#############is updated to ##########");
			productUpdated.printProductDetails();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	

	public String toString(String arr[]) {
		String string = "";
		for (int i = 0; i < arr.length; i++) {
			string = string+ arr[i] + " ,";
		}
		return string;

	}

}
