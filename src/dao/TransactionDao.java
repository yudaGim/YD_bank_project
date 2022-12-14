package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dto.Transaction;

public interface TransactionDao {
	/**
	 * 거래 생성
	 * @param: Transaction
	 * @return: int
	 * */
	public int save(Transaction transaction) throws SQLException;
	
	/**
	 * 전체 거래 검색
	 * @return: List<Transaction>
	 * */
	public List<Transaction> findAll() throws SQLException;
	
	/**
	 * 회원별 거래 검색
	 * @param: String id(회원 아이디)
	 * @return: List<Transaction>
	 * */
	public List<Transaction> findById(String id) throws SQLException;
	
	/**
	 * 계좌별 거래 검색
	 * @param: int accountId(계좌 번호)
	 * @return: List<Transaction>
	 * */
	public List<Transaction> findByAccountId(int accountId) throws SQLException;
	
	/**
	 * 거래 아이디로 거래 검색
	 * @param: int transactionId(거래 번호)
	 * @return: Transaction
	 * */
	public Transaction findByTransactionId(int transactionId) throws SQLException;
	
	/**
	 * 오늘 거래 검색
	 * @return: List<Transaction>
	 * */
	public List<Transaction> findByToday() throws SQLException;
}
