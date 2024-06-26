package dev.prashant.productserviceapr24.controllers;

import dev.prashant.productserviceapr24.dtos.SearchDto;
import dev.prashant.productserviceapr24.models.Product;
import dev.prashant.productserviceapr24.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
@RestController
public class SearchController {
    private final SearchService searchService;
    public SearchController(SearchService searchService){

        this.searchService=searchService;
    }
    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchDto searchDto){
         return  searchService.search(
              searchDto.getQuery(),
              searchDto.getPageNumber(),
              searchDto.getPageSize()

         );

    }
}
