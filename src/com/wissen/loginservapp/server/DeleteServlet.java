package com.wissen.loginservapp.server;

/**
 * 
 */

/**
 * @author wissen19
 *
 */
/**
 * 
 */
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wissen19
 * 
 * this servlet is meant for deleting and updating the details of the user
 * by checking if fnm(first name is null or not )
 */
public class DeleteServlet extends HttpServlet {

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
        String result = new String();
        String fnm = req.getParameter("first_name");
        String lnm = req.getParameter("second_name");
        String log_nm = req.getParameter("login_name");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");
        String ques = req.getParameter("ques");
        String ans = req.getParameter("ans");
        String country = req.getParameter("country");
        if (fnm != null) {//this is for update operation
            try {
                result = this.update(lnm, fnm, email, pass, log_nm, ques, ans, country);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + "Error Reported-server side" + "</h1>");
                out.println("<br>" + e.getMessage());
                out.println("</body>");
                out.println("</html>");
            }
            if (result.equalsIgnoreCase("Operation Success")) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Success</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + "Operation Success<br><a href=\"http://localhost:8090/LoginServApp/Login.html\">Click here for login</a> "
                        + "</h1>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out
                        .println("<h1>"
                                + "Error Reported-db side <br><a href=\"http://localhost:8090/LoginServApp/Login.html\">Click here for login</a>"
                                + "</h1>");
                out.println("<br>" + result);
                out.println("</body>");
                out.println("</html>");
            }
            out.close();
        } else {//this is for delete operation as refering operation.jsp there is no value for login name for delete form
            try {
                result = this.delete(log_nm);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + "Error Reported-server side" + "</h1>");
                out.println("<br>" + e.getMessage());
                out.println("</body>");
                out.println("</html>");
            }
            if (result.equalsIgnoreCase("Operation Success")) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Success</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + "Operation Success<br><a href=\"http://localhost:8090/LoginServApp/Login.html\">Click here for login</a> "
                        + "</h1>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out
                        .println("<h1>"
                                + "Error Reported-db side <br><a href=\"http://localhost:8090/LoginServApp/Login.html\">Click here for login</a>"
                                + "</h1>");
                out.println("<br>" + result);
                out.println("</body>");
                out.println("</html>");
            }
            out.close();
        }
    }

     //this method is for update
    public String update(String lnm, String fnm, String email, String pass, String log_nm, String ques, String ans, String country)
            throws ClassNotFoundException, SQLException {
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "wissen");
        try {
            Connection con = DriverManager.getConnection(CONNECTION, p);
            Statement stmt = con.createStatement();
            String updateString1 = "update user set fname='" + fnm + "',lname='" + lnm + "',log_name='" + log_nm + "',pass='" + pass + "',email='"
                    + email + "',ques='" + ques + "',ans='" + ans + "',country='" + country + "' where log_name='" + log_nm + "' ";
            System.out.println(updateString1);
            stmt = con.createStatement();
            stmt.executeUpdate(updateString1);
            stmt.close();
            con.close();
            return "Operation Success";

        } catch (Exception ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return "Operation Failed : (login name already exist or empty fields)" + ex.getMessage();
        }

    }

    public String delete(String log_nm) throws ClassNotFoundException, SQLException {
        System.out.println(dbClassName);
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "wissen");
        try {
            Connection con = DriverManager.getConnection(CONNECTION, p);
            Statement stmt = con.createStatement();
            String insertString1 = "delete from user  where log_name='" + log_nm + "' ";
            System.out.println(insertString1);
            stmt = con.createStatement();
            stmt.executeUpdate(insertString1);
            stmt.close();
            con.close();
            return "Operation Success";

        } catch (Exception ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return "Operation Failed : (login name already exist or empty fields)" + ex.getMessage();
        }

    }

}
