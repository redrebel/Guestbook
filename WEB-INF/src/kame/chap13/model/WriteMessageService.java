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
		
	public 
}