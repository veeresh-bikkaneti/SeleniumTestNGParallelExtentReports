package com.individualcollection.pagefactory;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;

@Getter
@Setter
public class PagePo {

    private By countdownBy = By.id("javascript_countdown_time");
    private By hamburgerMenu = By.cssSelector("#nav-hamburger-menu");

    private By hmenuCustomerPorofile = By.cssSelector("#hmenu-customer-profile");


}
