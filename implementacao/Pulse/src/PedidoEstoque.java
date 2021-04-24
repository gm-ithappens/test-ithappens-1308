
public class PedidoEstoque {
	int id;
	String obsEntrega;
	double totalGeral;
	Filial filial;
	Usuario usuario;
	Cliente cliente;
	ItensPedido itens[];
	
		//Get e Set id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		//Get e Set observação de entrega
	public String getObsEntrega() {
		return obsEntrega;
	}
	public void setObsEntrega(String obsEntrega) {
		this.obsEntrega = obsEntrega;
	}
		//Get e Set filial
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
		//Get e Set usuário
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		//Get e Set cliente
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adcItem(ItensPedido item) {
		for (int i = 0; i < itens.length; i++) {
			if (itens[i] == null) {
				itens[i] = item;
				break;
			}
		}
	}
	
	public double somaTotal(double ttlGeral) {
		return this.totalGeral += ttlGeral; 
	}

}
