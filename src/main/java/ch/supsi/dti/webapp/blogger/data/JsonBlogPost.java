package ch.supsi.dti.webapp.blogger.data;

import java.util.Date;

public class JsonBlogPost {
    private int id;
    private String title;
    private String text;
    private String user;
    private String category;
    private String date;

    public JsonBlogPost(BlogPost post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.user = post.getUser().getUsername();
        this.category = post.getCategory().getName();
        this.date = post.getDate().toString();
    }
    public JsonBlogPost() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
