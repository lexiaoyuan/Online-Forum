package dao;

import beans.UserInfo;
import dbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register_dao {
    public UserInfo addUserInfo(UserInfo userInfo) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String addUserInfoSql = "insert into user_info values(?, ?)";
            ps = conn.prepareStatement(addUserInfoSql);
            ps.setString(1, userInfo.getUsername());
            ps.setString(2, userInfo.getUserpwd());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

}
