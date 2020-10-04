package com.example.springmvcdemo.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    @Null(groups = ValidationGroups.Create.class)
    private Long id;

    @NotBlank
    private String name;

    private List<ProductDto> products;
}
