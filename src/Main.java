import java.awt.EventQueue;

/**
 * 
 * @author Ismael Martín Ramírez
 *
 * https://github.com/museumis
 *
 */
public class Main {

	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz ventana = new Interfaz();
					ventana.iniciar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
