package in.app.safelock.repository;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.app.safelock.entities.Credential;
import in.app.safelock.entities.User;

@Repository
public interface CredentialsRepo extends JpaRepository<Credential, Long>{

    // find the contact by user
    // custom finder method
    Page<Credential> findByUser(User user, Pageable pageable);

    // custom query method
    // @Query("SELECT c FROM Credential c WHERE c.user.id = :userId")
    // Optional<Credential> findById(@Param("userId") long userId);

    Page<Credential> findByUserAndNameContaining(User user, String serviceNamekeyword, Pageable pageable);

    Page<Credential> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);

    List<Credential> findByUser(User user);

    // List<Credential> findByServiceName(String serviceName);
}
