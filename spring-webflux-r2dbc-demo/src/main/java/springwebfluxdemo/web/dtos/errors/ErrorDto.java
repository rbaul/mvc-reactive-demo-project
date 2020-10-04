package springwebfluxdemo.web.dtos.errors;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

@ApiModel("Error")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private String errorCode;

    private String message;

    @Builder.Default
    private final Date timestamp = Date.from(Instant.now());

    @Singular
    private Set<Object> errors;
}