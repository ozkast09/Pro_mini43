/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author OSCAR
 */
public class C_Inventario {
    int idCategoria;

    public void establecerIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    int idMarca;

    public void establecerIdMarca (int idMarca) {
        this.idMarca = idMarca;
    }
    
    int idMedida;

    public void establecerIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }
    
    int idProveedor;

    public void estableceIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
    
    
    
    
    
    public void MostrarcomboCategoria( JComboBox comboCategoria){
        
        clases.CConexion objetoConexion=new clases.CConexion();
        
        String sql="select * from categoria;";
        
        Statement st;
        
        try {
            st=objetoConexion.establecerConexion().createStatement();
            
            ResultSet rs=st.executeQuery(sql);
            comboCategoria.removeAllItems();
            
            while (rs.next()) {
               
                String nombreCategoria= rs.getString("categoria");
                this.establecerIdCategoria(rs.getInt("id"));
                
                comboCategoria.addItem(nombreCategoria);
                comboCategoria.putClientProperty(nombreCategoria,idCategoria);
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"no se mostro categoria"+ e.toString());
        }
        finally{
        objetoConexion.cerrarConexion();
        }
                  
    }
    
    public void MostrarComboMarca(JComboBox comboMarca){
        clases.CConexion objetoConexion=new clases.CConexion();
        
        String sql="select * from marca";
        
        Statement st;
        
        try {
            st=objetoConexion.establecerConexion().createStatement();
            ResultSet rs= st.executeQuery(sql);
            comboMarca.removeAllItems();
            while (rs.next()) {
                String nombreMarca=rs.getString("marca");
                this.establecerIdMarca(rs.getInt("id"));
                
                comboMarca.addItem(nombreMarca);
                comboMarca.putClientProperty(nombreMarca,idMarca);
                
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo mostrar marca"+ e.toString());
        }
        
        finally{
        objetoConexion.cerrarConexion();
        }
    
    }
    
    public void mostrarComboMedida (JComboBox comboMedida){
        
        clases.CConexion objetoConexion=new clases.CConexion();
        
        String sql="select * from unidadMedida;";
        
        Statement st;
        
        try {
            st= objetoConexion.establecerConexion().createStatement();
            
            ResultSet rs=st.executeQuery(sql);
            comboMedida.removeAllItems();
            
            while (rs.next()) {
               
                String nombreMedida= rs.getString("unidadMedida");
                this.establecerIdMedida(rs.getInt("id"));
                
                comboMedida.addItem(nombreMedida);
                comboMedida.putClientProperty(nombreMedida,idMedida);
                
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se muestra Medida"+ e.toString());
        }
        
        finally{
        objetoConexion.cerrarConexion();
        }
    
    }
    
    public void mostrarComboProveedor (JComboBox comboProveedor){
    clases.CConexion objetoConexion=new clases.CConexion();
    
    String sql="select * from proveedor";
    
    Statement st;
    
        try {
            st=objetoConexion.establecerConexion().createStatement();
            
            ResultSet rs= st.executeQuery(sql);
            comboProveedor.removeAllItems();
            
            while (rs.next()) {
                
                String nombreProveedor = rs.getString("proveedor");
                this.estableceIdProveedor(rs.getInt("id3"));
                
                comboProveedor.addItem(nombreProveedor);
                comboProveedor.putClientProperty(nombreProveedor, idProveedor);
                
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"no se pudo mostrar en proveedor "+ e.toString());
        }
        
        finally{
        objetoConexion.cerrarConexion();
        }
    
    }
    // metodo para agregar producto a la base de datos
    public void agregarProducto(JTextField nombre,JTextField descripcion,JComboBox comboCategoria,JComboBox comboMarca, JComboBox comboMedida,JComboBox comboProveedor, JTextField cantidad){
    CConexion objetoConexion=new CConexion();
    
    String consulta="insert into Inventario (nombre,descripcion,fkcategoria,fkmarca,fkunidadmedida,fkproveedor,cantidad) values (?,?,?,?,?,?,?);";
    
        try {
            CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, nombre.getText());
            cs.setString(2,descripcion.getText());
            
            int IDproducto= (int) comboCategoria.getClientProperty(comboCategoria.getSelectedItem());
          cs.setInt(3,IDproducto);
          
          int idMarca= (int) comboMarca.getClientProperty(comboMarca.getSelectedItem());
          cs.setInt(4, idMarca);
        
          
          int idMedida=(int) comboMedida.getClientProperty(comboMedida.getSelectedItem());
          cs.setInt(5,idMedida);
          
          int idProveedor=(int) comboProveedor.getClientProperty(comboProveedor.getSelectedItem());
          cs.setInt(6,idProveedor);
            
            cs.setInt(7, Integer.parseInt(cantidad.getText()));
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Producto guardado");
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al guardar: "+ e.toString());
        }
    }
    // metodo para mostrar producto 
     public void mostrarInventario( JTable tablaInventario){
        
        clases.CConexion objetoConexion=new clases.CConexion();
        
        DefaultTableModel modelo=new DefaultTableModel();
        
        String sql="";
        
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Categoria");
        modelo.addColumn("Marca");
        modelo.addColumn("U. medida");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Cantidad");
        
        tablaInventario.setModel(modelo);
        
        sql="select Inventario.id,Inventario.nombre,Inventario.descripcion,categoria.categoria,marca.marca,unidadMedida.unidadmedida,proveedor.proveedor,Inventario.cantidad from Inventario inner join categoria on Inventario.fkcategoria= categoria.id inner join marca on Inventario.fkmarca= marca.id inner join unidadMedida on Inventario.fkunidadmedida= unidadMedida.id inner join proveedor on Inventario.fkproveedor= proveedor.id3;";
        
        try {
            Statement st= objetoConexion.establecerConexion().createStatement();
            ResultSet rs= st.executeQuery(sql);
            
            
            while (rs.next()) {  
                
                String id= rs.getString("id");
                String nombre= rs.getString("nombre");
                String descripcion= rs.getString("descripcion");
                String categoria= rs.getString("categoria");
                String marca= rs.getString("marca");
                String medida= rs.getString("unidadmedida");
                String proveedor= rs.getString("proveedor");
                String cantidad= rs.getString("cantidad");
                
                modelo.addRow(new Object[] {id,nombre,descripcion,categoria,marca,medida,proveedor,cantidad});
                
                tablaInventario.setModel(modelo);
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos de la tabla : "+e.toString());
        }
        
        finally{
        
        objetoConexion.cerrarConexion();
        }
   
}
     //  metodo para seleccionar producto 
     public void seleccionar (JTable totalInventario, JTextField id, JTextField nombre, JTextField descripcion, JComboBox categoria, JComboBox marca, JComboBox medida, JComboBox proveedor, JTextField cantidad ){
       int fila= totalInventario.getSelectedRow();
        
        if (fila>= 0) {
            
            id.setText(totalInventario.getValueAt(fila,0).toString());
            nombre.setText(totalInventario.getValueAt(fila,1).toString());
            descripcion.setText(totalInventario.getValueAt(fila,2).toString());
            categoria.setSelectedItem(totalInventario.getValueAt(fila,3).toString());
            marca.setSelectedItem(totalInventario.getValueAt(fila,4).toString());
            medida.setSelectedItem(totalInventario.getValueAt(fila,5).toString());
            proveedor.setSelectedItem(totalInventario.getValueAt(fila,6).toString());
            cantidad.setText(totalInventario.getValueAt(fila,7).toString());
            
        } 
    }
     // metodo para modificar datos del producto
     
     public void modificarProducto(JTextField id,JTextField nombre,JTextField descripcion,JComboBox comboCategoria,JComboBox comboMarca, JComboBox comboMedida,JComboBox comboProveedor, JTextField cantidad){
         
         CConexion objetoConexion=new CConexion();
         
         String consulta="update Inventario set Inventario.nombre=?,Inventario.descripcion=?,Inventario.fkcategoria=?,Inventario.fkmarca=?,Inventario.fkunidadmedida=?,Inventario.fkproveedor=?,Inventario.cantidad=? where Inventario.id=?";
         
         try {
             CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
             
             cs.setString(1, nombre.getText());
             cs.setString(2, descripcion.getText());
             
             int idCategoria= (int) comboCategoria.getClientProperty(comboCategoria.getSelectedItem());
             cs.setInt(3, idCategoria);
             
             int idMarca= (int) comboMarca.getClientProperty(comboMarca.getSelectedItem());
             cs.setInt(4, idMarca);
             
             int idMedida= (int) comboMedida.getClientProperty(comboMedida.getSelectedItem());
             cs.setInt(5,idMedida );
             
             int idProveedor= (int) comboProveedor.getClientProperty(comboProveedor.getSelectedItem());
             cs.setInt(6, idProveedor);
             
             cs.setInt(7,Integer.parseInt(cantidad.getText()));
             
             cs.setInt(8,Integer.parseInt(id.getText()));
             
             cs.execute();
             
             JOptionPane.showMessageDialog(null,"se modifico correctamente");
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"error al modificar producto" +e.toString());
         }
         
         finally{
             objetoConexion.cerrarConexion();
         }
     
     
     
     }
     
     // metodo para eliminar productos de la base de datos
     public void eliminarProducto(JTextField id){
         
         CConexion objetoConexion=new CConexion();
         
         String consulta="delete from Inventario where Inventario.id=?;";
         
         try {
             CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
             
             cs.setInt(1,Integer.parseInt(id.getText()));
             
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Producto eliminado");
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null,"Error al eliminar producto"+ e.toString());
         }
         
         
     
     
     }
     
   // metodo para limpiar campos de datos
     
     public void limpiarCampos(JTextField id, JTextField nombre,JTextField descripcion,JTextField cantidad){
         
         id.setText("");
         nombre.setText("");
         descripcion.setText("");
         cantidad.setText("");
                              
     }
     
}
