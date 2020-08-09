package edu.miu.eshop.product.service.Impl;

import edu.miu.eshop.product.constants.ProductStatus;
import edu.miu.eshop.product.dto.ProductDto;
import edu.miu.eshop.product.entity.Category;
import edu.miu.eshop.product.entity.Product;
import edu.miu.eshop.product.entity.Promotion;
import edu.miu.eshop.product.repository.CategoryRepository;
import edu.miu.eshop.product.repository.ProductRepository;
import edu.miu.eshop.product.repository.PromotionRepository;
import edu.miu.eshop.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;


    public ProductDto getProduct(String productId) {
        return convertToProductDTO(productRepository.findById(productId).orElse(null));
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(String categoryId) {

        List<Product> products =  productRepository.findByCategoryId(categoryId);
         if(products.size()==0|| products==null) {
//        category.getSubCategories().stream().map(p->((Category)p).getCategoryName()).forEach(s->{
//            products.addAll(productRepository.findByProductCategory_CategoryName(s));
//                }
//        );
         }
         return  products.stream()
                         .map(this::convertToProductDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                                          .map(this::convertToProductDTO)
                                          .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryName(String categoryid, String subCategoryName) {

        List<Product> products =  new ArrayList<>();
        List<Product> productsUnderCategory = productRepository.findByCategoryId(categoryid);
//        productsUnderCategory.stream()
//                    .map(p->p.getProductCategory().getSubCategories().stream().filter(c->c.getCategoryName().equals(subCategoryName)).forEach(cc-> products.add(p)));
        //return productsUnderCategory.stream().filter(p-> p.getProductCategory().getSubCategories().stream().map(pp->pp.getCategoryName()).equals(subCategoryName))

        for (Product p: productsUnderCategory  ) {
            Category subCat = categoryRepository.findById(p.getCategoryId()).get();
            for (Category c:subCat.getSubCategories()){
                if(c.getCategoryName().equals(subCategoryName)){
                    products.add(p);
                }
            }
        }
        System.out.println(products);
        return  products.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByVendorId(String findByVendroId) {
        return productRepository.findByVendorId(findByVendroId)
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsOnPromotion() {
        List<Promotion> promotions = promotionRepository.findAll();
        List<Product> products = new ArrayList<>();
        promotions.stream().map(prom -> prom.getProductId()).forEach(
                id-> {
                    products.add(productRepository.findByProductId(id));
                }
        );

        return products.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Product updateStatus(String id, ProductStatus newStatus) {
        Product product =  productRepository.findByProductId(id);
        product.setStatus(newStatus);
        productRepository.save(product);
        return  product;
    }

    @Override
    public Product updateProduct(ProductDto productDto, String productid) throws ParseException {

         Product product = convertToEntity(productDto,  productid);
         productRepository.save(product);
         return  product;
    }

    /**
     *TO DO
     */
    private ProductDto convertToProductDTO(Product product ) {
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.LOOSE);
        if(product==null) return  null;
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto, String productid) throws ParseException {
        Product product = modelMapper.map(productDto, Product.class);
        System.out.println(product);
            Product oldProduct = productRepository.findByProductId(productid);
            product.setProductName(oldProduct.getProductName());
            product.setDescription(oldProduct.getDescription());
            product.setCurrentQuantity(oldProduct.getCurrentQuantity());
            product.setProductDetails(oldProduct.getProductDetails());
            product.setVendorId(oldProduct.getVendorId());
            product.setCategoryId(oldProduct.getCategoryId());
            product.setCategoryName(oldProduct.getCategoryName());
            product.setImageList(oldProduct.getImageList());
            product.setProductId(oldProduct.getProductId());

        return product;
    }
}
