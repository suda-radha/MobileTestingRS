package com.suba.appium_proj1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasics {
	
	@Test
	public void appiumTest1() throws MalformedURLException, URISyntaxException {
		System.out.println("====:Appium basics testing starts..");
		
		//1. start appium server programatically
		String appiumServicePath = "C://Users//sudab//AppData//Roaming//npm//node_modules//appium//build//lib//main.js";
		
		Map<String, String> env = new HashMap<>(System.getenv());
		env.put("ANDROID_SDK_ROOT", "C:\\Users\\sudab\\AppData\\Local\\Android\\Sdk");
		env.put("ANDROID_HOME", "C:\\Users\\sudab\\AppData\\Local\\Android\\Sdk");
		
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File(appiumServicePath))
				.withIPAddress("127.0.0.1").usingPort(4723)
//				.withEnvironment(new HashMap<String, String>() {{
//			        put("ANDROID_SDK_ROOT", "C:\\Users\\sudab\\AppData\\Local\\Android\\Sdk");
//			        put("JAVA_HOME", "C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.17.10-hotspot");
//			        put("PATH", System.getenv("PATH") + ";C:\\Users\\sudab\\AppData\\Local\\Android\\Sdk\\platform-tools");
//			    }})
				.withEnvironment(env)
				.build();
		service.start();
		
		//flow: Appium code-> Appium server-> Mobile
		
		String appPath = "C://Suda_files_2024_lenovo//MobileAPKs//ApiDemos-debug.apk";
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("suda_small_phone");
		options.setApp(appPath);
		
		//2. AndroidDriver or IOSDriver (like webdriver)
		AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		
		//3. Run your tests
		
		
		//4. quit the AndroidDriver
		driver.quit();
		System.out.println("====:AndroidDriver quit");
		
		//5. stop the appium service
		service.stop();
		System.out.println("====:Appium service stopped");
		
		
		
	}

}
