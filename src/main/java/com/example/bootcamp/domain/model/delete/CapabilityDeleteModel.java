package com.example.bootcamp.domain.model.delete;

import com.example.bootcamp.domain.model.list.TechnologyModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class CapabilityDeleteModel {
    private Long id;
    private String name;
    private List<TechnologyModel> technologies;
}
