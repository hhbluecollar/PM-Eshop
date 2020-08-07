package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.entity.Category;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.repository.CategoryRepository;
import edu.miu.eshop.product.repository.ProductRepository;
import edu.miu.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

   @Autowired
   private ProductRepository productRepository  ;
   @Autowired
   private CategoryRepository categoryRepository;
   @Override
   public void save(Category category) {
      categoryRepository.save(category);
   }

   @Override
   public void addCategory(String parentId, String value, String categoryId) {

      Category category = categoryRepository.findById(parentId).get();
      category.addCategory(parentId, value, categoryId);
      categoryRepository.save(category);
   }

   @Override
   public void editCategory(String parentId, String value, String CategoryId) {

      Category category = categoryRepository.findById(parentId).get();
      category.editCategory(CategoryId, value);
      categoryRepository.save(category);

   }

   @Override
   public void deleteCategory(String parentId, String categoryId) { // this is the category id in the dto used inplace of paretntId
       Category category = categoryRepository.findById(parentId).get();
      System.out.println(categoryId);
       category.deleteCategory(categoryId);
       categoryRepository.save(category);
   }
}
