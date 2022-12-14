package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dto.Account;
import dto.AccountState;
import util.DbUtil;

public class AccountDaoImpl implements AccountDao {
	private Properties profile = DbUtil.getProfile();

	/**
	 * 계좌 개설
	 * @param: String id
	 * @return: int
	 * */
	@Override
	public int save(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = profile.getProperty("account.save");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * 계좌 출금 처리
	 * @param: Connection con, int accountId, Long amount
	 * @return: int
	 * */
	@Override
	public int withdraw(Connection con, int accountId, Long amount) throws SQLException {
		PreparedStatement ps = null;
		
		String sql = profile.getProperty("account.withdraw");
		int result = 0;
				
		try {
			ps = con.prepareStatement(sql);
			ps.setLong(1, amount);
			ps.setInt(2, accountId);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}

	/**
	 * 계좌 입금 처리
	 * @param: Connection con, int accountId, Long amount
	 * @return: int
	 * */
	@Override
	public int deposit(Connection con, int accountId, Long amount) throws SQLException {
		PreparedStatement ps = null;
		
		String sql = profile.getProperty("account.deposit");
		int result = 0;
				
		try {
			ps = con.prepareStatement(sql);
			ps.setLong(1, amount);
			ps.setInt(2, accountId);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}

	/**
	 * 계좌 상태 변경
	 * @param: Account
	 * @return: int(1일 경우 성공, 아닐 경우 실패)
	 * */
	@Override
	public int updateClose(Account account) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = profile.getProperty("account.updateClose");
		int result = 0;
				
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, account.getAccountId());
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}

	/**
	 * 전체 계좌 검색
	 * @return: List<Account>
	 * */
	@Override
	public List<Account> findAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = profile.getProperty("account.findAll");
		List<Account> accounts = new ArrayList<>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Account account = new Account(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getString(5), rs.getString(6));
				accounts.add(account);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return accounts;
	}

	/**
	 * 계좌 번호로 계좌 검색
	 * @param: int accountId
	 * @return: Account
	 * */
	@Override
	public Account findByAccountid(int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = profile.getProperty("account.findByAccountid");
		Account account = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, accountId);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				account = new Account(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getString(5), rs.getString(6));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return account;
	}

	/**
	 * 회원 아이디로 계좌 검색
	 * @param: String id, boolean state(true일 경우 사용 중 계좌만, false일 경우 모든 계좌)
	 * @return: List<Account>
	 * */
	@Override
	public List<Account> findById(String id, boolean state) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = null;
		if (state) {			
			sql = profile.getProperty("account.findByIdTrue");
		} else {
			sql = profile.getProperty("account.findByIdFalse");
		}
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Account account = new Account(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getString(5), rs.getString(6));
				accounts.add(account);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return accounts;
	}

	@Override
	public AccountState findByStateId(int stateId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = profile.getProperty("account.findByStateId");
		AccountState accountState = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, stateId);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				accountState = new AccountState(rs.getInt(1), rs.getString(2));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return accountState;
	}
}