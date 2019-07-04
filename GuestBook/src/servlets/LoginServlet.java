package servlets;

import dbc.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置统一编码
        request.setCharacterEncoding("UTF-8");
        //获取用户名和密码
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        //创建数据库连接和执行对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        HttpSession session = request.getSession();

        session.setAttribute("username", username);

        try {
            if (username.equals("")) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else if (userpwd.equals("")) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else {
                conn = JdbcUtil.getConnection();
                String checkLoginSql = "select user_name from user_info where user_name = ? and user_pwd = ?";
                ps = conn.prepareStatement(checkLoginSql);
                ps.setString(1, username);
                ps.setString(2, userpwd);
                rs = ps.executeQuery();
                if (rs.next()) {
                    request.getRequestDispatcher("forum.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
