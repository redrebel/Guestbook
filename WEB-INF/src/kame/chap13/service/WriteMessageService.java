package kame.chap13.service;

import java.sql.Connection;
import java.sql.SQLException;


import kame.chap13.dao.MessageDao;
import kame.chap13.dao.MessageDaoProvider;
import kame.chap13.model.Message;
import kame.jdbc.JdbcUtil;
import kame.jdbc.connection.ConnectionProvider;

public class WriteMessageService {
	private static WriteMessageService instance = 
		new WriteMessageService();
		
	public static WriteMessageService getInstance() {
		return instance;
	}
	
	private WriteMessageService(){
	}
	
	public void write(Message message) throws ServiceException {
		Connection conn = null;
		
		if(message.getGuestName() == null || message.getGuestName().isEmpty()){
			throw new IllegalArgumentException("invalid guest name");
		} else if(!message.hasPassword()){
			throw new IllegalArgumentException("invalid password");

		}
		try{
			conn = ConnectionProvider.getConnection();
			MessageDao messageDao = 
				MessageDaoProvider.getInstance().getMessageDao();
			messageDao.insert(conn, message);
		} catch (SQLException e) {
		} finally {
			JdbcUtil.close(conn);
		}
	}
}