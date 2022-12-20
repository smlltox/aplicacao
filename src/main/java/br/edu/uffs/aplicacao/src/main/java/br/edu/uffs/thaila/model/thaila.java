package br.edu.uffs.thaila.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
//import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "usuario")
public class thaila {

    @Id
    @NotNull(message = "Campo id não pode estar em branco!")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequencia")
    @SequenceGenerator(name="sequencia", sequenceName="usuario_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id_usuario")
    private Integer id;

    @NotNull(message = "Campo nome não pode estar em branco!")
    @Size(min = 3, max = 30, message = "O campo deve ter no minimo 3 caracteres e no máximo 30")
    @Pattern(regexp = "[^0-9]*")
    private String nome;

    
    @NotEmpty(message = "Campo CPF não pode estar em branco!")
    @Size(min = 11, max = 11, message = "O campo deve ter 11 caracteres")
    private String cpf;
    
    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull
	@NotEmpty(message = "Não pode estar vazio")
    @Size(min = 1, max = 25)
    private String senha;
    
    @NotNull
    @NotEmpty(message = "Não pode estar vazio")
    @Email(message = "Email deve ter formato correto!")
    @Column(name = "email")
    private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}


