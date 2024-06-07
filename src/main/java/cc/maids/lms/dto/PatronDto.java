package cc.maids.lms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatronDto {

    @NotNull(message = "The name parameter is required")
    @NotBlank(message = "please provide a name for the patron")
    private String name;
    @NotNull(message = "The phoneNumber parameter is required")
    @NotBlank(message = "Please provide a phoneNumber for the patron")
    private String phoneNumber;
    @NotNull(message = "The email parameter is required")
    @NotBlank(message = "Please provide a email for the patron")
    @Email(message = "Please provide a valid email")
    private String email;
}
