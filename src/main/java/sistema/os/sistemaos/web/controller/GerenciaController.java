package sistema.os.sistemaos.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class GerenciaController {
    //Listar pagina clientes.html em localhost/clientes ou dominio/clientes
    @GetMapping("/usuarios")
    public String clientes() {
        //Localização do html em template
        return "paginas/gerencia/usuarios";
    }
    //Listar pagina clientes.html em localhost/servicos ou dominio/servicos
    @GetMapping("/relatorios")
    public String servicos(){
        //Localização do html em template
        return "paginas/gerencia/relatorios";
    }
}
