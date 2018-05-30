package ch.supsi.dti.webapp.blogger.login;


import ch.supsi.dti.webapp.blogger.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private BloggerService blogService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ch.supsi.dti.webapp.blogger.data.User user = blogService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList (user.getRole().getRole());
        return new User(username, user.getPassword(), true, true, true, true, auth);
    }
}