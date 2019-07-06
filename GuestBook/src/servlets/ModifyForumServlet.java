package servlets;

import beans.GuestBook;
import dao.GuestBook_dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyForumServlet")
public class ModifyForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String guestBook_id = (String) request.getSession().getAttribute("guestBook_id");
        String guestBook_title = request.getParameter("guestBook_title");
        String guestBook_content = request.getParameter("guestBook_content");
        String username = (String) request.getSession().getAttribute("username");


        String toast;

        if (guestBook_title == "") {
            toast = "标题不能为空";
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("modify-forum.jsp").forward(request, response);
        } else if (guestBook_content == "") {
            toast = "内容不能为空";
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("modify-forum.jsp").forward(request, response);
        } else if (username == "") {
            toast = "未登录，无法访问";
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            GuestBook guestBook = new GuestBook();

            guestBook.setGuestbook_id(guestBook_id);
            guestBook.setGuestbook_title(guestBook_title);
            guestBook.setGuestbook_content(guestBook_content);
            guestBook.setUser_name(username);

            GuestBook_dao guestBook_dao = new GuestBook_dao();
            try {
                guestBook_dao.modifyGuestBook(guestBook);
                toast = "修改成功";
                request.setAttribute("toast", toast);
                request.getRequestDispatcher("LookForumServlet").forward(request, response);
            } catch (Exception e) {
                toast = "修改失败";
                request.setAttribute("toast", toast);
                request.getRequestDispatcher("LookForumServlet").forward(request, response);
                e.printStackTrace();
            }
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
