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

import sistema.os.sistemaos.dominio.Fornecedores;
import sistema.os.sistemaos.service.Fornecedores.FornecedoresService;

@Controller
@RequestMapping("/fornecedores")
public class FornecedoresController {
    
    @Autowired
    private FornecedoresService fornecedoresService;

    // Listar pagina fornecedores.html em localhost/fornecedores ou dominio/fornecedores
    @GetMapping("/lista")
    public String fornecedores(ModelMap model) {
        model.addAttribute("fornecedores", fornecedoresService.buscarTodos());
        return "paginas/cadastros/fornecedores/fornecedores";
    }

    //Redireciona para a pagina cadastro atribuindo classe fornecedores a ela
    @GetMapping("/cadastro")
    public String cadastro(Fornecedores fornecedor) {
        return "paginas/cadastros/fornecedores/cadastro";
    }

    //Ao terminar formulario de cadastro chama esse metodo e preenche todos os campos
    @PostMapping("/salvar")
    public String salvar(@RequestParam("cpf") String cpf, @Valid Fornecedores fornecedor, BindingResult erros,RedirectAttributes attr) {
        //Valida se tem erros no cadastro
        if(erros.hasErrors()) {
            return "paginas/cadastros/fornecedores/cadastro";
        }
        //Verifica se o CPF já foi cadastrado
        if(!fornecedoresService.buscarCPF(cpf).isEmpty()) {
            attr.addFlashAttribute("falha","Este CPF/CNPJ já está vinculado a outro Fornecedor");
        } else {
            attr.addFlashAttribute("concluido","Fornecedor adicionado com sucesso");
            fornecedoresService.salvar(fornecedor);
        }
        //Retorna para a pagina de cadastro informando um alerta e limpando todos os campos
        return "redirect:/fornecedores/cadastro";
    }

    //Redireciona para a pagina de editagem com o ID solicitado
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        //Busca pela ID e atribui uma variavel fornecedores
        model.addAttribute("fornecedores", fornecedoresService.buscarPorId(id));
        //Retorna para a pagina de cadastro preenchendo todos os dados
        return "paginas/cadastros/fornecedores/cadastro";
    }

    //Ao editar quando salvar vai rodar esse metodo preenchendo todos os dados necessarios
    @PostMapping("/editar")
    public String editar(Fornecedores fornecedor, RedirectAttributes attr,@Valid Fornecedores fornecedores,BindingResult erros) {
        if(erros.hasErrors()) {
            return "paginas/cadastros/fornecedores/cadastro";
        }
        fornecedoresService.editar(fornecedor);
        attr.addFlashAttribute("concluido","Fornecedor editado com sucesso");
        //Retorna para a pagina de cadastro com todos os campos limpos
        return "redirect:/fornecedores/cadastro";
    }

    //Ao solicitar esse metodo ele vai rodar todos os checks e abrir um modal definido no HTML
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        fornecedoresService.excluir(id);
        attr.addFlashAttribute("concluido","Deu tudo certo");
        return "redirect:/fornecedores/lista";
    }

    //Ao solicitar esse metodo ele vai rodar a busca do fornecedor baseado em seu nome
    @GetMapping("/buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome,ModelMap model) {
        model.addAttribute("fornecedores", fornecedoresService.buscarPorNome(nome));
        return "paginas/cadastros/fornecedores/fornecedores";
    }

}
