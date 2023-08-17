package com.qa.constants;

import com.qa.baseclass.BaseClass;
import com.qa.lib.TakeScreenshot;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

@Log4j2
public class Constants {
    public final int targetProfitPercentage = 10;
    public final int basisPoints = 10;
    public final int capital = 1;
    public final int limitOrderPrice = 10;
    public final int limitQuantities = 10;
    public final int marketQuantities = 80;
    public final int gridBuyLimits = 80;
    public final int depositAmount = 1;
}