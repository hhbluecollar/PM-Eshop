package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.entity.Promotion;
import edu.miu.eshop.product.repository.PromotionRepository;
import edu.miu.eshop.product.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

   @Autowired
   private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion get(String promotionid) {
        return promotionRepository.findById(promotionid).get();
    }

    @Override
    public void delete(String promotionid) {
        promotionRepository.deleteById( promotionid);
    }

    @Override
    public void update(String promotionid, Promotion promotion) {
        Promotion oldPromotion = promotionRepository.findById(promotionid).get();
        //oldPromotion.setProductId(promotion.getProductId());
        oldPromotion.setPromotionDescription(promotion.getPromotionDescription());
        oldPromotion.setPromoName(promotion.getPromoName());
        oldPromotion.setPromotionPercentage(promotion.getPromotionPercentage());
        oldPromotion.setStartDate(promotion.getStartDate());
        oldPromotion.setEndDate(promotion.getEndDate());
        promotionRepository.save(oldPromotion);

    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }
}
