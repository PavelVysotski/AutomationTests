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

    @Test(testName = "Search location, date and number of guests")
    public void searchLocationAndDate() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindRoomPage findRoomPage = PageFactory.initElements(driver, FindRoomPage.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        String town = "Минск";
        findRoomPage.fillLocation(town);
        findRoomPage.chooseStartDate("Сентябрь");
        findRoomPage.chooseEndDate("Октябрь");
        findRoomPage.chooseGuests();
        findRoomPage.startSearch();

        Assert.assertTrue(findRoomPage.confirmLogin().contains(town + ": найдено"), "Search didn't return any results");
    }


    @Test(testName = "Search air tickets")
    public void searchAirTickets() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindAirTickets findAirTickets = PageFactory.initElements(driver, FindAirTickets.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        findAirTickets.chooseAirTickets();
        findAirTickets.chooseDirection("Лондон", "Минск");
        findAirTickets.chooseDate("08.04.2021", "20.04.2021");

        Assert.assertTrue(findAirTickets.confirmTest());
    }

    @Test(testName = "Search car")
    public void SearchCarRental()  {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindAuto findAuto = PageFactory.initElements(driver, FindAuto.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        findAuto.chooseRentalCar();
        findAuto.choosePlaceReceive("Минск");
        findAuto.chooseTime("14", "15", "12", "45");

        Assert.assertTrue(findAuto.confirmTest());
    }

    @Test(testName = "Search leisure options")
    public void searchLeisureOptions() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        LeisureOptions leisureOptions = PageFactory.initElements(driver, LeisureOptions.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        String town = "Москва";
        leisureOptions.enterSearch(town);

        Assert.assertTrue(leisureOptions.confirmTestGlobal().contains(town), "Search didn't return any results");
    }

    @Test(testName = "Choose from propose towns")
    public void chooseProposeTownForRelax() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        LeisureOptions leisureOptions = PageFactory.initElements(driver, LeisureOptions.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        String town = "Ванкувер";
        leisureOptions.choiceFromTheProposed(town);
        System.out.println(leisureOptions.confirmTestPropose());

        Assert.assertTrue(leisureOptions.confirmTestPropose().contains((town).toLowerCase()), "Search didn't return any results");
    }

    @Test(testName = "Search taxi")
    public void SearchTaxi() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        FindTaxi findTaxi = PageFactory.initElements(driver, FindTaxi.class);

        homePage.uploadSite(configFileReader.getBaseUrl());
        findTaxi.chooseTaxiOptions();
    }
}
