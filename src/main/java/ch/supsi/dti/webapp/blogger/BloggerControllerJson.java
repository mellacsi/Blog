package ch.supsi.dti.webapp.blogger;

import ch.supsi.dti.webapp.blogger.data.BlogPost;
import ch.supsi.dti.webapp.blogger.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class BloggerControllerJson {
    //va a prendere sempre la stessa istanza del service e la inietta
    @Autowired
    private BloggerService bloggerService;

    @RequestMapping(value = "/blogpost", method = RequestMethod.GET)
    public ResponseEntity<Iterable<BlogPost>> getPosts() {
        return new ResponseEntity<Iterable<BlogPost>>(bloggerService.getPosts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.GET)
    //@PathVariable take id from URL
    public ResponseEntity<BlogPost> getPostFromId(@PathVariable int id) {
        BlogPost post = bloggerService.getPostFromId(id);
        if (post == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BlogPost> deletePostFromId(@PathVariable int id) {
        BlogPost post = bloggerService.deletePostFromId(id);
        if (post != null)
            return new ResponseEntity<BlogPost>(post, HttpStatus.OK);
        return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BlogPost> modifyFromId(@RequestBody BlogPost post, @PathVariable int id) {
        BlogPost findedPost = bloggerService.modifyFromId(post, id);
        if (findedPost == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(findedPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogpost", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> postPost(@RequestBody BlogPost post, HttpSession session) {
        SecurityContextImpl sc = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        User user = (User) sc.getAuthentication().getPrincipal();
        return new ResponseEntity<>(bloggerService.postPost(post, user.getUsername()), HttpStatus.OK);
    }
}
