package app.services.graphicServices;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class ObjGraficosService {
    private ObjGraficosService() {
    }

    private JPanel panel;
    private JButton button;
    private JScrollPane panelScroll;
    private JLabel label;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JPasswordField passwordField;
    private ImageIcon iDimAux;

    static private ObjGraficosService servicio;

    public JPanel construirJPanel(int x, int y, int ancho, int alto, Color colorFondo, Border borde) {
        panel = new JPanel();
        panel.setSize(ancho, alto);
        panel.setLocation(x, y);
        panel.setLayout(null);
        panel.setBackground(colorFondo);
        panel.setBorder(borde);
        return panel;
    }

    public JButton construirJButton(
            String texto, int x, int y, int ancho, int alto, Cursor cursor, ImageIcon imagen, Font fuente,
            Color colorFondo, Color colorFuente, Border borde, String direccion, boolean esSolido) {
        button = new JButton(texto);
        button.setLocation(x, y);
        button.setSize(ancho, alto);
        button.setFocusable(false);
        button.setCursor(cursor);
        button.setFont(fuente);
        button.setBackground(colorFondo);
        button.setForeground(colorFuente);
        button.setIcon(imagen);
        button.setBorder(borde);
        button.setContentAreaFilled(esSolido);
        switch (direccion) {
            case "l":
                button.setHorizontalAlignment(SwingConstants.LEFT);
                break;
            case "r":
                button.setHorizontalAlignment(SwingConstants.RIGHT);
                button.setHorizontalTextPosition(SwingConstants.LEFT);
                break;
            case "t":
                button.setVerticalTextPosition(SwingConstants.TOP);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                break;
            case "b":
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                break;
            default:
                break;
        }
        return button;
    }

    public JLabel construirJLabel(
            String texto, int x, int y, int ancho, int alto, Cursor cursor, ImageIcon imagen,
            Font fuente, Color colorFondo, Color colorFuente, Border borde, String direccion) {
        label = new JLabel(texto);
        label.setLocation(x, y);
        label.setSize(ancho, alto);
        label.setForeground(colorFuente);
        label.setFont(fuente);
        label.setCursor(cursor);
        label.setIcon(imagen);
        label.setBorder(borde);
        if (colorFondo != null) {
            label.setOpaque(true);
            label.setBackground(colorFondo);
        }
        switch (direccion) {
            case "c":
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                break;
            case "r":
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setHorizontalTextPosition(SwingConstants.LEFT);
                break;
            case "t":
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalTextPosition(SwingConstants.TOP);
                label.setHorizontalTextPosition(SwingConstants.CENTER);
                break;
            case "b":
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalTextPosition(SwingConstants.BOTTOM);
                label.setHorizontalTextPosition(SwingConstants.CENTER);
                break;
            case "l":
                label.setHorizontalAlignment(SwingConstants.LEFT);
                break;
            default:
                break;
        }
        return label;
    }

    public JTextField construirJTextField(
            String texto, int x, int y, int ancho, int alto, Font fuente, Color colorFondo,
            Color colorFuente, Color colorCaret, Border borde, String direccion) {
        textField = new JTextField();
        textField.setLocation(x, y);
        textField.setSize(ancho, alto);
        textField.setText(texto);
        textField.setForeground(colorFuente);
        textField.setBackground(colorFondo);
        textField.setCaretColor(colorCaret);
        textField.setFont(fuente);
        textField.setBorder(BorderFactory.createCompoundBorder(borde, BorderFactory.createEmptyBorder(0, 8, 0, 8)));
        switch (direccion) {
            case "c":
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case "r":
                textField.setHorizontalAlignment(SwingConstants.RIGHT);
                break;
            default:
                break;
        }
        return textField;
    }

    public JPasswordField construirJPasswordField(
            String texto, int x, int y, int ancho, int alto, Font fuente, Color colorFondo,
            Color colorFuente, Color colorCaret, Border borde, String direccion) {
        passwordField = new JPasswordField();
        passwordField.setLocation(x, y);
        passwordField.setSize(ancho, alto);
        passwordField.setText(texto);
        passwordField.setForeground(colorFuente);
        passwordField.setBackground(colorFondo);
        passwordField.setCaretColor(colorCaret);
        passwordField.setBorder(borde);
        switch (direccion) {
            case "c":
                passwordField.setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case "r":
                passwordField.setHorizontalAlignment(SwingConstants.RIGHT);
                break;
            default:
                break;
        }
        return passwordField;
    }

    public ImageIcon reziseIcon(ImageIcon img, int ancho, int alto) {
        iDimAux = new ImageIcon(
                img.getImage()
                        .getScaledInstance(ancho, alto, Image.SCALE_AREA_AVERAGING));
        return iDimAux;
    }

    public JScrollPane construirPanelBarra(
            Component componente, int x, int y, int ancho, int alto, Color colorFondo, Border borde) {
        panelScroll = new JScrollPane(componente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelScroll.setLocation(x, y);
        panelScroll.setSize(ancho, alto);
        panelScroll.getViewport().setBackground(colorFondo);
        panelScroll.setBorder(borde);
        return panelScroll;
    }

    public JComboBox<String> construirJComboBox(
            int x, int y, int ancho, int alto, Color colorFondo, Color colorFuente, Border border, String direccion) {
        comboBox = new JComboBox<String>();
        comboBox.setSize(ancho, alto);
        comboBox.setLocation(x, y);
        comboBox.setBackground(colorFondo);
        comboBox.setForeground(colorFuente);
        comboBox.setBorder(border);
        comboBox.setOpaque(false);
        switch (direccion) {
            case "c":
                ((JLabel) comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case "r":
                ((JLabel) comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.RIGHT);
                break;
            case "l":
                ((JLabel) comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.LEFT);
                break;
            default:
                break;
        }
        return comboBox;
    }

    public static ObjGraficosService getService() {
        if (servicio == null)
            servicio = new ObjGraficosService();
        return servicio;
    }
}
