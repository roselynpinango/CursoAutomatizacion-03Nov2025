package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import paginas.PaginaInicio;
import paginas.PaginaLogin;

public class PracticaM4bTest {
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
	
	@Test
	public void iniciarSesion() {
		inicio.hacerClicEnSignIn();
		
		PaginaLogin login = new PaginaLogin(driver);
		login.escribirEmail("trudi.gutmann@hotmail.com");
		login.escribirPassword("1q2w3e4r5t");
		login.hacerClicEnLogin();
		
		//login.ingresarCredenciales("trudi.gutmann@hotmail.com", "1q2w3e4r5t");
	}
	
	@Test
	public void buscarPalabra() {
		inicio.escribirPalabraABuscar("dress");
		inicio.hacerClicEnBuscar();
	}
	
	@AfterSuite
	public void tearDown() {
		//driver.quit();
	}
}
