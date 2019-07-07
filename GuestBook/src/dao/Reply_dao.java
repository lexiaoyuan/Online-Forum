package dao;

import beans.Reply;
import dbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Reply_dao {
    public Reply addReply(Reply reply) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String addGuestBookSql = "insert into reply values(reply_id_seq.nextval, ?, ?, ?, ?, SYSDATE)";
            ps = conn.prepareStatement(addGuestBookSql);
            ps.setString(1, reply.getGuestbook_id());
            ps.setString(2, reply.getHost_user_name());
            ps.setString(3, reply.getGuest_user_name());
            ps.setString(4, reply.getReply_content());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }

        return reply;
    }

    public List<Reply> showReply() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reply> replyList = new ArrayList<>();
        try {
            conn = JdbcUtil.getConnection();
            String showReplySql = "select * from reply order by guestbook_id,reply_date desc";
            ps = conn.prepareStatement(showReplySql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reply reply1 = new Reply();
                reply1.setGuestbook_id(rs.getString(2));
                reply1.setHost_user_name(rs.getString(3));
                reply1.setGuest_user_name(rs.getString(4));
                reply1.setReply_content(rs.getString(5));
                reply1.setReply_date(rs.getString(6));
                replyList.add(reply1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }

        return replyList;
    }
}
