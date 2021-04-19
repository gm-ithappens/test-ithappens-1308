/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClientesDao;
import dao.ProdutosDao;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.Clientes;
import model.Produtos;

/**
 *
 * @author ANDRADE
 */
public class Frmvendas extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Clientes obj = new Clientes();
	double total, preco, subtotal;
	int qtd;

	DefaultTableModel carrinho;

	public Frmvendas() {
		initComponents();
		this.getContentPane().setBackground(Color.WHITE);

	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		btncancelar = new javax.swing.JButton();
		btnpagamento = new javax.swing.JButton();
		painel_clientes = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		txtdataatual = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		txtnome = new javax.swing.JTextField();
		btnbuscacliente = new javax.swing.JButton();
		txtcpf = new javax.swing.JFormattedTextField();
		jPanel3 = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		txtcodigo = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		txtdescricao = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		txtpreco = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		txtqtd = new javax.swing.JTextField();
		btnbuscaproduto = new javax.swing.JButton();
		btnadd = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tabelaItens = new javax.swing.JTable();
		jPanel5 = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		txttotal = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Pedidos de Venda");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowActivated(java.awt.event.WindowEvent evt) {
				formWindowActivated(evt);
			}
		});

		jPanel1.setBackground(new java.awt.Color(0, 153, 51));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		jLabel1.setText("Pedido de Venda");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(338, 338, 338).addComponent(jLabel1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		btncancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btncancelar.setText("Cancelar Venda");
		btncancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btncancelarActionPerformed(evt);
			}
		});

		btnpagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnpagamento.setText("Pagamento");
		btnpagamento.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnpagamentoActionPerformed(evt);
			}
		});

		painel_clientes.setBackground(new java.awt.Color(255, 255, 255));
		painel_clientes.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Cliente"));

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel3.setText("CPF:");

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel4.setText("Data:");

		txtdataatual.setEditable(false);
		txtdataatual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtdataatual.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtdataatualActionPerformed(evt);
			}
		});

		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel5.setText("Nome:");

		txtnome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtnome.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtnomeActionPerformed(evt);
			}
		});

		btnbuscacliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnbuscacliente.setText("Pesquisar");
		btnbuscacliente.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnbuscaclienteActionPerformed(evt);
			}
		});

		try {
			txtcpf.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtcpf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtcpf.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtcpfKeyPressed(evt);
			}
		});

		javax.swing.GroupLayout painel_clientesLayout = new javax.swing.GroupLayout(painel_clientes);
		painel_clientes.setLayout(painel_clientesLayout);
		painel_clientesLayout
				.setHorizontalGroup(painel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								painel_clientesLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(btnbuscacliente).addGap(151, 151, 151))
						.addGroup(painel_clientesLayout.createSequentialGroup()
								.addGroup(painel_clientesLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(painel_clientesLayout.createSequentialGroup().addComponent(jLabel5)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtnome))
										.addGroup(painel_clientesLayout.createSequentialGroup().addComponent(jLabel3)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jLabel4)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtdataatual, javax.swing.GroupLayout.PREFERRED_SIZE, 113,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 0, Short.MAX_VALUE)));
		painel_clientesLayout.setVerticalGroup(painel_clientesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(painel_clientesLayout.createSequentialGroup().addContainerGap().addGroup(painel_clientesLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel4)
						.addComponent(txtdataatual, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(painel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(19, 19, 19)
						.addGroup(painel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel5))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(btnbuscacliente).addGap(0, 11, Short.MAX_VALUE)));

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Produto"));

		jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel6.setText("Código:");

		txtcodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtcodigo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtcodigoActionPerformed(evt);
			}
		});
		txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtcodigoKeyPressed(evt);
			}
		});

		jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel7.setText("Produto:");

		txtdescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtdescricao.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtdescricaoActionPerformed(evt);
			}
		});

		jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel8.setText("Preço:");

		txtpreco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtpreco.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtprecoActionPerformed(evt);
			}
		});

		jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel9.setText("Qtd:");

		txtqtd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtqtd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtqtdActionPerformed(evt);
			}
		});

		btnbuscaproduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnbuscaproduto.setText("Pesquisar");
		btnbuscaproduto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnbuscaprodutoActionPerformed(evt);
			}
		});

		btnadd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnadd.setText("Adicionar Item");
		btnadd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnaddActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel7)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtdescricao))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout
												.createSequentialGroup().addComponent(jLabel6)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 167,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnbuscaproduto)))
								.addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel8)
												.addGap(18, 18, 18)
												.addComponent(txtpreco, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel9)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtqtd, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel3Layout.createSequentialGroup().addGap(136, 136, 136)
												.addComponent(btnadd)))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(18, 18, 18)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel6).addComponent(btnbuscaproduto))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtdescricao, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel7))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtpreco, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel8)
								.addComponent(txtqtd, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel9))
						.addGap(31, 31, 31).addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));

		jPanel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens do Pedido"));

		tabelaItens.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Código", "Produto", "Qtd", "Preço", "Subtotal" }));
		jScrollPane1.setViewportView(tabelaItens);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));

		jPanel5.setBackground(new java.awt.Color(255, 255, 255));
		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));

		jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel10.setText("Total:");

		txttotal.setEditable(false);
		txttotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		txttotal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txttotalActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addGap(66, 66, 66).addComponent(jLabel10)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 251,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel10)
								.addComponent(txttotal, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
						.addGap(19, 19, 19)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(painel_clientes, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnpagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 137,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE,
										169, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(73, 73, 73)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(painel_clientes, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(btnpagamento, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 56,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(33, Short.MAX_VALUE)));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void btnpagamentoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnpagamentoActionPerformed
		// botao pagamento
		Frmpagamentos telap = new Frmpagamentos();
		telap.txttotal.setText(String.valueOf(total));

		// passo o objeto client para a view forma de pagamento
		telap.cliente = obj;
		// passo o carrinho
		telap.carrinho = carrinho;

		telap.setVisible(true);
		this.dispose();
	}// GEN-LAST:event_btnpagamentoActionPerformed

	private void formWindowActivated(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowActivated
		// Carrega a data atual do sistema
		Date agora = new Date();
		SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");
		String dataformatada = dataBr.format(agora);
		txtdataatual.setText(dataformatada);
	}// GEN-LAST:event_formWindowActivated

	private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btncancelarActionPerformed
		// botao cancelar venda
	}// GEN-LAST:event_btncancelarActionPerformed

	private void txtdataatualActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtdataatualActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtdataatualActionPerformed

	private void txtnomeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtnomeActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtnomeActionPerformed

	private void btnbuscaclienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnbuscaclienteActionPerformed
		// Botão pesqusiar cliente
		Clientes obj = new Clientes();
		ClientesDao dao = new ClientesDao();

		obj = dao.buscaPorCFPClientes((txtcpf.getText()));

		txtnome.setText(obj.getNome());

	}// GEN-LAST:event_btnbuscaclienteActionPerformed

	private void txtcodigoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtcodigoActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtcodigoActionPerformed

	private void txtdescricaoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtdescricaoActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtdescricaoActionPerformed

	private void txtprecoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtprecoActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtprecoActionPerformed

	private void txtqtdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtqtdActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtqtdActionPerformed

	private void btnbuscaprodutoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnbuscaprodutoActionPerformed
		// Botão buscar produto
		Produtos objs = new Produtos();
		ProdutosDao dao = new ProdutosDao();

		objs = dao.buscaPorCodigo(Integer.parseInt(txtcodigo.getText()));

		txtdescricao.setText(objs.getDescricao());
		txtpreco.setText(String.valueOf(objs.getPreco()));
	}// GEN-LAST:event_btnbuscaprodutoActionPerformed

	private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnaddActionPerformed
		// botão salvar item
		qtd = Integer.parseInt(txtqtd.getText());
		preco = Double.parseDouble(txtpreco.getText());

		subtotal = qtd * preco;

		total += subtotal;
		txttotal.setText(String.valueOf(total));

		// Adicionar o produto no carrinho
		carrinho = (DefaultTableModel) tabelaItens.getModel();

		carrinho.addRow(new Object[] { txtcodigo.getText(), txtdescricao.getText(), txtqtd.getText(),
				txtpreco.getText(), subtotal });
	}// GEN-LAST:event_btnaddActionPerformed

	private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txttotalActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txttotalActionPerformed

	private void txtcodigoKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtcodigoKeyPressed
		// buscar produto ao digitar
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			Produtos obj = new Produtos();
			ProdutosDao dao = new ProdutosDao();

			obj = dao.buscaPorCodigo(Integer.parseInt(txtcodigo.getText()));

			txtdescricao.setText(obj.getDescricao());
			txtpreco.setText(String.valueOf(obj.getPreco()));

		}
	}// GEN-LAST:event_txtcodigoKeyPressed

	private void txtcpfKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtcpfKeyPressed
		// buscar cliente ao digitar
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

			ClientesDao dao = new ClientesDao();

			obj = dao.buscaPorCFPClientes((txtcpf.getText()));

			txtnome.setText(obj.getNome());

		}
	}// GEN-LAST:event_txtcpfKeyPressed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Frmvendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Frmvendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Frmvendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Frmvendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Frmvendas().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnadd;
	private javax.swing.JButton btnbuscacliente;
	private javax.swing.JButton btnbuscaproduto;
	private javax.swing.JButton btncancelar;
	private javax.swing.JButton btnpagamento;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPanel painel_clientes;
	private javax.swing.JTable tabelaItens;
	private javax.swing.JTextField txtcodigo;
	private javax.swing.JFormattedTextField txtcpf;
	private javax.swing.JTextField txtdataatual;
	private javax.swing.JTextField txtdescricao;
	private javax.swing.JTextField txtnome;
	private javax.swing.JTextField txtpreco;
	private javax.swing.JTextField txtqtd;
	private javax.swing.JTextField txttotal;
	// End of variables declaration//GEN-END:variables
}
