package sistema.os.sistemaos.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sistema.os.sistemaos.dominio.Produtos;
import sistema.os.sistemaos.service.Produtos.ProdutosService;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    // Listar pagina clientes.html em localhost/clientes ou dominio/clientes
    @GetMapping("/lista")
    public String produtos(ModelMap model) {
        model.addAttribute("produtos", produtosService.buscarTodos());
        return "paginas/cadastros/produtos/produtos";
    }

    // Redireciona para a pagina cadastro atribuindo classe CLientes a ela
    @GetMapping("/cadastro")
    public String cadastro(Produtos produtos) {
        return "paginas/cadastros/produtos/cadastro";
    }

    // Ao terminar formulario de cadastro chama esse metodo e preenche todos os
    // campos
    @PostMapping("/salvar")
    public String salvar(@RequestParam("codigo") String codigo, @Valid Produtos produto, BindingResult erros,
            RedirectAttributes attr) {
        // Valida se tem erros no cadastro
        if (erros.hasErrors()) {
            attr.addFlashAttribute("falha", "Houve um erro ao cadastrar o produto, contate o suporte");
            System.out.println(erros);
            return "redirect:/produtos/cadastro";
        }
        // Verifica se o CPF já foi cadastrado
        if (!produtosService.buscarPorCodigo(codigo).isEmpty()) {
            attr.addFlashAttribute("falha", "Este CPF já está vinculado a outro cliente");
        } else {
            attr.addFlashAttribute("concluido", "Cliente adicionado com sucesso");
            produtosService.salvar(produto);
        }
        // Retorna para a pagina de cadastro informando um alerta e limpando todos os campos
        return "redirect:/produtos/cadastro";
    }

    // Ao solicitar esse metodo ele vai rodar a busca do cliente baseado em seu nome
    @GetMapping("/buscar/descricao")
    public String getPordDescricao(@RequestParam("descricao") String descricao, ModelMap model) {
        model.addAttribute("produtos", produtosService.buscarPorDescricao(descricao));
        return "paginas/cadastros/produtos/produtos";
    }

}
