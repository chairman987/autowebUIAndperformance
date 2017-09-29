import Performance.HttpPerformance;

public class test1 {
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
        testgetHttpCode();
        testpostHttpCode();


    }
    private static void TestHttpgetCodeBatch() {
        String _url = "http://www.xs.la/0_32/4649504.html" ;
        HttpPerformance _http = new HttpPerformance(_url);
        System.out.println( "Http info "+_http.getMedthodBatchTest(100,"key=1").getStatisMsg());
    }
    private static void testgetHttpCode() {
        String _url = "http://www.xs.la/0_32/4649504.html" ;
        HttpPerformance  _http = new HttpPerformance(_url);
        System.out.println( "HttpStatusCode"+_http.getHttpStatusCode(_url,"key"));

        System.out.println("get结束!");
    }
    private static void testpostHttpCode() {
        String _url = "http://www.xs.la/0_32/4649504.html" ;
        HttpPerformance  _http = new HttpPerformance(_url);
        System.out.println( "HttpStatusCode"+_http.postHttpStatusCode(_url,"key"));

        System.out.println("post结束!");
    }
}
