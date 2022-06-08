package com.masai.service;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.DTO.AddMoneyDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.entity.BankAccount;
import com.masai.entity.Customer;
import com.masai.entity.Transaction;
import com.masai.entity.UserSession;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

@Service
public class CustomerServiceImpl implements customerServiceIntr{
	
	@Autowired
	private CustomerDao wdo;
	@Autowired
	private UserSessionDao user;
//	@Autowired
//	private WalletDaoJpa wdj;
//	
//	@Autowired
//	private TransactionDao trans;
	@Autowired
	private BankAccountRepo bankR;
	
	public Customer createAcc(Customer cs)throws CustomerNotFoundException  {
		// TODO Auto-generated method stub
		Optional<Customer> opt=wdo.findById(cs.getMobileNumber());
		
		if(opt.isPresent()){
			throw new CustomerNotFoundException("Customer is present already with this mobile number : "+cs.getMobileNumber());
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
	public CustomerDTO showBlacnce(String mobile) throws CustomerNotFoundException {
			Optional<Customer> cust= wdo.findById(mobile);
		
		return wdo.viewBalance(cust.get().getWallet().getId(),cust.get().getWallet().getId());
		
		//return wdo.findById(mobile).orElseThrow(()->new );
	}


	@Override
	public Customer depositAmount(String mobile, Double amount) throws CustomerNotFoundException {
		//System.out.println(mobile+""+amount);
		    Optional<Customer> opt=wdo.findById(mobile);
		    if(opt.isPresent()==false) {
		    	throw new CustomerNotFoundException("Customer not found with this : "+mobile);
		    }
		    
		    Customer cs=opt.get();
		    Wallet wlt=cs.getWallet();
		    wlt.setBalance(wlt.getBalance()+amount);
		    Transaction tr=new Transaction();
		    tr.setAmount(amount);
		    tr.setDescription("Deposit");
		    tr.setTransactionType("Credit");
		    tr.setDateTime(LocalDateTime.now());
		    wlt.getTran().add(tr);
		    cs.setWallet(wlt);
		return wdo.save(cs);
	}


	@Override
	public String fundTransfer(String mobileNo, String targetMobileNo, Double amount) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Customer getListCustomer(String key) throws CustomerNotFoundException {
		
		UserSession userS=user.findByUuid(key);	
		
		
		return wdo.findById(userS.getMobile()).get();
	}


	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> opt=wdo.findById(customer.getMobileNumber());
		
		if(opt.isPresent()==false) {
			throw new CustomerNotFoundException("Customer not found");
		}
		Customer cs=opt.get();
		cs.setMobileNumber(customer.getMobileNumber());
		cs.setName(customer.getName());
		cs.setPassword(customer.getPassword());
		return wdo.save(cs);
	}
	@Override
	public Customer addMoney(AddMoneyDTO addmoney,String mobile) throws CustomerNotFoundException {
		
		Optional<BankAccount> bank=bankR.findById(addmoney.getAccountaccountNo());
		
		Optional<Customer> customer=wdo.findById(mobile);
		
		BankAccount bankAccount=bank.get();
		
		if(bankAccount.getWallet().getId()!=customer.get().getWallet().getId())
			throw new CustomerNotFoundException("Your Bank Account Does Not Exits to Your Wallet");
		
		if(bankAccount.getBankBalance()<addmoney.getBalance())
			throw new CustomerNotFoundException("Not Enough Balance to your Account");
			
		bankAccount.setBankBalance(bankAccount.getBankBalance()-addmoney.getBalance());
		bankR.save(bankAccount);
		  customer.get().getWallet().setBalance(customer.get().getWallet().getBalance()+addmoney.getBalance());
		  
		  Transaction tran=new Transaction();
		  tran.setAmount(addmoney.getBalance());
		  tran.setDateTime(LocalDateTime.now());
		  tran.setTransactionType("Credit");
		  tran.setDescription("Credited by "+bankAccount.getBankName());
		  customer.get().getWallet().getTran().add(tran);
		 
		return  wdo.save(customer.get());
	}


}
