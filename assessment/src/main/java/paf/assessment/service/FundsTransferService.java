package paf.assessment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paf.assessment.model.Account;
import paf.assessment.model.Transfer;

import paf.assessment.repository.AccountsRepository;
import static paf.assessment.repository.SqlQueries.*;

@Service
public class FundsTransferService {

    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private AccountsRepository accountsRepo;

    //Method to retrieve all account records from accounts table
    public List <Account> getAllAccounts() {
        return accountsRepo.getAllAccounts();
    }
    
    //Method to retrieve account details by accountid
    public Account getAccountById(String acctId) {
        return accountsRepo.getAccountById(acctId);
    }

    //Method to perform the transaction
    @Transactional (rollbackFor = Exception.class)
    public Transfer performTransfer(Transfer formObj) {

    //Generate a random 8 char id and set into comment as comment id
	String transactionId = UUID.randomUUID().toString().substring(0, 8);
	formObj.setTransactionId(transactionId);

    transferFrom(formObj);
    transferTo(formObj);

    return formObj;

    }

    //Methods to perform funds transfer    
    //1. Transfer from
	//SQL_TRANSFERFROM = UPDATE accounts SET balance = balance - ? WHERE account_id = ?
    public Boolean transferFrom(Transfer formObj) {
        Integer rowsUpdated = template.update(SQL_TRANSFERFROM, formObj.getTransferAmt(), formObj.getFromAcct()); 
        
        //Check for update success
        System.out.println("Accounts transferred from: " + rowsUpdated);
        return rowsUpdated > 0;
    }

	//2. Transfer to
    //String SQL_TRANSFERTO = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?
    public Boolean transferTo(Transfer formObj) {
        Integer rowsUpdated = template.update(SQL_TRANSFERTO, formObj.getTransferAmt(), formObj.getToAcct()); 
        
        //Check for update success
        System.out.println("Accounts transferred to: " + rowsUpdated);
        return rowsUpdated > 0;
    }
    
}