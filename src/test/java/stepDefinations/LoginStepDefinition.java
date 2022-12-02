package stepDefinations;

import Pages.LoginButtons;
import Pages.RegisterButtons;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginStepDefinition {
    WebDriver driver;
     Assert soft;
     RegisterButtons registerButtons;
    LoginButtons loginButtons;
    @Given("start user navigates to the website")
    public void navigatesToNewScenario() {

        //driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        loginButtons = new LoginButtons(driver);
        registerButtons = new RegisterButtons(driver);
    }

    @Given("user navigates to the website")
    public void navigatesToWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        loginButtons = new LoginButtons(driver);
    }

    @And("press into login")
    public void pressLogin() {
        driver.navigate().to("https://demo.nopcommerce.com/");
        loginButtons.loginButtonEle().click();
    }

    @When("^enter a valid Email \"(.*)\"$")
    public void validEmail(String email) {
        loginButtons.writeEmailEle().sendKeys(email);
    }

    @And("enter a valid password \"(.*)\"$")
    public void validPassword(String pass) {
        loginButtons.writePasswordEle().sendKeys(pass);
    }

    @And("press login button")
    public void pressLoginButton() throws InterruptedException {
        loginButtons.okButtonEle().click();
        Thread.sleep(5000);
    }

    @Then("user could login successfully and go to home page")
    public void goToHomePage() {
        String expectedResult = "Log out";
        String actualResult = loginButtons.actualResult1().getText();
        Assert.assertTrue(actualResult.contains(expectedResult));

    }

    @When("^enter an Email \\\"(.*)\\\"$")
    public void invalidEmail(String email) {
        loginButtons.writeEmailEle().sendKeys(email);
    }

    @And("enter an password \"(.*)\"$")
    public void invalidPassword(String pass) {
        loginButtons.writePasswordEle().sendKeys(pass);
    }

    @Then("^user couldn't login successfully error message \"(.*)\"")
    public void errorMessage(String email) {
        if(email.contains("mohamedalifahmy2021@gmail.com")) {
            String expectedResult = "Login was unsuccessful. Please correct the errors and try again.";
            String actualResult = loginButtons.actualResult2().getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }else if(email.isEmpty()){
            String expectedResult = "Please enter your email";
            String actualResult = driver.findElement(By.xpath("//*[@id=\"Email-error\"]")).getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }else
        {
            String expectedResult = "Wrong email";
            String actualResult = driver.findElement(By.xpath("//*[@id=\"Email-error\"]")).getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }
    }
    @And("enter an invalid password")
    public void invalidPassword() {
        loginButtons.writePasswordEle().sendKeys("123");
    }
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @And("press into register")
    public void register(){
        registerButtons.registerButtonEli().click();
    }
    @And("choose the gender")
    public void chooseGender(){
        registerButtons.genderButtonEli().click();
    }
    @And("^write the firstname \"(.*)\"$")
    public void writeFirstname(String firstname){
        registerButtons.firstNameEli().sendKeys(firstname);
    }
    @And("^write the lastname \\\"(.*)\\\"$")
    public void writeLastname(String lastname){
        registerButtons.lastNameEli().sendKeys(lastname);
    }
    @And("^select day \\\"(.*)\\\"$")
    public void selectDay(String day){
        Select days = new Select(registerButtons.selectDayEli());
        days.selectByValue(day);
    }
    @And("^select month \\\"(.*)\\\"$")
    public void selectMonth(int months){
        Select month = new Select(registerButtons.selectMonthEli());
        month.selectByIndex(months);
    }
    @And("^select year \\\"(.*)\\\"$")
    public void selectYear(String years){
        Select year = new Select(registerButtons.selectYearEli());
        year.selectByValue(years);
    }
    @And("^write Email\\\"(.*)\\\"$")
    public void writeEmail(String Email){
        registerButtons.writeEmailEli().sendKeys(Email);
    }
    @And("^write the company \\\"(.*)\\\"$")
    public void writeCompany(String company){
        registerButtons.writeCompanyEli().sendKeys(company);
    }
    @And("^enter the password \\\"(.*)\\\"$")
    public void enterPassword(String P){
        registerButtons.writePasswordEli().sendKeys(P);

    }
    @And("confirm password {string}")
    public void confirmPassword(String p){
        registerButtons.writeConfirmPasswordEli().sendKeys(p);
    }
    @And("press register button")
    public void pressRegisterButton(){
        registerButtons.registerButtonBEli(driver).click();
    }
    @Then("user could register successfully")
    public void registerSuccessfully() throws InterruptedException {
        String expectedResult = null, actualResult;
        String actual1 = "https://demo.nopcommerce.com/registerresult/1?returnUrl=/";

        if (driver.getCurrentUrl().equalsIgnoreCase(actual1)) {
            expectedResult="Your registration completed";
            actualResult=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]")).getText();
            Assert.assertTrue(actualResult.contains(expectedResult));

        } else {
            expectedResult = "The specified email already exists";
            actualResult = registerButtons.actualResultEli(driver).getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }
    }
    @Given("user navigates to the website register")
    public void navigatesWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        registerButtons = new RegisterButtons(driver);
    }
    @Then("error message \"(.*)\" \"(.*)\" \"(.*)\" \"(.*)\" \"(.*)\"$")
    public void error(String Email,String firstName,String lastname,String confirmPassword,String Pass) throws InterruptedException {
        String expectedResult = null, actualResult;
        String actual1 = "https://demo.nopcommerce.com/registerresult/1?returnUrl=/";
        Thread.sleep(3000);

        if (!Email.contains("mohamedalifahmy2021@gmail.com")) {
            expectedResult = "Wrong email";
            Thread.sleep(3000);
            actualResult = driver.findElement(By.xpath("//*[@id=\"Email-error\"]")).getText();
            soft.assertTrue(actualResult.contains(expectedResult));

        } else if (firstName.isEmpty()) {
            expectedResult = "First name is required.";
            actualResult = driver.findElement(By.xpath("//*[@id=\"FirstName-error\"]")).getText();
            soft.assertTrue(actualResult.contains(expectedResult));
        }
        else if (lastname=="") {
            expectedResult = "Last name is required.";
            actualResult = driver.findElement(By.xpath("//*[@id=\"LastName-error\"]")).getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }else if (confirmPassword!=Pass) {
            expectedResult = "The password and confirmation password do not match.";
            actualResult = driver.findElement(By.xpath("//*[@id=\"ConfirmPassword-error\"]")).getText();
            Assert.assertTrue(actualResult.contains(expectedResult));

        } else {
            if (driver.getCurrentUrl().equalsIgnoreCase(actual1)) {
                expectedResult="Your registration completed";
                actualResult=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]")).getText();
                Assert.assertTrue(actualResult.contains(expectedResult));

            } else {
                expectedResult = "The specified email already exists";
                actualResult = registerButtons.actualResultEli(driver).getText();
                Assert.assertTrue(actualResult.contains(expectedResult));
            }
        }


    }
    @After
    public void after(){
        driver.quit();
    }

}


