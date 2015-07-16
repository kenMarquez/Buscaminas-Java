/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Buscaminas
 * @author darius
 */
public class Interfaz extends JFrame implements ActionListener, MouseListener {

    Cuadro buscaminas[][];
    JButton start;
    JTextField filas;
    JTextField columnas;
    int nfilas;
    int ncolumnas;
    JLabel fil;
    JLabel col;
    JLabel minas;
    JPanel superior;
    JPanel juego;
    Panel pan;
    PanelJuego panJuego;
    int contadorMinas = 0;
    int numeroCuadros = 0;
    int contador;

    /**
     * Constructor por omision
     */
    public Interfaz() {
        super("Buscaminas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850, 720);        
        this.setContentPane(new JPanel());
        this.getContentPane().setLayout(null);
        paneles();       
        superior.setBounds(20, 10, 800, 100);
        superior.addMouseListener(this);        
        juego.setBounds(20, 110, 800, 550);        
        this.getContentPane().add(superior);
        this.getContentPane().add(juego);
        this.setVisible(true);
    }

    private void iniciar() {
        contador = 0;//jugadas validas
        contadorMinas = 0;//numero minas
        this.juego.removeAll();    
        nfilas = Integer.parseInt(filas.getText());
        ncolumnas = Integer.parseInt(columnas.getText());
        buscaminas = new Cuadro[nfilas][ncolumnas];
        this.numeroCuadros = nfilas * ncolumnas;
        this.juego.setLayout(new java.awt.GridLayout(nfilas, ncolumnas));
        for (int i = 0; i < nfilas; i++) {
            for (int j = 0; j < ncolumnas; j++) {
                buscaminas[i][j] = new Cuadro();
                buscaminas[i][j].addActionListener(this);
                buscaminas[i][j].addMouseListener(this);
                buscaminas[i][j].setX(i);
                buscaminas[i][j].setY(j);
                if (buscaminas[i][j].estaMinado()) {
                    contadorMinas++;
                }
                buscaminas[i][j].setVisible(true);
                this.juego.add(buscaminas[i][j]);
            }
        }
        calcularNumero();
        minas.setText("Minas : " + contadorMinas);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cuadro tem2 = (Cuadro) e.getSource();
        calcular(tem2);
    }

    public void calcular(Cuadro x) {
        Cuadro tem2 = x;
        if (tem2.estaMinado()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Le diste a una mina pendejo, Perdiste");
            tem2.setBackground(Color.BLACK);
            mostrarSol();
        } else if (!tem2.estaMinado()) {
            if (!tem2.yaVisto()) {
                if (tem2.getNum() == 0) {
                    espacioBlanco(tem2);
                } else {
                    visto(tem2);
                }
            }
        }
    }
    /**
     * 
     * @param tem2 
     */
    private void visto(Cuadro tem2) {
        tem2.setBackground(new Color(30,130,190));
        tem2.setVisto(true);
        contador++;
        tem2.setFont(new Font(null, 5, 15));
        tem2.setForeground(Color.white);
        tem2.setText(tem2.getNum() + "");        
        if (contador == numeroCuadros) {
            JOptionPane.showMessageDialog(this, "Ganaste =D :) :3 ;) =0 =b xD =D ");
        }
    }

    private void espacioBlanco(Cuadro t) {
        t.setVisto(true);
        int i = t.getX();
        int j = t.getY();
        t.setBackground(new Color(30,130,190));
        if (i - 1 >= 0) {
            if (j - 1 >= 0) {//esquina sup izq
                if (buscaminas[i - 1][j - 1].getNum() == 0) {
                    calcular(buscaminas[i - 1][j - 1]);
                } else {
                    visto(buscaminas[i - 1][j - 1]);
                }
            }
            if (j + 1 < ncolumnas) {//esquina sup der
                if (buscaminas[i - 1][j + 1].getNum() == 0) {
                    calcular(buscaminas[i - 1][j + 1]);
                } else {
                    visto(buscaminas[i - 1][j + 1]);
                }
            }
            if (buscaminas[i - 1][j].getNum() == 0) {//arriba
                calcular(buscaminas[i - 1][j]);
            } else {
                visto(buscaminas[i - 1][j]);
            }
        }
        if (i + 1 < nfilas) {
            if (j - 1 >= 0) { //esq inf izquierda
                if (buscaminas[i + 1][j - 1].getNum() == 0) {
                    calcular(buscaminas[i + 1][j - 1]);
                } else {
                    visto(buscaminas[i + 1][j - 1]);
                }
            }
            if (j + 1 < ncolumnas) {//esqu inf der
                if (buscaminas[i + 1][j + 1].getNum() == 0) {
                    calcular(buscaminas[i + 1][j + 1]);
                } else {
                    visto(buscaminas[i + 1][j + 1]);
                }
            }
            if (buscaminas[i + 1][j].getNum() == 0) {//abajo
                calcular(buscaminas[i + 1][j]);
            } else {
                visto(buscaminas[i + 1][j]);
            }
        }
        if (j - 1 >= 0) {//izquierda
            if (buscaminas[i][j - 1].getNum() == 0) {
                calcular(buscaminas[i][j - 1]);
            } else {
                visto(buscaminas[i][j - 1]);
            }
        }
        if (j + 1 < ncolumnas) {//derecha
            if (buscaminas[i][j + 1].getNum() == 0) {
                calcular(buscaminas[i][j + 1]);
            } else {
                visto(buscaminas[i][j + 1]);
            }
        }
    }

    private void paneles() {
        superior = new JPanel();
        superior.setSize(800, 100);
        superior.setBackground(new Color(30, 200, 200));
        superior.setLayout(null);
        campos();
        etiquetas();
        botones();
        juego = new JPanel();
        juego.setSize(800, 600);
        juego.setBackground(Color.LIGHT_GRAY);
    }

    public void calcularNumero() {
        int numero;
        for (int i = 0; i < buscaminas.length; i++) {
            for (int j = 0; j < buscaminas[0].length; j++) {
                numero = 0;
                if (i - 1 >= 0) {
                    if (j - 1 >= 0) {//esquina sup izq
                        if (buscaminas[i - 1][j - 1].estaMinado()) {
                            numero++;
                        }
                    }
                    if (j + 1 < ncolumnas) {//esquina sup der
                        if (buscaminas[i - 1][j + 1].estaMinado()) {
                            numero++;
                        }
                    }
                    if (buscaminas[i - 1][j].estaMinado()) {//arriba
                        numero++;
                    }
                }

                if (i + 1 < nfilas) {
                    if (j - 1 >= 0) { //esq inf izquierda
                        if (buscaminas[i + 1][j - 1].estaMinado()) {
                            numero++;
                        }
                    }
                    if (j + 1 < ncolumnas) {//esqu inf der
                        if (buscaminas[i + 1][j + 1].estaMinado()) {
                            numero++;
                        }
                    }
                    if (buscaminas[i + 1][j].estaMinado()) {//abajo
                        numero++;
                    }
                }
                if (j - 1 >= 0) {//izquierda
                    if (buscaminas[i][j - 1].estaMinado()) {
                        numero++;
                    }
                }

                if (j + 1 < ncolumnas) {//derecha
                    if (buscaminas[i][j + 1].estaMinado()) {
                        numero++;
                    }
                }
                buscaminas[i][j].setNum(numero);
            }
        }
    }

    public void campos() {
        filas = new JTextField();
        filas.setBounds(70, 15, 50, 30);
        superior.add(filas);
        columnas = new JTextField();
        columnas.setBounds(210, 15, 50, 30);
        superior.add(columnas);
    }

    public void etiquetas() {
        fil = new JLabel("Filas :");
        fil.setBounds(10, 20, 50, 30);
        superior.add(fil);
        col = new JLabel("Columnas :");
        col.setBounds(130, 20, 80, 30);
        superior.add(col);
        minas = new JLabel("");
        minas.setBounds(350, 15, 80, 30);
        superior.add(minas);
    }

    /**
     * Botones de la parte superior del juego
     */
    public void botones() {
        start = new JButton("Start");
        start.setBounds(600, 15, 100, 30);
        start.addMouseListener(this);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(rootPane, "hola");
                iniciar();
                paintAll(getGraphics());
            }
        });
        superior.add(start);
    }
    /**
     * Muestra la construccion completa del buscaminas
     */
    public void mostrarSol() {
        for (int i = 0; i < buscaminas.length; i++) {
            for (int j = 0; j < buscaminas[0].length; j++) {                
                Cuadro tem2 = buscaminas[i][j];
                if (tem2.estaMinado()){
                    tem2.setBackground(new Color(200,30,100));
            } else {
                    tem2.setBackground(new Color(30,130,190));
                    if (tem2.getNum() !=0){
                    tem2.setFont(new Font(null, 5, 15));
                    tem2.setForeground(Color.white);
                    tem2.setText(tem2.getNum() + "");
                    }
                }
        }
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            if (e.getComponent() instanceof JButton) {
                Cuadro tm = (Cuadro) e.getSource();
                if (!tm.yaVisto()) {
                    tm.setBackground(Color.red);
                    contadorMinas--;
                    minas.setText("Minas : " + contadorMinas);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
