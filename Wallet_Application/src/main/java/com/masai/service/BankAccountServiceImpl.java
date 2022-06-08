package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.BankAccountDTO;
import com.masai.entity.BankAccount;
import com.masai.entity.Transaction;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.CustomerDao;
import com.masai.repository.TransactionDao;
import com.masai.repository.WalletDaoJpa;


@Service
public class BankAccountServiceImpl implements BankAccountService {
   
	@Autowired
	private BankAccountRepo bankAccRepo;
	
	@Autowired
	TransactionDao trDao;
	
	@Autowired
	private WalletDaoJpa walletRepo;
	
	@Autowired
	private CustomerDao cusDao;
	
	@Override
	public String createBankAccount(BankAccount bankAccount) {
		
		   BankAccount newBank = bankAccRepo.findByBankNameAndWalletId(bankAccount.getBankName(),bankAccount.getWallet().getId());
		   if(newBank!=null)
			   return "Account Already Exists";
         
		 Optional<Wallet> opt = walletRepo.findById(bankAccount.getWallet().getId());
		 
		 if(!opt.isPresent())
			 throw new CustomerNotFoundException("Wallet is not found with this Id: "+bankAccount.getWallet().getId());
		 
		 
		BankAccount newAccount = new BankAccount();
		newAccount.setAccountNo(generateUniqueId());
		newAccount.setIfscCode(bankAccount.getIfscCode());
		newAccount.setBankBalance(bankAccount.getBankBalance());
		newAccount.setBankName(bankAccount.getBankName());
		newAccount.setWallet(opt.get());
		
		
		bankAccRepo.save(newAccount);
		
		
		
		return newAccount.getBankName()+" is successfully added..";
	}

	@Override
	public BankAccount getAccountByAccountNumber(Integer accountNumber) throws CustomerNotFoundException {
        
		Optional<BankAccount> opt = bankAccRepo.findById(accountNumber);
		
		if(opt.isPresent())
			return opt.get();
		else
			throw new CustomerNotFoundException("No Account Found with this Accoutn Number: "+accountNumber); 
	}
	
	@Override
	public List<BankAccountDTO> getAccountByWalletId(Integer walletId) throws CustomerNotFoundException {
        
		    List<BankAccountDTO> bankDTO =  bankAccRepo.viewAccount(walletId);
		    
		    if(bankDTO.size()>0)
		    	return bankDTO;
		    else
		    	throw new CustomerNotFoundException("No Account Found With this wallet Id: "+walletId);
      
	}
	
	
	@Override
	public String removeAccount(Integer accountNo) throws CustomerNotFoundException {
                  
          Optional<BankAccount> opt = bankAccRepo.findById(accountNo);
          Integer temp;
          if(opt.isPresent()) {
        	   BankAccount newBank = opt.get();
        	    temp = newBank.getWallet().getId();
        	   newBank.setWallet(null);
        	   bankAccRepo.delete(newBank);
        	   newBank.setWallet(opt.get().getWallet());
             return newBank.getBankName()+" has removed from wallet No "+temp;
          }
          else
        	  throw new CustomerNotFoundException("Account Not Found With this Account Number: "+accountNo);
          
	}
	
	
	@Override
	public BankAccount moneyTransfer(Integer accountNumber1, Integer accountNumber2, double balance,Wallet wallet) {
           
		    Optional<BankAccount> opt1 = bankAccRepo.findById(accountNumber1);
		    Optional<BankAccount> opt2 = bankAccRepo.findById(accountNumber2);
		    
		    
		    
		    if(opt1.isPresent()&&opt2.isPresent())
		    {
		    
		     if(balance <= opt1.get().getBankBalance())
		     {
		   
		    	opt2.get().setBankBalance(opt2.get().getBankBalance()+balance);
		    	
		    	opt1.get().setBankBalance(opt1.get().getBankBalance()-balance);
		    	
		    	Transaction tr = new Transaction();
		    	  tr.setDateTime(LocalDateTime.now());
		    	  tr.setAmount(balance);
		    	  tr.setTransactionType("Debit");
		    	  tr.setDescription("Sent to Bank "+accountNumber1);
		    	  opt1.get().getWallet().getTran().add(tr);
		    	  
		    	  Transaction tr1 = new Transaction();
		    	  tr1.setDateTime(LocalDateTime.now());
		    	  tr1.setAmount(balance);
		    	  tr1.setTransactionType("Credit");
		    	  tr1.setDescription("Received to Bank "+accountNumber2);
		    	  opt2.get().getWallet().getTran().add(tr1);
		    	  

		    	bankAccRepo.save(opt2.get());
		    	
		    	bankAccRepo.save(opt1.get());
		    	
		    	return opt1.get();
		     }
		     else
		    	 throw new CustomerNotFoundException("Insufficient Fund");
		    	
		    }
		    else
		    	throw new CustomerNotFoundException("Enter Correct Account Number");
        
	}
	
	
	
	
	//for generating unique number
	 public static int generateUniqueId() {
		 
	        UUID idOne = UUID.randomUUID();
	        String str=""+idOne;        
	        int uid=str.hashCode();
	        String filterStr=""+uid;
	        str=filterStr.replaceAll("-", "");
	        return Integer.parseInt(str);
	    }

	

}
