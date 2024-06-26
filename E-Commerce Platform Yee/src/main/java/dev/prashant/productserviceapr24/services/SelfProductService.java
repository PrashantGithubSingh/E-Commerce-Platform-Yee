package dev.prashant.productserviceapr24.services;

import dev.prashant.productserviceapr24.dtos.FakeStoreProductDto;
import dev.prashant.productserviceapr24.models.Category;
import dev.prashant.productserviceapr24.models.Product;
import dev.prashant.productserviceapr24.repositories.categoryrepository;
import dev.prashant.productserviceapr24.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private categoryrepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              categoryrepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return productRepository.findByIdIs(id);
    }
    @Override
    public Product createProduct(String title, String description, String image, String categoryTitle, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category categoryFromDatabase = categoryRepository.findByTitle(categoryTitle);
        if(categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDatabase = newCategory;
            // categoryFromDatabase = categoryRepository.save(newCategory);
        }

        // If category was found from DB, then no new category will be created
        // If category is not found in the DB, a new category will be created because of cascade persist type
        product.setCategory(categoryFromDatabase);

        List<Product> productsTemp = categoryFromDatabase.getProducts();

        return productRepository.save(product);

    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProductPut(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    @Override
    public Product updateProductPatch(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    @Override
    public List<Product> getProductbyCategory(String category) {
        return null;
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<String> getProductCategories() {
        return null;
    }


}
