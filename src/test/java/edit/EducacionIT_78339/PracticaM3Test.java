package edit.EducacionIT_78339;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticaM3Test {
	String url = "http://www.automationpractice.pl/";
	WebDriver driver;
	String directorioEvidencias = "..\\EducacionIT-78339\\Evidencias\\";
	String nombreArchivo = "Documento de Evidencias AutomationPractice.docx";
	File pantalla;
	
	@BeforeSuite
	public void abrirNavegador() {
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test(priority=1, description="CP01 - Ir a Contactanos (Caso Positivo)")
	public void irAContactanos() throws InvalidFormatException, IOException, InterruptedException {
		// Captura de Evidencia #1 Pantalla Inicial
		CapturaEvidencia.escribirTituloEnDocumento(
				directorioEvidencias + nombreArchivo,
				"Evidencias AutomationPractice - Curso EducacionIT",
				20);
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				directorioEvidencias + "img.jpg",
				directorioEvidencias + nombreArchivo,
				"Paso 1: Pantalla Inicial de la Prueba");
		
		// (1) Hacer clic en 'Contact us'
		WebElement lnkContact = driver.findElement(By.linkText("Contact us"));
		lnkContact.click();
		
		// Captura de Evidencia #2 Formulario vacío
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				directorioEvidencias + "img.jpg",
				directorioEvidencias + nombreArchivo,
				"Paso 2: Formulario a ser completado");
		
		// (2) Completar el formulario
		// (2.1) Elegir opción en la lista 'Subject'
		Select lstSubject = new Select(driver.findElement(By.cssSelector("#id_contact")));
		lstSubject.selectByVisibleText("Webmaster");
		
		// (2.2) Correo
		WebElement txtEmail = driver.findElement(By.xpath("//input[@id='email']"));
		txtEmail.sendKeys("correo@gmail.com");
		
		// (2.3) Order
		WebElement txtOrder = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/form[1]/fieldset[1]/div[1]/div[1]/div[2]/input[1]"));
		txtOrder.sendKeys("FACT-105");
		
		// (2.4) Adjuntar un archivo
		WebElement fileAdjunto = driver.findElement(By.id("fileUpload"));
		fileAdjunto.sendKeys("C:\\addIntegerData.txt"); // Hay que verificar que esta ruta exista en tu computadora
		
		// (2.5) TextArea Mensaje
		WebElement txtMessage = driver.findElement(By.tagName("textarea"));
		txtMessage.sendKeys("Mensaje de Contacto con AutomationPractice.pl");
		
		// Captura de Evidencia #3 Formulario lleno
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				directorioEvidencias + "img.jpg",
				directorioEvidencias + nombreArchivo,
				"Paso 3: Formulario completo");
		
		// (3) Hacer clic en 'Send'
		WebElement btnSend = driver.findElement(By.name("submitMessage"));
		btnSend.click();
		
		// Captura de Evidencia #4 Formulario enviado
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				directorioEvidencias + "img.jpg",
				directorioEvidencias + nombreArchivo,
				"Paso 4: Envío del formulario de contacto");
		
		// Assertion: Chequear que aparezca el mensaje de éxito
		try {
			WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(60));
			espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Your message has been successfully sent to our team.']")));
			
			driver.findElement(By.xpath("//*[text()='Your message has been successfully sent to our team.']"));
		} catch (Exception e) {
			// Tomar control sobre el error que va a arrojar el script en caso que falle
			Assert.assertTrue(false, "ERROR: El mensaje de operación exitosa no aparece en pantalla");
		}
		
	}
	
	@Test(priority=2, description="CP02 - Buscar Producto (Caso Positivo)")
	public void buscarProducto() throws IOException {
		// Captura de Evidencia #1: Pantalla Inicial
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(directorioEvidencias + "01_pantallaInicial.jpg"));
		
		WebElement txtBuscador = driver.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress");
		
		// Captura de evidencia #2: Palabra a buscar
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(directorioEvidencias + "02_palabraABuscar.jpg"));
		
		WebElement btnBuscar = driver.findElement(By.name("submit_search"));
		btnBuscar.click();
		
		// Captura de evidencia #3: Resultado de la búsqueda
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(directorioEvidencias + "03_resultadoBusqueda.jpg"));
		
		
		// Assertions: Chequear que la URL y título de la página hayan cambiado
		String urlEsperada = "http://www.automationpractice.pl/index.php?controller=search&orderby=position&orderway=desc&search_query=dress&submit_search=";
		String urlActual = driver.getCurrentUrl();
		
		String tituloEsperado = "Search - My Shop";
		String tituloActual = driver.getTitle();
		
		Assert.assertEquals(urlActual, urlEsperada);
		Assert.assertEquals(tituloActual, tituloEsperado);
		
		/*
		 * Otros métodos de Assert
		 * Assert.assertTrue(1==1);
		 * Assert.assertFalse(false);
		 * Assert.assertNotEquals("A", "B");
		 * Assert.assertNull(url);
		 * Assert.assertNotNull(url);
		 * */
	}
	
	@AfterSuite
	public void cerrarNavegador() {
		//driver.quit();
	}
}
