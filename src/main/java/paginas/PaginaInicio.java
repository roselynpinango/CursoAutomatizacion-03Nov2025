package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaginaInicio {
	// Localización de los Elementos Web 
	@FindBy(className="login")
	WebElement lnkSignIn;
	
	@FindBy(id="search_query_top")
	WebElement txtBuscador;
	
	@FindBy(css="button[name='submit_search']")
	WebElement btnBuscar;
	
	// Constructor (Java: Ofrecer la posibilidad de que lo que está en este lugar de utilice desde afuera)
	public PaginaInicio(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Acciones que se puedn hacer sobre los elementos Web
	public void hacerClicEnSignIn() {
		lnkSignIn.click();
	}
	
	public void escribirPalabraABuscar(String palabra) {
		txtBuscador.clear();
		txtBuscador.sendKeys(palabra);
	}
	
	public void hacerClicEnBuscar() {
		btnBuscar.click();
	}
}
