package kame.chap13.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kame.chap13.dao.MessageDao;
import kame.chap13.model.Message;
import kame.jdbc.JdbcUtil;

public class OracleMessageDao extends MessageDao{
	public int insert(Connection conn, Message message) throws SQLException{
		PreparedStatment pstmt = null;
		try{
			pstmt = conn.prepareStatment(
				"insert into guestbook_message " +
				"(guest_name, password, message) values (?, ?, ?)");
			pstmt.setString(1, messsge.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		} finally {
			jdbcUtil(pstmt);
		}
	}
	
	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
				"select * from guestbook_message " +
				"order by message_id desc limit ?, ?");
			pstmt.setInt(1, firstRow - 1);
			pstmt.setInt(2, endRow - firstRow + 1);
			rs = pstmt.executeQuery();
			if(rs.next()){
				List<Message> messageList = new ArrayList<Message>();
				do {
					messageList.add(super.makeMessageFromResultSet(rs));
				} while (rs.next());
			} else {
				return Collections.emptyList();
			}
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
	}
}