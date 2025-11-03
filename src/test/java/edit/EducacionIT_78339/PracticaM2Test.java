package edit.EducacionIT_78339;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class PracticaM2Test {
	String url = "http://www.automationpractice.pl";
	
	@Test
	public void registrarUsuario() {
		// (1) Definimos el navegador a utilizar
		WebDriver driver = new EdgeDriver();
		
		// (2) Abrimos la página
		driver.navigate().to(url); // hace lo mismo que .get(url)
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		// (3) Hacer clic en 'Sign in'
		WebElement lnkSignIn = driver.findElement(By.partialLinkText("Sign"));
		lnkSignIn.click();
		
		// (4) Escribimos el correo 
		WebElement txtEmail = driver.findElement(By.cssSelector("#email_create"));
		txtEmail.sendKeys("correo27oct@gmail.com");
		
		// (5) Hacer clic en 'Create An Account'
		WebElement btnCreate = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
		btnCreate.click();
		
		// Es necesaria una espera
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1")));
		
		// (6) Completar el formulario
		// (6.1) Elegir el 'Title'
		WebElement radTitle = driver.findElement(By.id("id_gender1"));
		radTitle.click();
		
		// (6.2) Escribir el nombre
		WebElement txtFirstName = driver.findElement(By.name("customer_firstname"));
		txtFirstName.sendKeys("Arturo");
		
		// (6.3) Escribe el Apellido
		WebElement txtLastName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[3]/input[1]"));
		txtLastName.sendKeys("Lopez");
		
		// (6.4) Modifica el correo
		// Hay que utilizar un correo aleatorio
		// Opcion 1: Usar un numero aleatorio para hacer el correo diferente (con Java)
		//String correo = "correo" + Math.random() + "@gmail.com";
		// Opcion 2: Utilizar una librería de datos aleatorios
		Faker faker = new Faker();
		String correo = faker.internet().emailAddress();
		
		/* Algunos usos de la librería Faker
		faker.address().fullAddress();
		faker.address().city();
		faker.address().firstName();
		faker.address().lastName();
		faker.finance().creditCard();
		*/
		
		WebElement txtEmailForm = driver.findElement(By.cssSelector("#email"));
		txtEmailForm.clear(); // limpia el valor anterior que tenía el campo
		txtEmailForm.sendKeys(correo);
		
		// (6.5) Escribe la contraseña
		WebElement txtPassword = driver.findElement(By.xpath("//input[@id='passwd']"));
		txtPassword.sendKeys("1q2w3e4r5t");
		
		// (6.6) Establece la fecha de nacimiento (Día, Mes y Año)
		Select lstDays = new Select(driver.findElement(By.id("days")));
		lstDays.selectByVisibleText("18  ");
		
		Select lstMonths = new Select(driver.findElement(By.name("months")));
		lstMonths.selectByValue("6");
		
		Select lstYears = new Select(driver.findElement(By.cssSelector("#years")));
		lstYears.selectByIndex(26);
		
		// (6.7) Hacer clic en el checkbox
		WebElement chkNews = driver.findElement(By.xpath("//input[@id='newsletter']"));
		chkNews.click();
		
		// Otra forma de localizar
		/* driver.findElement(By.xpath("//input[@id='newsletter']")).click(); */
		
		// (6.x) Hacer clic en 'Register'
		WebElement btnRegister = driver.findElement(By.id("submitAccount"));
		btnRegister.click();
		
		// () Cerrar el navegador
		
	}
}
