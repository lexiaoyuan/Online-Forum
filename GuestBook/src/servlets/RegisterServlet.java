package servlets;

import beans.UserInfo;
import dao.Register_dao;
import dbc.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置统一编码
        request.setCharacterEncoding("UTF-8");
        //获取用户名和密码
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        String userpwdConfirm = request.getParameter("userpwdConfirm");

        String toast;

        //创建数据库连接和执行对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //HttpSession session = request.getSession();

        UserInfo userInfo = new UserInfo();
        Register_dao register_dao = new Register_dao();

        try {
            if (username =="") {
                toast = "用户名不能为空";
                request.setAttribute("toast", toast);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (userpwd == "") {
                toast = "密码不能为空";
                request.setAttribute("toast", toast);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (userpwdConfirm =="" ) {
                toast = "确认密码不能为空";
                request.setAttribute("toast", toast);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (!userpwd.equals(userpwdConfirm)) {
                toast = "两次密码不同";
                request.setAttribute("toast", toast);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            else {
                conn = JdbcUtil.getConnection();
                String checkRegisterSql = "select user_name from user_info where user_name = ?";
                ps = conn.prepareStatement(checkRegisterSql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    userInfo.setUsername(username);
                    userInfo.setUserpwd(userpwd);
                    register_dao.addUserInfo(userInfo);
                    toast = "注册成功";
                    request.setAttribute("toast", toast);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    toast = "该用户名已注册";
                    request.setAttribute("toast", toast);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            toast = "注册失败";
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
