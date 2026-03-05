package com.fiap.minha_primeira_api.DTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/*
 * DTO (Data Transfer Object) -- Objeto para transferência de dados
 * Usado para enviar uma resposta ao cliente sem expor a entidade completa
 * Estende RepresentationModel para incluir links HATEOAS
 */

@Relation(collectionRelation="usuarios",itemRelation="usuario")
public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO>{

	private Long id;
	private String nome;
	private String email;
	private int idade;
	
	
	
	public UsuarioResponseDTO(Long id, String nome, String email, int idade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}
	
	public UsuarioResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioResponseDTO(Iterable<Link> initialLinks) {
		super(initialLinks);
		// TODO Auto-generated constructor stub
	}


	public UsuarioResponseDTO(Link initialLink) {
		super(initialLink);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	//Método helper para criar DTO a partir do modelo (model)
	public static UsuarioResponseDTO fromUsuario (com.fiap.minha_primeira_api.model.Usuario usuario) {
		return new UsuarioResponseDTO (
				usuario.getId(),
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getIdade()
				);
	}
}
