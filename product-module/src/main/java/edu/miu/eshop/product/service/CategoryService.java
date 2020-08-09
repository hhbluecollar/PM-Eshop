package edu.miu.eshop.product.service;


import edu.miu.eshop.product.entity.Category;

public interface CategoryService {

    void save(Category category);

    void addCategory(String parentId, String value);

    void deleteCategory(  String CategoryId);

    void editCategory( String value,String categoryId);

    Object getAll();
}
