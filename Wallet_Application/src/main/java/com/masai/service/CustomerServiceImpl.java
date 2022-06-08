package com.masai.service;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.DTO.AddMoneyDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.DTO.DepositDTO;
import com.masai.entity.BankAccount;
import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.Transaction;
import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.BeneficiaryDetailsDao;
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
	@Autowired
	private BeneficiaryDetailsDao bene;
	
	public Customer createAcc(Customer cs)throws CustomerNotFoundException  {
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
	public Customer depositAmount(DepositDTO deposit, String mobile) throws CustomerNotFoundException {
		
		Optional<Customer> customer =wdo.findById(mobile);
		
		BeneficiaryDetails beneficiary =bene.findByMobileNoAndWalletId(deposit.getMobile(),customer.get().getWallet().getId());
		if(beneficiary==null) 
			throw new CustomerNotFoundException("Beneficiary Not Found in your beneficiary list first add in your list.");
		Optional<Customer> customer2=wdo.findById(beneficiary.getMobileNo());
		Customer cust1=customer.get();
		Customer cust2=customer2.get();
		
		if(cust1.getWallet().getBalance()<deposit.getAmount())
			throw new  CustomerNotFoundException("Balance Not Enough in Your Account");
		
		cust1.getWallet().setBalance(cust1.getWallet().getBalance()-deposit.getAmount());
		cust2.getWallet().setBalance(cust2.getWallet().getBalance()+deposit.getAmount());
		 
		Transaction tran1=new Transaction();
		tran1.setAmount(deposit.getAmount());
		tran1.setDateTime(LocalDateTime.now());
		tran1.setDescription("Transefer Ammount to The "+deposit.getMobile());
		tran1.setTransactionType("Debit");
		
		Transaction tran2=new Transaction();
		tran1.setAmount(deposit.getAmount());
		tran1.setDateTime(LocalDateTime.now());
		tran1.setDescription("Accept Ammount to The "+cust1.getMobileNumber());
		tran1.setTransactionType("Credit");
		
		cust1.getWallet().getTran().add(tran1);
		cust2.getWallet().getTran().add(tran2);
		
		wdo.save(cust2);
		return wdo.save(cust1);
	}


//	@Override
//	public String fundTransfer(String mobileNo, String targetMobileNo, Double amount) throws CustomerNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}


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
