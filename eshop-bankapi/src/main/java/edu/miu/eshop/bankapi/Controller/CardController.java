package edu.miu.eshop.bankapi.Controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import edu.miu.eshop.bankapi.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.miu.eshop.bankapi.Service.CardService;
import edu.miu.eshop.bankapi.Service.PaymentMethodService;
import edu.miu.eshop.bankapi.Service.TransactionHistService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService service;

    @Autowired
    private TransactionHistService histservice;

    @Autowired
    private PaymentMethodService payment;

    @PostMapping("/addcard")
    public ResponseEntity<?> addCard(@RequestBody Card card) {
        String resp = service.addNewCard(card);
        return new ResponseEntity<String>(resp, HttpStatus.OK);

    }

    @GetMapping("/getcard/{cardnumber}")
    public ResponseEntity<?> getCard(@PathVariable("cardnumber") String cardnumber) {
        Card card = service.getCard(cardnumber);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    @PostMapping("/process")
    public ResponseEntity<BooleanDto> processTransaction(@RequestBody Transaction transaction) {
        System.out.println("CARD CONTROLLER BANK: " + transaction);
        RestTemplate restTemplate = new RestTemplate();
        String cardnumber = transaction.getCard().getCardNumber();
        String cardtype = service.getCardType(cardnumber);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transaction> reqTrans = new HttpEntity<>(transaction, headers);
        PaymentMethod paymentmethod = payment.findPaymentMethodByname(cardtype);
        String url = paymentmethod.getUrl();
        Boolean response = restTemplate.postForObject(url, reqTrans, Boolean.class);
        LocalDate date = LocalDate.now();
        ZoneId zone = ZoneId.of("America/Chicago");
        LocalTime time = LocalTime.now(zone);
        histservice.saveTransactionHist(transaction, response, date, time);
        return new ResponseEntity<>(BooleanDto.build(response), HttpStatus.OK);
    }

    @GetMapping("/paymentmethod/{id}")
    public ResponseEntity<?> getPaymentMethod(@PathVariable("id") String id) {
        PaymentMethod paymentmethod = payment.findPaymentMethodById(id);
        return new ResponseEntity<PaymentMethod>(paymentmethod, HttpStatus.OK);
    }

    @GetMapping("/deletepaymentmethod/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable("id") String id) {
        payment.deletePaymentMethod(id);
        return new ResponseEntity<String>("Payment method deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/allpaymentmethod")
    public ResponseEntity<?> getAllPaymentMethod() {
        List<PaymentMethod> paymentmethod = payment.getAllPaymentMethod();
        return new ResponseEntity<List<PaymentMethod>>(paymentmethod, HttpStatus.OK);
    }

    @PostMapping("/addpaymentmethod")
    public ResponseEntity<?> createPaymentMethod(@RequestBody PaymentMethod paymentmethod) {
        payment.addPaymentMethod(paymentmethod);
        return new ResponseEntity<String>("Payment Method Created Successfully", HttpStatus.OK);
    }

    @GetMapping("/alltransaction")
    public ResponseEntity<?> allTransactionHist() {
        List<TransactionHist> transaction = histservice.getAllTransactionHist();
        return new ResponseEntity<List<TransactionHist>>(transaction, HttpStatus.OK);
    }

    @GetMapping("/transactiondata")
    public ResponseEntity<?> getTotalTransaction() {
        double total =  histservice.getTotalTransactionAmt();
        double success = histservice.getTotalTransaction(true);
        double failure = histservice.getTotalTransaction(false);
        TransactionData transaction = new TransactionData(total, success, failure);
        return new ResponseEntity<TransactionData>(transaction, HttpStatus.OK);
    }

    @GetMapping("/totalamtbymonth")
    public ResponseEntity<?> getTotalAmountByMonth() {
        Map<Month, Double> data = histservice.getTransactionByMonth(2020);
        return new ResponseEntity<Map<Month,Double>>(data, HttpStatus.OK);

    }
}