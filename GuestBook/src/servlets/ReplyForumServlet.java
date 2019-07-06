package servlets;

import beans.GuestBook;
import beans.Reply;
import dao.Reply_dao;
import dao.SearchGuestBook_dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReplyForumServlet")
public class ReplyForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String replyText = request.getParameter("replyText");
        String guestBookId = request.getParameter("guestbook_id");
        String hostUsername = (String) request.getSession().getAttribute("username");

        String toast;

        if (replyText == "") {
            toast = "回复内容不能为空";
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("look-forum.jsp").forward(request, response);
        } else if (hostUsername == "") {
            toast = "未登录，无法访问";
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            GuestBook guestBook = new GuestBook();
            SearchGuestBook_dao searchGuestBook_dao = new SearchGuestBook_dao();
            Reply reply = new Reply();

            try {
                guestBook.setGuestbook_id(guestBookId);
                List<GuestBook> guestBookList = new ArrayList<>();
                guestBookList = searchGuestBook_dao.searchById(guestBook);

                reply.setGuestbook_id(guestBookId);
                reply.setReply_content(replyText);
                reply.setGuest_user_name(guestBookList.get(0).getUser_name());   //发留言的用户名
                reply.setHost_user_name(hostUsername);  //执行回复操作的用户名

                Reply_dao reply_dao = new Reply_dao();
                try {
                    reply_dao.addReply(reply);
                    toast = "回复成功";
                    request.setAttribute("toast", toast);

                    List<Reply> replyList;
                    replyList = reply_dao.showReply(reply);
                    request.setAttribute("replyList", replyList);

                    request.getRequestDispatcher("LookForumServlet").forward(request, response);
                } catch (Exception e) {
                    toast = "回复失败";
                    request.setAttribute("toast", toast);
                    request.getRequestDispatcher("LookForumServlet").forward(request, response);
                    e.printStackTrace();
                }

            } catch (Exception e) {
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
