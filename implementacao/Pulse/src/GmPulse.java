import java.util.Scanner;

public class GmPulse {
		public static void main(String[] args) {
			//Variaveis
			int usr;
			int contPsw = 0;
			int operacao;
			int selFilial;
			String psw;
			String selProduto;
			int intProduto = 0;
			double qtdItem;
			double valUnit;
			double vlTotal;
			String maisItens;
			
			// Inicio das inclus�es para opera��es
			// Inclus�o de 2 clientes
			Cliente cliente1 = new Cliente();
			cliente1.id = 001;
			cliente1.nome = "Jose";
			cliente1.cpf = "012.345.678-99";
			cliente1.endereco = "Rua A, 1";
			
			Cliente cliente2 = new Cliente();
			cliente2.id = 002;
			cliente2.nome = "Maria";
			cliente2.cpf = "987.654.321-00";
			cliente2.endereco = "Rua B, 2";
			
			// Inclus�o de 2 filiais
			Filial filial1 = new Filial();
			filial1.id = 001;
			filial1.loja = "Filial 01";
			
			Filial filial2 = new Filial();
			filial2.id = 002;
			filial2.loja = "Filial 02";
			
			// Inclus�o de 3 formas de pagamento
			FormaPagamento pg1 = new FormaPagamento();
			pg1.id = 001;
			pg1.descricao = "A Vista";
			
			FormaPagamento pg2 = new FormaPagamento();
			pg2.id = 002;
			pg2.descricao = "Boleto";
			
			FormaPagamento pg3 = new FormaPagamento();
			pg3.id = 003;
			pg3.descricao = "Cartao";	
			
			// Inclus�o de 2 produtos
			Produto prod1 = new Produto();
			prod1.id = 001;
			prod1.codigo = "0123-4";
			prod1.prodNome = "Arroz";

			Produto prod2 = new Produto();
			prod2.id = 002;
			prod2.codigo = "1234-5";
			prod2.prodNome = "Biscoito";
			
			// Inclus�o de 2 usu�rios
			Usuario user1 = new Usuario();
			user1.id = 001;
			user1.nome = "Usuario 01";
			user1.nomeUsr = "user01";
			user1.senha = "123";
			
			Usuario user2 = new Usuario();
			user2.id = 002;
			user2.nome = "Usuario 02";
			user2.nomeUsr = "user02";
			user2.senha = "456";
			// Fim das inclus�es
									
			// Novo pedido
			
			Scanner ler = new Scanner(System.in);
			
			PedidoEstoque ped1 = new PedidoEstoque();
			ped1.setId(001);
			// Login e senha
			System.out.println("USUARIOS ");
			System.out.println("1. Usuario 01");
			System.out.println("2. Usuario 02");
			System.out.println("Selecione o usuario:");
			usr = ler.nextInt();
			if (usr == 1) {
				ped1.setUsuario(user1);
				System.out.println("Senha:");
				psw = ler.next();
				while (contPsw < 2) {					
					if (psw != null && !psw.equals(ped1.getUsuario().senha)) {
						System.out.println("Senha:");
						psw = ler.next();
					} else {
						break;
					}
				}				
			} else if (usr == 2) {
				ped1.setUsuario(user2);
				System.out.println("Senha:");
				psw = ler.next();
				while (contPsw < 2) {					
					if (psw != null && !psw.equals(ped1.getUsuario().senha)) {
						System.out.println("Senha:");
						psw = ler.next();
					} else {
						break;
					}
				}
			}
			// Fim login e senha
			
			// Sele��o de Filial
			System.out.println("FILIAIS");
			System.out.println("1. Filial 01");
			System.out.println("2. Filial 02");
			System.out.println("Selecione a filial:");
			selFilial = ler.nextInt();
			if (selFilial == 1) {
				ped1.setFilial(filial1);
			} else if (selFilial == 2) {
				ped1.setFilial(filial2);
			}
			//Fim filial
			
			// Inicio opera��o
			System.out.println("OPERACAO");
			System.out.println("1. Entrada");
			System.out.println("2. Saida");
			System.out.println("Selecione a operacao:");
			operacao = ler.nextInt();				
			//Fim opera��o
			
			
			// Adicionando itens
			System.out.println("PEDIDO ABERTO");
			//Quantidade definida para 4 itens
			ped1.itens = new ItensPedido[2]; 
			
			//Item 1
			ItensPedido item1 = new ItensPedido(); 
			System.out.println("Codigo do produto:");
			selProduto = ler.next();
			// Teste e busca para inser��o produto
			if (selProduto.matches("^[0-9]*")) {
				intProduto = Integer.decode(selProduto);
			} 			
			if (selProduto.equals(prod1.prodNome) || selProduto.equals(prod1.codigo) || intProduto == prod1.id) {				
				item1.setProduto(prod1);
			} else if (selProduto.equals(prod2.prodNome) || selProduto.equals(prod2.codigo) || intProduto == prod2.id) {
				item1.setProduto(prod2);
			}
			item1.setId(001);
			item1.setStatus("Ativo");
			System.out.println("Quantidade:");
			qtdItem = ler.nextDouble();
			item1.setQtdItem(qtdItem);
			System.out.println("Valor unitario:");
			valUnit = ler.nextDouble();
			item1.setValUnitario(valUnit);			
			item1.calculaTotal(qtdItem, valUnit);	
			System.out.println("Item 1");
			System.out.println(item1.getProduto().id + " | " + item1.getProduto().codigo + " | " + item1.getProduto().prodNome + " | " + item1.getQtdItem() + " | " + item1.getValUnitario() + " | " + item1.getValTotal());
			ped1.adcItem(item1);
			ped1.somaTotal(item1.getValTotal());			

			// Mais item ou n�o
			System.out.println("Deseja adicionar mais itens? (S/N)");
			maisItens = ler.next();
			if (maisItens.equals("S") || maisItens.equals("s")) {
				
				//Item 2
				ItensPedido item2 = new ItensPedido(); 
				System.out.println("Codigo do produto:");
				selProduto = ler.next();
				// Teste e busca para inser��o produto
				if (selProduto.matches("^[0-9]*")) {
					intProduto = Integer.decode(selProduto);
				}
				if (selProduto.equals(prod2.prodNome) || selProduto.equals(prod2.codigo) || intProduto == prod2.id) {					
					item2.setProduto(prod2);
					if (item1.getProduto().equals(item2.getProduto())) {
						selProduto = null;
						System.out.println("Produto j� inserido");
						System.out.println("Reinsira o codigo do produto:");
						item2.setProduto(null);
						selProduto = ler.next();				
					} else {
						item2.setProduto(prod1);						
						}				
				}
				item2.setId(002);
				item2.setStatus("Ativo");
				System.out.println("Quantidade:");
				qtdItem = ler.nextDouble();
				item2.setQtdItem(qtdItem);
				System.out.println("Valor unitario:");
				valUnit = ler.nextDouble();
				item2.setValUnitario(valUnit);			
				item2.calculaTotal(qtdItem, valUnit);	
				System.out.println("Item 2");
				System.out.println(item2.getProduto().id + " | " + item2.getProduto().codigo + " | " + item2.getProduto().prodNome + " | " + item2.getQtdItem() + " | " + item2.getValUnitario() + " | " + item2.getValTotal());
				ped1.adcItem(item2);
				ped1.somaTotal(item2.getValTotal());
				
		}
			System.out.println("Valor total do pedido: R$ " + ped1.totalGeral);
		
			// Fim itens
			
			//Testes
	
			

			//Fim teste
			
			
		}

}
