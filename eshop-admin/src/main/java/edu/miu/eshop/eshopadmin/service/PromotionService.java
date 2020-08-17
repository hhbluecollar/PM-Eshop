package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.Promotion;

import java.util.List;

public interface PromotionService {

    void save(Promotion promotion);

    Promotion get(String id);

    List<Promotion> getAll();

    void delete(String promotionId);

    Promotion update(String promotionId, Promotion promotion);

    List<Promotion> getByVendor(String vendorId);
}