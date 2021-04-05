package bookingTest;

import config.ConfigFileReader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageEntity.*;

public class TestPages extends BaseTest {

    private ConfigFileReader configFileReader = new ConfigFileReader();

    @Test
    public void login() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        SignUp signUpPage = PageFactory.initElements(driver, SignUp.class);

        homePage.enterAccount(configFileReader.getBaseUrl());
        signUpPage.fillEmail(configFileReader.getEmail());
        signUpPage.fillPassword(configFileReader.getPassword());
        Assert.assertTrue(signUpPage.confirmLogin().contains("Павел Высоцкий"), "User was not logged in");
    }

    @Test(testName = "Enter location, date and number of guests")
    public void enterLocationAndDate() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindRoomPage findRoomPage = PageFactory.initElements(driver, FindRoomPage.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        findRoomPage.fillLocation("Минск");
        findRoomPage.chooseStartDate("Сентябрь");
        findRoomPage.chooseEndDate("Октябрь");
        findRoomPage.chooseGuests();
        findRoomPage.startSearch();
        Assert.assertTrue(findRoomPage.confirmLogin().contains("Минск: найдено"), "Search didn't return any results");
    }


    @Test(testName = "Find air tickets")
    public void findAirTickets() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindAirTickets findAirTickets = PageFactory.initElements(driver, FindAirTickets.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        findAirTickets.chooseAirTickets();
        findAirTickets.chooseDirection("Минск", "Стамбул");
        findAirTickets.chooseDate("08.04.2021", "20.04.2021");
int i=1;

    }

    @Test(testName = "Find car")
    public void findCarRental() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindAuto findAuto = PageFactory.initElements(driver, FindAuto.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        findAuto.chooseRentalCar();
        findAuto.choosePlaceOfReceipt("Минск");
        //        findAuto.chooseDate();
        int i =1;


    }

    @Test(testName = "Search leisure options")
    public void findLeisureOptions() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        LeisureOptions leisureOptions = PageFactory.initElements(driver, LeisureOptions.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        leisureOptions.enterSearch("Санкт-Петербург");
        int i=1;

    }

    @Test(testName = "Choose from propose towns")
    public void chooseProposeTownForRelax() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        LeisureOptions leisureOptions = PageFactory.initElements(driver, LeisureOptions.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        leisureOptions.choiceFromTheProposed();
        int i = 1;
    }

    @Test(testName = "Find taxi")
    public void findTaxi() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindTaxi findTaxi = PageFactory.initElements(driver, FindTaxi.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        findTaxi.chooseTaxiOptions();
        int i = 1;
    }


}
