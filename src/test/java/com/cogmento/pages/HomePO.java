package com.cogmento.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;

@Setter
@Getter
public class HomePO {

     By topHeaderMenuLogo = By.cssSelector("div#top-header-menu > .header.item");
     By versionOfApp = By.cssSelector("div#top-header-menu > .item:nth-of-type(2)");
     By userName = By.cssSelector(".right .user-display");
     By trialIndicator = By.cssSelector(".right .trial-indicator");
     By searchField = By.cssSelector("[placeholder='Search']");
     By pinButton = By.cssSelector(".button .icon.pin");
     By trashCanIcon=By.cssSelector(".buttons .button.item:nth-of-type(2)");
     By settingsIcon=By.cssSelector("div[role='listbox'] > .icon.settings");


     By leftVerticalMenu=By.cssSelector("#main-nav.left.vertical .item");






     By passwordField = By.name("password");
     By loginButton = By.cssSelector("div.submit");
     By forgotPasswordbutton = By.cssSelector("div.message:nth-of-type(1)");
     By checkFreeCRMorCRMProUserButton = By.cssSelector(".column > div.message:nth-of-type(2)");
     By resgisterOrSignUpButton = By.cssSelector(".column > div.message:nth-of-type(3)");


}

