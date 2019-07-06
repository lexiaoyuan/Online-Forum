package dao;

import beans.GuestBook;
import dbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchGuestBook_dao {
    //通过guestbook_id查询留言
    public List<GuestBook> searchById (GuestBook guestBook) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GuestBook> guestBookList=new ArrayList<>();
        try {
            conn = JdbcUtil.getConnection();
            String lookGuestBookSql = "select * from guestbook where guestbook_id = ?";
            ps = conn.prepareStatement(lookGuestBookSql);
            ps.setString(1, guestBook.getGuestbook_id());
            rs = ps.executeQuery();
            while (rs.next()) {
                GuestBook guestBook1 = new GuestBook();
                guestBook1.setUser_name(rs.getString(2));
                guestBook1.setGuestbook_title(rs.getString(3));
                guestBook1.setGuestbook_content(rs.getString(4));
                guestBookList.add(guestBook1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return guestBookList;
    }
}
