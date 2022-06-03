package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Customer;
import com.masai.entity.Transaction;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import com.masai.repository.TransactionDao.TransactionDao;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.walletDao.WalletDaoJpa;

public class WalletService implements WalletServiceIRT{

	@Autowired
	private CustomerDao wdo;
	@Autowired
	private WalletDaoJpa wdj;
	
	@Autowired
	private TransactionDao trans;
	
	public Customer createAccount(Customer cs)throws CostumerNotFoundException  {
		// TODO Auto-generated method stub
		Optional<Customer> opt=wdo.findById(cs.getMobileNumber());
		
		if(opt.isPresent()){
			throw new CostumerNotFoundException("Customer is present already with this mobile number : "+cs.getMobileNumber());
		}
		Transaction tr=new Transaction();
		tr.setAmount(cs.getWallet().getBalance());
		tr.setDescription("Opening Balance");
		tr.setDateTime(LocalDateTime.now());
		tr.setTransactionType("Credit");
		cs.getWallet().getTran().add(tr);
		return wdo.save(cs);
	}


	@Override
	public Customer showBlacnce(String mobile) throws CostumerNotFoundException {
		//System.out.println("2"+mobile);
		
		return wdo.findById(mobile).orElseThrow(()-> new CostumerNotFoundException("Customer not found with this : "+mobile));
		
		//return wdo.findById(mobile).orElseThrow(()->new );
	}


	@Override
	public Customer depositAmount(String mobile, Double amount) throws CostumerNotFoundException {
		//System.out.println(mobile+""+amount);
		    Optional<Customer> opt=wdo.findById(mobile);
		    if(opt.isPresent()==false) {
		    	throw new CostumerNotFoundException("Customer not found with this : "+mobile);
		    }
		    
		    Customer cs=opt.get();
		    Wallet wlt=cs.getWallet();
		    wlt.addBalance(amount);
		    Transaction tr=new Transaction();
		    tr.setAmount(amount);
		    tr.setDescription("Deposit");
		    tr.setTransactionType("Credit");
		    tr.setDateTime(LocalDateTime.now());
		    //wlt.getTran().add(tr);
		    cs.setWallet(wlt);
		    
		    
		    trans.save(tr);
		    wdj.save(wlt);
		return wdo.save(cs);
	}


	@Override
	public String fundTransfer(String mobileNo, String targetMobileNo, Double amount) throws Exception {
		// TODO Auto-generated method stub
		Customer cs=wdo.getByMobileNumber(mobileNo);
		Customer csTarget=wdo.getByMobileNumber(mobileNo);
		
		Wallet w=cs.getWallet();
		Wallet wTarget=csTarget.getWallet();
		
		wTarget.addBalance(w.deductBalance(amount));
		wdj.save(w);
		wdj.save(wTarget);
//		Transaction tr=new Transaction();
//	    tr.setAmount(amount);
//	    tr.setDescription("Deposit");
//	    tr.setTransactionType("Credit");
//	    tr.setDateTime(LocalDateTime.now());
//	    trans.save(tr);
		return "transaction successfull";
	}


	@Override
	public List<Customer> getListCustomer() throws CostumerNotFoundException {
		
		return wdo.findAll();
	}


	@Override
	public Customer updateCustomer(Customer customer) throws CostumerNotFoundException {
		Optional<Customer> opt=wdo.findById(customer.getMobileNumber());
		
		if(opt.isPresent()==false) {
			throw new CostumerNotFoundException("Customer not found");
		}
		Customer cs=opt.get();
		cs.setMobileNumber(customer.getMobileNumber());
		cs.setName(customer.getName());
		cs.setPassword(customer.getPassword());
		return wdo.save(cs);
	}


	@Override
	public Customer addMoney(Wallet wallet, Double amount) throws CostumerNotFoundException {
		wallet.addBalance(amount);
		return wdo.getByWallet(wallet.getId());
	}

	

}
