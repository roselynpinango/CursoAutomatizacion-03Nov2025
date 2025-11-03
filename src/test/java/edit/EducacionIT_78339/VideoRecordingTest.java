package edit.EducacionIT_78339;

import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import Utilities.VideoRecorder;

public class VideoRecordingTest {
	@Test
	public void testRecording() throws Exception {
	  ScreenRecorder recorder = VideoRecorder.startRecording("testRecording");

	  WebDriver driver = new EdgeDriver();
	  driver.get("http://www.automationpractice.pl");
	  
	  // Código de prueba con Selenium WebDriver
	  // Identificar el campo de texto de búsqueda y escribimos el valor
	  WebElement txtBuscador = driver.findElement(By.cssSelector("#search_query_top"));
	  txtBuscador.sendKeys("dress");
			
	  // Simulamos que presionamos la tecla ENTER
	  txtBuscador.sendKeys(Keys.ENTER);

	  VideoRecorder.stopRecording(recorder);
	}

}
