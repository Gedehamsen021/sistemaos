package sistema.os.sistemaos.service.Fornecedores;

import java.util.List;

import sistema.os.sistemaos.dominio.Fornecedores;

public interface FornecedoresService {

    void salvar(Fornecedores fornecedores);
    void editar(Fornecedores fornecedores);
    void excluir(Long id);
    List<Fornecedores> buscarTodos();
    Fornecedores buscarPorId(Long id);
    List<Fornecedores> buscarPorNome(String nome);
    List<Fornecedores> buscarCPF(String cpf);
}
