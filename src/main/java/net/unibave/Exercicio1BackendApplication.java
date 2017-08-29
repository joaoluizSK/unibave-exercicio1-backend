package net.unibave;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.unibave.domain.Cliente;
import net.unibave.domain.Fornecedor;
import net.unibave.domain.Item;
import net.unibave.domain.Pedido;
import net.unibave.domain.PedidoItem;

@SpringBootApplication
public class Exercicio1BackendApplication {

	public static void main(String[] args) {
		// SpringApplication.run(Exercicio1BackendApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicio-1");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Cliente cliente = new Cliente();
		cliente.setNome("Cliente 1 ");
		em.persist(cliente);
		tx.commit();

		tx = em.getTransaction();
		tx.begin();
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Apple");
		em.persist(fornecedor);
		tx.commit();

		tx = em.getTransaction();
		tx.begin();
		Item item = new Item();
		item.setNome("Iphone 7");
		item.adicionarFornecedor(fornecedor);
		em.persist(item);
		tx.commit();

		tx = em.getTransaction();
		tx.begin();
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(new Date());
		em.persist(pedido);
		tx.commit();

		tx = em.getTransaction();
		tx.begin();
		PedidoItem pedidoItem = new PedidoItem();
		pedidoItem.setItem(item);
		pedidoItem.setPedido(pedido);
		em.persist(pedidoItem);
		tx.commit();

		// Listagem
		em = emf.createEntityManager();
		List<Pedido> pedidos = em.createQuery("SELECT p from Pedido p", Pedido.class).getResultList();

		mostrarInformacoesPedido(pedidos);

		em.close();
		emf.close();

	}

	private static void mostrarInformacoesPedido(List<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {

			System.out.println(" ------INFORMACOES------ ");
			System.out.println("Pedido: " + pedido.getId());
			System.out.println("Data Pedido: " + pedido.getDataPedido());

			System.out.println(" ------CLIENTE------ ");
			System.out.println("Cliente: " + pedido.getCliente().getId());
			System.out.println("Cliente: " + pedido.getCliente().getNome());

			List<PedidoItem> itens = pedido.getItens();

			System.out.println(" ------ITENS PEDIDO------ ");
			System.out.println("Qtde Itens: " + itens.size());

			for (PedidoItem item : itens) {
				System.out.println("Sequencia: " + item.getId());

				System.out.println(" ------ITEM------ ");
				System.out.println("Item: " + item.getItem().getId());
				System.out.println("Nome Item: " + item.getItem().getNome());

				List<Fornecedor> fornecedores = item.getItem().getFornecedores();

				for (Fornecedor fornecedor : fornecedores) {
					System.out.println(" ------FORNECEDOR------ ");
					System.out.println("Qtde Fornecedores: " + fornecedores.size());
					System.out.println("Fornecedor: " + fornecedor.getId());
					System.out.println("Nome Fornecedor: " + fornecedor.getNome());

				}

			}
		}
	}
}
