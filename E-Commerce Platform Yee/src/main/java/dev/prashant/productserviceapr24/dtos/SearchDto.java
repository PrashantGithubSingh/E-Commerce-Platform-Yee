package dev.prashant.productserviceapr24.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    private String Query;
    private int PageNumber;
    private int PageSize;
}
