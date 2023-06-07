package basicSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Date;

public class CreateProjectTest {
    ChromeDriver chrome;

    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
        chrome = new ChromeDriver();

        // implicit wait --> tiempo de espera generico para todos los controles
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        chrome.manage().window().maximize();
        chrome.get("http://todo.ly/");
    }

    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }

    @Test
    public void verifyCreateProject() throws InterruptedException {
        // click login button
        chrome.findElement(By.xpath("//img[@src='/Images/design/pagelogin.png']")).click();
        // type email in email txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("selenium2023@upb.com");
        // type pwd in password txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        // click login button
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();

        // Crear un Projecto
        String nameProject = "Eynar"+new Date().getTime();
        // click AddNewProject
        chrome.findElement(By.xpath("//td[text()='Add New Project']")).click();
        // set name project
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        // click Add project
        chrome.findElement(By.id("NewProjNameButton")).click();
        Thread.sleep(2000);
        // verification 1

        Assertions.assertTrue(chrome.findElements(By.xpath("(//li//td[text()='"+nameProject+"'])[last()]")).size() == 1 ,
                "ERROR, the project was not created");
        // verification 2
        String actualResult=chrome.findElement(By.id("CurrentProjectTitle")).getText();
        String expectedResult =nameProject;
        Assertions.assertEquals(expectedResult,actualResult,"ERROR, the project was not created");

    }


}
