package az.abb.etaskify.dtos;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RegisterDTO {
    private Long Id;
    @Size(min = 3)
    private String userName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 3)
    private String organizationName;
    @NotNull
    @Size(min = 3)
    private String phoneNumber;
    @Size(min = 3)
    private String address;
    @NotBlank
    @Size(min=6)
    private String passWord;
}
