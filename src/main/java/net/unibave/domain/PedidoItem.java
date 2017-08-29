package net.unibave.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PedidoItem {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Pedido pedido;

	@OneToOne
	private Item item;

	public Long getId() {
		return id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "PedidoItem [id=" + id + ", pedido=" + pedido + ", item=" + item + "]";
	}
	
	

}
