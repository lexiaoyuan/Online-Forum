package servlets;

import beans.GuestBook;
import dao.GuestBook_dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteForumServlet")
public class DeleteForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String guestbook_id = request.getParameter("guestbook_id");

        String toast;

        GuestBook guestBook = new GuestBook();
        GuestBook_dao guestBook_dao = new GuestBook_dao();

        guestBook.setGuestbook_id(guestbook_id);
        try {
            guestBook_dao.deleteGuestBook(guestBook);
            toast = "删除成功";
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("look-forum.jsp").forward(request, response);
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
