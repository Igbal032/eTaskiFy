package az.abb.etaskify.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskDTO {
    private Long Id;
    @NotBlank
    @Size(min = 3)
    private String title;
    @NotBlank
    @Size(min = 3)
    private String description;
    @NotNull
    private LocalDateTime deadline;
    @NotNull
    private String status;
}
