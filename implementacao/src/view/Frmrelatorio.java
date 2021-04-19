/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ItensPedidosDao;
import dao.PedidosDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ItensPedidos;
import model.Pedidos;

/**
 *
 * @author ANDRADE
 */
public class Frmrelatorio extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form Frmrelatorio
	 */
	public Frmrelatorio() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		txtdatainicio = new javax.swing.JFormattedTextField();
		jLabel8 = new javax.swing.JLabel();
		txtdatafim = new javax.swing.JFormattedTextField();
		btnpesquisar = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		tabelarelatorio = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Relatório de Vendas");

		jPanel1.setBackground(new java.awt.Color(0, 153, 51));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		jLabel1.setText("Relatório de Vendas");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(305, 305, 305).addComponent(jLabel1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel1).addContainerGap()));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta por Data"));

		jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel7.setText("Data Inicial:");

		try {
			txtdatainicio.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtdatainicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtdatainicio.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtdatainicioActionPerformed(evt);
			}
		});
		txtdatainicio.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtdatainicioKeyPressed(evt);
			}
		});

		jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel8.setText("Data Final:");

		try {
			txtdatafim.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtdatafim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtdatafim.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtdatafimActionPerformed(evt);
			}
		});
		txtdatafim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtdatafimKeyPressed(evt);
			}
		});

		btnpesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnpesquisar.setText("Pesquisar");
		btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnpesquisarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(114, 114, 114).addComponent(jLabel7)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtdatainicio, javax.swing.GroupLayout.PREFERRED_SIZE, 155,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(39, 39, 39).addComponent(jLabel8)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtdatafim, javax.swing.GroupLayout.PREFERRED_SIZE, 161,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(btnpesquisar).addContainerGap(118, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtdatainicio, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel7)
								.addComponent(txtdatafim, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel8).addComponent(btnpesquisar))
						.addContainerGap(29, Short.MAX_VALUE)));

		tabelarelatorio.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Código", "Data da Venda", "Cliente", "Total da Venda", "Obs" }));
		tabelarelatorio.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabelarelatorioMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tabelarelatorio);
		if (tabelarelatorio.getColumnModel().getColumnCount() > 0) {
			tabelarelatorio.getColumnModel().getColumn(0).setHeaderValue("Código");
			tabelarelatorio.getColumnModel().getColumn(3).setResizable(false);
			tabelarelatorio.getColumnModel().getColumn(4).setResizable(false);
		}

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jScrollPane1))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
						.addContainerGap()));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void txtdatainicioKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtdatainicioKeyPressed
		// Buscar endereço pelo CEP ao pressionar Enter

	}// GEN-LAST:event_txtdatainicioKeyPressed

	private void txtdatafimKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtdatafimKeyPressed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtdatafimKeyPressed

	private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnpesquisarActionPerformed
		// Botão pesqusiar prelatoriio de vendas por período
		try {
			// Receber as datas
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			LocalDate data_inicio = LocalDate.parse(txtdatainicio.getText(), formato);
			LocalDate data_fim = LocalDate.parse(txtdatafim.getText(), formato);

			PedidosDao dao = new PedidosDao();
			List<Pedidos> lista = dao.listarPedidosPorPeriodo(data_inicio, data_fim);

			DefaultTableModel dados = (DefaultTableModel) tabelarelatorio.getModel();
			dados.setNumRows(0);

			for (Pedidos v : lista) {
				dados.addRow(new Object[] { v.getId(), v.getData_venda(), v.getCliente().getNome(), v.getTotal_venda(),
						v.getObs() });

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Digite seu intervalo de datas!");
		}

	}// GEN-LAST:event_btnpesquisarActionPerformed

	private void txtdatainicioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtdatainicioActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtdatainicioActionPerformed

	private void txtdatafimActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtdatafimActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtdatafimActionPerformed

	private void tabelarelatorioMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tabelarelatorioMouseClicked
		// Clicou em uma linha da tabela de relatório e Pedidos
		Frmrdetalhevenda tela = new Frmrdetalhevenda();

		tela.txtcliente.setText(tabelarelatorio.getValueAt(tabelarelatorio.getSelectedRow(), 2).toString());
		tela.txttotal.setText(tabelarelatorio.getValueAt(tabelarelatorio.getSelectedRow(), 3).toString());
		tela.txtdata.setText(tabelarelatorio.getValueAt(tabelarelatorio.getSelectedRow(), 1).toString());
		tela.txtobs.setText(tabelarelatorio.getValueAt(tabelarelatorio.getSelectedRow(), 4).toString());

		int produto_id = Integer.parseInt(tabelarelatorio.getValueAt(tabelarelatorio.getSelectedRow(), 0).toString());

		ItensPedidosDao dao_item = new ItensPedidosDao();
		List<ItensPedidos> listaitens = dao_item.listarItensPorPedidos(produto_id);

		DefaultTableModel dados = (DefaultTableModel) tela.tabelarelatoriodetalhes.getModel();
		dados.setNumRows(0);

		for (ItensPedidos c : listaitens) {
			dados.addRow(new Object[] {

					c.getId(), c.getProduto().getDescricao(), c.getQtd(), c.getProduto().getPreco(), c.getSubtotal() });

		}

		tela.setVisible(true);
	}// GEN-LAST:event_tabelarelatorioMouseClicked

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
			java.util.logging.Logger.getLogger(Frmrelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Frmrelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Frmrelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Frmrelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Frmrelatorio().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnpesquisar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tabelarelatorio;
	private javax.swing.JFormattedTextField txtdatafim;
	private javax.swing.JFormattedTextField txtdatainicio;
	// End of variables declaration//GEN-END:variables
}
