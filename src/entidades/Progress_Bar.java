package entidades;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;


public class Progress_Bar extends JFrame{
	
	JProgressBar barra = new JProgressBar();
	
	public Progress_Bar() {
		configurarJanela();
		barra.setBounds(40,40,500,50);
		barra.setStringPainted(true);
		barra.setValue(50);
		barra.setMaximum(1000);
		barra.setForeground(new Color(50,200,50));
		add(barra);
		new temporizador().start();
		
	
	}
	
	public void configurarJanela() {
		setTitle("Barra de progresso");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,170);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public class temporizador extends Thread{
		public void run() {
			while (barra.getValue() < 1000) {
				try {
					sleep(100);
					barra.setValue(barra.getValue()+10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "Carregado!");
		}
	}
	
	public static void main(String[] args) {
		new Progress_Bar();
	}


}
