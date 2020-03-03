package br.com.gft.mvc.apiController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class loginApiController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
