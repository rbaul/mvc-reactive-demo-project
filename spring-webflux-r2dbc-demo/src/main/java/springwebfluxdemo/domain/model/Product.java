package springwebfluxdemo.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@ToString(callSuper = true)
@AllArgsConstructor
public class Product extends AuditableEntity {
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

//    @DBRef
//    private Store store;

}
