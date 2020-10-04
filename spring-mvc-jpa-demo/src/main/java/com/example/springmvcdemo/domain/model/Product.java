package com.example.springmvcdemo.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Product extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(0)
    private Double price;

    @Min(0)
    private Short quantity;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
}
