package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.DatosExcel;
import paginas.PaginaInicio;
import paginas.PaginaLogin;

public class PracticaM5Test {
	String url = "http://www.automationpractice.pl";
	WebDriver driver;
	PaginaInicio inicio;
	
	@BeforeSuite
	public void setUp() {
		driver = new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@BeforeTest
	public void inicializarTest() {
		inicio = new PaginaInicio(driver);
	}
	
	@Test(dataProvider="Obtener Datos desde Excel")
	public void iniciarSesion(String email, String password) {
		inicio.hacerClicEnSignIn();
		
		PaginaLogin login = new PaginaLogin(driver);
		login.escribirEmail(email);
		login.escribirPassword(password);
		login.hacerClicEnLogin();
		
		//login.ingresarCredenciales("trudi.gutmann@hotmail.com", "1q2w3e4r5t");
	}
	
	@DataProvider(name="Obtener Datos desde Excel")
	public Object[][] obtenerDatosDesdeExcel() throws Exception {
		String directorio = "..//EducacionIT-78339/Datos/";
		String nombreArchivo = "Datos_Login_29Oct2025.xlsx";
		String nombreHoja = "Hoja1";
		
		return DatosExcel.leerExcel(directorio + nombreArchivo, nombreHoja);
	}
	
	@DataProvider(name="Obtener Datos")
	public Object[][] obtenerDatos() {
		// Armar matriz bidimensional (4x2)
		// [Nombre Alternativo: Arreglo]
		Object[][] datos = new Object[4][2];
		
		datos[0][0] = "abc@gmail.com";
		datos[0][1] = "3er43r4e";
		
		datos[1][0] = "def@gmail.com";
		datos[1][1] = "6tr4de";
		
		datos[2][0] = "ghi@gmail.com";
		datos[2][1] = "3eftt5,.";
		
		datos[3][0] = "jkl@gmail.com";
		datos[3][1] = "344rf5%";
		
		return datos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@AfterSuite
	public void tearDown() {
		//driver.quit();
	}
}

