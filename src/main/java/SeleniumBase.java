import Moblie.SeleuinmAndroid;
import Model.ResutStaticsModel;
import Model.Task;
import MulHttpTask.IMutHttpTaskTest;
import MulHttpTask.MutHttpTaskPerformance;
import Performance.HttpPerformance;
import Performance.PerformanceTool;
import webUITestTool.SeleniumFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

public class SeleniumBase {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        //WebUI();
        //TestHttpgetCode();
        //TestHttpgetCodeBatch();
        Task  firstStep =new Task();
        firstStep.setKey( UUID.randomUUID().toString().replaceAll("-", ""));
        firstStep.setParam("param=1");
        firstStep.setUrl("http://www.chinanews.com/yl/2015/06-16/7347550.shtml" );
        firstStep.setJsCode("  (function getValue (){ return 100;})() ");
        Task  secondStep =new Task();
        secondStep.setParam("param=1");
        secondStep.setKey( UUID.randomUUID().toString().replaceAll("-", ""));
        secondStep.setUrl("http://www.chinanews.com/yl/2015/06-16/7347550.shtml" );
        secondStep.setJsCode(" (function getValue (){ return 100;})() ");
        firstStep.setTask(secondStep); //必须跟上个连起来

        IMutHttpTaskTest _mht = new MutHttpTaskPerformance();

        _mht.excuteTask(firstStep);
          for  (String res : _mht.getReturnSet().values()) {
              System.out.println(res);
          }
        // batchStepTest();
    }

    private static void AndroidTest() {
        SeleuinmAndroid android = new SeleuinmAndroid();
        try {
            android.Test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void batchStepTest() throws InterruptedException {
        String _url = "http://www.chinanews.com/yl/2015/06-16/7347550.shtml" ;
        PerformanceTool _http = new PerformanceTool();
        Integer mm =2;
        _http.processTest(_url,"ke=1",2,1,1,false,mm);
        Date begin = new Date();
        Date endTime = new Date(begin .getTime() + mm*1000);
        while (_http.getIsRun()){


            Thread.sleep(100);// 睡眠100毫秒
            if (PerformanceTool.compare_date(endTime, new Date()) != 1)
            break;

        }
        System.out.println( " run over");
        HashMap<String , ResutStaticsModel> all =  _http .get_map();
        if(all.values().size() == 0){
            System.out.println( " all.values().size() == 0 ");
        }
        System.out.println( " all.values().size() "+all.values().size() );
        for (ResutStaticsModel value :all.values()) {

            System.out.println("Value = " + value.getStatisMsg());

        }
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
