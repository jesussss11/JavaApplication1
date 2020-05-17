/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ciuda
 */
public class database {
   

/**
 *
 * @author Alumno
 */

    private String db = "dbtest";
  /** usuario */
  String user = "root";
  /** contraseña de MySql*/
  String password = "";
  /** url*/
  private String url = "";
  /** Cadena de conexion */
  //////////////////////

  public void url() {
      
	        try {
	           FileReader fr = new FileReader("C:\\Users\\ciuda\\OneDrive\\Documentos\\NetBeansProjects\\JavaApplication1\\url.txt");
                    BufferedReader br = new BufferedReader(fr);
                    setUrl(br.readLine());
                    setDb(br.readLine());
                    setUser(br.readLine());
                    setPassword(br.readLine());

	        } catch (IOException e) {

	            System.out.println("No se ha encontrado el archivo");
	        }
  }
  //////////////////////
  /** variable para trabajar con la conexion a la base de datos */
  private Connection conn = null;

   /** Constructor de clase */
   public database(){
       url();
	   this.url = getUrl()+getDb();
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection( getUrl(), getUser(), getPassword());         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }


   public Connection getConexion()
   {
    return this.conn;
   }
   
   public String getDb() {
		return db;
	}


	public void setDb(String db) {
		this.db = db;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

}
