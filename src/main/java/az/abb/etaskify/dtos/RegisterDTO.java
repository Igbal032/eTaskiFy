package az.abb.etaskify.dtos;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RegisterDTO {
    private String userName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String organizationName;
    @NotNull
    private String phoneNumber;
    private String address;
    @NotBlank
    @Size(min=6)
    private String passWord;
}
