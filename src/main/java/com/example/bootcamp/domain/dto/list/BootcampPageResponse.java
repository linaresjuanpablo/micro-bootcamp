package com.example.bootcamp.domain.dto.list;

import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.domain.model.list.PaginationMetadata;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class BootcampPageResponse {

    private List<BootcampListModel> content;
    private PaginationMetadata metadata;
}
