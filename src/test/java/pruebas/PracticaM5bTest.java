package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import paginas.PaginaAlerta;

public class PracticaM5bTest {
	String url = "https://demoqa.com/alerts";
	WebDriver driver;
	
	@BeforeSuite 
	public void setUp() {
		// Configurar un % de zoom en particular
		double zoom = 0.75;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--force-device-scale-factor=" + zoom);
		options.addArguments("incognito");
		//options.addArguments("headless");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test
	public void manejarAlerta() {
		PaginaAlerta pagina = new PaginaAlerta(driver);
		pagina.hacerClicEnAlerta();
		
		pagina.aceptarAlerta(pagina.obtenerAlerta());
	}
	
	@Test
	public void manejarAlertaConDemora() {
		PaginaAlerta pagina = new PaginaAlerta(driver);
		pagina.hacerClicEnAlertaEspera();
		
		pagina.aceptarAlerta(pagina.obtenerAlerta());
	}
	
	@AfterSuite
	public void tearDown() {
		//driver.quit();
	}
}
