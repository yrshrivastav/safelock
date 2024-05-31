package in.app.safelock.forms;


// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

//     @NotBlank(message = "Username is required")
//     @Size(min = 3, message = "Min 3 Characters is required")
    private String name;

//     @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", 
//             message = "Invalid Email Address")
//     @NotBlank(message = "Email is required")
    private String email;

//     @NotBlank(message = "Phone is required")
//     @Pattern(regexp = "^(\\+91|0)?\\s?[7-9][0-9]{9}$", message = "Invalid Phone Address")
    private String phoneNo;

//     @NotBlank(message = "Password is required")
//     @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//             message = "Invalid password")
    private String password;

}
