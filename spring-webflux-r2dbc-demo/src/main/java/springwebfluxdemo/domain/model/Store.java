package springwebfluxdemo.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Store extends AuditableEntity {
    @Id
    private Long id;

    @NotBlank
    private String name;

    //    @DBRef
//    private List<Product> products = new ArrayList<>();

}
