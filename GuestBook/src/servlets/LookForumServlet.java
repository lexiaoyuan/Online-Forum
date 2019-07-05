package servlets;

import beans.GuestBook;
import dao.GuestBook_dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "LookForumServlet")
public class LookForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        GuestBook guestBook = new GuestBook();
        GuestBook_dao guestBook_dao = new GuestBook_dao();
        try {
            List<GuestBook> guestBookList;
            guestBookList = guestBook_dao.lookGuestBook(guestBook);
            Collections.reverse(guestBookList);
            request.setAttribute("guestBookList", guestBookList);
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
