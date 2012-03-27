package kame.chap13.service;

import java.sql.Connection;
import java.sql.SQLException;

import kame.chap13.dao.MessageDao;
import kame.chap13.dao.MessageDaoProvider;
import kame.chap13.model.Message;
import kame.jdbc.JdbcUtil;
import kame.jdbc.connection.ConnectionProvider;

public class DeleteMessageService {
	private static DeleteMessageService instance =
			new DeleteMessageService();
	public static DeleteMessageService getInstance() {
		return instance;
	}
	
	private DeleteMessageService(){
	}
	
	public void deleteMessage(int messageId, String password)
			throws ServiceException, InvalidMessagePasswordException, MessageNotFoundException {
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MessageDao messageDao = MessageDaoProvider.getInstance().getMessageDao();
			Message message = messageDao.select(conn, messageId);
			if(message == null) {
				throw new MessageNotFoundException("�޽����� �����ϴ�:" + messageId);
			}
			
			if(!message.hasPassword()) {
				throw new InvalidMessagePasswordException("�޽����� ��ȣ�� ������ ���� �ʽ��ϴ�");
			}
			
			if(!message.getPassword().equals(password)) {
				throw new InvalidMessagePasswordException("�޽��� ��ȣ�� �ٸ��ϴ�.");
			}
			
			messageDao.delete(conn, messageId);
			
			conn.commit();
		} catch (SQLException ex) {
			JdbcUtil.rollback(conn);
			throw new ServiceException("���� ó�� �� ������ �߻��߽��ϴ�.:" 
					+ ex.getMessage(),ex);
		} catch (InvalidMessagePasswordException ex) {
			JdbcUtil.rollback(conn);
			throw ex;
		} catch (MessageNotFoundException ex) {
			JdbcUtil.rollback(conn);
			throw ex;
		} finally {
			if(conn != null) {
				try{
					conn.setAutoCommit(false);
				} catch (SQLException e) {
				}
				JdbcUtil.close(conn);
			}
		}
	}
}