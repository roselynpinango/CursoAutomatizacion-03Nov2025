package  edit.EducacionIT_78339;		
import  java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.AccesoBD;

import  java.sql.ResultSet;
import java.sql.SQLException;	

public class  AccesoABaseDeDatosTest {				
    	@Test
    	public void testBaseDeDatos() throws ClassNotFoundException, SQLException {
    		
    		Connection con = AccesoBD.abrirConexionBD(
    				"localhost",
    				"3306",
    				"inventariodb",
    				"root",
    				"rose");
    		
			//Query to Execute		
			String query = "select count(1) from producto;";
			//query = "select count(1) from clientes where DNI = " + dniUtilizado;
                
       		// Execute the SQL Query. Store results in ResultSet		
         	ResultSet rs= AccesoBD.ejecutarQuery(con, query);					
         
         	int count = 0;
         		
         	// While Loop to iterate through all data and print results		
			while (rs.next()){
				count = rs.getInt(1); // obtener número entero
				
				// select nombre, edad, fechaNacimiento from clientes;
				//nombre = rs.getString(1);
				//edad = rs.getInt(2);
				//fechaNacimiento = rs.getDate(3);
            }		
		
				
			System.out.println("Resultados de la Consulta a la Base de Datos");
			System.out.println("Número de Productos en Base de Datos: " + count);
			Assert.assertTrue(count > 0);
			Assert.assertEquals(count, 15);
			
			System.out.println("Prueba exitosa!");
			System.out.println("Terminó la prueba");
			//Assert.assertEquals(count > 0, true);
				
      		// closing DB Connection		
      		AccesoBD.cerrarConexionBD(con);			
		}
}