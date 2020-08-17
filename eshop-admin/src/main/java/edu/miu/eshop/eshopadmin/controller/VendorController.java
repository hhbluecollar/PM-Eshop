package edu.miu.eshop.eshopadmin.controller;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.*;
import edu.miu.eshop.eshopadmin.domain.Vendor;
import edu.miu.eshop.eshopadmin.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopadmin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<VendorDto> getAllCustomer(){
        return vendorService.getAll().stream().map(VendorDto::build).collect(Collectors.toList());
    }

    @GetMapping("/{vendorId}")
    public VendorDto findCustomerById(@PathVariable("vendorId") String vendorId){
        try {
            return VendorDto.build(vendorService.findById(vendorId));
        } catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Vendor ID not found!", ex);
        }
    }

    // -- Related to the BANK CARD --
    @GetMapping("/cards/{vendorId}")
    public Set<BankCardDto> getCards(@PathVariable("vendorId") String vendorId){
        return vendorService.findById(vendorId).getCards();
    }

    @PostMapping("/cards/{vendorId}")
    public void addCard(@PathVariable("vendorId") String vendorId, @RequestBody BankCardDto bankCardDto){
        try {
            Vendor vendor = vendorService.findById(vendorId);
            vendor.addCard(bankCardDto);
            vendorService.save(vendor);
        }catch(CustomerNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Vendor not found!", ex);
        }
    }

    @DeleteMapping("/cards/{vendorId}")
    public void removeCard(@PathVariable("vendorId") String vendorId, @RequestBody BankCardDto bankCardDto){
        vendorService.removeCard(vendorId, bankCardDto);
    }
    // -- End of BANK CARD --

    @PutMapping("/{vendorId}")
    public VendorDto updateCustomer(@PathVariable("vendorId") String vendorId, @RequestBody VendorDto newCustomer){
        return VendorDto.build(vendorService.update(vendorId, newCustomer));
    }

    @DeleteMapping("/{vendorId}")
    public void deleteCustomer(@PathVariable("vendorId") String vendorId) {
        vendorService.deleteById(vendorId);
    }

    @PatchMapping("/{vendorId}/reset")
    private void resetPassword(@PathVariable String vendorId, @RequestBody PasswordDto passwordDto){
        vendorService.resetPassword(vendorId, passwordDto.getPassword());
    }

    @GetMapping("/email/{vendorId}")
    public EmailDto getEmail(@PathVariable String vendorId){
        return EmailDto.build(vendorService.findById(vendorId));
    }

    @PostMapping("/onetimepayment/{vendorId}")
    public ResponseEntity<BooleanDto> oneTimePayment(@PathVariable String vendorId, @RequestBody BankCardDto bankCard ){
        return new ResponseEntity<>(vendorService.oneTimePayment(vendorId, bankCard), HttpStatus.OK);
    }

    @GetMapping("/orderitems/{vendorId}")
    public List<OrderItemDto> getOrderByVendor(@PathVariable("vendorId") String vendorId){
        return vendorService.getOrderByVendor(vendorId);
    }
}
