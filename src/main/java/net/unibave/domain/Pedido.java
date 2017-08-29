package net.unibave.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Cliente cliente;

	@Temporal(value = TemporalType.DATE)
	private Date dataPedido;

	@OneToMany(mappedBy = "pedido")
	private List<PedidoItem> itens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<PedidoItem> getItens() {
		return Collections.unmodifiableList(itens);
	}

	public void removerItem(PedidoItem item) {
		this.itens.remove(item);
	}

	public void adicionarItem(PedidoItem item) {
		this.itens.add(item);
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cliente=" + cliente + ", dataPedido=" + dataPedido + ", itens="
				+ Arrays.toString(itens.toArray()) + "]";
	}

}
