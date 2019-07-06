package dao;

import beans.Reply;
import dbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Reply_dao {
    public Reply addReply (Reply reply) throws Exception {
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
}
