package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ProductInfo.LaptopInfo;


public class OnlineBrowsingSteps {
	
	WebDriver driver = null;
	LaptopInfo laptop;
	
	@SuppressWarnings("deprecation")
	@Given("browser is open")
	public void browser_is_open() {
		System.out.println("Inside step - browser is open");
		System.setProperty("webdriver.edge.driver", "C:\\Users\\darya\\eclipse-workspace\\CucumberJava\\drivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@And("user is on home page")
	public void user_is_on_home_page() {
		System.out.println("Inside step - user is on home page");
		driver.navigate().to("https://www.amazon.ca/");
	}

	@When("user search up product")
	public void user_search_up_product() {
		System.out.println("Inside step - user search up product");
		driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("laptop");
		driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys(Keys.ENTER);
	}

	@SuppressWarnings("deprecation")
	@And("clicks on product link")
	public void clicks_on_product_link() {
		System.out.println("Inside step - user clicks on product link");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div/div/div[2]/div[1]/h2/a"))));
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div/div/div[2]/div[1]/h2/a")).click();
	}

	@Then("user is successfully navigated to product page")
	public void user_is_successfully_navigated_to_product_page() {
		System.out.println("Inside step - user is successfully navigated to product page");
		
		laptop = new LaptopInfo();
		String laptopName = driver.findElement(By.xpath("//*[@id=\"productTitle\"]")).getAttribute("innerHTML");
		String laptopBrand = driver.findElement(By.xpath("//*[@id=\"poExpander\"]/div[1]/div/table/tbody/tr[2]/td[2]/span")).getAttribute("innerHTML");
		String laptopInfo = driver.findElement(By.xpath("//*[@id=\"feature-bullets\"]/ul/li[1]/span")).getAttribute("innerHTML");

		laptop.setName(laptopName);
		laptop.setBrand(laptopBrand);
		laptop.setDescription(laptopInfo);

		System.out.println("Report of chosen product ");
		System.out.println(laptop.getBrand());
		System.out.println(laptop.getName());
		System.out.println(laptop.getDescription());

		driver.quit();
	}
	
	@And("enters refined search")
	public void enter_refined_search() {
		driver.findElement(By.xpath("//*[@id=\"low-price\"]")).sendKeys("350");
		driver.findElement(By.xpath("//*[@id=\"high-price\"]")).sendKeys("950");
		driver.findElement(By.xpath("//*[@id=\"a-autoid-1\"]/span/input")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"p_89/Dell\"]/span/a/div/label/input"))));
		driver.findElement(By.xpath("//*[@id=\"p_89/Lenovo\"]/span/a/div/label/i")).click();
	}
	
	@Then("user is displayed with better matching products")
	public void user_is_displayed_with_better_marching_products() {
		String result = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[1]/div/span/div/div/span")).getText();
		assertEquals("RESULTS", result);
	}
	
}
