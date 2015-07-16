/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author darius
 */
public class Panel extends JPanel{
    
    JButton start;
    JTextField filas;
    JTextField columnas;
    JLabel fil;
    JLabel col;    
    
    
   
    public Panel(){    
    this.setLayout(null);
    this.setSize(800,100);
    this.setBackground(Color.red);
    this.campos();
    this.etiquetas();
    this.botones();    
    }

    
    public void campos(){
        filas = new JTextField();
        filas.setBounds(70,15,50,30);
        this.add(filas);
        columnas = new JTextField();
        columnas.setBounds(210, 15, 50, 30);
        this.add(columnas);
    }
    
    public void etiquetas(){
        fil = new JLabel("Filas :");
        fil.setBounds(10, 20, 50, 30);
        this.add(fil);
        col = new JLabel("Columnas :");
        col.setBounds(130,20,80,30);
        this.add(col);
    }        
    
    public void botones(){
        start = new JButton("Start");
        start.setBounds(600, 15, 100, 30);
        
        
        this.add(start);
    }
    
    public int getFilas(){
    return Integer.parseInt(this.filas.getText());
    }
    public int getColumnas(){
    return Integer.parseInt(this.columnas.getText());
    }
    
    
}
