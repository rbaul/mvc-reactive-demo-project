package springmvcdemo.web.dtos.errors;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@ApiModel("ValidationError")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationErrorDto {

    private String fieldName;

    private String errorCode;

    private Object rejectedValue;

    @Singular
    private List<Object> params;

    private String message;
}