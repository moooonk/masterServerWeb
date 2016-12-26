/**
 * 
 */
package com.masterserver.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.masterserver.config.CoreConfig;


/**
 * @author huangguanlin
 *
 * 2016年12月23日
 */
public class MasterServerDao {
	public static final MasterServerDao instance = new MasterServerDao();
	private Connection conn;
	
	private MasterServerDao(){
		try {
			com.mysql.jdbc.Driver.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadServerList(ArrayList<String[]> serverList){
		serverList.clear();
		try {
			conn = DriverManager.getConnection(CoreConfig.instance.getConfig().url(), CoreConfig.instance.getConfig().user(), CoreConfig.instance.getConfig().password());
			Statement statement = conn.createStatement();
			String sql = "select * from " + CoreConfig.instance.getConfig().tableName();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String []ipPort = new String[2];
				ipPort[0] = rs.getString("ip");
				ipPort[1] = rs.getString("port");
				serverList.add(ipPort);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
