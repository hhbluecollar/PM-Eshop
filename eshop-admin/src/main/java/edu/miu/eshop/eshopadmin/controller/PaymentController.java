package edu.miu.eshop.eshopadmin.controller;

import edu.miu.eshop.eshopadmin.domain.*;
import edu.miu.eshop.eshopadmin.service.CardService;
import edu.miu.eshop.eshopadmin.service.PaymentMethodService;
import edu.miu.eshop.eshopadmin.service.TransactionHistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/card")
public class PaymentController {

    @Autowired
    private CardService service;

    @Autowired
    private TransactionHistService histService;

    @Autowired
    private PaymentMethodService payment;

    @PostMapping("/addcard")
    public ResponseEntity<String> addCard(@RequestBody Card card) {
        return new ResponseEntity<>(service.addNewCard(card), HttpStatus.OK);

    }

    @GetMapping("/getcard/{cardnumber}")
    public ResponseEntity<Card> getCard(@PathVariable("cardnumber") String cardnumber) {
        return new ResponseEntity<>(service.getCard(cardnumber), HttpStatus.OK);
    }

    @PostMapping("/process")
    public ResponseEntity<Boolean> processTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(service.process(transaction), HttpStatus.OK);

    }

    @GetMapping("/paymentmethod/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethod(@PathVariable("id") String id) {
        PaymentMethod paymentmethod = payment.findPaymentMethodById(id);
        return new ResponseEntity<>(paymentmethod, HttpStatus.OK);

    }

    @GetMapping("/deletepaymentmethod/{id}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable("id") String id) {
        payment.deletePaymentMethod(id);
        return new ResponseEntity<>("Payment method deleted successfully", HttpStatus.OK);

    }

    @GetMapping("/allpaymentmethod")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethod() {
        return new ResponseEntity<>(payment.getAllPaymentMethod(), HttpStatus.OK);
    }

    @PostMapping("/addpaymentmethod")
    public ResponseEntity<String> createPaymentMethod(@RequestBody PaymentMethod paymentmethod) {
        payment.addPaymentMethod(paymentmethod);
        return new ResponseEntity<String>("Payment Method Created Successfully", HttpStatus.OK);
    }


    @GetMapping("/alltransaction")
    public ResponseEntity<?> allTransactionHist() {
        List<TransactionHist> transaction = histService.getAllTransactionHist();
        return new ResponseEntity<List<TransactionHist>>(transaction, HttpStatus.OK);
    }

    @GetMapping("/transactiondata")
    public ResponseEntity<?> getTotalTransaction() {
        double total =  histService.getTotalTransactionAmt();
        double success = histService.getTotalTransaction(true);
        double failure = histService.getTotalTransaction(false);
        TransactionData transaction = new TransactionData(total, success, failure);
        return new ResponseEntity<TransactionData>(transaction, HttpStatus.OK);
    }

    @GetMapping("/totalamtbymonth")
    public ResponseEntity<?> getTotalAmountByMonth() {
        Map<Month, Double> data = histService.getTransactionByMonth(2020);
        return new ResponseEntity<Map<Month,Double>>(data, HttpStatus.OK);

    }
}
