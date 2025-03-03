package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.BasePage.clickElement;

public class DashboardPage {

    private final By avatarIkon = By.xpath("//nz-avatar[@class='ant-avatar ant-avatar-circle ant-avatar-icon']");
    private final By signOut = By.xpath("//span[contains(text(),'Sign Out')]");

    public void logout() {
        clickElement(avatarIkon);
        clickElement(signOut);
    }

}
