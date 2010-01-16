/**
 * 
 */
package com.wissen.loginservapp.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author wissen19
 * this servlet is meant for responding for the request send by sign up form for 
 * checking availability of login name
 */
public class LoginAvail extends HttpServlet {

    private static final String dbClassName = "com.mysql.jdbc.Driver";

    private static final String CONNECTION  = "jdbc:mysql://127.0.0.1/test";

    public void init() throws ServletException {
        // TODO Auto-generated method stub

        super.init();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        /*
         * Write the HTML to the response
         */
        System.out.println("Check Const invoked");
        String result = new String();
        String log_nm = req.getParameter("login_name");
        try {
            result = this.check(log_nm);
        } catch (Exception e) {
            // TODO Auto-generated catch bTestHellolock
            out.println("Db access problem or login already exist");
        }
        if (result.equalsIgnoreCase("Operation Success")) {
            out.println("Login available");
        } else {
            out.println("Login Name already exist ");
        }
        out.close();

    }

    public String check(String log_nm) throws ClassNotFoundException, SQLException {
        System.out.println(dbClassName);
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "wissen");
        try {
            Connection c = DriverManager.getConnection(CONNECTION, p);
            Statement stmt = c.createStatement();
            ResultSet rs;
            String sql = "select log_name from user where log_name='" + log_nm + "' ";
            rs = stmt.executeQuery(sql);
            rs.next();
            String lastName = rs.getString("log_name");
            if (lastName != null) {
                c.close();
                stmt.close();
                return "Operation Failed";
            }
            c.close();
            stmt.close();
            return "Operation Failed";
        } catch (Exception ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return "Operation Success";
        }

    }

}
