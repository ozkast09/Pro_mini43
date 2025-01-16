/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author OSCAR
 */
public class CConexion {
    Connection conectar=null;
    
    String usuario="root";
    String contraseña="";
    String db="inventariomini43";
    String ip="localhost";
    String puerto="3306";
    
    String cadena="jdbc:mysql://"+ip+":"+puerto+"/"+db;
    
    public Connection establecerConexion (){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection(cadena,usuario,contraseña);
            
            JOptionPane.showMessageDialog(null,"se conecto a la base de datos");
       
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"no se conecto a la base de datos");
        }
        return conectar;
    
       }
    
    public void cerrarConexion(){
    
        try {
            if (conectar !=null && !conectar.isClosed()){
            conectar.close();
            JOptionPane.showMessageDialog(null,"conexion cerrada");
            }
        } catch (Exception e) {
            
           JOptionPane.showMessageDialog(null,"no se pudo cerrar la conexion");
        }
    }
    
}
