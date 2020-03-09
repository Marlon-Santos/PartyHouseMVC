package br.com.gft.mvc.apiController;

import br.com.gft.mvc.enums.MusicStyle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estilos")
public class MusicStyleApiController {
    @GetMapping
    public List<String> styleMusic() {
        List<String> styles = new ArrayList<>();
        for (MusicStyle test : MusicStyle.values()) {
            styles.add(test.getMusicStyle());
        }
        return styles;
    }
}
