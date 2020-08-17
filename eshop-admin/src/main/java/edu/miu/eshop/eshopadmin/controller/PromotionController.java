package edu.miu.eshop.eshopadmin.controller;

import edu.miu.eshop.eshopadmin.domain.Promotion;
import edu.miu.eshop.eshopadmin.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("")
    public ResponseEntity getAll(){
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( promotionService.getAll());
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity get(@PathVariable String promotionId){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( promotionService.get(promotionId));
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Promotion promotion){

        promotionService.save(promotion);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( promotionService.get(promotion.getId()));
    }

    @DeleteMapping("/delete/{promotionId}")
    public ResponseEntity delete(@PathVariable String promotionId){
        promotionService.delete(promotionId);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body("The Promotion Deleted");
    }

    @PutMapping("/update/{promotionId}")
    public ResponseEntity update(@PathVariable String promotionId, @RequestBody Promotion promotion){
        Promotion updatedPromotion = promotionService.update(promotionId, promotion);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( updatedPromotion);
    }
    @GetMapping("vendor/{vendorid}")
    public ResponseEntity getByVendor(@PathVariable String vendorid){
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(promotionService.getByVendor(vendorid));
    }
}
