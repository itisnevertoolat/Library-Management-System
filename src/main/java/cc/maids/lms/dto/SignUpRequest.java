package cc.maids.lms.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotNull(message = "The email parameter is required")
    @NotBlank(message = "please provide a value for the email")
    @Email(message = "please provide a valid email")
    String email;
    @NotNull(message = "The password parameter is required")
    @NotBlank(message = "please provide a value for the password field")
    String password;
}
