package edu.miu.eshop.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@AllArgsConstructor
public class Category {

    @Id
    @JsonProperty("key")
    private String id;
    @JsonProperty("label")
    private String categoryName;
    @JsonProperty("nodes")
    private List<Category> subCategories = new ArrayList<>();;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category addCategory(String parentId, String value){
        if(this.id.equals(parentId)) {
            Category c = new Category(value);
            c.setId(generateCategoryId());
            this.subCategories.add(c);
            return c;
        }

        for(Category c : this.subCategories){
            Category category = c.addCategory(parentId, value);
                if(category!=null)
                    return category;
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
                if(c.deleteCategory( id))
                    return true;
            }
        }
        return false;
    }

    public boolean editCategory(String id, String value){
        Iterator<Category> iterator = this.subCategories.iterator();
        while(iterator.hasNext()){
            Category c = iterator.next();
            if(c.id.equals(id)  ) {
                c.categoryName=value;
                return true;
            }
            else {
                if(c.editCategory( id, value))
                    return true;
            }
        }
        return false;

    }


    //generate order number of length 20
    private String generateCategoryId() {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789" ;

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 20; i++) {

            // generate a random number between 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
