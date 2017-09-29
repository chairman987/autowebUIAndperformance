package Performance;

import Model.ResutStaticsModel;
import Performance.HttpPerformance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Performance.HttpPerformance.getHttpStatusCode;
import static Performance.HttpPerformance.sendGet;

public class PerformanceTool {

    public static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //  System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                // System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    public static int compare_date(Date begin, Date end) {
        try {

            if (begin.getTime() > end.getTime()) {
              //  System.out.println("dt1 在dt2前");
                return 1;
            } else if (begin.getTime() < end.getTime()) {
                //  System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    private  String  _url = "";
    private  String  _getParam = "";
    private  String  _postParam = "";
    private  int  _step = 0;
    private  int  httpOkCode = 200 ;
    private  Date _endTime = new Date();
    private Integer _suc = 0;
    private Integer _fail = 0;
    private Integer _all = 0;
    private List<ResutStaticsModel> _subList= new Vector<ResutStaticsModel>();
    private ResutStaticsModel _resutStaticsModel = new ResutStaticsModel();
    private boolean isrun = true;

    public  boolean getIsRun(){
        return this.isrun;
    }

    public  ResutStaticsModel get_resutStaticsModel(){
        return _resutStaticsModel;
    }
    //执行测试 开始  n个 实现每次增加n个,直到执行完
    public  void processTest(String url ,String param,int maxTests,int step ,int maxStep,boolean iswaiteTime,Integer mm) throws InterruptedException {

        isrun = true;
        Date now = new Date();
        Date endTime = new Date(now .getTime() + mm*1000);

        HttpPerformance _http = new HttpPerformance("");

        int indexCurrent = 0;
        if(step>maxStep)
            step = maxStep;
        _url = url;
        _getParam = param;
        _step = step;
        _endTime = endTime;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String begin =   df.format(new Date());// new Date()为获取当前系统时间
        while (indexCurrent<maxTests){

            if(isrun == false)
                break;
//            for(int index = 0;index < step;index++){
//
//                indexCurrent++;
//            }
            try{

                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

                    cachedThreadPool.execute(new Runnable() {
                        public void run() {

                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                            String begin =   df.format(new Date());// new Date()为获取当前系统时间
                            int processCounter = 1;
                            int processZeroCounter = 0;
                            for(int index = 0;index < _step;index++ ){
                                if(isrun == false)
                                {
                                    index = _step;
                                    break;
                                }

                                while(true){
                                    Date nowMM = new Date();
                                   if(compare_date(_endTime,nowMM) == -1){
                                       isrun = false;
                                            break;
                                   }
                                    ResutStaticsModel resutStaticsModelSub = new ResutStaticsModel();
                                    String beginSub =   df.format(new Date());// new Date()为获取当前系统时间
                                    int    res =   getHttpStatusCode(_url,_getParam);
                                    System.out.println("getHttpStatusCode excute! isrun = "+isrun);
                                    //Log 4  记录  返回  结果和时间
                                    if (res == httpOkCode)
                                    {
                                        _suc++;
                                        resutStaticsModelSub.setSuce(processCounter);
                                        resutStaticsModelSub.setFail(processZeroCounter);
                                    }
                                    else
                                    {
                                        _fail ++;
                                        resutStaticsModelSub.setSuce(processZeroCounter);
                                        resutStaticsModelSub.setFail(processCounter);
                                    }
                                    _all++;
                                    String endSub =   df.format(new Date());
                                    resutStaticsModelSub.setAll(processCounter);
                                    resutStaticsModelSub.setMark(beginSub+":"+endSub);
                                    resutStaticsModelSub.setBatchId(String.valueOf(java.util.UUID.randomUUID()) );
                                    _subList.add(resutStaticsModelSub);


                                    try {
                                        Thread.sleep(100);// 睡眠100毫秒
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        }
                    });

                if(isrun == false)
                {
                    cachedThreadPool.shutdown();
                }

                indexCurrent += step;
                    //Log 4  记录  返回  结果和时间

            }catch (Exception e) {}



            if(step<maxStep)
                step += step;

            if(step>maxTests)
            {
                step = step-maxTests ;//保证不会超出
            }

            if(iswaiteTime)
                Thread.sleep(100);// 睡眠100毫秒
        }
        String end =   df.format(new Date());
        _resutStaticsModel.setAll(_all);
        _resutStaticsModel.setSuce(_suc);
        _resutStaticsModel.setFail(_fail);
        _resutStaticsModel.setMark(begin+"结束"+end);
        _resutStaticsModel.setBatchId(String.valueOf(java.util.UUID.randomUUID()) );
        _resutStaticsModel.setSubList(_subList);
    }

}
