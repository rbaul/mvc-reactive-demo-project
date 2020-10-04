package springmvcdemo.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Document
public class Store extends AuditFields {
    @Id
    private Long id;

    @NotBlank
    private String name;

    //    @DBRef
    private List<Product> products = new ArrayList<>();

}
