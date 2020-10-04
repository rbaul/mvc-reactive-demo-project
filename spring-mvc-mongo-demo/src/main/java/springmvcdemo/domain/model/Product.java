package springmvcdemo.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Document
public class Product extends AuditFields {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(0)
    private Double price;

    @Min(0)
    private Short quantity;

//    @ToString.Exclude
//    @DBRef
//    private Store store;
}
