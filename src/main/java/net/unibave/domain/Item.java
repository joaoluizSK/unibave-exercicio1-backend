package net.unibave.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "nome")
	private String nome;

	@ManyToMany
	@JoinTable(joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fornecedor_id") })
	private List<Fornecedor> fornecedores = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Fornecedor> getFornecedores() {
		return Collections.unmodifiableList(this.fornecedores);
	}

	public void adicionarFornecedor(Fornecedor fornecedor) {
		this.fornecedores.add(fornecedor);
	}

	public void removerFornecedor(Fornecedor fornecedor) {
		this.fornecedores.remove(fornecedor);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", nome=" + nome + ", fornecedores=" + Arrays.toString(fornecedores.toArray()) + "]";
	}

}
