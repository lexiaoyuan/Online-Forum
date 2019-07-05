package dao;

import beans.GuestBook;
import dbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<GuestBook> lookGuestBook (GuestBook guestBook) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GuestBook> guestBookList=new ArrayList<GuestBook>();
        try {
            conn = JdbcUtil.getConnection();
            String lookGuestBookSql = "select * from guestbook";
            ps = conn.prepareStatement(lookGuestBookSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GuestBook guestBook1 = new GuestBook();
                guestBook1.setUser_name(rs.getString(2));
                guestBook1.setGuestbook_title(rs.getString(3));
                guestBook1.setGuestbook_content(rs.getString(4));
                guestBook1.setGuestbook_date(rs.getString(5));
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
