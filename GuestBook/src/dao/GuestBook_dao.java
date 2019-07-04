package dao;

import beans.GuestBook;
import dbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GuestBook_dao {
    public GuestBook addGuestBook (GuestBook guestBook) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String addGuestBookSql = "insert into guestbook values(guestbook_id_seq.nextval, ?, ?, ?, SYSDATE)";
            ps = conn.prepareStatement(addGuestBookSql);
            ps.setString(1, guestBook.getUser_name());
            ps.setString(2, guestBook.getGuestbook_title());
            ps.setString(3, guestBook.getGuestbook_content());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
        return guestBook;
    }

}
