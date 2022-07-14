package pruebaSelenium;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1Test {
  
    //static String URL = "https://blazedemo.com/";
    static String URL = "https://www.facebook.com/";
	
	protected static int timeout = 5;

	//windows
//	static final String ZAP_PROXY_ADDRESS = "localhost";
//	static final int ZAP_PROXY_PORT = 8080;
//	static final String ZAP_API_KEY = "pl5btf8p6s2nvvjsgl2kc0umje";
	
	//Linux
//	static final String ZAP_PROXY_ADDRESS = "192.168.56.10";
//	static final int ZAP_PROXY_PORT = 8092;
//	static final String ZAP_API_KEY = "change-me-9203935709";
	
	private WebDriver driver;
//	private ClientApi api;
	
	@BeforeMethod
	public void setUp() {
		
//		String proxyServerUrl = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy(proxyServerUrl);
//		proxy.setSslProxy(proxyServerUrl);
//		
		ChromeOptions co = new ChromeOptions();
//		co.setProxy(proxy);
		co.setAcceptInsecureCerts(true);
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		
//		api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
		
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\alberto.freije\\OneDrive - Ricoh Europe PLC\\Desktop\\chromedriver_win32\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver","/home/seluser/workspace/Selenium-Zap/chromedriver");
		driver = new ChromeDriver(co);
		
	}

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
		
		 //System.setProperty("webdriver.chrome.driver","/home/seluser/workspace/Selenium-Zap/chromedriver");
		 //System.setProperty("webdriver.chrome.driver","C:\\Users\\alberto.freije\\OneDrive - Ricoh Europe PLC\\Desktop\\chromedriver_win32\\chromedriver.exe");
		 //driver = new ChromeDriver();
		 //String expectedTitle = "Welcome to the Simple Travel Agency!";
		 String expectedTitle = "facebook";
	     //String actualTitle = "";
		 //driver.navigate().to(URL);
	     driver.get(URL);
	     //actualTitle = driver.getTitle();
	     textoPresentePagina(driver, expectedTitle);
	    
	       
	}
	
	@Test 
//	public void findFlights() {
//		
//		 //System.setProperty("webdriver.chrome.driver","C:\\Users\\alberto.freije\\OneDrive - Ricoh Europe PLC\\Desktop\\chromedriver_win32\\chromedriver.exe");
//		 //System.setProperty("webdriver.chrome.driver","/home/seluser/workspace/Selenium-Zap/chromedriver");
//		 //driver = new ChromeDriver();
//		 driver.navigate().to(URL);
//		 List<WebElement> elementos = checkElement(driver, "free","/html/body/div[3]/form/div/input");
//		 elementos.get(0).click();
//		 String expectedTitle = "Flights from Paris to Buenos Aires:";
//		 textoPresentePagina(driver, expectedTitle);
//	 
//	       
//	}
	
	@AfterMethod
	public void tearDown() {
//		if(api != null) {
//			String title = "ZAP Security Report";
//			String template = "traditional-xml";
//			String description = "This is zap security test report";
//			String reportfilename = "zap-report.xml";
//			//windows
//			//String targetFolder = System.getProperty("user.dir");
//			//Linux
//			String targetFolder = "/home/seluser/workspace/Selenium-Zap";
//			
//			
//			try {
//				ApiResponse response = api.reports.generate(title, template, null, description, null, null, null, null, null, reportfilename, null, targetFolder, null);
//				System.out.println("ZAP report generated at this location" + response.toString());
//			} catch (ClientApiException e) {
//				e.printStackTrace();
//			}
//		}
		
		driver.quit();
	}
}
