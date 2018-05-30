package ch.supsi.dti.webapp.blogger;

import ch.supsi.dti.webapp.blogger.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;

    //torna come JSON  per Ajax research post
    @RequestMapping(value = "/blogpost/search", method = RequestMethod.GET)
    public ResponseEntity<List<JsonBlogPost>> search(Model model, String q) {
        return new ResponseEntity<List<JsonBlogPost>>(bloggerService.searchInPost(q), HttpStatus.OK);
    }

    //torna come JSON per Ajax, research user
    @RequestMapping(value = "/blogpost/searchuser", method = RequestMethod.GET)
    public ResponseEntity<List<JsonUser>> searchUser(Model model, String q) {
        return new ResponseEntity<List<JsonUser>>(bloggerService.searchInUser(q), HttpStatus.OK);
    }

    @RequestMapping(value = "/research_user", method = RequestMethod.GET)
    public String researchUsers(Model model) {
        model.addAttribute("users", bloggerService.getAuthors());
        return "research_user";
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.GET)
    public String chargePassword(Model model) {
        return "change_password";
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public String changePassword(Model model, @ModelAttribute ChangePswUser user) {
        bloggerService.resetPassword(user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = "/usability", method = RequestMethod.GET)
    public String usability(Model model) {
        return "blogpost-evaluation-report";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegistration(Model model, @ModelAttribute User user) {
        bloggerService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getList(Model model) {
        model.addAttribute("blogList", bloggerService.getPosts());
        return "index";
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    //@PathVariable take id from URL
    public String getPostFromId(Model model, @PathVariable int id) {
        model.addAttribute("post", bloggerService.getPostFromId(id));
        return "details_post";
    }

    @RequestMapping(value = "/blog/new", method = RequestMethod.GET)
    public String setNewPost(Model model) {
        model.addAttribute("categoryList", bloggerService.getCategories());
        return "new_post_form";
    }

    @RequestMapping(value = "/blog/new", method = RequestMethod.POST)
    public String createPost(@ModelAttribute BlogPost blogpost, HttpSession session) {
        SecurityContextImpl sc = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) sc.getAuthentication().getPrincipal();
        String username = user.getUsername();
        bloggerService.postPost(blogpost, username);
        return "redirect:/";
    }

    @RequestMapping(value = "/blog/{id}/edit", method = RequestMethod.GET)
    public String setPostGet(Model model, @PathVariable int id) {
        model.addAttribute("post", bloggerService.getPostFromId(id));
        model.addAttribute("categoryList", bloggerService.getCategories());
        return "edit_post";
    }

    @RequestMapping(value = "/blog/{id}/edit", method = RequestMethod.POST)
    public String setPost(@PathVariable int id, @ModelAttribute BlogPost blogpost) {
       bloggerService.modifyFromId(blogpost, id);
       return "redirect:/";
    }

    @RequestMapping(value = "/user/{username}/delete", method = RequestMethod.GET)
    public String removeUser(Model model, @PathVariable String username) {
        bloggerService.deleteUser(username);
        model.addAttribute("users", bloggerService.getAuthors());
        return "research_user";
    }

    @RequestMapping(value = "/blog/{id}/delete", method = RequestMethod.GET)
    public String removePost(@PathVariable int id) {
        bloggerService.deletePostFromId(id);
        return "redirect:/";
    }
}
