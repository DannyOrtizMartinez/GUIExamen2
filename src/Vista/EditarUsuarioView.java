package Vista;



import controller.UsuarioController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditarUsuarioView extends JFrame {
    private JTextField primerNombreField, segundoNombreField, primerApellidoField, segundoApellidoField;
    private JButton guardarButton, cancelarButton;
    private UsuarioController controller;

    public EditarUsuarioView(UsuarioController controller, String usuarioInfo) {
        this.controller = controller;

        // Configuración de la ventana
        setTitle("Editar Usuario");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de primer nombre
        JLabel primerNombreLabel = new JLabel("Primer Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(primerNombreLabel, gbc);

        primerNombreField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(primerNombreField, gbc);
        primerNombreField.setText(usuarioInfo.split(",")[1].split(":")[1].trim());

        // Campo de segundo nombre
        JLabel segundoNombreLabel = new JLabel("Segundo Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(segundoNombreLabel, gbc);

        segundoNombreField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(segundoNombreField, gbc);
        segundoNombreField.setText(usuarioInfo.split(",")[2].split(":")[1].trim());

        // Campo de primer apellido
        JLabel primerApellidoLabel = new JLabel("Primer Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(primerApellidoLabel, gbc);

        primerApellidoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(primerApellidoField, gbc);
        primerApellidoField.setText(usuarioInfo.split(",")[3].split(":")[1].trim());

        // Campo de segundo apellido
        JLabel segundoApellidoLabel = new JLabel("Segundo Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(segundoApellidoLabel, gbc);

        segundoApellidoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(segundoApellidoField, gbc);
        segundoApellidoField.setText(usuarioInfo.split(",")[4].split(":")[1].trim());

        // Botón para guardar cambios
        guardarButton = new JButton("Guardar");
        guardarButton.setBackground(new Color(0, 123, 255));
        guardarButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(guardarButton, gbc);

        // Botón para cancelar la edición
        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBackground(new Color(220, 53, 69));
        cancelarButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cancelarButton, gbc);

        // Acción para el botón de guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validaciones básicas
                String primerNombre = primerNombreField.getText().trim();
                String segundoNombre = segundoNombreField.getText().trim();
                String primerApellido = primerApellidoField.getText().trim();
                String segundoApellido = segundoApellidoField.getText().trim();

                if (primerNombre.isEmpty() || primerApellido.isEmpty()) {
                    JOptionPane.showMessageDialog(EditarUsuarioView.this, "El primer nombre y el primer apellido son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lógica para guardar cambios
                controller.editarUsuario(
                    Integer.parseInt(usuarioInfo.split(",")[0].split(":")[1].trim()),
                    primerNombre,
                    segundoNombre,
                    primerApellido,
                    segundoApellido
                );
                JOptionPane.showMessageDialog(EditarUsuarioView.this, "Usuario actualizado con éxito.");
                dispose();
            }
        });

        // Acción para el botón de cancelar
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana sin guardar cambios
            }
        });

        setVisible(true);
    }

    // Método principal para probar la ventana
    public static void main(String[] args) {
        // Simula un controlador simple
        UsuarioController controller = new UsuarioController() {
            @Override
            public void editarUsuario(int id, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
                System.out.println("Usuario editado:");
                System.out.println("ID: " + id);
                System.out.println("Primer Nombre: " + primerNombre);
                System.out.println("Segundo Nombre: " + segundoNombre);
                System.out.println("Primer Apellido: " + primerApellido);
                System.out.println("Segundo Apellido: " + segundoApellido);
            }
        };

        // Información simulada del usuario (formato: id:1,primer_nombre:Juan,segundo_nombre:Carlos,primer_apellido:Pérez,segundo_apellido:Gómez)
        String usuarioInfo = "id:1,primer_nombre:Juan,segundo_nombre:Carlos,primer_apellido:Pérez,segundo_apellido:Gómez";

        // Inicia la vista
        SwingUtilities.invokeLater(() -> new EditarUsuarioView(controller, usuarioInfo));
    }
}

