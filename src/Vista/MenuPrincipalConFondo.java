package Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MenuPrincipalConFondo extends JFrame {

    public MenuPrincipalConFondo() {
        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ruta absoluta de la imagen
        String rutaImagen = "C:\\\\Users\\\\PC\\\\Documents\\\\Bases de DAtos\\\\Examen2FBD\\\\GUIExamen2\\\\src\\\\Imagenes\\\\FondoPrincipal.jpg";

        // Verificar que la imagen existe
        if (!new File(rutaImagen).exists()) {
            JOptionPane.showMessageDialog(this, "No se encontró la imagen en la ruta especificada:\n" + rutaImagen,
                    "Error de carga", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        FondoPanel panelFondo = new FondoPanel(rutaImagen);
        panelFondo.setLayout(new BorderLayout()); // Layout para organizar componentes
        add(panelFondo);

        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setOpaque(false); // Para que el fondo sea visible
        JLabel etiquetaTitulo = new JLabel("MENÚ PRINCIPAL");
        etiquetaTitulo.setForeground(Color.WHITE);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        panelTitulo.add(etiquetaTitulo);
        panelFondo.add(panelTitulo, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 15, 20));
        panelBotones.setOpaque(false); // Hacer el panel transparente
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Crear botones estilizados
        JButton btnIngresar = crearBoton("Ingresar", "Registra nuevos datos en el sistema.");
        JButton btnConsultar = crearBoton("Consultar", "Consulta los datos existentes.");
        JButton btnActualizar = crearBoton("Actualizar", "Actualiza los datos en el sistema.");
        JButton btnEliminar = crearBoton("Eliminar", "Elimina datos del sistema.");
        JButton btnSalir = crearBoton("Salir", "Cierra la aplicación.");

        // Agregar botones al panel
        panelBotones.add(btnIngresar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        panelFondo.add(panelBotones, BorderLayout.CENTER);

        // Acción para el botón "Salir"
        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Salir",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Mostrar la ventana
        setVisible(true);
    }

    // Método para crear botones estilizados con opacidad
    private JButton crearBoton(String texto, String tooltip) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setFocusPainted(false);
        boton.setToolTipText(tooltip);

        // Color de fondo transparente
        final Color colorTransparente = new Color(60, 60, 60, 150); // Translúcido
        boton.setBackground(colorTransparente);
        boton.setOpaque(true); // Hacer el fondo opaco pero con transparencia
        boton.setForeground(Color.WHITE); // Texto en blanco
        boton.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2, true)); // Borde visible
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Desactivar efectos visuales al pasar el ratón por encima
        boton.setRolloverEnabled(false); // Desactiva el efecto de rollover
        boton.addChangeListener(e -> {
            // Mantener siempre el color y transparencia original
            if (!boton.getModel().isPressed() && !boton.getModel().isRollover()) {
                boton.setBackground(colorTransparente);
            }
        });

        // Desactivar efectos predeterminados de clic
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);

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
        SwingUtilities.invokeLater(MenuPrincipalConFondo::new);
    }
}