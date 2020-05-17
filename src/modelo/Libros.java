/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import javax.swing.JTable;
/**
 *
 * @author ciuda
 */
public class Libros extends database{
    int ISBN;
    String titulo;
    String autor;
    int npagin;
    String editorial;
    String anio;
    boolean res;
    
    //cambiar A de añadir a miniscula
    public boolean Añadir_libros ( String titulo, String autor, String npagin, String editorial){
        
        String q="INSERT INTO Libros (ISBN,titulo,autor,npagin,editorial) VALUES ('"+ISBN+"','"+titulo+"','"+autor+"','"+npagin+"','"+editorial+"')";
         try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
         return false;
    }
    
    
    //cambiar a de añadir a mayuscula
    public boolean añadir_libros( String ISBN,String titulo, String autor, String npagin, String editorial,String anio){
       
         if( valida_datos(   
             autor, titulo, ISBN) )
        {
       try {
        CallableStatement pstm;
        pstm = this.getConexion().prepareCall("{call Añadir_libro(?,?,?,?,?,?)}");
        pstm.setString(1, ISBN);
        pstm.setString(2, titulo);
        pstm.setString(3, autor);
        pstm.setString(4, npagin);
        pstm.setString(5, editorial);
        pstm.setString(6, anio);
       
        pstm.execute();
        pstm.close();
           res = true;
       }catch (SQLException e){
           System.err.println(e.getMessage());
       }
        return res;
       }return false;
    
    }
             
   
         
    /**
     *
     * 
     * @param titulo
     * @param autor
     * @param npagin
     * @param editorial
     * @return
     */
    //cambiar a mayuscula M para cambiar metodo
    public boolean modificar_libros  ( String titulo, String autor, String npagin, String editorial, String anio, int cod2) throws SQLException{
        
            String q = "UPDATE Libros SET titulo= '"+titulo+"', autor='"+autor+"',npagin='"+npagin+"',editorial='"+editorial+"' where ISBN ='"+cod2+"'";
            
             try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            } return false;
       
            
        }
      
    //cambiar minuscula m para cambiar el metodo
            public boolean Modificar_libros(  String titulo, String autor, String npagin, String editorial, String anio, int cod2){
       
       boolean res=false;
       try {
        CallableStatement pstm;
        pstm = this.getConexion().prepareCall("{call Modificar_libro(?,?,?,?,?,?)}");
        pstm.setString(1, titulo);
        pstm.setString(2, autor);
        pstm.setString(3, npagin);
        pstm.setString(4, editorial);
        pstm.setString(5, anio);
        pstm.setInt(6, cod2);
        pstm.execute();
        pstm.close();
        res=true;
       }catch (SQLException e){
           System.err.println(e.getMessage());
       }
        return res;
       }
        
        
       public boolean Eliminar_libros(String cod3){
        
       boolean res=false;
       try {
        CallableStatement pstm;
        pstm = this.getConexion().prepareCall("{call Eliminar_libro(?)}");
        pstm.setString(1, cod3);
        pstm.execute();
        pstm.close();
        res=true;
       }catch (SQLException e){
           System.err.println(e.getMessage());
       }
        return res;
       }
    

    
         
              public DefaultTableModel getTablaLibros() throws SQLException{
         DefaultTableModel  tablemodel = new DefaultTableModel();
         int registros = 0;
         String [] columNames ={"ISBN"," Titulo","Autor","N.Paginas","Editorial","Año"};
              try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Libros");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
              
                Object[][] data = new String[registros][6];
                
                 try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Libros");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "ISBN" );
                data[i][1] = res.getString( "titulo" );
                data[i][2] = res.getString( "autor" );
                data[i][3] = res.getString( "npagin" );
                data[i][4] = res.getString( "editorial" );
                data[i][5] = res.getString( "anio" );
                 
            i++;
         }res.close();
         
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
              }
              
              
              
              
              
                
               database database=new database();
            DefaultTableModel ModeloTabla;  
          
          public void Buscar(String valor, String filtro, JTable tablalibro){

    String [] columnas={"ISBN","Titulo","Autor","Paginas","Editorial","Año"};
    String [] registro=new String[6];
    ModeloTabla =new DefaultTableModel(null,columnas);
    String SSQL = null;
    Connection conn = null;

    if(filtro.equals("titulo")){

        SSQL= "SELECT ISBN,titulo,autor,npagin,editorial,anio "
                 + "FROM Libros WHERE titulo LIKE '%"+valor+"%'";

    }else if(filtro.equals("paginas")){

        SSQL= "SELECT ISBN,titulo,autor,npagin,editorial,anio "
                 + "FROM Libros WHERE npagin LIKE '%"+valor+"%'";
    }
    else if(filtro.equals("autor")){

        SSQL= "SELECT ISBN,titulo,autor,npagin,editorial,anio "
                 + "FROM Libros WHERE autor LIKE '%"+valor+"%'";
    } else if(filtro.equals("editorial")){

        SSQL= "SELECT ISBN,titulo,autor,npagin,editorial,anio "
                 + "FROM Libros WHERE editorial LIKE '%"+valor+"%'";
    }  else if(filtro.equals("año")){

        SSQL= "SELECT ISBN,titulo,autor,npagin,editorial,anio "
                 + "FROM Libros WHERE anio LIKE '%"+valor+"%'";
    }

    try {


    conn = (Connection) database.getConexion();
        PreparedStatement st = conn.prepareStatement(SSQL);

        ResultSet rs = st.executeQuery();

        while (rs.next()){

            registro[0]=rs.getString("ISBN");
            registro[1]=rs.getString("titulo");
            registro[2]=rs.getString("autor");
            registro[3]=rs.getString("npagin");
            registro[4]=rs.getString("editorial");
            registro[5]=rs.getString("anio");


            ModeloTabla.addRow(registro);


        }

        tablalibro.setModel(ModeloTabla);

    } catch (SQLException e) {


        JOptionPane.showMessageDialog(null, e, "Error durante el procedimiento", JOptionPane.ERROR_MESSAGE);


    }finally{

        if(conn!=null){

            try {

                conn.close();

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

}
          
          private boolean valida_datos( String autor, String titulo, String ISBN) {
             
         if( autor.equals("") || titulo.equals("") || !ISBN.matches("^[0-9]{0,12}$")){
            return false;
        }else return true;
        
        } 

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

          
          
    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNpagin() {
        return npagin;
    }

    public void setNpagin(int npagin) {
        this.npagin = npagin;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

       
              
}