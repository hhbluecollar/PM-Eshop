package edu.miu.eshop.product.service;


import edu.miu.eshop.product.entity.Category;

public interface CategoryService {

    void save(Category category);

    void addCategory(String parentId, String value, String categoryId);

    void deleteCategory(String parentId,  String CategoryId);

    void editCategory(String parentId, String value,String categoryId);
}
