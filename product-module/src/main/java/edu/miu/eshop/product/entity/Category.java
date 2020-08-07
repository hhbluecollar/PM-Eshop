package edu.miu.eshop.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
@EqualsAndHashCode
public class Category {

    /**
     * We should ask category status ??? ADDED USER ID
     */
    @Id
    private String id;
    private String categoryName;
    private List<Category> subCategories = new ArrayList<>();;

    public Category(String categoryName, String id) {
        this.categoryName = categoryName;
        this.id = id;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category addCategory(String parentId, String value, String categoryId){
        if(this.id.equals(parentId)) {

                Category c = new Category(value,categoryId);
            if(!this.subCategories.contains(c)){
                this.subCategories.add(c);
            }
            return c;
        }

        for(Category c : subCategories){
            if(!this.subCategories.contains(c)){
                c.addCategory(parentId, value, categoryId);
            }
        }
        return null;
    }

    public boolean deleteCategory(String id){
        ListIterator<Category> iterator = this.subCategories.listIterator();
        while(iterator.hasNext()){
            Category c = iterator.next();
            if(c.id.equals(id)) {
                iterator.remove();
                return true;
            }
            else {
                return c.deleteCategory( id);
            }
        }
        return false;
    }

    public boolean editCategory(String id, String value){
        Iterator<Category> iterator = this.subCategories.iterator();
        while(iterator.hasNext()){
            Category c = iterator.next();
            if(c.id!=null && c.id.equals(id)  ) {
                c.categoryName=value;
                return true;
            }
            else {
                return editCategory( id, value);
            }
        }
        return false;
    }
}
