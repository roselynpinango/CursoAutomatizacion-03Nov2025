package edit.EducacionIT_78339;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticaM4Test {
	// Definimos la página de prueba
	String url = "http://www.automationpractice.pl/";
	
	// Definimos el objeto que representa al navegador
	WebDriver driver;
	
	@Parameters("navegador")
	@BeforeTest
	public void setUp(String navegador) {
		// Definimos el navegador a utilizar
		if (navegador.equalsIgnoreCase("CHROME")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (navegador.equalsIgnoreCase("FIREFOX")) {
			driver = new FirefoxDriver();
		} else if (navegador.equalsIgnoreCase("EDGE")) {
			driver = new EdgeDriver();
		}
		
		// Abrimos la página de prueba
		driver.get(url);
		
		// Maximizamos la ventana del navegador
		driver.manage().window().maximize();
		
		// Borramos las cookies
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void buscarProducto() {
		// Identificar el campo de texto de búsqueda y escribimos el valor
		WebElement txtBuscador = driver.findElement(By.cssSelector("#search_query_top"));
		txtBuscador.sendKeys("dress");
		
		// Simulamos que presionamos la tecla ENTER
		txtBuscador.sendKeys(Keys.ENTER);		
	}
	
	@AfterTest
	public void tearDown() {
		// Cerramos el navegador
		//driver.quit();
	}
}
