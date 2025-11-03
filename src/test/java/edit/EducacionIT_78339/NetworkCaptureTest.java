package edit.EducacionIT_78339;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;

public class NetworkCaptureTest {
	@Test
    public void testNetworkCapture() throws IOException {
        // Iniciar el proxy BrowserMob
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // Obtener el proxy de Selenium
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        WebDriverManager.chromedriver().setup();
        
        // Configurar opciones de Chrome para usar el proxy
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PROXY, seleniumProxy);

        // Crear instancia del driver con proxy configurado
        WebDriver driver = new ChromeDriver(options);

        // Habilitar captura detallada en el proxy (opcional)
        proxy.enableHarCaptureTypes(
                net.lightbody.bmp.proxy.CaptureType.REQUEST_CONTENT,
                net.lightbody.bmp.proxy.CaptureType.RESPONSE_CONTENT);

        // Crear un nuevo archivo HAR para registrar el tráfico
        proxy.newHar("test_traffic");

        // Navegar hacia la página que se quiere probar
        driver.get("http://www.automationpractice.pl");

        // Obtener los datos capturados
        Har har = proxy.getHar();

        // Guardar los datos HAR en un archivo (formato JSON)
        File harFile = new File("network_traffic.har");
        har.writeTo(harFile);

        System.out.println("Archivo HAR guardado en: " + harFile.getAbsolutePath());

        // Cerrar navegador y proxy
        driver.quit();
        proxy.stop();
    }
}

