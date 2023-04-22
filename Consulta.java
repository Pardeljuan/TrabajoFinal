package trabajoFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Consulta {
	
  public static void main(String[] args) {
    
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/consultorio";
    String usuario="root";
    String clave="root"; // <- PONER CONTRASEÑA! 
    String consulta=" select * from pacientes "; // (EmpDni,EmpNom,EmpApe,EmpNac,EmpDep) values ('99','Luis','Java','Chile','1')";
        
    try {
    	Class.forName("com.mysql.jdbc.Driver");     
        conexion=DriverManager.getConnection(url,usuario,clave);    
        Statement sentencia=conexion.createStatement();
        sentencia.execute(consulta);   
        System.out.println("Consulta SQL exitosa");
    } catch (Exception e) {  
      e.printStackTrace();
      System.out.println("Error en la conexion de datos");
    }
  }
}
