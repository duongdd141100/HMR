package com.example.hrmbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_entry")
@Data
@SqlResultSetMapping(
        name = "EntryDtoResult",
        classes = {
                @ConstructorResult(
                        targetClass = com.example.hrmbe.dto.EntryDto.class,
                        columns = {
                                @ColumnResult(name = "slug", type = String.class),
                                @ColumnResult(name = "label", type = String.class),
                                @ColumnResult(name = "roleId", type = String.class),
                        }
                )
        }
)
public class Entry extends BaseEntity {

    @Column(name = "slug")
    private String slug;

    @Column(name = "label")
    private String label;

    @Column(name = "role_id")
    private String roleId;

}
