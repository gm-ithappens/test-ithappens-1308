package br.com.felipeomachado.apitest1308pulse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/api/v1/test")
public class ControllerTest {

    @GetMapping()
    @Transactional
    public String teste() {

        return "Server Online";
    }

}
