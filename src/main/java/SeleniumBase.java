import Performance.HttpPerformance;
import Performance.PerformanceTool;
import webUITestTool.SeleniumFactory;

public class SeleniumBase {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        //WebUI();
        //TestHttpgetCode();
        //TestHttpgetCodeBatch();
        System.out.println("结束!");

        String _url = "http://www.qu.la/book/148/" ;
        PerformanceTool _http = new PerformanceTool();
        _http.processTest(_url,"ke=1",1000,4,50,false,5);
        while (_http.getIsRun()){

            System.out.println( " is run check");
            System.out.println( "\n");
            Thread.sleep(100);// 睡眠100毫秒
            break;

        }
        System.out.println( "Http info end .msg 是"+ _http.get_resutStaticsModel().getStatisMsg());


    }

    private static void WebUI() {
        System.setProperty("webdriver.ie.driver", "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
        SeleniumFactory driverF =new SeleniumFactory();
        driverF.SingltonDriver();
        driverF.currentWebDriver().get("http://www.baidu.com/");
        String  checkText   =  driverF.getHtmlByIdOrName("su").getAttribute("value");
        driverF.reStartBower();
        driverF.currentWebDriver().get("http://www.sohu.com/");
        System.out.println("检查内容是" + checkText);
    }

    private static void TestHttpgetCodeBatch() {
        String _url = "http://www.xs.la/0_32/4649504.html" ;
        HttpPerformance _http = new HttpPerformance(_url);
        System.out.println( "Http info "+_http.getMedthodBatchTest(5,"key=1").getStatisMsg());
    }
    private static void TestHttpgetCode() {
        String _url = "http://www.xs.la/0_32/4649504.html" ;
        HttpPerformance  _http = new HttpPerformance(_url);
        System.out.println( "HttpStatusCode"+_http.getHttpStatusCode(_url,"key"));
    }
}
