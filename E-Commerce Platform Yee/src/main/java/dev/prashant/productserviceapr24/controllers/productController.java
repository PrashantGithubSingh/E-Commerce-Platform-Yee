package dev.prashant.productserviceapr24.controllers;

import dev.prashant.productserviceapr24.dtos.CreateProductRequestDto;
import dev.prashant.productserviceapr24.dtos.ErrorDto;
import dev.prashant.productserviceapr24.dtos.FakeStoreProductDto;
import dev.prashant.productserviceapr24.models.Product;
import dev.prashant.productserviceapr24.services.FakeStoreProductService;
import dev.prashant.productserviceapr24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {
    private ProductService productService;
    public productController(@Qualifier("selfProductService") ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/products")
    public  Product  createProduct(@RequestBody CreateProductRequestDto productRequestDto){
          return productService.createProduct(
                  productRequestDto.getTitle(),
                  productRequestDto.getDescription(),
                  productRequestDto.getImage(),
                  productRequestDto.getCategory(),
                  productRequestDto.getPrice()

          );
    }
    // ResponseEntity contains everything that a response requires:
    // Data, Status code and headers
    @GetMapping("/products")
    public List<Product> getAllProducts(){
       return productService.getAllProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        Product product= new Product();
        product.getId();
        product.setTitle("Singh");;
        return productService.getSingleProduct(id);

    }
    @GetMapping("/products/categories")
    public List<String> getProductCategories(){
        return productService.getProductCategories();

    }
    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);

    }
    @PutMapping("/products/{id}")
    public Product updateProductPut(@PathVariable("id") Long id,@RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productService.updateProductPut(id,fakeStoreProductDto);

    }
    @PatchMapping("/products/{id}")
    public Product updateProductPatch(@PathVariable("id") Long id,@RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productService.updateProductPatch(id,fakeStoreProductDto);

    }
    @GetMapping("products/category/{category}")
    public List<Product> getProductbyCategory(@PathVariable("catogory") String category){
        return productService.getProductbyCategory(category);

    }
}
