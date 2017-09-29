package HttpTool;

import java.net.HttpURLConnection;
import java.net.URLConnection;

public class HttpModify {

    //此处增加浏览器端访问IP ,仿冒ip
    public HttpURLConnection ModifyIp(HttpURLConnection http, String ip){
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        if(!ip.equals("")){
            http.setRequestProperty("x-forwarded-for",ip);
         }
        return http;
    }
}
