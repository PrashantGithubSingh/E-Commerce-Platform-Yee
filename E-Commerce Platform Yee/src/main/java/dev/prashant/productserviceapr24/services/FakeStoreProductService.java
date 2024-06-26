package dev.prashant.productserviceapr24.services;

import dev.prashant.productserviceapr24.dtos.FakeStoreProductDto;
import dev.prashant.productserviceapr24.models.Category;
import dev.prashant.productserviceapr24.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long id) {
//        FakeStoreProductDto fakeStoreProductDto = restTemplate
//                .getForObject(
//                        "https://fakestoreapi.com/products/" + id,
//                        FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);


        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setTitle(title);

        FakeStoreProductDto response = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class);


        return response.toProduct();
    }


    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: response) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }
    @Override
    public List<String> getProductCategories() {
        String[] categories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class);

        return Arrays.asList(categories);
    }
    @Override
    public Product updateProductPut(Long id, FakeStoreProductDto fakeStoreProductDto) {
        String url = "https://fakestoreapi.com/products/{id}";
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );
        return responseEntity.getBody().toProduct();
    }
    @Override
    public Product updateProductPatch(Long id, FakeStoreProductDto fakeStoreProductDto) {
        String url = "https://fakestoreapi.com/products/{id}";

        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );

        return responseEntity.getBody().toProduct();
    }

    @Override
    public List<Product> getProductbyCategory(String category) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        String url = "https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id
        );
        return responseEntity.getBody().toProduct();
    }
    @Override
    public List<Product> getProductByCategory(String category) {
        FakeStoreProductDto[] response = restTemplate
                .getForObject("https://fakestoreapi.com/products/category/" + category,
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto dto : response) {
            products.add(dto.toProduct());
        }

        return products;
    }

}
