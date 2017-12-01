import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.omg.CORBA.portable.ValueBase;

/**
 * 
 * @author Ismael Martín Ramírez
 *
 *         https://github.com/museumis
 *
 */
public class Interfaz {
	private final File circulo = new File("circulo.gif");
	private final File rectangulo = new File("rectangulo.gif");
	private JFrame frame;
	private JPanel navegacion, dibujo, extra;
	private JButton btnCuadrado, btnCirculo, btnColor, btnLinea;
	private JLabel textPosX, textPosY, separador, lienzo, labelTamanio;
	private JTextField editFieldX, editFieldY, editFieldTamanio;
	private BufferedImage canvas = null, canvasBtnCirculo = null, canvasBtnCuadrado = null;
	private Image escala = null;
	private ImageIcon icon;
	private Color colorDibujo = Color.BLUE;
	private int tamanioFigura = 100;
	private int ratonX, ratonY, preRatonX=-1, preRatonY=-1;
	private int grafico = 0;// 0-circulo,1-cuadrado,2-línea

	// Boton para obtener el color.
	private JButton btnPicker_G1I;

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

		// Panel de DERECHO
		// Panel dibujo
		dibujo = new JPanel();
		dibujo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dibujo.setLayout(new GridBagLayout());
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

		// Panel de NAVEGACION
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		data.ipadx = 155;
		data.fill = GridBagConstraints.BOTH;
		navegacion = new JPanel();
		navegacion.setLayout(new GridBagLayout());
		frame.getContentPane().add(navegacion, data);

		// Boton Picker
		GridBagConstraints data_G1I = new GridBagConstraints();
		data_G1I.gridx = 0;
		data_G1I.gridy = 1;
		data_G1I.insets = new Insets(10, 0, 0, 0);
		data_G1I.fill = GridBagConstraints.BOTH;
		btnPicker_G1I = new JButton("Picker");
		navegacion.add(btnPicker_G1I, data_G1I);

		// Boton linea
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		btnLinea = new JButton("Linea");
		navegacion.add(btnLinea, data);
		// Boton circulo
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 2;
		data.insets = new Insets(10, 0, 0, 0);
		btnCirculo = new JButton();
		// Imagen del boton
		try {
			canvasBtnCirculo = ImageIO.read(circulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		escala = canvasBtnCirculo.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		icon = new ImageIcon(escala);
		btnCirculo.setIcon(icon);
		navegacion.add(btnCirculo, data);

		// Boton cuadrado
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 3;
		data.insets = new Insets(10, 0, 0, 0);
		btnCuadrado = new JButton();
		// Imagen del boton
		try {
			canvasBtnCuadrado = ImageIO.read(rectangulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		escala = canvasBtnCuadrado.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		icon = new ImageIcon(escala);
		btnCuadrado.setIcon(icon);
		navegacion.add(btnCuadrado, data);

		// Separador
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 4;
		data.fill = GridBagConstraints.BOTH;
		data.insets = new Insets(10, 0, 0, 0);
		separador = new JLabel(" ");
		separador.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.BLACK));
		navegacion.add(separador, data);

		// Texto de la posicion x
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 5;
		data.fill = GridBagConstraints.BOTH;
		data.insets = new Insets(10, 0, 0, 0);
		textPosX = new JLabel("Posicion X");
		navegacion.add(textPosX, data);

		// Texto editable de la posicion X
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 6;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		editFieldX = new JTextField("10");
		navegacion.add(editFieldX, data);

		// Texto de la posicion y
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 7;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		textPosY = new JLabel("Posicion Y");
		navegacion.add(textPosY, data);

		// Texto editable de la posicion Y
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 8;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		editFieldY = new JTextField("10");
		navegacion.add(editFieldY, data);

		// PANEL EXTRA,derecha
		data = new GridBagConstraints();
		data.gridx = 2;
		data.gridy = 0;
		data.ipadx = 155;
		data.fill = GridBagConstraints.BOTH;
		extra = new JPanel();
		extra.setLayout(new GridBagLayout());
		frame.getContentPane().add(extra, data);
		// boton color
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		data.insets = new Insets(10, 0, 0, 0);
		btnColor = new JButton("Color");
		btnColor.setBackground(colorDibujo);
		extra.add(btnColor);
		// Separador
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 1;
		data.fill = GridBagConstraints.BOTH;
		data.insets = new Insets(10, 0, 0, 0);
		separador = new JLabel(" ");
		separador.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));
		extra.add(separador, data);
		// Texto tamanio
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 2;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		labelTamanio = new JLabel("Tamaño (r / l)");
		extra.add(labelTamanio, data);
		// Texto editable de la posicion Y
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 3;
		data.insets = new Insets(10, 0, 0, 0);
		data.fill = GridBagConstraints.BOTH;
		editFieldTamanio = new JTextField(String.valueOf(tamanioFigura));
		extra.add(editFieldTamanio, data);
	}

	public void iniciarListened() {
		// ******************************
		// Evento de los componentes
		// ******************************
		// Escuchador boton circulo
		btnCirculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grafico = 0;
			}

		});

		// Escuchador boton cuadrado
		btnCuadrado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grafico = 1;
			}
		});

		// Botón línea
		btnLinea.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				grafico = 2;
			}
		});
		// Boton del color
		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				colorDibujo = JColorChooser.showDialog(dibujo, " Elige Color. ", colorDibujo);
				btnColor.setBackground(colorDibujo);
				lienzo.repaint();

			}
		});

		/**
		 * Método que abre un frame y muesta el color del pixel en el que se cliquea(
		 * _G1I)
		 */
		btnPicker_G1I.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ???? ***vistaPicker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Ventana Picker
				JFrame vistaPicker_G1I = new JFrame("PICKER");
				vistaPicker_G1I.setBounds(0, 0, 300, 200);
				vistaPicker_G1I.setVisible(true);
				vistaPicker_G1I.setLayout(new GridLayout(3, 1));
				GridBagConstraints data_G1I;
				// Panel de formato de colores
				// Panel RGB
				JPanel panelRGB_G1I = new JPanel();
				panelRGB_G1I.setLayout(new GridLayout(1, 1));
				panelRGB_G1I.setBorder(BorderFactory.createTitledBorder("RGB"));
				// Edit text RGB
				JTextField editRgb_G1I = new JTextField();
				editRgb_G1I.setHorizontalAlignment(0);
				panelRGB_G1I.add(editRgb_G1I);
				// Añandir paneles de colores al frame picker
				vistaPicker_G1I.add(panelRGB_G1I);
				vistaPicker_G1I.repaint();
				lienzo.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// Obtener color del puntero
						Robot robot_G1I;
						try {
							robot_G1I = new Robot();
							Color color_G1I = robot_G1I.getPixelColor(e.getXOnScreen(), e.getYOnScreen());
							// RGB
							String colorRgb_G1I = color_G1I.toString();
							// Formateo color
							colorRgb_G1I = colorRgb_G1I.substring(colorRgb_G1I.indexOf("["), colorRgb_G1I.length());
							// Mostar
							editRgb_G1I.setText(colorRgb_G1I);

							// HSB
							float valor = 0.9f;
							float saturacion_G1I = 1.0f;
							float matriz = 0.8f;
							Color colorHSB_G1I = color_G1I.getHSBColor(valor, saturacion_G1I, matriz);
//							System.out.println(colorHSB_G1I.toString());

							// Excepciones
						} catch (AWTException e1_G1I) {
							System.out.println("Ocurrió un error");// e1_G1I.printStackTrace();
						}
						vistaPicker_G1I.repaint();
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});

			}
		});// Fin del escuchador del botón de picker _G1I

		// ******************************
		// Evento del ratón
		// ******************************
		
		lienzo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				preRatonX= e.getX();
				preRatonY = e.getY();
			}
		});
		lienzo.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {// TODO Auto-generated method stub
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				ratonX = e.getX();
				ratonY = e.getY();
				if (preRatonX == -1) {
					preRatonX = ratonX;
				}
				if (preRatonY == -1) {
					preRatonY = ratonY;
				}
				

				editFieldX.setText(String.valueOf(ratonX));
				editFieldY.setText(String.valueOf(ratonY));
				switch (grafico) {
				case 0: {
					try {
						// Coordenadas seleccionadas
						int x = Integer.parseInt(editFieldX.getText());
						int y = Integer.parseInt(editFieldY.getText());
						tamanioFigura = Integer.parseInt(editFieldTamanio.getText());

						// Control limites, Si esta fuera del limite nada
						// Si esta dentro del limite se pinta
						if (((x > canvas.getWidth()) || (x < 0)) || ((y > canvas.getHeight()) || (y < 0))) {
							editFieldX.setText("");
							editFieldY.setText("");
						} else {
							// Introducir circulo
							Graphics graficosCi;
							graficosCi = canvas.getGraphics();
							graficosCi.setColor(colorDibujo);
							graficosCi.fillOval(x - (tamanioFigura / 2), y - (tamanioFigura / 2), tamanioFigura,
									tamanioFigura);
							graficosCi.dispose();
							lienzo.repaint();
						}
						grafico = 0;
						// Excepcion
					} catch (Exception ex) {
						editFieldX.setText("");
						editFieldY.setText("");
						JOptionPane.showMessageDialog(null, "Datos erróneos", "Error...", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				case 1: {
					// Introducir cuadrado
					try {
						int x = Integer.parseInt(editFieldX.getText());
						int y = Integer.parseInt(editFieldY.getText());
						tamanioFigura = Integer.parseInt(editFieldTamanio.getText());
						// Control limites, Si esta fuera del limite se muestra mensaje
						// Si esta dentro del limite se pinta
						if (((x > canvas.getWidth()) || (x < 0)) || ((y > canvas.getHeight()) || (y < 0))) {
							editFieldX.setText("");
							editFieldY.setText("");
						} else {
							// Introducir cuadrado
							Graphics graficosCu;
							graficosCu = canvas.getGraphics();
							graficosCu.setColor(colorDibujo);
							graficosCu.fillRect(x - (tamanioFigura / 2), y - (tamanioFigura / 2), tamanioFigura,
									tamanioFigura);
							graficosCu.dispose();
							lienzo.repaint();
						}
						grafico = 1;
						// Excepcion
					} catch (Exception e2) {
						editFieldX.setText("");
						editFieldY.setText("");
						JOptionPane.showMessageDialog(null, "Datos erróneos", "Error...", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}

				case 2: {
					// Introducir linea
					Graphics graficosLi;
					graficosLi = canvas.getGraphics();
					graficosLi.setColor(colorDibujo);
					graficosLi.drawLine(preRatonX, preRatonY, e.getX(), e.getY());
					graficosLi.dispose();
					preRatonX = e.getX();
					preRatonY = e.getY();
					lienzo.repaint();
					
					grafico = 2;
					break;
				}
				default:
					break;
				}
			
			}

		});

	}

}
