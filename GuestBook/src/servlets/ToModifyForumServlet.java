package servlets;

import beans.GuestBook;
import dao.SearchGuestBook_dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ToModifyForumServlet")
public class ToModifyForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String guestbook_id = request.getParameter("guestbook_id");

        GuestBook guestBook = new GuestBook();
        SearchGuestBook_dao searchGuestBook_dao = new SearchGuestBook_dao();


        try {
            guestBook.setGuestbook_id(guestbook_id);
            List<GuestBook> guestBookList;
            guestBookList = searchGuestBook_dao.searchById(guestBook);

            HttpSession session = request.getSession();
            session.setAttribute("guestBook_id", guestbook_id);
            session.setAttribute("username", guestBookList.get(0).getUser_name());
            session.setAttribute("guestBook_title", guestBookList.get(0).getGuestbook_title());
            session.setAttribute("guestBook_content", guestBookList.get(0).getGuestbook_content());

            request.getRequestDispatcher("modify-forum.jsp").forward(request, response);

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
