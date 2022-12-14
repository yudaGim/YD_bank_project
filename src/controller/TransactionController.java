package controller;

import java.util.ArrayList;
import java.util.List;

import dao.CustomerDaoImpl;
import dto.Customer;
import dto.Transaction;
import exception.DoNotLoginException;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.TransactionService;
import service.TransactionServiceImpl;
import session.Session;
import view.FailView;
import view.SuccessView;

public class TransactionController {
	private TransactionService transactionService = new TransactionServiceImpl();
	private Session session = Session.getInstance();
	
	public void insertTransaction(Transaction transaction) {
		try {
			Customer customer = (Customer) session.getAttribute("loginUser");
			if (customer == null) throw new DoNotLoginException("로그인 후 이용해주세요.");
			transaction.setCustomer(customer);
			transactionService.insertTransaction(transaction);
			SuccessView.printMessage("거래가 완료되었습니다.");
			SuccessView.printTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.printErrorMessage(e);
		}
	}
	
	public void findAll() {
		try {
			List<Transaction> transactions = transactionService.findAll();
			SuccessView.printfindTransactionAll(transactions);
		} catch (Exception e) {
//			e.printStackTrace();
			FailView.printErrorMessage(e);
		}
	}
	
	public void findById(String id) {
		try {
			Customer customer = null;
			if (id == null) {
				customer = (Customer) session.getAttribute("loginUser");
				if (customer == null) throw new DoNotLoginException("로그인 후 이용해주세요.");
				id = customer.getId();
			} else {
				customer = new CustomerDaoImpl().findById(id);
			}
			List<Transaction> transactions = transactionService.findById(id);
			SuccessView.printTransactionsFindById(customer, transactions);
		} catch (Exception e) {
			FailView.printErrorMessage(e);
		}
	}
	
	public void findByToday() {
		try {
			List<Transaction> transactions = transactionService.findByToday();
			SuccessView.printFindTransactionByToday(transactions);
		} catch (Exception e) {
			FailView.printErrorMessage(e);
		}
	}
	
	public void findByTransactionId(int transactionId) {
		try {
			List<Transaction> transactions = new ArrayList<>();
			Transaction transaction = transactionService.findByTransactionId(transactionId);
			if (transaction != null) transactions.add(transaction);
			SuccessView.printTransactionsFindByTransactionId(transactionId, transactions);
		} catch (Exception e) {
			FailView.printErrorMessage(e);
		}
	}
	
	public void findByAccountId(int accountId) {
		try {
			List<Transaction> transactions = transactionService.findByAccountId(accountId);
			SuccessView.printTransactionsFindByAccountId(accountId, transactions);
		} catch (Exception e) {
			// TODO: handle exception
			FailView.printErrorMessage(e);
		}
	}
}
