package ch.supsi.dti.webapp.blogger.loader;

import ch.supsi.dti.webapp.blogger.data.Category;
import ch.supsi.dti.webapp.blogger.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LoadCategory {
    @Autowired
    private CategoryRepo categoryRepo;

    public LoadCategory(){ }

    @PostConstruct
    public void loadUsers(){
        if(categoryRepo.findAll().size() == 0) {
            categoryRepo.save(new Category("Science"));
            categoryRepo.save(new Category("Sport"));
            categoryRepo.save(new Category("Culture"));
        }
    }
}
