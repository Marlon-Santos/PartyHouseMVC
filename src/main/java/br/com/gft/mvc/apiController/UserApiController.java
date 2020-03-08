package br.com.gft.mvc.apiController;


import br.com.gft.mvc.apiController.dto.UserDto;
import br.com.gft.mvc.config.errorsHandle.BadRequest;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.UserRepository;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserApiController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers() {
        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            List<UserDto> userDto = UserDto.converter(users);
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listUser(@PathVariable Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(new UserDto(user.get()));
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new BadRequest());
        }

    }

}
