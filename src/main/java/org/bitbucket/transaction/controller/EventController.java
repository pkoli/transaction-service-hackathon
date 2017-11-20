package org.bitbucket.transaction.controller;

import org.bitbucket.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/transaction")
    public ResponseEntity processTransaction(@RequestBody String transactionEvent){
        transactionService.processTransaction(transactionEvent);
        return new ResponseEntity(HttpStatus.OK);
    }

}
