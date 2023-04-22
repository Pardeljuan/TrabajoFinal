package trabajoFinal;

/*
Procedimiento para conectar la DB y realizar la eliminacion de un registro 
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Eliminar {
  public static void main(String[] args) {
    
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/consultorio";
    String usuario="root";
    String clave="root";
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Escriba el DNI del paciente a eliminar:...");
    int DniPac  = sc.nextInt(); //asigna la entrada de usuario a la DNI
    
    String consulta="DELETE FROM pacientes WHERE DniPac = '"+DniPac+"'";
     // DELETE FROM Producto WHERE ClienDni = 7   
    try {
    	Class.forName("com.mysql.jdbc.Driver");     
        conexion=DriverManager.getConnection(url,usuario,clave);    
        Statement sentencia=conexion.createStatement();
        sentencia.execute(consulta);   
        System.out.println("El registro se elimino!!");
    } catch (Exception e) {  
      e.printStackTrace();
      System.out.println("Error en el borrado del registro!!");
    }
  }
}