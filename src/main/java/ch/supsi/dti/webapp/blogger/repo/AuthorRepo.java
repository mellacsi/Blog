package ch.supsi.dti.webapp.blogger.repo;


import ch.supsi.dti.webapp.blogger.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<User, Integer> {
    User findOneByUsername(String username);

    List<User> findByNameContainingOrSurnameContainingOrUsernameContaining(String name, String surname, String username);
}
