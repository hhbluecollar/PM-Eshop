package edu.miu.eshop.product.service;

import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.entity.Promotion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromotionService {

    public List<Promotion> getAll();
    public Promotion get(String promotionid);
    public void delete(String promotionid);
    public void update(String promotionid, Promotion promotion);

    void save(Promotion promotion);

    List<Promotion> getByVendor(String vendorid);
}
