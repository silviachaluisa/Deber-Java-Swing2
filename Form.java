import javax.swing.*;

public class Form {
    JPanel Menu;
    private JTextField textField4;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel Iniciar;
    private JButton ingresarButton;
    private JPasswordField passwordField1;
    private JTextField textFieldID;

    public JButton getIngresarButton() {
        return ingresarButton;
    }

    public JTextField getUsuarioTextField() {
        return textField1;
    }

    public JPasswordField getContraseñaPasswordField() {
        return passwordField1;
    }

    public JTextField getCorreoTextField() {
        return textField2;
    }

    public JTextField getIDTextField() {
        return textFieldID;
    }
}
