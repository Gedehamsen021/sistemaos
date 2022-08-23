package sistema.os.sistemaos.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sistema.os.sistemaos.dominio.Clientes;
import sistema.os.sistemaos.service.Clientes.ClientesService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clienteservice;

    // Listar pagina clientes.html em localhost/clientes ou dominio/clientes
    @GetMapping("/lista")
    public String clientes(ModelMap model) {
        model.addAttribute("clientes", clienteservice.buscarTodos());
        return "paginas/cadastros/clientes/clientes";
    }

    //Redireciona para a pagina cadastro atribuindo classe CLientes a ela
    @GetMapping("/cadastro")
    public String cadastro(Clientes clientes) {
        return "paginas/cadastros/clientes/cadastro";
    }

    //Ao terminar formulario de cadastro chama esse metodo e preenche todos os campos
    @PostMapping("/salvar")
    public String salvar(@RequestParam("cpf") String cpf, @Valid Clientes cliente, BindingResult erros,RedirectAttributes attr) {
        //Valida se tem erros no cadastro
        if(erros.hasErrors()) {
            return "paginas/cadastros/clientes/cadastro";
        }
        //Verifica se o CPF já foi cadastrado
        if(!clienteservice.buscarCPF(cpf).isEmpty()) {
            attr.addFlashAttribute("falha","Este CPF já está vinculado a outro cliente");
        } else {
            attr.addFlashAttribute("concluido","Cliente adicionado com sucesso");
            clienteservice.salvar(cliente);
        }
        //Retorna para a pagina de cadastro informando um alerta e limpando todos os campos
        return "redirect:/clientes/cadastro";
    }

    //Redireciona para a pagina de editagem com o ID solicitado
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        //Busca pela ID e atribui uma variavel clientes
        model.addAttribute("clientes", clienteservice.buscarPorId(id));
        //Retorna para a pagina de cadastro preenchendo todos os dados
        return "paginas/cadastros/clientes/cadastro";
    }

    //Ao editar quando salvar vai rodar esse metodo preenchendo todos os dados necessarios
    @PostMapping("/editar")
    public String editar(Clientes clientes, RedirectAttributes attr,@Valid Clientes cliente,BindingResult erros) {
        if(erros.hasErrors()) {
            return "paginas/cadastros/clientes/cadastro";
        }
        clienteservice.editar(clientes);
        attr.addFlashAttribute("concluido","Cliente editado com sucesso");
        //Retorna para a pagina de cadastro com todos os campos limpos
        return "redirect:/clientes/cadastro";
    }

    //Ao solicitar esse metodo ele vai rodar todos os checks e abrir um modal definido no HTML
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        //Vai verificar se tem OS
        if(clienteservice.temOS(id)){
            attr.addFlashAttribute("falha","Este cliente já tem uma OS ou mais em aberta");
        } else {
            clienteservice.excluir(id);
            attr.addFlashAttribute("concluido","Deu tudo certo");
        }
        //Retorna para a pagina de lista de clientes
        return "redirect:/clientes/lista";
    }

    //Ao solicitar esse metodo ele vai rodar a busca do cliente baseado em seu nome
    @GetMapping("/buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome,ModelMap model) {
        model.addAttribute("clientes", clienteservice.buscarPorNome(nome));
        return "paginas/cadastros/clientes/clientes";
    }
}
