package in.app.safelock.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import in.app.safelock.entities.Credential;
import in.app.safelock.entities.User;

public interface CredentialsService {

    Credential save(Credential credential);
    // Credential update(Credential credential);
    Optional<Credential> update(Credential credential);
    Credential getById(long id);
    Page<Credential> searchByServiceName(String serviceNameKeyword, int size, int page, String sortBy, String order, User user);
    void delete(long id);
    List<Credential> getAll();
    Page<Credential> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);
    List<Credential> getByUser(User user);
    Page<Credential> getByUser(User user, int page, int size, String sortField, String sortDirection);
}
