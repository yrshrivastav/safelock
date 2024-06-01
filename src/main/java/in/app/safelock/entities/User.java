package in.app.safelock.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_no", length = 30)
    private String phoneNo;

    @Getter(value = AccessLevel.NONE)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_pic", length = 1000)
    private String profilePic;

    // Account information
    @Getter(value = AccessLevel.NONE)
    private boolean isEnabled = true;
    private boolean isEmailVerified = false;
    private boolean isPhoneVerified = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Credential> credential = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    private Provider provider = Provider.SELF;
    private String providerUserId;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
    @Override
    public String getUsername() {
       return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

}
