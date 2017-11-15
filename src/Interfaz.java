import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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

public class Interfaz {

	private JFrame frame;
	private JPanel navegacion, dibujo, caballete;
	private JButton btnCuatrado, btnCirculo;
	private JLabel textPosX, textPosY, separacion, lienzo;
	private JTextField editFieldX, editFieldY;
	private final File circulo = new File("circulo.gif");
	private final File rectangulo = new File("rectangulo.gif");
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

		// Panel de DIBUJO
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 0;
		data.weightx = 1;
		data.weighty = 1;
		data.fill = GridBagConstraints.BOTH;
		dibujo = new JPanel();
		dibujo.setLayout(null);
		dibujo.setBackground(Color.GRAY);
		frame.getContentPane().add(dibujo, data);

		// Panel de dibujo(Caballete)
		caballete = new JPanel();
		caballete.setLayout(new GridLayout(1, 1));
		caballete.setBounds(50, 100, 400, 400);
		caballete.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		dibujo.add(caballete);

		// Lienzo
		lienzo = new JLabel();
		// canvas
		try {
			//POR AQUIIIIIIIIIII !!!! PON GRAFIS AQUI NO ES UNA IMAGEN,FONDO BLANCO
			canvas = ImageIO.read(circulo);
			escala = canvas.getScaledInstance(caballete.getWidth(), caballete.getHeight(),Image.SCALE_SMOOTH);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(escala);
		lienzo.setIcon(icon);
		caballete.add(lienzo);			
		
		/*
		BufferedImage buffer = new BufferedImage(caballete.getWidth(),caballete.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics graficos = buffer.getGraphics();
		graficos.setColor(Color.BLUE);
		graficos.fillRect(0,0, buffer.getWidth(),buffer.getHeight());		
		graficos.dispose();
		
		*/
			

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
		data.fill = GridBagConstraints.BOTH;
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
		data.fill = GridBagConstraints.BOTH;
		btnCuatrado = new JButton();
		// Imagen del boton
		try {
			canvas = ImageIO.read(rectangulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		escala = canvas.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		icon = new ImageIcon(escala);
		btnCuatrado.setIcon(icon);
		navegacion.add(btnCuatrado, data);

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
		editFieldX = new JTextField();
		navegacion.add(editFieldX, data);

		// Texto de la posicion y
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 5;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		textPosX = new JLabel("Posicion Y");
		navegacion.add(textPosX, data);

		// Texto editable de la posicion Y
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 6;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		editFieldY = new JTextField();
		navegacion.add(editFieldY, data);

	}

	public void iniciarListened() {
	}
}
