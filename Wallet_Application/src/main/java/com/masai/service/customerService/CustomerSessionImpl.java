//package com.masai.service.customerService;

////import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.masai.entity.Customer;
//import com.masai.entity.CustomerDTO;
//
//import com.masai.repository.CustomerSessionDao.CustomerSessionDao;
//import com.masai.repository.customerDao.CustomerDao;


//@Service
//public class CustomerSessionImpl implements CustomerSessionService{
//   
//	@Autowired
//	private CustomerDao cDao;
//	
//	@Autowired
//	private CustomerSessionDao cusDao;
//	
//	@Override
//	public String LoginToAccount(CustomerDTO customerDTO) {
//          
////		    String msg = "No Account Found By This Details...";
////		    
////		      CustomerSession optC=cusDao.findByMobileNumber(customerDTO.getMobileNumber());
////		      
////		      System.out.println(optC);
////		      
////		      if(optC!=null)
////		    	  return "Already LoggesIn"+optC;
//		
//		Customer cus = cDao.findByMobileNumber(customerDTO.getMobileNumber(),customerDTO.getPassword());
//		     
////
//     	      if(cus!=null)
//     	      {
////		    	
////		    	  //String cusMobileNumber = cus.getMobileNumber();
////		    	  
//////		    	  Optional<CustomerSession> existingCustomer = cusDao.findBy;
//////		    	  
//////		    	  if(existingCustomer.isPresent()) {
//////		    		  return "CustomerAlready Exists...";
//////		    	  }
//////		    	  
//////		    	  if(cus.getPassword().equals(customerDTO.getPassword()))
//////		    	  {
//////		    		  String key = RandomString.make(6);
//////		    		  CustomerSession cusSession = new CustomerSession();
//////		    		  cusSession.setCustomerId(customerId);
//////		    		  cusSession.setUuid(key);
//////		    		  cusSession.setLocaDateTime(LocalDateTime.now());
////		    	
////		    	  String key = RandomString.make(8);
////		    	  CustomerSession cusSession = new CustomerSession();
////		    	      cusSession.setUuid(key);
////		    	      cusSession.setLocaDateTime(LocalDateTime.now());
////		    	      cusSession.setCustomer(cus);
////		    		  cusDao.save(cusSession);
////		    		  return cusSession.toString();
//     	    	  return "Yes Present";
//               }
//              else
//	    	  return "Failed: Please Check Mobile and Password.";
//     }
//
//}
