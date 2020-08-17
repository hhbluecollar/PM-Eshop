package edu.miu.eshop.amexbank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.eshop.amexbank.domain.Amexdata;
import edu.miu.eshop.amexbank.domain.Transaction;
import edu.miu.eshop.amexbank.repository.AmexdataRepository;


@Service
public class AmexdataServiceImpl implements AmexdataService {


    @Autowired
    private AmexdataRepository repository;


    @Override
    public Amexdata findAmexdata(String cardnumber) {
        Optional<Amexdata> amex = repository.findById(cardnumber);
        return amex.orElse(null);
    }

    @Override
    public void saveAmexdata(Amexdata data) {
        repository.save(data);
    }

    @Override
    public List<Amexdata> getAmexdata() {
        return repository.findAll();
    }

    @Override
    public boolean confirmTransaction(Transaction transaction) {
        String cardNumber = transaction.getCard().getCardNumber();
        int cvv = transaction.getCard().getCvv();
        String cardHolder = transaction.getCard().getCardHolder();
        int month = transaction.getCard().getMonth();
        int year = transaction.getCard().getYear();
        String billingAddress = transaction.getCard().getBillingAddress();
        double amount = transaction.getAmount();
        LocalDate ld = LocalDate.now();
        if (year < ld.getYear() || (year == ld.getYear() && month < ld.getMonthValue())) return false;
        Amexdata amexdata = findAmexdata(cardNumber);
        if (amexdata == null) return false;
        //if (cvv != amexdata.getCvv() || (cardHolder.equalsIgnoreCase(amexdata.getCardHolder()) == false) || (billingAddress.equalsIgnoreCase(amexdata.getBillingAddress()) == false) || amexdata.getStatus() == false || amount > amexdata.getAmount())
        if (amexdata.getStatus() == false || amount > amexdata.getAmount())
            return false;

        double amt = amexdata.getAmount();
        amt -= amount;
        amexdata.setAmount(amt);
        saveAmexdata(amexdata);

        return true;

    }

}
