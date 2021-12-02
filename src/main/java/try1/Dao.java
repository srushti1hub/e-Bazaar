package try1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import Connection.MyCon;


public class Dao {

	// private Connection con;

	protected boolean insertSignUpDetails(User user) {
		boolean result = false;
		String name = user.getName();
		String email = user.getEmail();
		String mobNumber = user.getMobNumber();
		String pass = user.getPassword();
		String address = user.getAddress();
		String userType=user.getUserType();
		
		
		try {

			Connection con = MyCon.dbcon("user_signup_login_DATA_for_admin");
//			Statement stm=con.createStatement();
//			ResultSet rs=stm.executeQuery("")
//			int serial=

			String sql = "insert into "+ userType+"RegistrationDetails( username, userEmailid,userMobNumber,userPassword,userAddress) values(?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, mobNumber);
			st.setString(4, pass);
			st.setString(5, address);

			st.executeUpdate();
			System.out.println("Details Inserted ......");
			
			//Update the USERUniqueID as CUSTOMER34 or VENDOR23 in the table
			
			String tableName= userType+"RegistrationDetails";
			ResultSet rs = st
					.executeQuery("SELECT * FROM " + tableName+" ORDER BY userSerialID DESC LIMIT 1;");
			rs.next();
			int id = rs.getInt("userSerialID");
			String userID = userType.toUpperCase() + id;
			user.setUserID(userID);
			System.out.println(user.getUserID());
			String sql2="update "+tableName+" set userUniqueID='"+user.getUserID()+"' where userSerialID="+id;
			st.executeUpdate(sql2);
			System.out.println("Product ID updated");

			// get last insert id
			// create a Unique ID
			// set uniqueID of user
			// update the uniqueID into table using sql queries and with the help of last_insert_id();
			
			result = true;
		}

		catch (Exception f) {
			f.printStackTrace();
		}

		return result;
	}

	protected boolean checkLogIN(String user, String pass,String usertype) {
		boolean result = false;
		try {
			String sql = "select * from "+usertype+"RegistrationDetails where userEmailid=? and userPassword=?";
			Connection con = MyCon.dbcon("user_signup_login_DATA_for_admin");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				result = true;
			}

			String sql2 = "insert into signindetails(userID,fetchedUserName,fetchedUserRegistrationID,usertype) values('"+user+"','none','none','"+usertype+"');";
					//"insert into signindetails(userID,fetchedUserName,fetchedUserRegistrationID,userType) values('" + user
					//+ "', 'notFetched', 'notFetched','"+usertype+"')";
					
			st.execute(sql2);
//				if(rs2.next()) {
//				System.out.println("completed afdj");}
//				else {
//					System.out.println("unsuccessful  asldfh");
//				}

		} catch (Exception f) {
		}

		return result;
	}

}