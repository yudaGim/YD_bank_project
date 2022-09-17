package service;

import java.sql.SQLException;
import java.util.List;

import dto.Account;
import dto.AccountState;

public class AccountServiceImpl implements AccountService {

	/**
	 * 계좌 개설
	 * @param: Account
	 * @return: Account
	 * */
	@Override
	public Account insertAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 계좌 상태 변경
	 * @param: Account
	 * @return: int(1일 경우 성공, 아닐 경우 실패)
	 * */
	@Override
	public int updateAccountState(Account account) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 전체 계좌 검색
	 * @return: List<Account>
	 * */
	@Override
	public List<Account> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 계좌 번호로 계좌 검색
	 * @param: String accountId
	 * @return: Account
	 * */
	@Override
	public Account findByAccountid(String accountId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 회원 아이디로 계좌 검색
	 * @param: String id, boolean state(true일 경우 사용 중 계좌만, false일 경우 모든 계좌)
	 * @return: List<Account>
	 * */
	@Override
	public List<Account> findById(String id, boolean state) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 계좌 상태 아이디로 계좌 상태 검색
	 * @param: int stateId
	 * @return: AccountState
	 * */
	@Override
	public AccountState findByStateId(int stateId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}