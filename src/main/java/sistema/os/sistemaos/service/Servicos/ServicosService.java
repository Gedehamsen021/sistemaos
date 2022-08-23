package sistema.os.sistemaos.service.Servicos;

import java.util.List;

import sistema.os.sistemaos.dominio.Servicos;
//TODO:Documentar aqui
public interface ServicosService {
    void salvar(Servicos servicos);
    void editar(Servicos servicos);
    void excluir(Long id);
    Servicos buscarPorId(Long id);
    List<Servicos> buscarTodos();  
}
