package ch.supsi.dti.webapp.blogger.loader;

import ch.supsi.dti.webapp.blogger.repo.AuthorRepo;
import ch.supsi.dti.webapp.blogger.data.Role;
import ch.supsi.dti.webapp.blogger.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//TODO: carica in memoria
@Service
public class LoadUsers {
    @Autowired
    private AuthorRepo authorRepo;

    public LoadUsers(){ }

    //si assicura di caricare gli utenti nel DB subito dopo che l'oggetto viene iniettato
    @PostConstruct
    public void loadUsers(){
        if(authorRepo.findAll().size() == 0)
            authorRepo.save(new User("admin", "1234", new Role("ROLE_ADMIN")));

    }
}
