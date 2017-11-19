import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author Ismael Martin Escuchador del boton que pinta un circulo
 *
 */
public class EscuchadorBtnCirculo implements ActionListener {

	JTextField editFieldX, editFieldY;
	BufferedImage canvas;
	JLabel lienzo;
	int tamanio;

	public EscuchadorBtnCirculo(JTextField editX, JTextField editY, BufferedImage c, JLabel l,String tam) {
		this.editFieldX = editX;
		this.editFieldY = editY;
		this.canvas = c;
		this.lienzo = l;
		tamanio = Integer.parseInt(tam);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Coordenadas seleccionadas
			int x = Integer.parseInt(editFieldX.getText());
			int y = Integer.parseInt(editFieldY.getText());
			// Control limites, Si esta fuera del limite nada
			// Si esta dentro del limite se pinta
			if (((x > canvas.getWidth()) || (x < 0)) || ((y > canvas.getHeight()) || (y < 0))) {
				editFieldX.setText("Fuera del ");
				editFieldY.setText("límite. ");
			} else {
				// Introducir circulo
				Graphics graficosCi;
				graficosCi = canvas.getGraphics();
				//graficosCi.setColor(Interfaz.colorDibujo);
				graficosCi.fillOval(x, y, tamanio, tamanio);
				graficosCi.dispose();
				lienzo.repaint();
			}
			// Excepcion
		} catch (Exception ex) {
			editFieldX.setText("*Error");
			editFieldY.setText("*Error");
		}
	}

}
