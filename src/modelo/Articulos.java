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
public class Articulos extends database{
    int ISSN;
    String autor;
    String titulo;
    String nombrerevist;
    String anio;
    String mes;
    String paginic;
    String pagfin;
    
    
    public boolean Añadir_articulo(  String ISSN ,String autor, String titulo, String nombrerevist,String anio, String mes, String paginic, String pagfin) throws SQLException{
        
         if( valida_datos(   
             autor, titulo, ISSN) )
        {
         String q ="INSERT INTO Articulos(ISSN,autor,titulo,nombrerevist,anio,mes,paginic,pagfin) VALUES ('"+ISSN+"','"+autor+"','"+titulo+"','"+nombrerevist+"','"+anio+"','"+mes+"','"+paginic+"','"+pagfin+"')";
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
         return false;
    }
    public boolean Modificar_articulo( String autor, String titulo, String nombrerevist,String anio, String mes, String paginic, String pagfin, int cod) throws SQLException{
       
        //se arma la consulta
             String q=" UPDATE Articulos SET  autor='" +autor + "', titulo='" + titulo+"',nombrerevist='"+nombrerevist+"',anio='"+anio+"',mes='"+mes+"',paginic='"+paginic+"',pagfin='"+pagfin+"' where ISSN='"+cod+"'";
             //se ejecuta
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
    public boolean Eliminar_articulos(String cod4){
        
       boolean res=false;
       try {
        CallableStatement pstm;
        pstm = this.getConexion().prepareCall("{call Eliminar_articulo(?)}");
        pstm.setString(1, cod4);
        pstm.execute();
        pstm.close();
        res=true;
       }catch (SQLException e){
           System.err.println(e.getMessage());
       }
        return res;
       }

    
    
     public DefaultTableModel getTablaArticulos(){
         DefaultTableModel  tablemodel = new DefaultTableModel();
         int registros = 0;
         String [] columNames ={"ISSN","Autor","Titulo","N.revista","Año","Mes","P.inicio","P.fin" };
          
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Articulos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      
      
      Object[][] data = new String[registros][8];
       try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Articulos");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "ISSN" );
                data[i][1] = res.getString( "autor" );
                data[i][2] = res.getString( "titulo" );
                data[i][3] = res.getString( "nombrerevist" );
                data[i][4] = res.getString( "anio" );
                data[i][5] = res.getString( "mes" );
                data[i][6] = res.getString( "paginic" );
                data[i][7] = res.getString( "pagfin" );
                
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
         
         
     }
     
           
               database database=new database();
            DefaultTableModel ModeloTabla;  
          
          public void Buscar(String valor, String filtro, JTable tablaarticulo){

    String [] columnas={"ISSN","Autor","Titulo","N.revista","Año","Mes","P.inicio","P.fin"};
    String [] registro=new String[8];
    ModeloTabla =new DefaultTableModel(null,columnas);
    String SSQL = null;
    Connection conn = null;

    if(filtro.equals("autor")){

        SSQL= "SELECT ISSN,titulo,autor,nombrerevist,anio,mes,paginic,pagfin "
                 + "FROM Articulos WHERE autor LIKE '%"+valor+"%'";

    }else if(filtro.equals("titulo")){

        SSQL= "SELECT ISSN,titulo,autor,nombrerevist,anio,mes,paginic,pagfin "
                 + "FROM Articulos WHERE titulo LIKE '%"+valor+"%'";
    }
    else if(filtro.equals("N.revista")){

        SSQL= "SELECT ISSN,titulo,autor,nombrerevist,anio,mes,paginic,pagfin "
                 + "FROM Articulos  WHERE nombrerevist LIKE '%"+valor+"%'";
        
    } else if(filtro.equals("año")){

       
        SSQL= "SELECT ISSN,titulo,autor,nombrerevist,anio,mes,paginic,pagfin "
                 + "FROM Articulos WHERE anio LIKE '%"+valor+"%'";
        
    }  else if(filtro.equals("mes")){

        SSQL= "SELECT ISSN,titulo,autor,nombrerevist,anio,mes,paginic,pagfin "
                 + "FROM Articulos  WHERE mes LIKE '%"+valor+"%'";
        
    }  else if(filtro.equals("P.inicio")){

       
        SSQL= "SELECT ISSN,titulo,autor,nombrerevist,anio,mes,paginic,pagfin "
                 + "FROM Articulos WHERE paginic LIKE '%"+valor+"%'";
        
    }  else if(filtro.equals("P.fin")){

       
        SSQL= "SELECT ISSN,titulo,autor,nombrerevist,anio,mes,paginic,pagfin "
                 + "FROM Articulos WHERE pagfin LIKE '%"+valor+"%'";
    }

    try {


    conn = (Connection) database.getConexion();
        PreparedStatement st = conn.prepareStatement(SSQL);

        ResultSet rs = st.executeQuery();

        while (rs.next()){

            registro[0]=rs.getString("ISSN");
            registro[1]=rs.getString("autor");
            registro[2]=rs.getString("titulo");
            registro[3]=rs.getString("nombrerevist");
            registro[4]=rs.getString("anio");
            registro[5]=rs.getString("mes");
            registro[6]=rs.getString("paginic");
            registro[7]=rs.getString("pagfin");


            ModeloTabla.addRow(registro);


        }

        tablaarticulo.setModel(ModeloTabla);

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
     
       private boolean valida_datos_modif( String autor, String titulo) {
             
         if(  autor.equals("") || titulo.equals("")){
            return false;
        }else return true;
        
        } 
       
           
       private boolean valida_datos( String autor, String titulo, String ISSN) {
             
         if( autor.equals("") || titulo.equals("") || !ISSN.matches("^[0-9]{0,8}$")){
            return false;
        }else return true;
        
        } 

    public int getISSN() {
        return ISSN;
    }

    public void setISSN(int ISSN) {
        this.ISSN = ISSN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombrerevist() {
        return nombrerevist;
    }

    public void setNombrerevist(String nombrerevist) {
        this.nombrerevist = nombrerevist;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getPaginic() {
        return paginic;
    }

    public void setPaginic(String paginic) {
        this.paginic = paginic;
    }

    public String getPagfin() {
        return pagfin;
    }

    public void setPagfin(String pagfin) {
        this.pagfin = pagfin;
    }

   
   

  
    
}
