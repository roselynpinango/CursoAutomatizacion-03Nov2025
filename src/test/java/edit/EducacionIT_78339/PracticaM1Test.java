package edit.EducacionIT_78339;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticaM1Test {
	// Definir la URL de la página de prueba
	String url = "http://www.automationpractice.pl/";
	
	// Definir la prueba CP01 - Buscar Productos en AutomationPractice
	@Test
	public void buscarProductoEnEdge() {
		// (1) Definir qué navegador quiero usar
		WebDriver navegador = new EdgeDriver();
		
		// (2) Acceder a la URL de la página
		navegador.get(url);
		
		// (2.1) Limpiar las cookies y maximizar la ventana
		navegador.manage().deleteAllCookies();
		navegador.manage().window().maximize();
		
		// (3) Escribir la palabra a buscar
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress");
		
		// (4) Simular que presionamos la tecla ENTER
		txtBuscador.sendKeys(Keys.ENTER);
		
		// Comentario al margen: Para hacer Ctrl+C
		/*
		txtBuscador.sendKeys(Keys.chord(Keys.CONTROL, "C"));
		txtBuscador.sendKeys(Keys.chord(Keys.CONTROL, Keys.F5));
		txtBuscador.sendKeys(Keys.chord(Keys.CONTROL, Keys.ALT, Keys.DELETE));
		*/
		
		// (5) Cerrar el navegador
		//navegador.close(); // Cierra la ventana/pestaña que estamos usando
		navegador.quit(); // Cierra todas las ventanas que abrimos en la prueba
	}
	
	@Test
	public void buscarProductoEnFirefox() {
		// (1) Definir qué navegador quiero usar
		WebDriver navegador = new FirefoxDriver();
		
		// (2) Acceder a la URL de la página
		navegador.get(url);
		
		// (2.1) Limpiar las cookies y maximizar la ventana
		navegador.manage().deleteAllCookies();
		navegador.manage().window().maximize();
		
		// (3) Escribir la palabra a buscar
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress");
		
		// (4) Simular que presionamos la tecla ENTER
		txtBuscador.sendKeys(Keys.ENTER);
		
		// (5) Cerrar el navegador
		navegador.quit();
	}
	
	@Test
	public void buscarProductoEnChrome() {
		// (1) Definir qué navegador quiero usar
		WebDriverManager.chromedriver().setup();
		WebDriver navegador = new ChromeDriver();
		
		// (2) Acceder a la URL de la página
		navegador.get(url);
		
		// (2.1) Limpiar las cookies y maximizar la ventana
		navegador.manage().deleteAllCookies();
		navegador.manage().window().maximize();
		
		// (3) Escribir la palabra a buscar
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress");
		
		// (4) Simular que presionamos la tecla ENTER
		txtBuscador.sendKeys(Keys.ENTER);
		
		// (5) Cerrar el navegador
		navegador.quit();
	}
	
	@Test
	public void buscarProductoEnBrave() {
		// Automatización del caso de prueba positivo "Buscar Productos"
		// (1) Definir en qué navegador vamos a hacer la prueba
		//System.setProperty("webdriver.chrome.driver", "ruta/al/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
		
		WebDriver navegador = new ChromeDriver(options);
		navegador.get("http://www.automationpractice.pl");

		// (2) Podrías maximizar la ventana y limpiar las cookies
		navegador.manage().window().maximize();
		navegador.manage().deleteAllCookies();
		
		// (3) Acceder a la URL
		navegador.get("http://www.automationpractice.pl");
		
		// (4) Escribir el producto que queremos buscar (Campo de Texto)
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress"); 
		
		// (5) Simulamos que presionamos la tecla Enter
		txtBuscador.sendKeys(Keys.ENTER);
		
		// (6) Cerrar el navegador
		//navegador.close();
	}
}
