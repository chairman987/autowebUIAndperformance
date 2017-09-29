package Performance;

import Model.ResutStaticsModel;
import webUITestTool.StreamTool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// web api 性能测试
public class HttpPerformance  {
        private  String  _url = "";
        private  String  _getParam = "";
        private  String  _postParam = "";
        private  int  httpOkCode = 200 ;

        public HttpPerformance(String needUrl){

            _url = needUrl;
        }
    /**
     * 1.指定URL get counter 次
     * */
    public void getMedthodExcuteAsync(int counter,String param){

        if (counter <= 0)
            counter = 1;
        for(Integer cishu = 0; cishu < counter ;counter ++ ){
            String    res =   sendGet(_url,param);
            //Log 4  记录  返回  结果和时间
        }
    }
        /**
         * 1.指定URL get counter 次
         * */
        public ResutStaticsModel getMedthodBatchTest(int counter,String param){
            ResutStaticsModel resutStaticsModel = new ResutStaticsModel();
            List<ResutStaticsModel> subList= new Vector<ResutStaticsModel>();
            if (counter <= 0)
                counter = 1;
            Integer suc = 0;
            Integer fail = 0;
            Integer all = 0;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String begin =   df.format(new Date());// new Date()为获取当前系统时间
            int processCounter = 1;
            int processZeroCounter = 0;
            for(Integer cishu = 0; cishu < counter ;cishu ++ ){
                ResutStaticsModel resutStaticsModelSub = new ResutStaticsModel();
                String beginSub =   df.format(new Date());// new Date()为获取当前系统时间
               Integer    res =   getHttpStatusCode(_url,param);
                if (res == httpOkCode)
                {
                    suc++;
                    resutStaticsModelSub.setSuce(processCounter);
                    resutStaticsModelSub.setFail(processZeroCounter);
                }
                else
                    {
                        fail ++;
                        resutStaticsModelSub.setSuce(processZeroCounter);
                        resutStaticsModelSub.setFail(processCounter);
                    }

                all++;
                String endSub =   df.format(new Date());
                resutStaticsModelSub.setAll(processCounter);
                resutStaticsModelSub.setMark(beginSub+":"+endSub);
                resutStaticsModelSub.setBatchId(String.valueOf(java.util.UUID.randomUUID()) );
                subList.add(resutStaticsModelSub);
            }
            String end =   df.format(new Date());
            resutStaticsModel.setAll(all);
            resutStaticsModel.setSuce(suc);
            resutStaticsModel.setFail(fail);
            resutStaticsModel.setMark(begin+"结束"+end);
            resutStaticsModel.setBatchId(String.valueOf(java.util.UUID.randomUUID()) );
            resutStaticsModel.setSubList(subList);
            return  resutStaticsModel;
        }

    public ResutStaticsModel postMedthodBatchTest(int counter, String param){

        ResutStaticsModel resutStaticsModel = new ResutStaticsModel();
        List<ResutStaticsModel> subList= new Vector<ResutStaticsModel>();
        if (counter <= 0)
            counter = 1;
        Integer suc = 0;
        Integer fail = 0;
        Integer all = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String begin =   df.format(new Date());// new Date()为获取当前系统时间
        int processCounter = 1;
        int processZeroCounter = 0;
        for(Integer cishu = 0; cishu < counter ;cishu ++ ){
            ResutStaticsModel resutStaticsModelSub = new ResutStaticsModel();
            String beginSub =   df.format(new Date());// new Date()为获取当前系统时间
            int    res =   postHttpStatusCode(_url,param);
            //Log 4  记录  返回  结果和时间
           if (res == httpOkCode)
           {
               suc++;
               resutStaticsModelSub.setSuce(processCounter);
               resutStaticsModelSub.setFail(processZeroCounter);
           }
           else
           {
               fail ++;
               resutStaticsModelSub.setSuce(processZeroCounter);
               resutStaticsModelSub.setFail(processCounter);
           }
            all++;
            String endSub =   df.format(new Date());
            resutStaticsModelSub.setAll(processCounter);
            resutStaticsModelSub.setMark(beginSub+":"+endSub);
            resutStaticsModelSub.setBatchId(String.valueOf(java.util.UUID.randomUUID()) );
            subList.add(resutStaticsModelSub);
        }
        String end =   df.format(new Date());
        resutStaticsModel.setAll(all);
        resutStaticsModel.setSuce(suc);
        resutStaticsModel.setFail(fail);
        resutStaticsModel.setMark(begin+"结束"+end);
        resutStaticsModel.setBatchId(String.valueOf(java.util.UUID.randomUUID()) );
        resutStaticsModel.setSubList(subList);
        return  resutStaticsModel;
    }
    public void getMedthodTestAsnyc(int counter,String param) {
        try{
            if (counter <= 0)
                counter = 1;
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            _getParam = param;
            for(Integer cishu = 0; cishu < counter ;cishu ++ ){
                cachedThreadPool.execute(new Runnable() {
                    public void run() {
                        String    res =   sendGet(_url,_getParam);
                    }
                });
                //Log 4  记录  返回  结果和时间
            }
        }catch (Exception e) {}


    }
    public void postMedthodTestAsnyc(int counter,String param) {
      try{
        if (counter <= 0)
            counter = 1;
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
       _postParam  = param;
        for(Integer cishu = 0; cishu < counter ;cishu ++ ){
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    String    res =   sendPost(_url,_postParam);
                }
            });
            //Log 4  记录  返回  结果和时间
        }
    }catch (Exception e) {}
    }
    //批次执行 每次 加 step 个
    public void getMedthodTest(int counter,String param,int step) {

        if (counter <= 0)
            counter = 1;
        if (step <= 1)
            step = 2;
        int processCount = 0;
        while (processCount <= counter) {
            for (Integer cishu = 0; cishu < step%counter; cishu++) {

                String res = sendGet(_url, param);
                //Log 4  记录  返回
                processCount++;
            }
            //模拟批次执行 每次step 个
        }
    }
    public void postMedthodTest(int counter,String param,int step){

        if (counter <= 0)
            counter = 1;
        int processCount = 0;
        while(processCount <=counter  ){
            for(Integer cishu = 0; cishu < step ;cishu ++ ){

                String    res =   sendPost(_url,param);
                //Log 4  记录  返回
                processCount ++ ;
            }
            //模拟批次执行 每次step 个
        }
    }
    public static  int getHttpStatusCode(String url,String param){
        int ok = -1;
        try {
            URL u = new URL(url+"?"+param);
            try {
                HttpURLConnection uConnection = (HttpURLConnection) u.openConnection();
                try {
                    uConnection.connect();
                    ok  =  uConnection.getResponseCode();

                    InputStream is = uConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    while(br.read() != -1){
                        sb.append(br.readLine());
                    }
                    String content = new String(sb);
                    content = new String(content.getBytes("GBK"), "ISO-8859-1");
                   // System.out.println(content);
                    br.close();
                } catch (Exception e) {
                    ok = 0;
                    e.printStackTrace();
                    System.out.println("connect failed");
                }

            } catch (IOException e) {
                ok = -2;
                System.out.println("build failed");
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            ok = -3;
            System.out.println("build url failed");
            e.printStackTrace();
        }
        return  ok ;
    }
    /**
     * 通过HttpURLConnection模拟post表单提交
     *
     * @param path
     * @param params 例如"name=zhangsan&age=21"
     * @return
     * @throws Exception
     */
    public static byte[] sendPostRequestByForm(String path, String params) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");// 提交模式
        // conn.setConnectTimeout(10000);//连接超时 单位毫秒
        // conn.setReadTimeout(2000);//读取超时 单位毫秒
        conn.setDoOutput(true);// 是否输入参数
        byte[] bypes = params.toString().getBytes();
        conn.getOutputStream().write(bypes);// 输入参数
        InputStream inStream=conn.getInputStream();

        return StreamTool.readInputStream(inStream);
    }
    public static  int postHttpStatusCode(String url,String param){
        int ok = -1;
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();

                //响应失败
                if (httpURLConnection.getResponseCode() >= 300) {
                    throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
                }
                ok = httpURLConnection.getResponseCode();
                // 定义BufferedReader输入流来读取URL的响应
               /* in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                System.out.println("发送 POST 返回内容" +result);*/

            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！" + e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            return ok ;
        }
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();


            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
