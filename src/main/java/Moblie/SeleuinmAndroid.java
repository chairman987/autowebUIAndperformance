package Moblie;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.common.device.DeviceTargetPlatform;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;
import io.selendroid.standalone.server.model.SelendroidStandaloneDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.selendroid.standalone.android.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SeleuinmAndroid {

    private SelendroidLauncher selendroidServer = null;
    private WebDriver driver = null;
  private   WebDriver _selendroidDriver;

  public void Test() throws Exception {

//        SelendroidCapabilities capa = new SelendroidCapabilities("io.selendroid.testapp:0.17.0");
      //一个选择就是要从测试代码直接启动Selendroid-standalone组件
//        SelendroidConfiguration config = new SelendroidConfiguration();
//        config.addSupportedApp("src/main/resources/selendroid-test-app-0.17.0.apk");
//        SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
//        selendroidServer.launchSelendroid();



       // SelendroidCapabilities capa = new SelendroidCapabilities("io.selendroid.testapp:0.17.0");
try{



    SelendroidCapabilities caps = new SelendroidCapabilities("io.selendroid.testapp:0.17.0");
    driver = new SelendroidDriver(caps);
    } catch (Exception var5) {
    System.out.println( var5);
    }

        //        SelendroidDriver selendroidDriver = new SelendroidDriver(capa);
       //WebElement inputField =  SelendroidDriver.findElement(By.id("my_text_field"));
//        Assert.assertEquals("true", inputField.getAttribute("enabled"));
//        inputField.sendKeys("Selendroid");
//        Assert.assertEquals("Selendroid", inputField.getText());
//        selendroidDriver.quit();
      //  WebDriver driver = new SelendroidDriver(capa);
//        WebElement inputField = driver.findElement(By.id("my_text_field"));
//        Assert.assertEquals("true", inputField.getAttribute("enabled"));
//        inputField.sendKeys("Selendroid");
//        Assert.assertEquals("Selendroid", inputField.getText());
//        driver.quit();
    }

//"io.selendroid.testapp:0.17.0"
//    public SeleuinmAndroid(String andoridInfo)throws Exception{
//        SelendroidCapabilities capa = new SelendroidCapabilities(andoridInfo);
//        SelendroidDriver selendroidDriver = new SelendroidDriver(capa);
//        _selendroidDriver = selendroidDriver;
//    }
    public void quit(){
        _selendroidDriver.quit();
    }
    /**
     * 1. link text 查找待链接的 HTMl
     * */
    public WebElement getHtmlByID(String input){
        String pageSource =  _selendroidDriver.getPageSource().toLowerCase() ;
        return _selendroidDriver.findElement(By.id(input));
    }
    /*
    *1.  partialLinkText 查找待链接的 HTMl
    */
    public WebElement getHtmlBypartialLinkText(String input){
        String pageSource =  _selendroidDriver.getPageSource().toLowerCase() ;
        return _selendroidDriver.findElement(By.partialLinkText(input));
    }
}
