package com.example.bootcamp.domain.model.list;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PaginationMetadata {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private String sortBy;
    private String direction;

}
