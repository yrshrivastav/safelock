package in.app.safelock.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsForm {

    private String Name;

    private String username;

    private String email;

    private String password;

    private String notes;

}
