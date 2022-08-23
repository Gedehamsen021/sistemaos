package sistema.os.sistemaos.service.Produtos;

import java.util.List;

import sistema.os.sistemaos.dominio.Produtos;

public interface ProdutosService {

    void salvar(Produtos produtos);
    void editar(Produtos produtos);
    void excluir(Long id);
    List<Produtos> buscarPorCodigo(String id);
    List<Produtos> buscarTodos();
	boolean temOS(Long id);
    List<Produtos> buscarPorDescricao(String descricao);
    
}
