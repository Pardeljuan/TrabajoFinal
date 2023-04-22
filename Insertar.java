package trabajoFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Insertar {
  public static void main(String[] args) {
    
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/consultorio";
    String usuario="root";
    String clave="root"; // <- PONER CONTRASEÑA!
    String consulta1 = "insert into pacientes(DniPac, NomPac, DomPac) values (123456789 ,'Pepe Pardel', 'Calle tuki 354')";
    String consulta2 = "insert into medicos(MatMed, NomMed, EspMed) values (25648, 'Alfred Gonzalez', 'Dermatologo')";
    // String consulta3 = "insert into historialmedico(NumHC, FecHC, DniPacHC, MatMedHC, DiagHC) values (1, '2015-04-04', '"+DniPac+" ,'"'"+DniPac+"'",'Fractura')";
   
        
    try {
    	Class.forName("com.mysql.jdbc.Driver");     
        conexion=DriverManager.getConnection(url,usuario,clave);    
        Statement sentencia=conexion.createStatement();
        sentencia.execute(consulta1);   
        sentencia.execute(consulta2); 
      // sentencia.execute(consulta3); 
        System.out.println("Los nuevos datos se agregaron a la tabla");
    } catch (Exception e) {  
      e.printStackTrace();
      System.out.println("Error en la insercion de datos");
    }
  }

}
