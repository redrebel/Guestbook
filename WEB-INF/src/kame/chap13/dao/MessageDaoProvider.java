package kame.chap13.dao;

import kame.chap13.dao.mssql.MSSQLMessageDao;
import kame.chap13.dao.mysql.MySQLMessageDao;
import kame.chap13.dao.oracle.OracleMessageDao;

public class MessageDaoProvider {
	private static MessageDaoProvider instance = new MessageDaoProvider();
	public static MessageDaoProvider getInstance(){
		return instance;
	}
	private MySQLMessageDao mysqlDao = new MySQLMessageDao();
	private OracleMessageDao oracleDao = new OracleMessageDao();
	private MSSQLMessageDao mssqlDao = new MSSQLMessageDao();
	private String dbms;
	
	void setDbms(String dbms)
	{
		this.dbms = dbms;
	}
	
	public MessageDao getMessageDao() {
		if("oracle".equals(dbms)) {
			return oracleDao;
		} else if ("mysql".equals(dbms)) {
			return mysqlDao;
		} else if ("mssql".equals(dbms)) {
			return mssqlDao;
		}
		return null;
	}
}