package sistema.os.sistemaos.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Classe responsavel por identar e armazenar get e set da id e armazenar toString e HashCode
//TODO:Documentar aqui, não mexer até segunda instancia
@MappedSuperclass
public class EntidadeAbstrata<ID extends Serializable> implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EntidadeAbstrata)) {
            return false;
        }
        EntidadeAbstrata<?> entidadeAbstrata = (EntidadeAbstrata<?>) o;
        return Objects.equals(id, entidadeAbstrata.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "id = " + id;
    }
    
}
//https://www.youtube.com/watch?v=nbiTRxUFIBo