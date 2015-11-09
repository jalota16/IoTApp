package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;

public class PostgreCon {
	
	private static DataSource PostgreCon = null;
	private static Context context = null;
	
	public static DataSource PostgreConConnection() throws Exception{
		
		if(PostgreCon != null){
			return PostgreCon;
		}
		
		try{
			
			if(context == null){
				
				context = new InitialContext();
				
			}
			
			System.out.println("+++++ "+context);
			PostgreCon = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
			System.out.println("+++++ "+PostgreCon);
		} catch(Exception e){
			e.printStackTrace();
		}
		return PostgreCon;
		
	}
}