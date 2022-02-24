/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: Boton.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */
package Vista;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 * Esta clase representa los botones que esconcen a las minas en el juego buscaminas
 * Los valores que puede tomar un boton son: 
 * 0 para las casillas vacias que no tienen minas cercas 
 * 1-8: para casillas que tienen minas cerca
 * 9: para casillas con minas
 * @author Sebastian Caballero
 */
public class Boton extends JToggleButton {

    //Constructor de la clase
    public Boton(int valor) {
        super();
        this.bandera = false;
        this._Valor = valor;
        this.crear();
    }

    /**
     * Metodo para definir el valor del boton
     * @param valorNuevo int nuevo valor del boton
     */
    public void setValor(int valorNuevo) {
        this._Valor = valorNuevo;
    }

    /**
     * Metodo que retorna el valor del boton
     * @return 
     */
    public int getValor() {
        return this._Valor;
    }

    /**
     * Metodo que devuelve true si hay una bandera en el boton
     * @return 
     */
    public boolean isbandera() {
        return this.bandera;
    }

    /**
     * Metodo para poner una bandera en una casilla
     * cambia el valor de el parametro bandera a true 
     */
    public void ponerBandera() {
        this.bandera = true;
    }

    /**
     * Metodo para quitar una bandera en una casilla
     * cambia el valor de el parametro bandera a false
     */
    public void quitarBandera() {
        this.bandera = false;
    }

    @Override
    /**
     * Metodo para evitar que el boton se pueda desseleccionar, 
     * rescatado de la web
     * https://stackoverflow.com/questions/27141032/click-j-togglebutton-then-set-icon-image
     */
    public void setSelected(boolean isSelected) {
        if (!this.isSelected()) {
            super.setSelected(isSelected);
        }
    }

    /**
     * Metodo para crear los botones 
     */
    private void crear() {
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setOpaque(false);
        this.setIcon(new ImageIcon("src\\images\\boton.png"));
        this.setSelectedIcon(new ImageIcon("src\\images\\" + this._Valor + ".png"));
        this.setContentAreaFilled(false);
    }

    private int _Valor; //parametro que define el valor de un boton
    private boolean bandera; //parametro que define si hay o no una bandera en el boton
}
