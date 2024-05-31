package in.app.safelock.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "phone_no", length = 30)
    private String phoneNo;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "profile_pic", length = 1000)
    private String profilePic;

    // Account information
    private boolean isEnabled = true;
    private boolean isEmailVerified = false;
    private boolean isPhoneVerified = false;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Credential> credential = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    private Provider provider = Provider.SELF;
    private String providerUserId;


}
