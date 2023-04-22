package trabajoFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 Ejemplo para conectar la DB y realizar una consulta de las tablas.
 */
public class Main {

    Connection conexion = null;
    Statement stm = null;
   
    public static void main(String[] args) {
      
        Scanner sc = new Scanner(System.in);
        Main m = new Main();

        m.conectar();    //CONECTO LA BBDD ANTES DE INICIAR EL MENÚ
        boolean salir = false;
        do {

            switch (menuPrin()) {
                case 1:
                    m.consultaTablaPacientes();    //Cuando pulse la opción 1 del menú me llevará a la función consultaTablaEstudiantes
                    break;
                case 2 :
                    m.consultaTablaMedicos();    //Cuando pulse la opción 1 del menú me llevará a la función consultaTablaEstudiantes
                    break;
                    
                case 0:
                    System.out.println("Vuelve pronto");
                    m.desconectar();   //CUANDO PULSO EL 0 CIERRO LA BBDD Y CIERRO LA APL.
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        } while (!salir);

    } //fin main

    //MENU PRINCIPAL
    private static int menuPrin() {

        Scanner sc = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println("Conexión de bbdd MySQL");
        System.out.println("--------------------------------");
        System.out.println("1.MOSTRAR EL CONTENIDO DE LA TABLA PACIENTES");
        System.out.println("2.MOSTRAR EL CONTENIDO DE LA TABLA MEDICOS");
        System.out.println("0. Salir");
        System.out.println("\n Por favor, escoja una opción.");
        System.out.println("--------------------------------");

        return sc.nextInt(); //Recibo un entero

    }//Fin MENU PRINCIPAL
   
   
   
     /*MÉTODO QUE CONECTA CON LA BBDD DE MYSQL*/
    public void conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            /*En esta línea es importante que indiquemos:
                  el nombre de la base de datos --> qatar2022
                  El usuario y contraseña que tengamos en nuestro gestor de base de datos phpMyAdmin*/
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/consultorio", "root", "root");

            System.out.println("**************************************");
            System.out.println(" * CONEXIÓN REALIZADA CORRECTAMENTE * ");
            System.out.println("**************************************");
           
        } catch (Exception e) {

            System.out.println("*****************************************");
            System.out.println(" * NO SE HA PODIDO REALIZAR LA CONEXIÓN * ");
            System.out.println("******************************************");
        }

    }//conectar

    /*DESCONECTAR LA BBDD*/
    private void desconectar() {

        try {
            conexion.close();
            System.out.println("\n************************************************************\n");
            System.out.println("La conexion a la base de datos se ha terminado");
            System.out.println("\n************************************************************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }//desconectar
   
    ////////////////////////////////////////////////////////////////////////////
   
    /*MÉTODO PARA REALIZAR UNA CONSULTA A UNA TABLA MYSQL*/
        private void consultaTablaPacientes() {
        //Realizamos la consulta sql para mostrar todos los datos de la tabla estudiante
        ResultSet r = buscar("select DniPac,NomPac,DomPac from pacientes");
       
        try {
            System.out.println("\n TODOS LOS REGISTROS DE LA TABLA PACIENTES:\n");
           
            /*
            Hacemos un While para recorrer toda la tabla estudiantes
            y así poder sacar todos los registros de la tabla
            */
            while (r.next()) {
                /*Se muestra los datos que queremos sacar por consola indicandole:
                        El tipo de dato (int,String...) de cada campo
                        El nombre de los campos de la tabla entre comillas doble " "
                */
                System.out.println(r.getInt("DniPac") + " | " + r.getString("NomPac") + " | " + r.getString("DomPac") + " | " );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//mostrarTablaPropietarios
        /*MÉTODO PARA REALIZAR UNA CONSULTA A UNA TABLA MYSQL*/
        private void consultaTablaMedicos() {
        //Realizamos la consulta sql para mostrar todos los datos de la tabla estudiante
        ResultSet r = buscar("select MatMed,NomMed,EspMed from medicos");
       
        try {
            System.out.println("\n TODOS LOS REGISTROS DE LA TABLA MEDICOS:\n");
           
            /*
            Hacemos un While para recorrer toda la tabla estudiantes
            y así poder sacar todos los registros de la tabla
            */
            while (r.next()) {
                /*Se muestra los datos que queremos sacar por consola indicandole:
                        El tipo de dato (int,String...) de cada campo
                        El nombre de los campos de la tabla entre comillas doble " "
                */
                System.out.println(r.getInt("MatMed") + " | " + r.getString("NomMed") + " | " + r.getString("EspMed") + " | " );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//mostrarTablaPropietarios
   
       
        //Este método lo uso para mostrar los datos de una tabla: (executeQuery)
    ResultSet buscar(String sql) {
        try {
            stm = conexion.createStatement();
            return stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }//buscar
       
    }//FIN