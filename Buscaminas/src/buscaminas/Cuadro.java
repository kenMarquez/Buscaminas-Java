package buscaminas;

import javax.swing.JButton;

/**
 * Clase que va a interpretar un JButton como un cuadro con valor true o false
 * para el buscaminas
 *
 * @author darius
 */
public class Cuadro extends JButton {

    private boolean mina;
    private boolean visto;
    private int numero;
    private int x;
    private int y;

    /**
     * Constructor por omision llama al super de este y este va a ser creado
     * aleatoriamente dependdiendo la dificultad que se le quiera poner al
     * cuadro
     */
    public Cuadro() {
        super();
        double random = Math.random();
        System.out.println(random+"");
        if (random > 0.77) {
            mina = true;
        } else {
            mina = false;
        }
        visto = false;
        numero = 0;
        x = 0;
        y = 0;
    }

    /**
     * regresa el booleano del cuadro si esta minado
     *
     * @return mina
     */
    public boolean estaMinado() {
        return mina;
    }

    /**
     * Obtiene el valor de la variable yaVisto
     *
     * @return
     */
    public boolean yaVisto() {
        return visto;
    }

    /**
     * Modifica el valor de visto
     *
     * @param f
     */
    public void setVisto(boolean f) {
        visto = f;
    }

    /**
     * Obtiene el numero de la variable numero
     *
     * @return
     */
    public int getNum() {
        return numero;
    }
    /**
     * Modifica el valor de la variable numero
     * @param x 
     */
    public void setNum(int x) {
        numero = x;
    }
    /**
     * Regresa la x
     * @return 
     */
    public int getX() {
        return x;
    }
    /**
     * Modifica la y
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Regresa el valor de y
     * @return 
     */
    public int getY() {
        return y;
    }
    /**
     * Modifica el valor de y
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }

}
