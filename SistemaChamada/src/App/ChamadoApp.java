package App;

import javax.swing.SwingUtilities;
import TELAS.Login;

public class ChamadoApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
