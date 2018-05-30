package ch.supsi.dti.webapp.blogger.repo;

import ch.supsi.dti.webapp.blogger.data.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {
    List<BlogPost> findByTitleContainingOrTextContaining(String text, String title);
}
