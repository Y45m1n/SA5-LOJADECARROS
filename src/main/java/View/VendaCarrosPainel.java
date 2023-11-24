package View;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import Controller.CarrosDAO;
import Model.Carros;

public class VendaCarrosPainel extends JPanel {
    JComboBox<String> carrosComboBox;
    List<Carros> carros;

    public VendaCarrosPainel() {
        super();
        carrosComboBox = new JComboBox<>();
        // Preencha o JComboBox com os carros
        carros = new CarrosDAO().listarTodos();
        carrosComboBox.addItem("Selecione o Carro");
        for (Carros carro : carros) {
            carrosComboBox.addItem(carro.getMarca()
                    + " " + carro.getModelo()
                    + " " + carro.getPlaca());
        }
        add(carrosComboBox);

    }

    public void atualizarComboBox() {
        // Preencha o JComboBox com os carros
        carros = new CarrosDAO().listarTodos();
        carrosComboBox.removeAllItems();
        carrosComboBox.addItem("Selecione o Carro");
        for (Carros carro : carros) {
            carrosComboBox.addItem(carro.getMarca()
                    + " " + carro.getModelo()
                    + " " + carro.getPlaca());
        }
    }

}
