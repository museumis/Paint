import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * 
 * @author Ismael Martin Interfaz de Paint
 *
 */
public class Interfaz {
	private final File circulo = new File("circulo.gif");
	private final File rectangulo = new File("rectangulo.gif");
	private JFrame frame;
	private JPanel navegacion, dibujo, caballete;
	private JButton btnCuadrado, btnCirculo;
	private JLabel textPosX, textPosY, separacion, lienzo;
	private JTextField editFieldX, editFieldY;
	private BufferedImage canvas = null;
	private Image escala = null;
	private ImageIcon icon;

	public Interfaz() {
		frame = new JFrame("PaintIs");
		frame.setBounds(400, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void iniciar() {
		iniciarConponentes();
		iniciarListened();
		frame.setVisible(true);
	}

	public void iniciarConponentes() {
		frame.setLayout(new GridBagLayout());
		GridBagConstraints data;

		// Panel de NAVEGACION
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		data.ipadx = 225;
		data.fill = GridBagConstraints.BOTH;
		navegacion = new JPanel();
		navegacion.setLayout(new GridBagLayout());
		frame.getContentPane().add(navegacion, data);

		// Boton circulo
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 1;
		data.insets = new Insets(10, 0, 0, 0);
		btnCirculo = new JButton();
		// Imagen del boton
		try {
			canvas = ImageIO.read(circulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		escala = canvas.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		icon = new ImageIcon(escala);
		btnCirculo.setIcon(icon);
		navegacion.add(btnCirculo, data);

		// Boton cuadrado
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		data.insets = new Insets(10, 0, 0, 0);
		btnCuadrado = new JButton();
		// Imagen del boton
		try {
			canvas = ImageIO.read(rectangulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		escala = canvas.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		icon = new ImageIcon(escala);
		btnCuadrado.setIcon(icon);
		navegacion.add(btnCuadrado, data);

		// Separador
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 2;
		data.fill = GridBagConstraints.BOTH;
		data.insets = new Insets(10, 0, 0, 0);
		separacion = new JLabel(" ");
		separacion.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.BLACK));
		navegacion.add(separacion, data);

		// Texto de la posicion x
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 3;
		data.fill = GridBagConstraints.BOTH;
		data.insets = new Insets(10, 0, 0, 0);
		textPosX = new JLabel("Posicion X");
		navegacion.add(textPosX, data);

		// Texto editable de la posicion X
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 4;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		editFieldX = new JTextField("10");
		navegacion.add(editFieldX, data);

		// Texto de la posicion y
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 5;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		textPosY = new JLabel("Posicion Y");
		navegacion.add(textPosY, data);

		// Texto editable de la posicion Y
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 6;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		editFieldY = new JTextField("10");
		navegacion.add(editFieldY, data);

		// Panel de DERECHO
		// Panel dibujo
		dibujo = new JPanel();
		dibujo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dibujo.setLayout(new GridLayout(1, 1));
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 0;
		data.weightx = 1;
		data.weighty = 1;
		frame.add(dibujo, data);

		// El lienzo
		lienzo = new JLabel();
		dibujo.add(lienzo);
		canvas = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		lienzo.setIcon(new ImageIcon(canvas));

	}

	public void iniciarListened() {
		// Escuchador boton circulo
		btnCirculo.addActionListener(new EscuchadorBtnCirculo(editFieldX, editFieldY, canvas, lienzo));	
		// Escuchador boton cuadrado
		btnCuadrado.addActionListener(new EscuchadorBtnCuadrado(editFieldX, editFieldY, canvas, lienzo));		
	}
}
