package sistema.os.sistemaos.web.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
public class HomeController {
    // TODO: Retirar daqui depois
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
    //Listar pagina Home.html em localhost/ ou dominio/
    @GetMapping("/")
    public String home() {
        //Localização do html em template
        return "/home";
    }
}
