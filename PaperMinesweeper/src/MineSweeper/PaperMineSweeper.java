/**
 * Alumno: Sebastian Caballero. 
 * Nombre de archivo: PaperMinesweeper.java
 * Introduccion a la Programacion II. 
 * FIUNI 2019.
 */
package MineSweeper;

import Vista.Juego;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main
 * @author Sebastian Caballero
 */
public class PaperMineSweeper {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            Juego juego = new Juego();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }
    }
}
