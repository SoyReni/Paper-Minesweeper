/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: Juego.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */
package Vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Esta clase define la vista del juego en general
 * @author Sebastian Caballero
 */
public final class Juego extends JFrame {

    /**
     * Constructor que crea el objeto juego que va en el main
     */
    public Juego() {
        super ();
        this._Filas = 0; //cantidad de filas del tablero
        this._Columnas = 0; // cantidad de columnas del tablero
        this._Minas = 0; // cantidad de minas del tablero
        this.cantMinasMarcadas = 0; //cantidad de minas marcadas con una bandera
        this.juego = new JLabel();
        
        //configuracion del JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new JLabel(new ImageIcon("src\\images\\fondo.jpg")));
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.crear();
        this.pack();
        Dimension d = new Dimension(350, 400);
        this.setSize(d);
        this.setResizable(false);
        this.setLocation(300, 20);
        this.setTitle("Buscaminas");

        //inicializacion del cronometro
        this.timer = new Timer(1000, null);
        this.timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarConteo(e);
            }
        });
        
        this._Nivel = 0;
        
    } //fin del constructor

    /**
     * Metodo para crear la carcasa principal del juego, aca se va actualizando 
     * dependiendo de los eventos que ocurran
     * Este metodo tambien agrega el menu donde se pueden seleccionar los niveles
     * del juego
     */
    private void crear() {
        this.menu = new JMenuBar();
        this.opcionesJuego = new JMenu();
        this.opcionPrincipiante = new JMenuItem();
        this.opcionIntermedio = new JMenuItem();
        this.opcionAvanzado = new JMenuItem();

        this.opcionesJuego.setText("Juego nuevo");
        this.opcionPrincipiante.setText("Principiante");
        this.opcionPrincipiante.addActionListener(new ActionListener() {
            @Override
            //este boton inicializa un tablero de nivel principiante
            public void actionPerformed(ActionEvent e) {
                setNivel(e, PRINCIPIANTE);
                juegoNuevo(e);
            }
        });
        this.opcionPrincipiante.setToolTipText("8x8 - 10 minas");

        this.opcionIntermedio.setText("Intermedio");
        this.opcionIntermedio.addActionListener(new ActionListener() {
            //este boton inicializa un tablero de nivel intermedio
            @Override
            public void actionPerformed(ActionEvent e) {
                setNivel(e, INTERMEDIO);
                juegoNuevo(e);
            }
        });
        this.opcionIntermedio.setToolTipText("16x16 - 40 minas");

        this.opcionAvanzado.setText("Avanzado");
        this.opcionAvanzado.addActionListener(new ActionListener() {
            //este boton inicializa un tablero de nivel avanzado
            @Override
            public void actionPerformed(ActionEvent e) {
                setNivel(e, AVANZADO);
                juegoNuevo(e);
            }
        });
        this.opcionAvanzado.setToolTipText("16x30 - 99 minas");

        //se configura y agrega la barra de menu
        this.menu.add(opcionesJuego);
        this.opcionesJuego.add(opcionPrincipiante);
        this.opcionesJuego.add(opcionIntermedio);
        this.opcionesJuego.add(opcionAvanzado);
        this.setJMenuBar(menu);
        this.iniciar(); 
        
    } //fin del metodo crear

    /**
     * Metodo para crear la cara inicial del juego que contiene tres botones para
     * seleccionar nivel
     */
    private void iniciar() {
        this.juego.setLayout(new GridBagLayout());
        GridBagConstraints limites = new GridBagConstraints();
        Insets margenes = new Insets(10, 10, 10, 10);

        this.principiante = new JButton();
        this.principiante.setIcon(new ImageIcon("src\\images\\principiante.png"));
        this.principiante.setRolloverIcon(new ImageIcon("src\\images\\principianteMO.png"));
        this.principiante.setToolTipText("Nivel principiante: 8x8 - 10 minas");
        this.principiante.setContentAreaFilled(false);
        this.principiante.setOpaque(false);
        this.principiante.setBorder(BorderFactory.createEmptyBorder());
        this.principiante.addActionListener(new ActionListener() {
            //este boton inicializa un tablero de nivel principiante
            @Override
            public void actionPerformed(ActionEvent e) {
                setNivel(e, PRINCIPIANTE);
                juegoNuevo(e);
            }
        });
        limites.gridx = 0;
        limites.gridy = 0;
        limites.weightx = 100;
        limites.weighty = 100;
        limites.insets = margenes;
        limites.fill = GridBagConstraints.BOTH;
        this.juego.add(this.principiante, limites);

        this.intermedio = new JButton();
        this.intermedio.setIcon(new ImageIcon("src\\images\\intermedio.png"));
        this.intermedio.setRolloverIcon(new ImageIcon("src\\images\\intermedioMO.png"));
        this.intermedio.setToolTipText("Nivel intermedio: 16x16 - 40 minas");
        this.intermedio.setContentAreaFilled(false);
        this.intermedio.setOpaque(false);
        this.intermedio.setBorder(BorderFactory.createEmptyBorder());
        this.intermedio.addActionListener(new ActionListener() {
            //este boton inicializa un tablero de nivel intermedio
            @Override
            public void actionPerformed(ActionEvent e) {
                setNivel(e, INTERMEDIO);
                juegoNuevo(e);
            }
        });
        limites.gridx = 0;
        limites.gridy = 1;
        this.juego.add(this.intermedio, limites);

        this.avanzado = new JButton();
        this.avanzado.setIcon(new ImageIcon("src\\images\\avanzado.png"));
        this.avanzado.setRolloverIcon(new ImageIcon("src\\images\\avanzadoMO.png"));
        this.avanzado.setToolTipText("Nivel avanzado: 16x30 - 99 minas");
        this.avanzado.setContentAreaFilled(false);
        this.avanzado.setOpaque(false);
        this.avanzado.setBorder(BorderFactory.createEmptyBorder());
        this.avanzado.addActionListener(new ActionListener() {
            //este boton inicializa un tablero de nivel avanzado
            @Override
            public void actionPerformed(ActionEvent e) {
                setNivel(e, AVANZADO);
                juegoNuevo(e);
            }
        });
        limites.gridx = 0;
        limites.gridy = 2;
        this.juego.add(this.avanzado, limites);

        limites.gridx = 0;
        limites.gridy = 0;
        this.add(juego, limites);
        
    } //fin del metodo iniciar
    
    /**
     * Metodo para dibujar los tableros en el juego
     * @param e 
     */
    private void juegoNuevo(ActionEvent e) {
        //para inicializar se necesita un nivel que define la configuracion
        //por defecto el nivel es principiante
        this.setNivel(e, this._Nivel);
        this.getLayout().removeLayoutComponent(juego);
        this.cantMinasMarcadas = 0;
        
        GridBagConstraints limites = new GridBagConstraints();
        this.juego.removeAll(); //se borran los elementos que hay en la pantalla para redibujar algo nuevo
        this.juego.setLayout(new GridBagLayout());

        this.minutos = 0;
        this.segundos = 0;
        this.reloj = new JLabel();
        this.reloj.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        this.reloj.setText(minutos + ":" + segundos);
        this.reloj.setHorizontalAlignment(SwingConstants.RIGHT);
        this.reloj.setOpaque(false);
        limites.gridx = 0;
        limites.gridy = 0;
        limites.insets = new Insets(25, 25, 25, 25);
        limites.gridwidth = (((this._Columnas) - 2) / 2);
        limites.fill = GridBagConstraints.BOTH;
        limites.anchor = GridBagConstraints.NORTH;
        this.juego.add(this.reloj, limites);

        conteo = new JLabel();
        conteo.setIcon(new ImageIcon("src\\images\\flag.png"));
        conteo.setText(this._Minas + "");
        conteo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        conteo.setOpaque(false);
        conteo.setToolTipText("Utilice el click derecho para poner una bandera, marque todas las minas para ganar");
        limites.gridx = ((((this._Columnas) - 2) / 2) + 2);
        this.juego.add(this.conteo, limites);

        carita = new JButton();
        this.carita.setIcon(new ImageIcon("src\\images\\carita.png"));
        this.carita.setBorder(BorderFactory.createEmptyBorder());
        this.carita.setOpaque(false);
        this.carita.setContentAreaFilled(false);
        this.carita.addActionListener(new ActionListener() {
            //este boton inicia un juego nuevo del nivel actual
            @Override
            public void actionPerformed(ActionEvent e) {
                juegoNuevo(e);
            }
        });
        this.carita.setToolTipText("Juego Nuevo");
        limites.insets = new Insets(0, 0, 0, 0);
        limites.gridx = ((((this._Columnas) - 2) / 2));
        limites.gridwidth = 2;
        this.juego.add(this.carita, limites);

        //se cargan los botones en el tablero
        this.cargarBotones();
        limites.gridwidth = 1;
        //el tablero inicia con un arreglo de botones vacios, 
        //el primer click randomiza las bombas e inicia el contador
        for (int i = 0; i < this._Columnas; i++) {
            for (int j = 0; j < this._Filas; j++) {
                this.botones[i][j].addActionListener(new ActionListener() {
                    //todos los botones esperan a que se haga el primer click
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        primerClick(e);
                        abrirAlrededor(e);
                        iniciarConteo(e);
                    }
                });
                limites.gridx = i;
                limites.gridy = j + 2;
                this.juego.add(this.botones[i][j], limites);
            }
        }

        limites.gridx = 0;
        limites.gridy = 1;
        limites.fill = GridBagConstraints.BOTH;
        limites.anchor = GridBagConstraints.NORTH;
        limites.weightx = 1;
        limites.weighty = 1;
        this.add(juego, limites);
        this.pack();
        
        //el tamaño minimo de la pantalla es siempre el tamaño maximo del tablero
        Dimension d = new Dimension(((50 * this._Columnas) + 50), ((50 * this._Filas) + 200)); //cambiar este
        this.setSize(d);
        this.setMinimumSize(d);

    } // fin del metodo juegoNuevo

    /**
     * Metodo para cargar un tablero vacio que luego del primer click se actualiza
     */
    private void cargarTablero() {
        this.tablero = new int[this._Columnas][this._Filas];
        for (int i = 0; i < this._Columnas; i++) {
            for (int j = 0; j < this._Filas; j++) {
                this.tablero[i][j] = 0;
            }
        }
    }

    /**
     * Metodo para cargar el tablero vacio de botones vacios que luego del primer 
     * click se actualizan
     */
    private void cargarBotones() {
        this.cargarTablero();
        this.botones = new Boton[this._Columnas][this._Filas];
        for (int i = 0; i < this._Columnas; i++) {
            for (int j = 0; j < this._Filas; j++) {
                this.botones[i][j] = new Boton(this.tablero[i][j]);
            }
        }
    }

    /**
     * Metodo para inicializar el cronometro
     * @param e 
     */
    private void iniciarConteo(ActionEvent e) {
        timer.restart();
        if (e.getSource() instanceof Timer) {
            this.segundos++;
            if (segundos == 60) {
                minutos++;
                segundos = 0;
            }
            reloj.setText(minutos + ":" + segundos);
        } else if (e.getSource() instanceof Boton) {
            timer.restart();
        }
    }

    /**
     * Metodo para randomizar las bombas y actualizar el tablero con el primer click
     * @param e 
     */
    private void primerClick(ActionEvent e) {
        Boton b = (Boton) e.getSource();
        b.setEnabled(false);
        b.setDisabledSelectedIcon(new ImageIcon("src\\images\\0.png"));

        int minasPuestas = 0;
        Random rand = new Random();

        //se cargan la cantidad de bombas que el nivel requiere
        while (minasPuestas < this._Minas) {
            int fila = rand.nextInt(this._Filas);
            int columna = rand.nextInt(this._Columnas);
            //se cargan las minas sin superponerlas y sin ponerlas en la casilla del primer click
            if ((botones[columna][fila] != b) && (botones[columna][fila].getValor() != 9)) {
                botones[columna][fila].setValor(9);
                minasPuestas++;
            }
        }
        //los valores de los botones se actualizan
        for (int i = 0; i < this._Columnas; i++) {
            for (int j = 0; j < this._Filas; j++) {
                if (botones[i][j].getValor() != 9) {
                    botones[i][j].setValor(this.buscarMinasCerca(i, j));
                }
                //se elimina el actionlistener de todos los botones al hacerse el primer click
                botones[i][j].removeActionListener(botones[i][j].getActionListeners()[0]);
                //se agregan los iconos de numeros y vacios
                botones[i][j].setSelectedIcon(new ImageIcon("src\\images\\" + this.botones[i][j].getValor() + ".png"));
                botones[i][j].setDisabledSelectedIcon(new ImageIcon("src\\images\\" + this.botones[i][j].getValor() + ".png"));
                botones[i][j].addMouseListener(new MouseListener() {
                    @Override
                    //este metodo es para agregar banderas con el click derecho
                    public void mouseClicked(MouseEvent me) {
                        if (me.getButton() == MouseEvent.BUTTON3) {
                            ponerBandera((Boton) me.getSource());
                            ganar(me);
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent me) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }
                    @Override
                    public void mouseExited(MouseEvent me) {
                    }

                });
                //si un boton es vacio, se deben abrir los botones alrededor
                if (botones[i][j].getValor() == 0) {
                    botones[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            abrirAlrededor(e);
                        }
                    });
                }
                //si un boton es una bomba se pierde
                if (botones[i][j].getValor() == 9) {
                    botones[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            perder(e);
                        }
                    });
                }
            }
        }
    } //fin del metodo primerClick

    /**
     * Metodo para abrir las casillas que se encuentras alrededor de la casilla 
     * presionada en caso de ser una casilla vacia
     * El metodo .doClick genera una cadena de clicks si es posible
     * @param e 
     */
    private void abrirAlrededor(ActionEvent e) {
        Boton b = (Boton) e.getSource();
        b.setEnabled(false);

        for (int i = 0; i < this._Columnas; i++) {
            for (int j = 0; j < this._Filas; j++) {
                if (this.botones[i][j] == b) {
                    if (((i + 1) >= 0) && ((i + 1) < this._Columnas) && ((j + 1) >= 0) && ((j + 1) < this._Filas)) {
                        if (!botones[i + 1][j + 1].isSelected() && (botones[i + 1][j + 1].getValor() != 9)) {
                            botones[i + 1][j + 1].doClick(); 
                            botones[i + 1][j + 1].setEnabled(false);
                        }
                    }
                    if (((i + 1) >= 0) && ((i + 1) < this._Columnas) && ((j - 1) >= 0) && ((j - 1) < this._Filas)) {
                        if (!botones[i + 1][j - 1].isSelected() && (botones[i + 1][j - 1].getValor() != 9)) {
                            botones[i + 1][j - 1].doClick();
                            botones[i + 1][j - 1].setEnabled(false);
                        }
                    }
                    if (((i + 1) >= 0) && ((i + 1) < this._Columnas) && ((j) >= 0) && ((j) < this._Filas)) {
                        if (!botones[i + 1][j].isSelected() && (botones[i + 1][j].getValor() != 9)) {
                            botones[i + 1][j].doClick();
                            botones[i + 1][j].setEnabled(false);
                        }
                    }
                    if (((i - 1) >= 0) && ((i - 1) < this._Columnas) && ((j + 1) >= 0) && ((j + 1) < this._Filas)) {
                        if (!botones[i - 1][j + 1].isSelected() && (botones[i - 1][j + 1].getValor() != 9)) {
                            botones[i - 1][j + 1].doClick();
                            botones[i - 1][j + 1].setEnabled(false);
                        }
                    }
                    if (((i - 1) >= 0) && ((i - 1) < this._Columnas) && ((j - 1) >= 0) && ((j - 1) < this._Filas)) {
                        if (!botones[i - 1][j - 1].isSelected() && (botones[i - 1][j - 1].getValor() != 9)) {
                            botones[i - 1][j - 1].doClick();
                            botones[i - 1][j - 1].setEnabled(false);
                        }
                    }
                    if (((i - 1) >= 0) && ((i - 1) < this._Columnas) && ((j) >= 0) && ((j) < this._Filas)) {
                        if (!botones[i - 1][j].isSelected() && (botones[i - 1][j].getValor() != 9)) {
                            botones[i - 1][j].doClick();
                            botones[i - 1][j].setEnabled(false);
                        }
                    }
                    if (((i) >= 0) && ((i) < this._Columnas) && ((j + 1) >= 0) && ((j + 1) < this._Filas)) {
                        if (!botones[i][j + 1].isSelected() && (botones[i][j + 1].getValor() != 9)) {
                            botones[i][j + 1].doClick();
                            botones[i][j + 1].setEnabled(false);
                        }
                    }
                    if (((i) >= 0) && ((i) < this._Columnas) && ((j - 1) >= 0) && ((j - 1) < this._Filas)) {
                        if (!botones[i][j - 1].isSelected() && (botones[i][j - 1].getValor() != 9)) {
                            botones[i][j - 1].doClick();
                            botones[i][j - 1].setEnabled(false);
                        }
                    }
                }
            }
        }
    } //fin del metodo abrirAlrededor

    /**
     * Metodo que se dispara al perder una partida
     * @param e 
     */
    private void perder(ActionEvent e) {
        //si presionas una mina, todo el tablero se abre
        for (int i = 0; i < this._Columnas; i++) {
            for (int j = 0; j < this._Filas; j++) {
                if (!this.botones[i][j].isSelected()) {
                    this.botones[i][j].setSelected(true);
                    this.botones[i][j].setEnabled(false);
                }
            }
        }
        //la carita se pone triste,
        ImageIcon icono = new ImageIcon("src\\images\\sad.png");
        this.carita.setIcon(icono);
        //el reloj para
        timer.stop();
        //y te avisa que perdiste
        JOptionPane.showMessageDialog(menu, "Perdiste", "", JOptionPane.PLAIN_MESSAGE, icono);
    }

    /**
     * Metodo que se dispara al ganar una partida
     * @param me 
     */
    private void ganar(MouseEvent me) {
        //si hay tantas minas como minas marcadas, ganas el juego
        if (this.cantMinasMarcadas == this.cantMinasInicial) {
            ImageIcon icono = new ImageIcon("src\\images\\carita.png");
            timer.stop();
            //te avisa que ganaste y te dice tu tiempo
            JOptionPane.showMessageDialog(menu, "Ganaste\nTu tiempo: " + minutos + ":" + segundos, "", JOptionPane.PLAIN_MESSAGE, icono);
        }
    }

    /**
     * Metodo que detecta la cantidad de minas que hay alrededor de una casilla
     * esto define el valor de la casilla 
     * @param columna
     * @param fila
     * @return cantidad de minas que hay alrededor de una casilla
     */
    private int buscarMinasCerca(int columna, int fila) {
        int minas = 0;
        minas += esMina(columna - 1, fila - 1);
        minas += esMina(columna - 1, fila + 1);
        minas += esMina(columna - 1, fila);
        minas += esMina(columna + 1, fila - 1);
        minas += esMina(columna + 1, fila + 1);
        minas += esMina(columna + 1, fila);
        minas += esMina(columna, fila + 1);
        minas += esMina(columna, fila - 1);
        return minas;
    } 

    /**
     * Metodo para saber si una casilla es una mina o no
     * @param columna
     * @param fila
     * @return 1 si es una mina, 0 si no lo es
     */
    private int esMina(int columna, int fila) {
        if ((columna >= 0) && (columna < this._Columnas) && (fila >= 0) && (fila < this._Filas) && (this.botones[columna][fila].getValor() == 9)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Metodo para definir el nivel del juego
     * @param e
     * @param nivel int 
     */
    private void setNivel(ActionEvent e, int nivel) {
        this._Nivel = nivel;
        if (this._Nivel == PRINCIPIANTE) {
            this._Columnas = 8;
            this._Filas = 8;
            this._Minas = 10;
            this.cantMinasInicial = 10;
        } else if (this._Nivel == INTERMEDIO) {
            this._Columnas = 16;
            this._Filas = 16;
            this._Minas = 40;
            this.cantMinasInicial = 40;
        } else if (this._Nivel == AVANZADO) {
            this._Columnas = 30;
            this._Filas = 16;
            this._Minas = 99;
            this.cantMinasInicial = 99;
        }
    } 

    /**
     * Metodo para poner una bandera en una casilla
     * @param b 
     */
    private void ponerBandera(Boton b) {
        //si hay banderas disponibles y no hay una bandera en esa casilla
        //se pone una bandera y el boton se deshabilita
        if (this._Minas != 0 && b.isbandera() == false) {
            b.setEnabled(false);
            b.setIcon(new ImageIcon("src\\images\\flag.png"));
            b.setDisabledIcon(new ImageIcon("src\\images\\flag.png"));
            b.ponerBandera();
            b.addMouseListener(new MouseListener() {
                //este metodo es para poder quitar una bandera de un boton deshabilitado
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (me.getButton() == MouseEvent.BUTTON3) {
                        quitarBandera((Boton) me.getSource());
                    }
                }
                @Override
                public void mousePressed(MouseEvent me) {
                }
                @Override
                public void mouseReleased(MouseEvent me) {
                }
                @Override
                public void mouseEntered(MouseEvent me) {
                }
                @Override
                public void mouseExited(MouseEvent me) {
                }

            });
            //la cantidad de banderas y de minas marcadas debe actualizarse con
            //cada bandera puesta para poder determinar cuando un jugador gana
            this._Minas--;
            this.conteo.setText(this._Minas + "");
            if (b.getValor() == 9) {
                this.cantMinasMarcadas++;
            }
        }
    } //fin del metodo poner bandera

    /**
     * Metodo para quitar una bandera de una casilla con bandera
     * @param b 
     */
    private void quitarBandera(Boton b) {
        //si hay bandera, se quita la bandera
        if (b.isbandera()) {
            //el conteo de banderas y minas marcadas se actualiza tambien
            this._Minas++;
            this.conteo.setText(this._Minas + "");
            if (b.getValor() == 9) {
                this.cantMinasMarcadas--;
            }
            b.quitarBandera();
            b.setEnabled(true);
            b.setIcon(new ImageIcon("src\\images\\boton.png"));
            b.addMouseListener(new MouseListener() {
                //metodo para poder poner una bandera de donde se saco
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (me.getButton() == MouseEvent.BUTTON3) {
                        ponerBandera((Boton) me.getSource());
                    }
                }
                @Override
                public void mousePressed(MouseEvent me) {
                }
                @Override
                public void mouseReleased(MouseEvent me) {
                }
                @Override
                public void mouseEntered(MouseEvent me) {
                }
                @Override
                public void mouseExited(MouseEvent me) {
                }
            });
        }
    } //fin del metodo quitar bandera

    //arreglos de botones y casillas
    private int[][] tablero;
    private Boton[][] botones;
    
    //items de la barra de menu
    private JMenuBar menu;
    private JMenu opcionesJuego;
    private JMenuItem opcionPrincipiante;
    private JMenuItem opcionIntermedio;
    private JMenuItem opcionAvanzado;

    //items de la cara principal
    private JButton principiante;
    private JButton intermedio;
    private JButton avanzado;

    //items de un tablero
    private JLabel reloj;
    private JButton carita;
    private JLabel conteo;
    private final Timer timer;
    private int segundos;
    private int minutos;

    //valores de un juego
    private int _Filas;
    private int _Columnas;
    private int _Minas;
    private int cantMinasMarcadas;
    private int cantMinasInicial;
    private int _Nivel;
    
    //contenedor de tableros
    private final JLabel juego;
    
    //valores de los niveles
    private final int PRINCIPIANTE = 0;
    private final int INTERMEDIO = 1;
    private final int AVANZADO = 2;
    
} //fin de la clase
