package app.services.graphicServices;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class RecursosService {

    static private RecursosService servicio;

    private Color cWhite, cGray, cTitle, cPrimary, cSecondary, cDark, cBlue, cBlueDark;
    private Font fTitle, ftext, serifJl, serifBtn, fontMediana, fontLigera;
    private Cursor cMano;
    private Border borderLbl, topBorder;
    private ImageIcon iCerrar, iLogotipo, iMinimizar, iIcon, iArrowDown;

    private RecursosService() {
        generarFuentes();
    
        this.crearColores();
        this.crearFuentes();
        this.crearCursores();
        this.crearBordes();
        this.crearImagenes();
      }

    private void crearColores() {
        cWhite = new Color(250, 250, 250);
        cGray = new Color(80, 80, 80);
        cTitle = new Color(199, 61, 191);
        cPrimary = new Color(23,11,58);
        cSecondary = new Color(7,22, 129);
        cBlue = new Color(0,15,105);
        cBlueDark = new Color(10,10,30);
        cDark = new Color(6,6,19);
    }
    
    private void crearFuentes() {
        fTitle = new Font("Nexa Script", Font.PLAIN, 30);
        fontMediana = new Font("LuzSans-Book", Font.BOLD, 20);
        ftext = new Font("LuzSans-Book", Font.PLAIN, 17);
        serifJl = new Font("Roboto Serif", Font.PLAIN, 17);
        serifBtn = new Font("Roboto Serif", Font.PLAIN, 19);
        fontLigera = new Font("LuzSans-Book", Font.PLAIN, 19);
    }
    
    private void crearCursores() { 
        cMano = new Cursor(Cursor.HAND_CURSOR); 
    }
    
    private void crearBordes() {
        borderLbl = BorderFactory.createMatteBorder(0, 0, 1, 0, cGray);
        topBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, cGray);
    }
    
    private void crearImagenes() {
        iLogotipo = new ImageIcon("src/resources/images/logotipo.png");
        iCerrar = new ImageIcon("src/resources/images/cerrar.png");
        iMinimizar = new ImageIcon("src/resources/images/minimizar.png");
        iIcon = new ImageIcon("src/resources/images/icono.png");
        iArrowDown = new ImageIcon("src/resources/images/arrow-down.png");
    }
    
    public void generarFuentes(){
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(
                Font.TRUETYPE_FONT,
                new File("src/resources/fonts/LUZRO.TTF")));
            ge.registerFont(Font.createFont(
                Font.TRUETYPE_FONT,
                new File("src/resources/fonts/Nexa Script 400.otf")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Color getColorcWhite() { return cWhite; }

    public Color getColorcGray() { return cGray; }

    public Color getColorcTitle() { return cTitle; }

    public Color getColorPrimary() { return cPrimary; }

    public Color getColorSecondary() { return cSecondary; }

    public Color getColorDark() { return cDark; }
    
    public Color getColorBlue() { return cBlue; }

    public Color getColorBlueDark() { return cBlueDark; }
    
    public Font getFontfTitle() { return fTitle; }
    
    public Font getFontftext() { return ftext; }
    
    public Font getFontserifJl() { return serifJl; }

    public Font getFontserifBtn() { return serifBtn; }
    
    public Font getFontfontMediana() { return fontMediana; }

    public Font getFontLigera() { return fontLigera; }
    
    public Cursor getCMano() { return cMano; }
    
    public Border getBborderLbl() { return borderLbl; }

    public Border getBTopBorder() { return topBorder; }
    
    public ImageIcon getICerrar() { return iCerrar; }

    public ImageIcon getILogotipo() { return iLogotipo; }

    public ImageIcon getIMinimizar() { return iMinimizar; }

    public ImageIcon getIIcon() { return iIcon; }

    public ImageIcon getIArrowDown() { return iArrowDown; }

    
    public static RecursosService getService() {
        if (servicio == null){
            servicio = new RecursosService();
        }
        return servicio;
    }
}
