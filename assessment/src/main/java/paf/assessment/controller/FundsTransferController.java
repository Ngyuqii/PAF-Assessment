package paf.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import paf.assessment.model.Account;
import paf.assessment.model.Transfer;
import paf.assessment.service.FundsTransferService;

@Controller
@RequestMapping(path="/")
public class FundsTransferController {

    @Autowired
    private FundsTransferService fTSvc;

    //Retrieve all account records from accounts table and set into landing page via model
    @GetMapping
    public String landingPage(Model model){
        
        List<Account> accountsList = fTSvc.getAllAccounts();
        model.addAttribute("accounts", accountsList);

        return "index";
    }

    @PostMapping(path="/transfer", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String fundsTransfer(@RequestBody MultiValueMap<String, String> form, Model model){

        //Check for return values
        System.out.printf("Form Data>>> %s\n", form);
        System.out.printf(form.getFirst("name1") + form.getFirst("name2") + form.getFirst("amount") + form.getFirst("comment"));
        
        //Perform checks
        //C0. Check account exists by accountId
        Account fromAcct = fTSvc.getAccountById(form.getFirst("name1"));
        Account toAcct = fTSvc.getAccountById(form.getFirst("name2"));
        if (fromAcct == null || toAcct == null){
            return "index";
        }

        //C1. Length of accountId is 10 characters
        int len1 = form.getFirst("name1").length();
        int len2 = form.getFirst("name2").length();
        if (len1 != 10 || len2 != 10){
            return "index";
        }

        //C2. Accounts should not be the same
        if (form.getFirst("name1").matches(form.getFirst("name2")))
        {
            return "index";
        }

        //C3 and C4 already set in html form
        //C5 fromAcct should have sufficient funds 
        Double fromAcctBalance = fromAcct.getBalance();
        Double transferAmt = Double.parseDouble(form.getFirst("amount"));
        if ( fromAcctBalance < transferAmt){
            return "index";
        }

        //Proceed with the transfer
        Transfer formObj = Transfer.createTransferObj(form);
        Transfer transactionCompleted = fTSvc.performTransfer(formObj);

        model.addAttribute("t", formObj);

        return "transferred";

    }
    
}
