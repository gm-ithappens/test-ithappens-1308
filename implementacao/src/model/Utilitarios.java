package model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ANDRADE
 */
public class Utilitarios {
	// metodo para limparCampos do JPanel
	public void LimpaTela(JPanel container) {
		Component components[] = container.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				((JTextField) component).setText(null);
			}
		}
	}
}
