package br.com.felipeomachado.apitest1308pulse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping("/api/test")
    public String teste() {
        return "Está ok";
    }

}
