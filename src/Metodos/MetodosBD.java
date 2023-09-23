
package Metodos;

import config.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel; 


public class MetodosBD {

Conexion coneccion = new Conexion();
DefaultTableModel ModeloTabla;
    
public void Buscar(String valor, String filtro, JTable tablacontactos){

    String [] columnas={"Id","nombre","precio","existencia"};
    String [] registro=new String[4];
    ModeloTabla=new DefaultTableModel(null,columnas);      
    String SSQL;
    Connection conect = null;
    
    if(filtro.equals("Id")){
    
        SSQL= "SELECT Id, nombre, precio, existencia "
                 + "FROM producto WHERE Id LIKE '%"+valor+"%'";
        
    }else if(filtro.equals("nombre")){
    
        SSQL= "SELECT Id, nombre, precio, existencia "
                 + "FROM producto WHERE nombre LIKE '%"+valor+"%'";
    
    }else{
    
         SSQL= "SELECT Id, nombre, precio, existencia "
                 + "FROM producto WHERE nombre LIKE '%"+valor+"%'";
    
    }
        
         
    try {

        conect = coneccion.getConnection();
        PreparedStatement st = conect.prepareStatement(SSQL);
        ResultSet rs = st.executeQuery();

        while (rs.next()){
          
            registro[0]=rs.getString("Id");
            registro[1]=rs.getString("nombre");
            registro[2]=rs.getString("precio");
            registro[3]=rs.getString("existencia");

            ModeloTabla.addRow(registro);
           
        }
        
        tablacontactos.setModel(ModeloTabla);

    } catch (SQLException e) {


        JOptionPane.showMessageDialog(null, e, "Error durante el procedimiento", JOptionPane.ERROR_MESSAGE);
    
    
    }finally{

        if(conect!=null){
        
            try {

                conect.close();

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

            }
        
        }
        
    }

}
    
    
    
    
    
    
    
    
    
    
}
