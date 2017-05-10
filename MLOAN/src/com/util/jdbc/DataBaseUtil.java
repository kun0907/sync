package com.util.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.util.base.ActionBase;

public class DataBaseUtil {

    private static DataSource ds;
    
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jndi-web");
        } catch (NamingException e) {
        	ActionBase.log.error(e);
        }
    }
    
	protected synchronized static Connection getConn(){
        
        try {
            return ds.getConnection() != null ? ds.getConnection() : null;
        } catch (SQLException e) {
        	ActionBase.log.error(e);
        }
        
        return null;
    }
	 protected static void closeRs(ResultSet rs) {
	        try{
	            if(rs != null) {
	            	rs.close();
	            	rs = null;
	            }
	        } catch (Exception e) {
	        	ActionBase.log.error(e);
	        }
	    }
    protected static void closeStmt(Statement stmt) {
        try{
            if(stmt != null) {
            	stmt.close();
            	stmt = null;
            }
        } catch (Exception e) {
        	ActionBase.log.error(e);
        }
    }
	 protected static void closeConn(Connection conn) {
	        try{
	            if(conn != null) {
	                conn.close();
	                conn = null;
	            }
	        } catch (Exception e) {
	        	ActionBase.log.error(e);
	        }
	    }
}