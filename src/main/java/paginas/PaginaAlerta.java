package paginas;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaAlerta {
	// Elementos Web
	@FindBy(id="alertButton")
	WebElement btnAlerta;
	
	@FindBy(id="timerAlertButton")
	WebElement btnEspera;
	
	WebDriver driver;
	
	// Constructor
	public PaginaAlerta(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	// Acciones
	public void hacerClicEnAlerta() {
		btnAlerta.click();
	}
	
	public void hacerClicEnAlertaEspera() {
		btnEspera.click();
	}
	
	public Alert obtenerAlerta() {
		WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(30));
		espera.until(ExpectedConditions.alertIsPresent());
		
		return driver.switchTo().alert();
	}
	
	public void aceptarAlerta(Alert alerta) {
		alerta.accept();
	}
	
	public void cancelarAlerta(Alert alerta) {
		alerta.dismiss();
	}
	
	public void escribirEnAlerta(Alert alerta, String palabra) {
		alerta.sendKeys(palabra);
	}
	
	public String obtenerTextoAlerta(Alert alerta) {
		return alerta.getText();
	}
}
