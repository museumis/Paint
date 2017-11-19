import java.awt.EventQueue;

/**
 * 
 * @author Ismael Mart�n Ram�rez
 *
 * https://github.com/museumis
 *
 */
public class Main {

	/**
	 * M�todo main
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
