/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Articulos;
import modelo.Libros;
import modelo.Notas_articulo;
import modelo.Notas_libro;
import vista.interfaz;

/**
 *
 * @author ciuda
 */
public class Controlador implements ActionListener,MouseListener,ItemListener {
    
    
    
    Libros l = new Libros();
    Articulos a= new Articulos();
    interfaz in = new interfaz();
    Notas_libro nl= new Notas_libro();
    Notas_articulo na=new Notas_articulo();
    int fila=-1;
    int fila2=-1;
    int fila3=-1;
    int fila4=-1;
    int fila5=-1;
    int fila6=-1;
    int fila7=-1;
    int fila8 = -1;
    String ISBN;
    String ISSN;
    int cod;
    int cod2;
    int cod3;
    int cod4;
    int cod5;
    int cod6;
    
    
    Controlador(){
        
    }
    
    
    
    

   

   

   
    public void mousePressed(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

   
   
    public void mouseExited(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

   

    
    public void itemStateChanged(ItemEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

   
    public void mouseEntered(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    public enum AccionMVC
    {
        
      //Libro
        añadirlibro,
        modiflibro,
        notalibro,

        /**
         *
         */
        buscarlibro,
        //articulo
        añadirartic,
        modifartic,
        notaartic,
        buscararticulo,
        //notas
        buscarnotaptem,
        buscarnotaptit,
        //articulo.añadir
        btnañadirarticulo,
        //Libro.añadir
        btnañadirlibro,
        //pelicula.modificar
        btnmodiflibro,
        //articulo.modificar
        btnmodifarticulo,
        //nota.añadir
        btnañadnotalibro,
        
        btnañadnotaarticulo,
        
        ComboBox1,
        
        buscnotatit,
        
        buscnotatem,
        
        ComboBox2,
        ComboBoxlibro,
        btnelimlibro,
        btnelimarticulo,
        btnmodifnotlib,
        btnmodifnotart,
        btnmodifnotlibro,
        btnmodifnotarticulo
        
        
    }
    public void iniciar() throws SQLException{
        
        in.txtinvisibleelimlibro.setVisible(false);
        in.txtinvisibleelimart.setVisible(false);
         try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(in);
            in.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
         
         this.in.añadirlibro.setActionCommand("añadirlibro");
         this.in.añadirlibro.addActionListener(this);
         
         this.in.modiflibro.setActionCommand("modiflibro"+"");
         this.in.modiflibro.addActionListener(this);
         
         this.in.notalibro.setActionCommand("notalibro");
         this.in.notalibro.addActionListener(this);
         
         this.in.tablalibro.addMouseListener(this);
         this.in.tablalibro.setModel(this.l.getTablaLibros());
         
         
         
         //falta boton buscar libros
         
         this.in.añadirartic.setActionCommand("añadirartic");
         this.in.añadirartic.addActionListener(this);
         
         this.in.modifartic.setActionCommand("modifartic");
         this.in.modifartic.addActionListener(this);
         
         this.in.notaartic.setActionCommand("notaartic");
         this.in.notaartic.addActionListener(this);
         
         this.in.tablartic.addMouseListener(this);
         this.in.tablartic.setModel(this.a.getTablaArticulos());
         
         
         this.in.tablanotaslibro.addMouseListener(this);
         this.in.tablanotaslibro.setModel(this.nl.getTablaNotasLibro());
         
         this.in.tablanotasarticulo.addMouseListener(this);
         this.in.tablanotasarticulo.setModel(this.na.getTablaNotasArticulos());
         
         this.in.btnañadirlibro.setActionCommand("btnañadirlibro");
         this.in.btnañadirlibro.addActionListener(this);
         
         //falta boton buscar articulos
         this.in.btnañadirarticulo.setActionCommand("btnañadirarticulo");
         this.in.btnañadirarticulo.addActionListener(this);
         
         this.in.btnañadnotalibro.setActionCommand("btnañadnotalibro");
         this.in.btnañadnotalibro.addActionListener(this);
         
         this.in.btnmodifarticulo.setActionCommand("btnmodifarticulo");
         this.in.btnmodifarticulo.addActionListener(this);
         
         this.in.btnmodiflibro.setActionCommand("btnmodiflibro");
         this.in.btnmodiflibro.addActionListener(this);
         
         this.in.btnañadnotaarticulo.setActionCommand("btnañadnotaarticulo");
         this.in.btnañadnotaarticulo.addActionListener(this);
         
         //faltan botones buscar notas
         this.in.buscnotatit.setActionCommand("buscnotatit");
         this.in.buscnotatit.addActionListener(this);
         
          this.in.buscnotatem.setActionCommand("buscnotatem");
         this.in.buscnotatem.addActionListener(this);
         
         this.in.buscarlibro.setActionCommand("buscarlibro");
         this.in.buscarlibro.addActionListener(this);
         
         this.in.buscararticulo.setActionCommand("buscararticulo");
         this.in.buscararticulo.addActionListener(this);
         
         this.in.btnelimlibro.setActionCommand("btnelimlibro");
         this.in.btnelimlibro.addActionListener(this);
         
         this.in.btnelimarticulo.setActionCommand("btnelimarticulo");
         this.in.btnelimarticulo.addActionListener(this);
         
         this.in.btnmodifnotlib.setActionCommand("btnmodifnotlib");
         this.in.btnmodifnotlib.addActionListener(this);
         
         this.in.btnmodifnotart.setActionCommand("btnmodifnotart");
         this.in.btnmodifnotart.addActionListener(this);
         
         this.in.btnmodifnotlibro.setActionCommand("btnmodifnotlibro");
         this.in.btnmodifnotlibro.addActionListener(this);
         
          this.in.btnmodifnotarticulo.setActionCommand("btnmodifnotarticulo");
         this.in.btnmodifnotarticulo.addActionListener(this);
         
         
    }  
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
        case añadirlibro:
            in.añadir_libro.setVisible(true);
            in.añadir_libro.setSize(600,345);
        {
            try {
                in.tablalibro.setModel(this.l.getTablaLibros());
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;

            
        case modiflibro:
            
            in.modificar_libro.setVisible(true);
            in.modificar_libro.setSize(600, 345);
        {
            try {
                in.tablalibro.setModel(this.l.getTablaLibros());
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
         break;

         
        case notalibro:
            in.añadirnotalibro.setVisible(true);
            in.añadirnotalibro.setSize(600,345);
        {
            try {
                in.tablanotaslibro.setModel(this.nl.getTablaNotasLibro());
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;

            
        case añadirartic:
            in.añadir_articulo.setVisible(true);
            in.añadir_articulo.setSize(600,345);
            in.tablartic.setModel(this.a.getTablaArticulos());
            break;
            
        case modifartic:
            in.modificar_articulo.setVisible(true);
            in.modificar_articulo.setSize(600,345);
            in.tablartic.setModel(this.a.getTablaArticulos());
            break;
            
        case notaartic:
            
            in.añadirnotaarticulo.setVisible(true);
            in.añadirnotaarticulo.setSize(600,345);
            
             {
            try {
                in.tablanotasarticulo.setModel(this.na.getTablaNotasArticulos());
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            
            
            
        case btnañadirlibro:   
        {
            try{
                if(this.l.añadir_libros(
                      this.in.txtañadlibroisbn.getText(),
                        this.in.txtañadlibrotitulo.getText(),
                        this.in.txtaañadlibroautor.getText(),
                        this.in.txtañadlibronpag.getText(),
                        this.in.txtañadlibroedit.getText(),
                        this.in.txtañadlibroaño.getText()
                       )){
                    
                    this.in.tablalibro.setModel(this.l.getTablaLibros());
                     JOptionPane.showMessageDialog(in,"Exito: Registro añadido.");
                     this.in.txtañadlibroisbn.setText("");
                     this.in.txtañadlibrotitulo.setText("");
                     this.in.txtaañadlibroautor.setText("");
                     this.in.txtañadlibronpag.setText("");
                     this.in.txtañadlibroedit.setText("");
                     this.in.txtañadlibroaño.setText("");
                     
                    
                }else{
                     JOptionPane.showMessageDialog(in,"Error: Los datos son incorrectos.");
                }
                    
                
            } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception t) {
           JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
        }
        this.in.añadir_libro.setVisible(false);
        break;
        
        case btnañadirarticulo: 
        {
            try{
                if(this.a.Añadir_articulo(
                this.in.txtañadartissn.getText(),
                this.in.txtañadartautor.getText(),
                this.in.txtañadarttitulo.getText(),
                this.in.txtañadartnomrevist.getText(),
                this.in.txtañadartaño.getText(),
                this.in.txtañadartmes.getText(),
                this.in.txtañadartpaginic.getText(),
                this.in.txtañadartpagfin.getText()
                )){
               
                 this.in.tablartic.setModel(this.a.getTablaArticulos());
                     JOptionPane.showMessageDialog(in,"Exito: Registro añadido.");
                     
                this.in.txtañadartissn.setText("");
                this.in.txtañadartautor.setText("");
                this.in.txtañadarttitulo.setText("");
                this.in.txtañadartnomrevist.setText("");
                this.in.txtañadartaño.setText("");
                this.in.txtañadartmes.setText("");
                this.in.txtañadartpaginic.setText("");
                this.in.txtañadartpagfin.setText("");
                        
        
                
            }else{
                     JOptionPane.showMessageDialog(in,"Error: Los datos son incorrectos.");
                }
            
        } catch (Exception t) {
           JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
            this.in.añadir_articulo.setVisible(false);
                        }break;
                        
        case btnmodifarticulo:
        {
            
            
        try {
            this.a.Modificar_articulo(
                    
                    this.in.txtmodifartautor.getText(),
                    this.in.txtmodifarttitulo.getText(),
                    this.in.txtmodifartnomrevist.getText(),
                    this.in.txtmodifartaño.getText(),
                    this.in.txtmodifartmes.getText(),
                    this.in.txtmodifartpaginic.getText(),
                    this.in.txtmodifartpagfin.getText(),
                    cod);
      
                    
            
            this.in.tablartic.setModel(this.a.getTablaArticulos());
            JOptionPane.showMessageDialog(in,"Exito: Registro modificado.");
            this.in.txtmodifartautor.setText("");
            this.in.txtmodifarttitulo.setText("");
            this.in.txtmodifartnomrevist.setText("");
            this.in.txtmodifartaño.setText("");
            this.in.txtmodifartmes.setText("");
            this.in.txtmodifartpaginic.setText("");
            this.in.txtmodifartpagfin.setText("");
            in.modificar_articulo.setVisible(false);
           
        
  } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

                        }break;
                        
        case btnmodiflibro:
            
        {
            try{
                this.l.Modificar_libros(
                        
                this.in.txtmodiflibrotitulo.getText(),
                this.in.txtmodiflibroautor.getText(),
                this.in.txtmodiflibronpag.getText(),
                this.in.txtmodiflibroedit.getText(),
                this.in.txtmodiflibroaño.getText(),
                cod2
                
                );
                
                this.in.tablalibro.setModel(this.l.getTablaLibros());
                 JOptionPane.showMessageDialog(in,"Exito: Registro modificado.");
                this.in.txtmodiflibrotitulo.setText("");
                this.in.txtmodiflibroautor.setText("");
                this.in.txtmodiflibronpag.setText("");
                this.in.txtmodiflibroedit.setText("");
                this.in.txtmodiflibroaño.setText("");
              in.modificar_libro.setVisible(false);
            } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

                        }break;
                        
                        
        case btnañadnotalibro:
        {
            try{
               if( this.nl.añadir_nota(
                
                this.in.txtañadnotlibtit.getText(),
                this.in.txtañadnotlibtema.getText(),
                this.in.txtañadnotlibtexto.getText(),
                cod2)){
                   
                    this.in.tablanotaslibro.setModel(this.nl.getTablaNotasLibro());
                JOptionPane.showMessageDialog(in,"Exito: Registro añadido.");
                
                this.in.txtañadnotlibtit.setText("");
                this.in.txtañadnotlibtema.setText("");
                this.in.txtañadnotlibtexto.setText("");
                   
               } else {
                JOptionPane.showMessageDialog(in,"Error: Los datos son incorrectos.");
            }
            }catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.in.añadirnotalibro.setVisible(false);
        }break;
        
        case btnañadnotaarticulo:
            
             {
            try{
               if( this.na.añadir_nota(
                
                this.in.txtañadnotarttema.getText(),
                this.in.txtañadnotarttitulo.getText(),
                this.in.txtañadnotarttexto.getText(),
                cod)){
                   
                    this.in.tablanotasarticulo.setModel(this.na.getTablaNotasArticulos());
                JOptionPane.showMessageDialog(in,"Exito: Registro añadido.");
                
                this.in.txtañadnotarttema.setText("");
                this.in.txtañadnotarttitulo.setText("");
                this.in.txtañadnotarttexto.setText("");
                   
               } else {
                JOptionPane.showMessageDialog(in,"Error: Los datos son incorrectos.");
            }
            }catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.in.añadirnotaarticulo.setVisible(false);
        }break;
        
        case btnelimlibro:
            
        {
            try {
                in.tablalibro.setModel(this.l.getTablaLibros());
                if(this.l.Eliminar_libros(this.in.txtinvisibleelimlibro.getText())){
                    this.in.tablalibro.setModel(this.l.getTablaLibros());
                    this.in.tablanotaslibro.setModel(this.nl.getTablaNotasLibro());
                     JOptionPane.showMessageDialog(in,"Exito: Registro eliminado.");
                       this.in.txtinvisibleelimlibro.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }break;

             case btnelimarticulo:
            
        {
            in.tablartic.setModel(this.a.getTablaArticulos());
            if(this.a.Eliminar_articulos(this.in.txtinvisibleelimart.getText())){
                this.in.tablartic.setModel(this.a.getTablaArticulos());
                try {
                    this.in.tablanotasarticulo.setModel(this.na.getTablaNotasArticulos());
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(in,"Exito: Registro eliminado.");
                this.in.txtinvisibleelimart.setText("");
            }
        }
break;

             case btnmodifnotart:
                in.modificarnota_articulo.setVisible(true);
                 in.modificarnota_articulo.setSize(600,345);
        {
            try {
                in.tablanotasarticulo.setModel(this.na.getTablaNotasArticulos());
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }break;
        
        
             case btnmodifnotlib:
                 
                 
                 in.modificarnota_libro.setVisible(true);
                 in.modificarnota_libro.setSize(600,345);
        {
            try {
                in.tablanotaslibro.setModel(this.nl.getTablaNotasLibro());
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            
             case btnmodifnotlibro:
                 
                   {
            try{
                this.nl.modificarnota_libro(
                        
                this.in.txtmodifnotlibtitulo.getText(),
                this.in.txtmodifnotlibtema.getText(),
                this.in.txtmodifnotlibtexto.getText(),
                cod5
                
                );
                
                this.in.tablanotaslibro.setModel(this.nl.getTablaNotasLibro());
                 JOptionPane.showMessageDialog(in,"Exito: Registro modificado.");
                this.in.txtmodifnotlibtitulo.setText("");
                this.in.txtmodifnotlibtema.setText("");
                this.in.txtmodifnotlibtexto.setText("");
                
              in.modificarnota_libro.setVisible(false);
            } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

                        }break;
                        
             case btnmodifnotarticulo:
                        
                   {
            try{
                this.na.modificarnota_articulo(
                        
                this.in.txtmodifnotarttitulo.getText(),
                this.in.txtmodifnotarttema.getText(),
                this.in.txtmodifnotarttexto.getText(),
                cod6
                
                );
                
                this.in.tablanotasarticulo.setModel(this.na.getTablaNotasArticulos());
                 JOptionPane.showMessageDialog(in,"Exito: Registro modificado.");
                this.in.txtmodifnotarttitulo.setText("");
                this.in.txtmodifnotarttema.setText("");
                this.in.txtmodifnotarttexto.setText("");
                
              in.modificarnota_articulo.setVisible(false);
            } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

                        }break;
        }
    
    
         
    }
    public void mouseClicked(MouseEvent e){
        
        //boton izquierdo
        if(e.getButton()==1){
        fila = this.in.tablalibro.rowAtPoint(e.getPoint());//primeros para momdificar
        fila2= this.in.tablartic.rowAtPoint(e.getPoint());
        fila3 = this.in.tablalibro.rowAtPoint(e.getPoint());//para notas
        fila4 = this.in.tablartic.rowAtPoint(e.getPoint());
        fila5 = this.in.tablalibro.rowAtPoint(e.getPoint());//para eliminar
        fila6 = this.in.tablartic.rowAtPoint(e.getPoint());
        fila7 = this.in.tablanotaslibro.rowAtPoint(e.getPoint());//modificar notas
        fila8 = this.in.tablanotasarticulo.rowAtPoint(e.getPoint());

       
    }
         if(fila>-1){
            
            cod2 = Integer.parseInt((String)this.in.tablalibro.getValueAt(fila, 0));
            
           in.txtmodiflibroisbn.setText(String.valueOf(this.in.tablalibro.getValueAt(fila,0)));
            in.txtmodiflibrotitulo.setText(String.valueOf(this.in.tablalibro.getValueAt(fila, 1)));
            in.txtmodiflibroautor.setText(String.valueOf(this.in.tablalibro.getValueAt(fila, 2)));
            in.txtmodiflibronpag.setText(String.valueOf(this.in.tablalibro.getValueAt(fila, 3)));
            in.txtmodiflibroedit.setText(String.valueOf(this.in.tablalibro.getValueAt(fila, 4)));
            in.txtmodiflibroaño.setText(String.valueOf(this.in.tablalibro.getValueAt(fila, 5)));
        }
         
         if(fila2>-1 && e.getSource().equals(in.tablartic)){
             
             cod=Integer.parseInt((String) this.in.tablartic.getValueAt(fila2, 0));
             in.txtmodifartissn.setText(String.valueOf(this.in.tablalibro.getValueAt(fila2,0)));
             in.txtmodifartautor.setText(String.valueOf(this.in.tablartic.getValueAt(fila2, 1)));
             in.txtmodifarttitulo.setText(String.valueOf(this.in.tablartic.getValueAt(fila2, 2)));
             in.txtmodifartnomrevist.setText(String.valueOf(this.in.tablartic.getValueAt(fila2, 3)));
             in.txtmodifartaño.setText(String.valueOf(this.in.tablartic.getValueAt(fila2, 4)));
             in.txtmodifartmes.setText(String.valueOf(this.in.tablartic.getValueAt(fila2, 5)));
             in.txtmodifartpaginic.setText(String.valueOf(this.in.tablartic.getValueAt(fila2, 6)));
             in.txtmodifartpagfin.setText(String.valueOf(this.in.tablartic.getValueAt(fila2, 7)));
             
             
         }
         if(fila3>-1 && e.getSource().equals(in.tablalibro)){
             cod2 = Integer.parseInt((String)this.in.tablalibro.getValueAt(fila3, 0));
             in.txtañadnotlibtit.setText(String.valueOf(this.in.tablalibro.getValueAt(fila3, 1)));
         }
         
         if(fila4>-1 && e.getSource().equals(in.tablartic)){
              cod=Integer.parseInt((String) this.in.tablartic.getValueAt(fila4, 0));
              in.txtañadnotarttitulo.setText(String.valueOf(this.in.tablartic.getValueAt(fila4, 2)));
         }
         if(fila5>-1 && e.getSource().equals(in.tablalibro)){
             cod3= Integer.parseInt((String)this.in.tablalibro.getValueAt(fila5, 0));
             in.txtinvisibleelimlibro.setText(String.valueOf(this.in.tablalibro.getValueAt(fila5, 0)));
         }
         
         if(fila6>-1 && e.getSource().equals(in.tablartic)){
              cod4=Integer.parseInt((String) this.in.tablartic.getValueAt(fila6, 0));
              in.txtinvisibleelimart.setText(String.valueOf(this.in.tablartic.getValueAt(fila6, 0)));
         }
         if(fila7>-1 && e.getSource().equals(in.tablanotaslibro)){
             cod5= Integer.parseInt((String) this.in.tablanotaslibro.getValueAt(fila7, 0));
             in.txtmodifnotlibtitulo.setText(String.valueOf(this.in.tablanotaslibro.getValueAt(fila7, 2)));
             in.txtmodifnotlibtema.setText(String.valueOf(this.in.tablanotaslibro.getValueAt(fila7, 3)));
             in.txtmodifnotlibtexto.setText(String.valueOf(this.in.tablanotaslibro.getValueAt(fila7, 4)));
         }
         if(fila8>-1 && e.getSource().equals(in.tablanotasarticulo)){
             cod6= Integer.parseInt((String) this.in.tablanotasarticulo.getValueAt(fila8, 0));
             in.txtmodifnotarttitulo.setText(String.valueOf(this.in.tablanotasarticulo.getValueAt(fila8, 2)));
             in.txtmodifnotarttema.setText(String.valueOf(this.in.tablanotasarticulo.getValueAt(fila8, 3)));
             in.txtmodifnotarttexto.setText(String.valueOf(this.in.tablanotasarticulo.getValueAt(fila8, 4)));
         }
         
         
    }
    
    
    

}
