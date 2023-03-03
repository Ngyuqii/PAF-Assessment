package paf.assessment.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.assessment.model.Account;
import paf.assessment.model.Transfer;
import paf.assessment.repository.AccountsRepository;


@Service
public class FundsTransferService {

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

    // //Method to perform the transaction
    // public String performTransfer(Transfer fT) {

    // //Generate a random 8 char id and set into comment as comment id
	// String transactionId = UUID.randomUUID().toString().substring(0, 8);
	// fT.setTransactionId(transactionId);

    
	
    // return fT;

	//}
}
