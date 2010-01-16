/**
 * 
 */
package com.wissen.loginservapp.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

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
 * this servlet is meant for checking login and forwarding the 
 * validated request to jsp page else show error message
 */
public class LoginValServlet extends HttpServlet {

    private static final String dbClassName = "com.mysql.jdbc.Driver";

    private static final String CONNECTION  = "jdbc:mysql://127.0.0.1/test";

    public void init() throws ServletException {
        // TODO Auto-generated method stub

        super.init();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String log_nm = req.getParameter("log_name");
        String pass = req.getParameter("pass");
        System.out.println("login " + log_nm + " pass" + pass);
        String attrib[] = new String[6];
        try {
            attrib = check(log_nm, pass);
        } catch (Exception e) {
            PrintWriter out = res.getWriter();
            out.println("Internal Error : " + e);
            out.close();
        }
        if (attrib[0] != null) {
            req.setAttribute("log_name", log_nm);
            req.setAttribute("fname", attrib[0]);
            req.setAttribute("lname", attrib[1]);
            req.setAttribute("email", attrib[2]);
            req.setAttribute("ques", attrib[3]);
            req.setAttribute("ans", attrib[4]);
            req.setAttribute("country", attrib[5]);
            System.out.println("valid invoked");
            RequestDispatcher rd = req.getRequestDispatcher("/Operation.jsp");
            rd.forward(req, res);//forwarding request to jsp page
        } else {
            PrintWriter out = res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("</head>");
            out.println("<body>");
            out
                    .println("<h1>"
                            + "Error Reported-Login Failed<br><a href=\"http://localhost:8090/LoginServApp/Login.html\">Click here for login</a>"
                            + "</h1>");
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

    }

    //method to check the login if exist
    public String[] check(String log_nm, String pass) throws ClassNotFoundException, SQLException {
        System.out.println(dbClassName);
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "wissen");
        String attrib[] = new String[6];
        try {
            Connection c = DriverManager.getConnection(CONNECTION, p);
            Statement stmt = c.createStatement();
            ResultSet rs;
            String sql = "select * from user where log_name='" + log_nm + "' && pass='" + pass + "' ";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                attrib[0] = rs.getString("fname");
                attrib[1] = rs.getString("lname");
                attrib[2] = rs.getString("email");
                attrib[3] = rs.getString("ques");
                attrib[4] = rs.getString("ans");
                attrib[5] = rs.getString("country");
                c.close();
                stmt.close();
                return attrib;
            }
            c.close();
            stmt.close();
            return attrib;
        } catch (Exception ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return attrib;
        }

    }

}