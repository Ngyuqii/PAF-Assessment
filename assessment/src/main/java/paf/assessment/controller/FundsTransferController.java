package paf.assessment.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import paf.assessment.model.Account;
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




    
}
