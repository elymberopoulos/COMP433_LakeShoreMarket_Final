package databaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import order.Order;
import serviceUsers.Customer;
import serviceUsers.Partner;

public class PartnerDAO {
	
	public static void post(Partner partner){
		String postQuery = "insert into partner"
				+ "(partnerId,firstName,lastName,companyName,address,"
				+ "phoneNumber,email,partnerPassword,bankAccountNumber) "
				+ "values('" + partner.getUserID() + "','" + partner.getFirstName() + "', '" + partner.getLastName() + "','" + partner.getCompanyName() + "','" + 
				partner.getAddress() + "','" + partner.getPhoneNumber() + "','" + partner.getEmail() + "','" + 
				partner.getPartnerPassword() + "', '" + partner.getBankAccountNumber() + "');";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(postQuery);
			
		}catch(SQLException se) {
			se.printStackTrace();

		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
	public static void put(Partner partner){
		String putQuery = "UPDATE partner "
				+ "SET partnerID ='"+partner.getUserID()+ "',firstName ='"+ partner.getFirstName()+ 
				"',lastName='"+partner.getLastName()+"',companyName='"+partner.getCompanyName()+
				"',address='"+partner.getAddress()+"',phoneNumber='"+partner.getPhoneNumber()+
				"',email='"+partner.getEmail()+"',partnerPassword='"+partner.getPartnerPassword()+"',bankAccountNumber='"+partner.getBankAccountNumber()+
				"' WHERE partnerID='"+partner.getUserID()+"';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(putQuery);
			
		}catch(SQLException se) {
			se.printStackTrace();

		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
	public static List<Partner> get(){
		String getQuery = "select * from partner;";
		Connection connection = DBConnect.getDatabaseConnection();
		List<Partner> returnList = new ArrayList<Partner>();

		try {
			Statement getStatement = connection.createStatement();
			ResultSet rs = getStatement.executeQuery(getQuery);
			while(rs.next()){
				System.out.println(rs.getString("partnerId") + ", " + rs.getString("firstName") + ", " + rs.getString("lastName") + ", " + rs.getString("companyName")
				+ ", " + rs.getString("address") + ", " + rs.getString("phoneNumber") + ", " + rs.getString("email")
				+ ", " + rs.getString("partnerPassword") + ", " + rs.getString("bankAccountNumber"));
				Partner targetPartner = new Partner(rs.getString("firstName"), rs.getString("lastName"), rs.getString("companyName"), 
						rs.getString("address"), rs.getInt("phoneNumber"), rs.getString("email"), rs.getString("partnerPassword"),rs.getString("partnerId"), rs.getInt("bankAccountNumber"));
				returnList.add(targetPartner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}
	public static Partner getMatchingPartner(String userID){
		
		String selectQuery = "SELECT * FROM partner where partnerId = " + "'" + userID + "';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement getStatement = connection.createStatement();
			ResultSet rs = getStatement.executeQuery(selectQuery);
			rs.next();
			Partner targetPartner = new Partner(rs.getString("firstName"), rs.getString("lastName"), rs.getString("companyName"), 
					rs.getString("address"), rs.getInt("phoneNumber"), rs.getString("email"), rs.getString("partnerPassword"),rs.getString("partnerId"), rs.getInt("bankAccountNumber"));
			return targetPartner;
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return null;
	}
	
	public static void delete(Partner partner){
		String userID = partner.getUserID();
		String deleteQuery = "delete from partner where partnerId=" + "'" + userID + "';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(deleteQuery);
			
		}catch(SQLException se) {
			se.printStackTrace();

		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
	public static void deleteIDMatch(String userID){
		String deleteQuery = "delete from partner where partnerId=" + "'" + userID + "';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(deleteQuery);
			
		}catch(SQLException se) {
			se.printStackTrace();

		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
}
