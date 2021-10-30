package az.abb.etaskify.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RegisterDTO {
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String organizationName;
    @NotNull
    private String phoneNumber;
    private String address;
    @NotBlank
    private String password;
}
