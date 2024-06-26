package dev.prashant.productserviceapr24.services;

import dev.prashant.productserviceapr24.dtos.CreateProductRequestDto;
import dev.prashant.productserviceapr24.dtos.FakeStoreProductDto;
import dev.prashant.productserviceapr24.models.Product;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    public List<String> getProductCategories();
    public Product createProduct(String title, String description,
                                 String image, String category, double price);


    public Product deleteProduct(Long id);
    public Product updateProductPut(Long id, FakeStoreProductDto fakeStoreProductDto);
    public Product updateProductPatch(Long id, FakeStoreProductDto fakeStoreProductDto);



    public List<Product> getProductbyCategory(String category);

    List<Product> getProductByCategory(String category);
}
