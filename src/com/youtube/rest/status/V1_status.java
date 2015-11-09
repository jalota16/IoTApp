package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;

import com.youtube.dao.*;

@Path("/v1/status")
public class V1_status {
		
	private static final String api_version = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: </p>"+api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseTimestamp() throws Exception{
		
		PreparedStatement query = null;
		String date = null;
		String returnString = null;
		Connection conn = null;
		
		try{
			conn = PostgreCon.PostgreConConnection().getConnection();
			query = conn.prepareStatement("SELECT NOW()");
			
			ResultSet rs = query.executeQuery(); 
			
			while(rs.next()){
				//System.out.println("RS1: "+rs.getInt(1));
				date = rs.getString("now");
				
			}
			query.close();
			//System.out.println(JSONString);
			returnString =  "<p>Database DateTime: </p>"+date;
			
		} catch(Exception e){
			e.printStackTrace();
			
		} finally {
			if(conn!=null){conn.close();}
		}
		
		return returnString;
	}
	
	
	
	@Path("/firstRow")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnFirstRow() throws Exception{
		
		PreparedStatement query = null;
		int id = 0;
		String name = null;
		float price = 0.0f;
		String returnString = null;
		String JSONString  = null;
		Connection conn = null;
		
		try{
			conn = PostgreCon.PostgreConConnection().getConnection();
			query = conn.prepareStatement("SELECT * from products as name");
			
			ResultSet rs = query.executeQuery(); 
			
			while(rs.next()){
				//System.out.println("RS1: "+rs.getInt(1));
				id = rs.getInt("product_no");
				name = rs.getString("name");
				price = rs.getFloat("price");
				
			}
			query.close();
			JSONString = "{\"product_no\":"+ id
						+", \"name\": "+"\""+ name+"\""
						+",\"price\":"+ price
						+"}";
			System.out.println(JSONString);
			returnString =  JSONString;
			
		} catch(Exception e){
			e.printStackTrace();
			
		} finally {
			if(conn!=null){conn.close();}
		}
		
		return returnString;
	}
	
	
	
}
