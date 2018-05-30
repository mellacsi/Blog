package ch.supsi.dti.webapp.blogger.data;


import javax.persistence.*;
import java.util.Date;

//TODO: Per salvare classe: Entity, Id, costruttore + getter e setter relazioni
@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    private Date date;

    public BlogPost(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public BlogPost() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
