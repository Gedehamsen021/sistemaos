package sistema.os.sistemaos.dominio;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // Classe é uma entidade
@Table(name = "FORNECEDORES")
public class Fornecedores extends EntidadeAbstrata<Long> {
    @NotNull(message = "Informe o CPF")
    @Column(name = "cpf_cnpj", nullable = false, unique = true, length = 18)
    private String cpf;

    @NotNull(message = "Informe o nome.")
    @Size(min = 3, max = 60,message = "Nome precisa ter entre {min} e {max} caracteres")
    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "O nome fantasia é obrigatório.")
    @Column(name = "nome_fant", nullable = false ,length = 60)
    private String nome_fant;

    @NotBlank(message = "Informe o endereço.")
    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "complemento")
    private String complemento;

    @NotBlank(message = "Informe o bairro")
    @Column(name = "bairro")
    private String bairro;

    @Column(name = "numero")
    private String numero;

    @NotBlank(message = "Informe a cidade")
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @NotBlank(message = "Informe o CEP")
    @Column(name = "cep", nullable = false)
    private String cep;

    @NotBlank(message = "Informe o estado")
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotBlank(message = "Informe o telefone")
    @Column(name = "telefone")
    private String telefone;

    @Column(name = "telefonefixo")
    private String telefone_fixo;

    @Column(name = "email")
    private String email;


    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_fant() {
        return this.nome_fant;
    }

    public void setNome_fant(String nome_fant) {
        this.nome_fant = nome_fant;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone_fixo() {
        return this.telefone_fixo;
    }

    public void setTelefone_fixo(String telefone_fixo) {
        this.telefone_fixo = telefone_fixo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
