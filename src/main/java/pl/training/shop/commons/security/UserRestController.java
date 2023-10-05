package pl.training.shop.commons.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("api/users/me")
@RestController
public class UserRestController {

    @GetMapping
    public User getInfo(Authentication authentication, Principal principal) {
        var userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}
