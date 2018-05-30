package ch.supsi.dti.webapp.blogger.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<BlogPost> blogPosts;

    public Category(String name){
        this.name = name;
    }
    public Category(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
