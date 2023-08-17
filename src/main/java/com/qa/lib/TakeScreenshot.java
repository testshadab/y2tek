package com.qa.lib;

import java.io.File;

import com.qa.baseclass.BaseClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TakeScreenshot extends BaseClass
{
	public static String captuerScreenshot(WebDriver driver,String screenshotName) {
		try {

			//Convert web driver object to TakeScreenshot

			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file

			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination

			File DestFile=new File("./Screenshot/"+screenshotName+System.currentTimeMillis()+".png");

			//Copy file at destination

			FileUtils.copyFile(SrcFile, DestFile);

			log.info("Screenshot Captured");

		} catch (Exception e) {
			log.info("Exception while taking Screesnhot"+e.getMessage());
		}
		return screenshotName;
	}
}
