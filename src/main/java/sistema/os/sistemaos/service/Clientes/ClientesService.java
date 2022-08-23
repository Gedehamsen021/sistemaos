package sistema.os.sistemaos.service.Clientes;

import java.util.List;

import sistema.os.sistemaos.dominio.Clientes;

//TODO:Documentar aqui
public interface ClientesService {
    
    void salvar(Clientes cliente);
    void editar(Clientes cliente);
    void excluir(Long id);
    Clientes buscarPorId(Long id);
    List<Clientes> buscarTodos();
	boolean temOS(Long id);
    List<Clientes> buscarCPF(String cpf);
    List<Clientes> buscarPorNome(String nome);
}
