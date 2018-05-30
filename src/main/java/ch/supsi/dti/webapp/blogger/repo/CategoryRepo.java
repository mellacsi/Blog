package ch.supsi.dti.webapp.blogger.repo;

import ch.supsi.dti.webapp.blogger.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
