package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ToModifyForumServlet")
public class ToModifyForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String guestbook_id = request.getParameter("guestbook_id");
        String user_name = "";
        String guestbook_title = "";
        String guestbook_content = "";

        HttpSession session = request.getSession();
        session.setAttribute("username", user_name);
        session.setAttribute("guestbook_title", guestbook_title);
        session.setAttribute("guestbook_content", guestbook_content);

        request.getRequestDispatcher("modify-forum.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
