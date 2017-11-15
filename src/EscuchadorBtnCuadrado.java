import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author Ismael Martin
 *
 */
public class EscuchadorBtnCuadrado implements ActionListener {
	JTextField editFieldX, editFieldY;
	BufferedImage canvas;
	JLabel lienzo;

	public EscuchadorBtnCuadrado(JTextField editX, JTextField editY, BufferedImage c, JLabel l) {
		this.editFieldX = editX;
		this.editFieldY = editY;
		this.canvas = c;
		this.lienzo = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {

			int x = Integer.parseInt(editFieldX.getText());
			int y = Integer.parseInt(editFieldY.getText());
			// Control limites, Si esta fuera del limite se muestra mensaje
			// Si esta dentro del limite se pinta
			if (((x > canvas.getWidth()) || (x < 0)) || ((y > canvas.getHeight()) || (y < 0))) {
				editFieldX.setText("Fuera del ");
				editFieldY.setText("límite. ");
			} else {
				// Introducir cuadrado
				Graphics graficosCu;
				graficosCu = canvas.getGraphics();
				graficosCu.setColor(Color.RED);
				graficosCu.fillRect(x, y, 90, 90);
				graficosCu.dispose();
				lienzo.repaint();
			}
			// Excepcion
		} catch (Exception e2) {
			editFieldX.setText("*Error");
			editFieldY.setText("*Error");
		}
		
	}
}
