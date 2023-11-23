package View;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class JanelaPrincipal extends JFrame {
    // criação do tabbedPane para incluir as tabs
    private JTabbedPane jTPane;

    public JanelaPrincipal() {

                jTPane = new JTabbedPane();
                add(jTPane);
                // criandos as tabs
                // tab1 carros
                CarrosPainel tab1 = new CarrosPainel();
                jTPane.add("Carros", tab1);
                setBounds(100, 100, 600, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
            }
        
            // métodos para tornar a janela visível
            public void run() {
                this.setVisible(true);
            }
        
        }
       