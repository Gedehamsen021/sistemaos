package sistema.os.sistemaos.dao;

import java.util.List;

import sistema.os.sistemaos.dominio.Servicos;

public interface ServicosDAO {

    void save(Servicos servicos);

    void update(Servicos servicos);

    void delete(Long id);

    Servicos findById(Long id);

    List<Servicos> findAll();
}
