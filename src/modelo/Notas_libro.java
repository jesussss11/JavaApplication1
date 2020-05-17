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
public class Notas_libro extends database {
    
    
    String texto;
    String titulo;
    String tema;
    int id_notalibro;
    int ISBN_libro;
    
    
    
    public boolean añadir_nota( String titulo,String tema, String texto,int cod2){
          String q="INSERT INTO notas_libros (ISBN_libro,titulo,tema,texto,id_notalibro) VALUES ('"+cod2+"','"+titulo+"','"+tema+"','"+texto+"','"+id_notalibro+"')";
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
    
              public DefaultTableModel getTablaNotasLibro() throws SQLException{
         DefaultTableModel  tablemodel = new DefaultTableModel();
         int registros = 0;
         String [] columNames ={"ID","ISBN" , "Titulo","Tema","Texto"};
              try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM notas_libros");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
               Object[][] data = new String[registros][5];
                
                 try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM notas_libros");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id_notalibro" );
                data[i][1] = res.getString( "ISBN_libro" );
                data[i][2] = res.getString( "titulo" );
                data[i][3] = res.getString( "tema" );
                data[i][4] = res.getString( "texto" );
           
                 
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
          
          public void Buscar(String valor, String filtro, JTable tablanotaslibro){

    String [] columnas={"ID","ISBN","Titulo","Tema","Texto"};
    String [] registro=new String[5];
    ModeloTabla =new DefaultTableModel(null,columnas);
    String SSQL = null;
    Connection conn = null;

    if(filtro.equals("titulo")){

        SSQL= "SELECT id_notalibro,ISBN_libro,titulo,tema,texto "
                 + "FROM notas_libros WHERE titulo LIKE '%"+valor+"%'";

    }else if(filtro.equals("tema")){

        SSQL= "SELECT id_notalibro,ISBN_libro,titulo,tema,texto "
                 + "FROM notas_libros WHERE tema LIKE '%"+valor+"%'";

    }
    try {


    conn = (Connection) database.getConexion();
        PreparedStatement st = conn.prepareStatement(SSQL);

        ResultSet rs = st.executeQuery();

        while (rs.next()){
            
            registro[0]=rs.getString("id_notalibro");
            registro[1]=rs.getString("ISBN_libro");
            registro[2]=rs.getString("titulo");
            registro[3]=rs.getString("tema");
             registro[4]=rs.getString("texto");


            ModeloTabla.addRow(registro);


        }

        tablanotaslibro.setModel(ModeloTabla);

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
        
           public boolean modificarnota_libro  ( String titulo, String tema,String texto, int cod5) throws SQLException{
        
            String q = "UPDATE notas_libros SET  titulo='"+titulo+"',tema='"+tema+"',texto='"+texto+"' where id_notalibro ='"+cod5+"'";
            
             try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            } return false;
       
            
        }

    public int getId_notalibro() {
        return id_notalibro;
    }

    public void setId_notalibro(int id_notalibro) {
        this.id_notalibro = id_notalibro;
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

    public int getISBN_libro() {
        return ISBN_libro;
    }

    public void setISBN_libro(int ISBN_libro) {
        this.ISBN_libro = ISBN_libro;
    }

    
}
