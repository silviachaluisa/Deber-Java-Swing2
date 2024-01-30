import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/usuarios";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bienvenido");
        Form form = new Form();
        frame.setContentPane(form.Menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JButton ingresarButton = form.getIngresarButton();
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = form.getUsuarioTextField().getText();
                String contraseña = form.getContraseñaPasswordField().getText();

                if (camposCompletos(usuario, contraseña)) {
                    if (autenticarUsuario(usuario, contraseña)) {
                        // Si los datos son correctos, abrir Form1
                        openForm1(form);
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos incorrectos. Inténtalo de nuevo.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Campos obligatorios", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private static boolean camposCompletos(String usuario, String contraseña) {
        return !usuario.isEmpty() && !contraseña.isEmpty();
    }

    private static boolean autenticarUsuario(String usuario, String contraseña) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM usuario WHERE Usuario = ? AND Contraseña = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contraseña);

                ResultSet resultSet = preparedStatement.executeQuery();

                // Si hay resultados, los datos son correctos
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static void openForm1(Form form) {
        JFrame form1Frame = new JFrame("Mi Biografía");
        Form1 form1 = new Form1();
        form1Frame.setContentPane(form1.getPanel1());
        form1Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        form1Frame.pack();
        form1Frame.setVisible(true);

        JButton salirButton = form1.getBoton2();
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí deberías cerrar Form1 y volver al Form
                form1Frame.dispose(); // Cierra el Form1
                openForm(form); // Muestra el Form original
            }
        });
    }

    private static void openForm(Form form) {
        JFrame frame = new JFrame("Bienvenido");
        frame.setContentPane(form.Menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
