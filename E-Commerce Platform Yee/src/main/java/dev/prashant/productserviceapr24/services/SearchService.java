package dev.prashant.productserviceapr24.services;

import dev.prashant.productserviceapr24.models.Product;
import dev.prashant.productserviceapr24.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public Page<Product> search(String Query, int PageNumber,int PageSize ){
        Sort sort = Sort.by(Sort.Direction.DESC, "category_id")
                .and(Sort.by(Sort.Direction.ASC, "title"));
        Pageable pageable = PageRequest.of(PageNumber, PageSize, sort);

        return productRepository.findByTitleContaining(Query, pageable);
    }

}
