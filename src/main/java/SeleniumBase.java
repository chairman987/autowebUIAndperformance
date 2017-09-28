import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumBase {
    public static void main(String[] args) {
        System.out.println("Hello World!");

  /*      System.setProperty("webdriver.ie.driver", "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
        SeleniumFactory  driverF =new  SeleniumFactory();
         driverF.SingltonDriver();
        driverF.currentWebDriver().get("http://www.baidu.com/");
         String  checkText   =  driverF.getHtmlByIdOrName("su").getAttribute("value");
        driverF.reStartBower();
        driverF.currentWebDriver().get("http://www.sohu.com/");
        System.out.println("检查内容是" + checkText);*/
       String _url = "http://www.xs.la/0_32/4649504.html" ;
        HttpPerformance  _http = new HttpPerformance(_url);
        System.out.println( "HttpStatusCode"+_http.getHttpStatusCode(_url,"key"));

        System.out.println("结束!");



    }
}
