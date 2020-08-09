package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.entity.Promotion;
import edu.miu.eshop.product.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Promotion promotion){

        promotionService.save(promotion);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( promotionService.get(promotion.getId()));
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        return  ResponseEntity
                        .status(HttpStatus.OK)
                        .body( promotionService.getAll());
    }

    @GetMapping("/{promotionid}")
    public ResponseEntity get(@PathVariable String promotionid){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( promotionService.get(promotionid));
    }

    @DeleteMapping("/delete/{promotionid}")
    public ResponseEntity delete(@PathVariable String promotionid){
        promotionService.delete(promotionid);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( promotionService.getAll());
    }

    @PutMapping("/update/{promotionid}")
    public ResponseEntity update(@PathVariable String promotionid, @RequestBody Promotion promotion){
        promotionService.update(promotionid, promotion);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body( promotionService.get(promotionid));
    }
}
