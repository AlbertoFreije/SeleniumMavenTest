package pruebaSelenium;



import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {
	
	private WebDriver driver;
	
	static String URL = "https://blazedemo.com/";
	
	protected static int timeout = 5;
	
	public static int getTimeout() {
		return timeout;
	}

	public static void setTimeout(int setTimeout) {
		timeout = setTimeout;
	}
	
	static public void textoPresentePagina(WebDriver driver, String texto)
	{
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));		
		assertTrue( list.size() > 0);			
	}
	
	static public List<WebElement> EsperaCargaPagina(WebDriver driver, String criterio, String text, int timeout)
	{
		String busqueda;
		if (criterio.equals("id")) busqueda = "//*[contains(@id,'" + text + "')]";
		else if (criterio.equals("class")) busqueda = "//*[contains(@class,'" + text + "')]";
		else if (criterio.equals("text")) busqueda = "//*[contains(text(),'" + text + "')]";
		else if (criterio.equals("free")) busqueda = text;
		else busqueda = "//*[contains("+criterio+",'" + text + "')]";

		return EsperaCargaPaginaxpath(driver, busqueda, timeout);
	}
	
	static public List<WebElement> EsperaCargaPaginaxpath(WebDriver driver, String xpath, int timeout)
	{
		WebElement resultado = 
				(new WebDriverWait(driver, Duration.ofSeconds(timeout))).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		assertTrue(resultado != null);
		List<WebElement> elementos = driver.findElements(By.xpath(xpath));

		return elementos;					
	}
	
	static public List<WebElement> checkElement(WebDriver driver, String type, String text) {
		List<WebElement> elementos = EsperaCargaPagina(driver, type, text, getTimeout());
		return elementos;		
	}
	
	@Test 
	public void conectarPagina() {
		
		 System.setProperty("webdriver.chrome.driver","/home/seluser/workspace/Selenium-Zap/chromedriver");
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\alberto.freije\\OneDrive - Ricoh Europe PLC\\Desktop\\chromedriver_win32\\chromedriver.exe");
		 driver = new ChromeDriver();
		 String expectedTitle = "Welcome to the Simple Travel Agency!";
	     //String actualTitle = "";
		 driver.navigate().to(URL);
	     //driver.get(URL);
	     //actualTitle = driver.getTitle();
	     textoPresentePagina(driver, expectedTitle);
	     driver.quit();
	       
	}
	
	@Test 
	public void findFlights() {
		
		 //System.setProperty("webdriver.chrome.driver","C:\\Users\\alberto.freije\\OneDrive - Ricoh Europe PLC\\Desktop\\chromedriver_win32\\chromedriver.exe");
		 System.setProperty("webdriver.chrome.driver","/home/seluser/workspace/Selenium-Zap/chromedriver");
		 driver = new ChromeDriver();
		 driver.navigate().to(URL);
		 List<WebElement> elementos = checkElement(driver, "free","/html/body/div[3]/form/div/input");
		 elementos.get(0).click();
		 String expectedTitle = "Flights from Paris to Buenos Aires:";
		 textoPresentePagina(driver, expectedTitle);
	     driver.quit();
	       
	}

}
