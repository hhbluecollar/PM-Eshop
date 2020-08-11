package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.entity.Category;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.repository.CategoryRepository;
import edu.miu.eshop.product.repository.ProductRepository;
import edu.miu.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
   public void addCategory(String parentId, String value) {

      Category root = categoryRepository.findByCategoryName("AllProducts");
      System.out.println(root);
//      List<Category> root = categoryRepository.findByCategoryName("Root");
     // Category category = root.addCategory(parentId, value);
     // System.out.println(category);

//     Category category = new Category();
//          for (Category c: root.getSubCategories())
//           if(c.getId().equals(parentId))
//              category = c;
//      System.out.println(category);
//
//      category.addCategory(parentId, value);
      root.addCategory(parentId,value);
//      categoryRepository.save(category);
      categoryRepository.save(root);

   }

   @Override
   public void editCategory( String value, String CategoryId) {

      Category category = categoryRepository.findByCategoryName("Root");
      category.editCategory(CategoryId, value);
      categoryRepository.save(category);

   }

   @Override
   public List<Category> getAll() {
      return categoryRepository.findAll();
   }

   @Override
   public void deleteCategory( String categoryId) { // this is the category id in the dto used inplace of paretntId
       Category category = categoryRepository.findByCategoryName("Root");
       category.deleteCategory(categoryId);
       categoryRepository.save(category);
   }
}
