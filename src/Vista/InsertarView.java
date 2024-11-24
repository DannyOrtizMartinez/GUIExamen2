package Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InsertarView extends JFrame {

    public InsertarView() {
        // Configuración de la ventana principal
        setTitle("Formulario de Registro de Nombres");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ruta absoluta de la imagen
        String rutaImagen = "src/Imagen/fondo.jpg";

        // Verificar que la imagen existe
        if (!new File(rutaImagen).exists()) {
            JOptionPane.showMessageDialog(this, "No se encontró la imagen en la ruta especificada:\n" + rutaImagen,
                    "Error de carga", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        FondoPanel panelFondo = new FondoPanel(rutaImagen);
        panelFondo.setLayout(new GridBagLayout()); // Layout para organizar componentes
        add(panelFondo);

        // Configuración de layout y restricciones
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Crear etiquetas y campos de texto
        JLabel labelPrimerNombre = new JLabel("Primer Nombre:");
        JTextField txtPrimerNombre = new JTextField(20);
        labelPrimerNombre.setToolTipText("Ingrese su primer nombre.");
        txtPrimerNombre.setToolTipText("Este campo es obligatorio.");

        JLabel labelSegundoNombre = new JLabel("Segundo Nombre:");
        JTextField txtSegundoNombre = new JTextField(20);
        labelSegundoNombre.setToolTipText("Ingrese su segundo nombre (opcional).");

        JLabel labelPrimerApellido = new JLabel("Primer Apellido:");
        JTextField txtPrimerApellido = new JTextField(20);
        labelPrimerApellido.setToolTipText("Ingrese su primer apellido.");
        txtPrimerApellido.setToolTipText("Este campo es obligatorio.");

        JLabel labelSegundoApellido = new JLabel("Segundo Apellido:");
        JTextField txtSegundoApellido = new JTextField(20);
        labelSegundoApellido.setToolTipText("Ingrese su segundo apellido (opcional).");

        // Botón de envío
        JButton btnEnviar = crearBoton("Enviar", "Haga clic para enviar los datos.");
        btnEnviar.addActionListener(e -> {
            String mensaje = String.format(
                    "Datos ingresados:\nPrimer Nombre: %s\nSegundo Nombre: %s\nPrimer Apellido: %s\nSegundo Apellido: %s",
                    txtPrimerNombre.getText(),
                    txtSegundoNombre.getText(),
                    txtPrimerApellido.getText(),
                    txtSegundoApellido.getText()
            );
            JOptionPane.showMessageDialog(this, mensaje, "Datos Ingresados", JOptionPane.INFORMATION_MESSAGE);
        });

        // Posicionar componentes en el panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panelFondo.add(labelPrimerNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFondo.add(txtPrimerNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panelFondo.add(labelSegundoNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFondo.add(txtSegundoNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panelFondo.add(labelPrimerApellido, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFondo.add(txtPrimerApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        panelFondo.add(labelSegundoApellido, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFondo.add(txtSegundoApellido, gbc);

        gbc.gridx = 1; gbc.gridy = 4; gbc.anchor = GridBagConstraints.CENTER;
        panelFondo.add(btnEnviar, gbc);

        // Mostrar la ventana
        setVisible(true);
    }

    // Método para crear botones estilizados
    private JButton crearBoton(String texto, String tooltip) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setToolTipText(tooltip);
        boton.setBackground(new Color(70, 130, 180));
        boton.setForeground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    // Clase personalizada para pintar una imagen de fondo
    private static class FondoPanel extends JPanel {
        private final Image imagenFondo;

        public FondoPanel(String rutaImagen) {
            // Cargar la imagen desde la ruta especificada
            this.imagenFondo = new ImageIcon(rutaImagen).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagenFondo != null) {
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InsertarView::new);
    }
}
