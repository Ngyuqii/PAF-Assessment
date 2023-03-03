package paf.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import paf.assessment.model.Transfer;
import paf.assessment.service.FundsTransferService;
@RestController
public class RedisController {

    @Autowired
    private FundsTransferService fTSvc;

    @GetMapping(path="/transfer")
	public ResponseEntity<String> fundsTransfer (@RequestBody MultiValueMap<String, String> form) {

        Transfer formObj = Transfer.createTransferObj(form);
        
        try{
        Transfer t = fTSvc.performTransfer(formObj);

        JsonObject jsonObj = Json.createObjectBuilder()
                    .add("transactionid", t.getTransactionId())
                    .add("from_account", t.getFromAcct())
                    .add("to_account", t.getToAcct())
                    .add("amount",t.getTransferAmt())
                    .build();
		
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(jsonObj.toString());
        }
        catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body(Json.createObjectBuilder()
                        .add("message","Transaction not successful")
                        .build().toString());

        }
}
    
}
