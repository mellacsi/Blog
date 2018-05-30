package ch.supsi.dti.webapp.blogger;

import ch.supsi.dti.webapp.blogger.data.*;
import ch.supsi.dti.webapp.blogger.repo.AuthorRepo;
import ch.supsi.dti.webapp.blogger.repo.BlogPostRepo;
import ch.supsi.dti.webapp.blogger.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BloggerService {

    //TODO Repo espongono: repo. exist(id) findOne(id) delete(id o entity) findAll() save(entity) count() chiamateMioMetodo()
    @Autowired
    private BlogPostRepo blogPostRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private AuthorRepo authorRepo;

    public BloggerService() {
    }

    public Iterable<BlogPost> getPosts() {
        return blogPostRepo.findAll();
    }
    public Iterable<Category> getCategories() {
        return categoryRepo.findAll();
    }
    public Iterable<User> getAuthors() {
        return authorRepo.findAll();
    }

    public BlogPost getPostFromId(int id) {
        if(blogPostRepo.exists(id))
            return blogPostRepo.findOne(id);
        return null;
    }

    public BlogPost deletePostFromId(int id) {
        if(blogPostRepo.exists(id)) {
            BlogPost post = blogPostRepo.findOne(id);
            blogPostRepo.delete(id);
            return post;
        }
       return null;
    }

    public BlogPost modifyFromId(BlogPost post, int id) {
        if(blogPostRepo.exists(id)) {
            BlogPost toModify = blogPostRepo.findOne(id);
            toModify.setText(post.getText());
            toModify.setTitle(post.getTitle());
            Category category = categoryRepo.findOne(post.getCategory().getId());
            toModify.setCategory(category);

            blogPostRepo.save(toModify);
            return toModify;
        }
        return null;
    }

    public BlogPost postPost(BlogPost blogPost, String username) {
        BlogPost newPost = new BlogPost();
        newPost.setUser(authorRepo.findOneByUsername(username));
        Category category = categoryRepo.findOne(blogPost.getCategory().getId());
        newPost.setCategory(category);
        newPost.setDate(new Date());
        newPost.setTitle(blogPost.getTitle());
        newPost.setText(blogPost.getText());
        blogPostRepo.save(newPost);
        return newPost;
    }

    public User findUserByUsername(String username) {
        return authorRepo.findOneByUsername(username);
    }

    public void saveUser(User user) {
        User findUser = null;
        findUser = findUserByUsername(user.getUsername());
        if(findUser == null) {
            //System.out.println("SALVO:");
            //System.out.println(user.getUsername() + user.getPassword() + user.getName() + user.getSurname());
            authorRepo.save(new User(user.getName(), user.getSurname(), user.getUsername(), user.getPassword()));
        }
    }

    public void deleteUser(String userName) {
        User findUser = null;
        findUser = findUserByUsername(userName);
        if(findUser != null) {
            //System.out.println("Elimino:");
            //System.out.println(user.getUsername() + user.getPassword() + user.getName() + user.getSurname());
            authorRepo.delete(findUser);
        }
    }

    public List<JsonUser> searchInUser(String q) {
        List<User> list = authorRepo.findByNameContainingOrSurnameContainingOrUsernameContaining(q, q, q);
        List<JsonUser> jsonList = new ArrayList<>();
        for (User user : list) {
            jsonList.add(new JsonUser(user));
        }
        return jsonList;
    }

    public List<JsonBlogPost> searchInPost(String q) {
        List<BlogPost> list = blogPostRepo.findByTitleContainingOrTextContaining(q, q);
        List<JsonBlogPost> jsonList = new ArrayList<>();
        for (BlogPost post : list) {
            jsonList.add(new JsonBlogPost(post));
        }
        return jsonList;
    }

    public void resetPassword(ChangePswUser user) {
        User oldUser = findUserByUsername(user.getUsername());
        User newUser = oldUser;
        if(oldUser != null){
            //System.out.println(user.getUsername() + " old: " + user.getOldPassword() + " new: " + user.getNewPassword());
            //TODO: controllo vecchia psw
            newUser.setPassword(user.getNewPassword());
            deleteUser(oldUser.getUsername());
            saveUser(newUser);
        }
    }
}
