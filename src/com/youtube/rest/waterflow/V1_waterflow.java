package com.youtube.rest.waterflow;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.youtube.dao.PostgreCon;
import com.youtube.util.ToJSON;

@Path("/v1/waterflow")
public class V1_waterflow {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllWaterflowData() throws Exception {
		
		PreparedStatement query = null;
		String returnString = null;
		Connection conn = null;
		Response rb = null;	
		//JSONArray json = new JSONArray();
		
		try {
			
			conn = PostgreCon.PostgreConConnection().getConnection();
			query = conn.prepareStatement("SELECT * FROM WATERFLOW as flow");
			
			ResultSet rs = query.executeQuery(); 
			
			ToJSON convertor = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = convertor.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			
			
			//Schema308tube dao = new Schema308tube();
			
			//json = dao.queryAllPcParts();
			//returnString = json.toString();
			
			rb = Response.ok(returnString).build();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			if(conn!=null){conn.close();}
		}
		
		return rb;
	}
	
}
