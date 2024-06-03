package in.app.safelock.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.app.safelock.entities.Credential;
import in.app.safelock.entities.User;
import in.app.safelock.repository.CredentialsRepo;
import in.app.safelock.services.CredentialsService;
import in.app.safelock.services.RSAEncryptPassword;
import in.app.safelock.support.ResourceNotFoundException;

@Service
public class CredentialsServiceImplementation implements CredentialsService {

    @Autowired
    private CredentialsRepo credentialsRepo;

    @Autowired
    private RSAEncryptPassword rsa;

    @Override
    public Credential save(Credential credential) {
        // yaha pe ham rsa encrypt to call kareke password encrypt karenge
        
        return credentialsRepo.save(credential);
    }

    @Override
    public Optional<Credential> update(Credential credential) {
        return null;
    }
    // public Optional<Credential> update(Credential credential) {
    //     Credential credential1 = credentialsRepo.findById(credential.getId()).orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
    //     credential1.setServiceName(credential.getServiceName());
    //     credential1.setUsername(credential.getUsername());
    //     credential1.setEmail(credential.getEmail());
    //     credential1.setPassword(credential.getPassword());
    //     credential1.setNotes(credential.getNotes());
    //    return Optional.of(credentialsRepo.save(credential1));
    // }

    @Override
    public Credential getById(long id) {
        return credentialsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found with given id " + id));
    }

   
    @Override
    public Page<Credential> searchByServiceName(String serviceNameKeyword, int size, int page, String sortBy, String order, User user){
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return credentialsRepo.findByUserAndNameContaining(user, serviceNameKeyword, pageable);
    }

    @Override
    public void delete(long id) {
        var credential = credentialsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found with given id " + id));
        credentialsRepo.delete(credential);
    }

    @Override
    public List<Credential> getAll() {
        return credentialsRepo.findAll();
    }

    @Override
    public Page<Credential> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,
            User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return credentialsRepo.findByUserAndEmailContaining(user, emailKeyword, pageable);
    }

    

    @Override
    public Page<Credential> getByUser(User user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return credentialsRepo.findByUser(user, pageable);

    }


}
