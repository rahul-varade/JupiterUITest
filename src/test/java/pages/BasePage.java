package pages;

//import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;


public class BasePage {
	protected AppiumDriver driver;
	protected WebDriverWait wait;
	TouchAction action;

	
	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		action = new TouchAction(driver);
	}
	
	public void waitForVisibility(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		
	}
	
	public void enterNumber(By element,String txt) {
		waitForVisibility(element);
		WebElement ele = driver.findElement(element);
		ele.sendKeys(txt);
	}
	
	public void sendKeys(By element,String txt) throws Exception {
		waitForVisibility(element);
		WebElement ele = driver.findElement(element);
//		ele.clear();
		for (int i = 0; i < txt.length(); i++) {
			ele.sendKeys(Character.toString(txt.charAt(i)));
			Thread.sleep(500);
        }
	}
	
	public void click(By element) {
		waitForVisibility(element);
		driver.findElement(element).click();
	}
	
	public String getText(By element) {
		waitForVisibility(element);
		return driver.findElement(element).getAttribute("text");
	}
	
	public void showPageSource() {
		System.out.println(driver.getPageSource());
	}
	
	public void goBack() {
		 driver.navigate().back();
	}
	
	public static void swipeUp() throws IOException, InterruptedException {        
		String shell_cmd = "adb shell input swipe 500 1000 300 300";
		Runtime run = Runtime.getRuntime();
	    Process pr = run.exec(shell_cmd);
	    pr.waitFor();
	    BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	    String line = "";
	    while ((line=buf.readLine())!=null) {
	    System.out.println(line);
	    }
	    }
	public static void swipeDown() throws IOException, InterruptedException {        
		String shell_cmd="adb shell input swipe 300 300 500 1000";
		Runtime run = Runtime.getRuntime();
	    Process pr = run.exec(shell_cmd);
	    pr.waitFor();
	    BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	    String line = "";
	    while ((line=buf.readLine())!=null) {
	    System.out.println(line);
	    }
	    }
	public static void swipeLeftToRight() throws IOException, InterruptedException {        
		String shell_cmd = "adb shell input swipe 500 1000 1000 1000";
		Runtime run = Runtime.getRuntime();
	    Process pr = run.exec(shell_cmd);
	    pr.waitFor();
	    BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	    String line = "";
	    while ((line=buf.readLine())!=null) {
	    System.out.println(line);
	    }
	    }
	public static void swipeRightToLeft() throws IOException, InterruptedException {        
		String shell_cmd= "adb shell input swipe 1000 1000 500 1000";
		Runtime run = Runtime.getRuntime();
	    Process pr = run.exec(shell_cmd);
	    pr.waitFor();
	    BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	    String line = "";
	    while ((line=buf.readLine())!=null) {
	    System.out.println(line);
	    	}
	    }
	public boolean isElementVisible(By element) {
		waitForVisibility(element);
		return driver.findElement(element).isDisplayed();
	}
	
	public WebElement convertToWebElement(By element) {
		waitForVisibility(element);
		return driver.findElement(element);
	}
	
//	public void dragAndDrop(By dragElement, By dropElement) {
//		WebElement dragElementTwo = driver.findElement(dragElement);
//		WebElement dropElementTwo = driver.findElement(dropElement);
//		int middleXCoOrdinate_dragElement = dragElementTwo.getLocation().x + (dragElementTwo.getSize().width/2);
//		int middleYCoOrdinate_dragElement = dragElementTwo.getLocation().y + (dragElementTwo.getSize().height/2);
//		
//		int middleXCoOrdinate_dropElement = dropElementTwo.getLocation().x + (dropElementTwo.getSize().width/2);
//		int middleYCoOrdinate_dropElement = dropElementTwo.getLocation().y + (dropElementTwo.getSize().height/2);
//		
//		action.longPress(PointOption.point(middleXCoOrdinate_dragElement, middleYCoOrdinate_dragElement)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(middleXCoOrdinate_dropElement,middleYCoOrdinate_dropElement)).release().perform();
//		}
	
	
	public void dragAndDrop(WebElement dragElement, WebElement dropElement) {

		int middleXCoordinate_dragElement =dragElement.getLocation().x + (dragElement.getSize().width/2);

		int middleYCoordinate_dragElement =dragElement.getLocation().y + (dragElement.getSize().height/2);
						
		int middleXCoordinate_dropElement =dropElement.getLocation().x + (dropElement.getSize().width/2);

		int middleYCoordinate_dropElement =dropElement.getLocation().y + (dropElement.getSize().height/2);
						
//		action.longPress(PointOption.point(middleXCoordinate_dragElement, middleYCoordinate_dragElement))
//		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
//		.moveTo(PointOption.point(middleXCoordinate_dropElement, middleYCoordinate_dropElement))
//		.release()
//		.perform();
//		
//		System.out.println("-----------=============");
//		
//		action.press(PointOption.point(middleXCoordinate_dragElement, middleYCoordinate_dragElement))
//		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
//		.moveTo(PointOption.point(middleXCoordinate_dropElement, middleYCoordinate_dropElement))
//		.release()
//		.perform();
		TouchAction dragNDrop = new TouchAction((MobileDriver)driver);
        Dimension size = driver.manage().window().getSize();
		int starty = (int) (size.height * 0.85);
		int endy = (int) (size.height * 0.45);
		int width = size.width / 2;
		dragNDrop.press(new PointOption().withCoordinates(width, starty)).waitAction().moveTo(new PointOption().withCoordinates(width, endy)).release().perform();
	}
	
	
	
	public void swipeScreen(Direction dir) {
	    final int ANIMATION_TIME = 200;
	    final int PRESS_TIME = 200;
	    int edgeBorder = 10;
	    PointOption pointOptionStart, pointOptionEnd;
	    Dimension dims = driver.manage().window().getSize();
	    pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

	    switch (dir) {
	        case DOWN:
	            pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
	            break;
	        case UP:
	            pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
	            break;
	        case LEFT:
	            pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
	            break;
	        case RIGHT:
	            pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
	            break;
	        default:
	            throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
	    }

	    try {
	        new TouchAction(driver).press(pointOptionStart)
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
	                .perform();
	    } catch (Exception e) {
	        System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
	        return;
	    }

	    try {
	        Thread.sleep(ANIMATION_TIME);
	    } catch (InterruptedException e) {
	    }
	}

	public enum Direction {
	    UP, DOWN, LEFT, RIGHT;
	}
}