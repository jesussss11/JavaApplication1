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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ciuda
 */
public class Notas_articulo extends database{
    
    
     
    String texto;
    String titulo;
    String tema;
 
    int ISSN_articulo;
    
    
    public boolean añadir_nota( String titulo,String tema, String texto,int cod){
          String q="INSERT INTO notas_articulos (ISSN_articulo,titulo,tema,texto) VALUES ('"+cod+"','"+titulo+"','"+tema+"','"+texto+"')";
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
    
          public DefaultTableModel getTablaNotasArticulos() throws SQLException{
         DefaultTableModel  tablemodel = new DefaultTableModel();
         int registros = 0;
         String [] columNames ={"ISSN" , "Titulo","Tema","Texto"};
              try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM notas_articulos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
               Object[][] data = new String[registros][4];
                
                 try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM notas_articulos");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "ISSN_articulo" );
                data[i][1] = res.getString( "titulo" );
                data[i][2] = res.getString( "tema" );
                data[i][3] = res.getString( "texto" );
           
                 
            i++;
         }res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
        
        
}
          database database=new database();
            DefaultTableModel ModeloTabla;  
          
          public void Buscar(String valor, String filtro, JTable tablanotasarticulo){

    String [] columnas={"ISSN","Titulo","Tema","Texto"};
    String [] registro=new String[4];
    ModeloTabla =new DefaultTableModel(null,columnas);
    String SSQL = null;
    Connection conn = null;

    if(filtro.equals("titulo")){

        SSQL= "SELECT ISSN_articulo,titulo,tema,texto "
                 + "FROM notas_articulos WHERE titulo LIKE '%"+valor+"%'";

      }else if(filtro.equals("tema")){

        SSQL= "SELECT ISSN_articulo,titulo,tema,texto "
                 + "FROM notas_articulos WHERE Tema LIKE '%"+valor+"%'";
      }

    try {


    conn = (Connection) database.getConexion();
        PreparedStatement st = conn.prepareStatement(SSQL);

        ResultSet rs = st.executeQuery();

        while (rs.next()){

            registro[0]=rs.getString("ISSN_articulo");
            registro[1]=rs.getString("titulo");
            registro[2]=rs.getString("tema");
            registro[3]=rs.getString("texto");


            ModeloTabla.addRow(registro);


        }

        tablanotasarticulo.setModel(ModeloTabla);

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
            public boolean modificarnota_articulo  ( String titulo, String tema,String texto, int cod6) throws SQLException{
        
            String q = "UPDATE notas_articulos SET  titulo='"+titulo+"',tema='"+tema+"',texto='"+texto+"' where ISSN_articulo ='"+cod6+"'";
            
             try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            } return false;
       
            
        }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getISSN_articulo() {
        return ISSN_articulo;
    }

    public void setISSN_articulo(int ISNN_articulo) {
        this.ISSN_articulo = ISSN_articulo;
    }
    
}
