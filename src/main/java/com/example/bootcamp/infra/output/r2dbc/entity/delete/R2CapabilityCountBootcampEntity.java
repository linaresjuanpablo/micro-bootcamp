package com.example.bootcamp.infra.output.r2dbc.entity.delete;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;

@Table("capability")
@Setter
@Getter

public class R2CapabilityCountBootcampEntity {

    private Long id;
    private String name;
    private String description;
    private List<Long> bootcampIds;
    private List<Long> technologyIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof R2CapabilityCountBootcampEntity)) return false;
        R2CapabilityCountBootcampEntity that = (R2CapabilityCountBootcampEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString para depuración
    /*@Override
    public String toString() {
        return "Capability{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }*/
    @Override
    public String toString() {
        return "R2CapabilityCountBootcampEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", bootcampIds=" + bootcampIds +
                ", technologyIds=" + technologyIds +
                '}';
    }

}
