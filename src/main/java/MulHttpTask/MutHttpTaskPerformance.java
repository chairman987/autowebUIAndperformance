package MulHttpTask;

import Model.Task;
import Performance.HttpPerformance;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashSet;
import java.util.Hashtable;

public class MutHttpTaskPerformance implements IMutHttpTaskTest {


    private volatile HttpPerformance _httpTool = null;
    private volatile String _httpMehtodGet = "get";
    private volatile String _httpMehtodPost = "post";
    private static Hashtable<String, String> returnSet = new Hashtable<String, String>();

    //TODO 结果以后可以丰富
    /*
    * 返回结果
    * */
    public Hashtable<String, String> getReturnSet() {
        return this.returnSet;
    }

    /*
     * 加载脚本引擎，并在java中调用js方法
     */
    public static String evalJsResult(String jscode, String param) {
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine = manager.getEngineByName("javascript");
        String res = "";
        try {
            String str = " var  param = '" + param + "' ; " + jscode;
            res = String.valueOf(engine.eval(str));
            System.out.println(res);
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * 1. 执行步骤任务
     */
    public void excuteTask(Task task) {

        String result = "";
        if (task != null) {
            if (task.getMethod().toLowerCase() == _httpMehtodGet) {
                result = HttpPerformance.getHttpResponseStr(task.getUrl(), task.getParam());
            }
            if (task.getMethod().toLowerCase() == _httpMehtodPost) {
                result = HttpPerformance.postHttpResponseStr(task.getUrl(), task.getParam());
            }
            if (!this.returnSet.containsKey(task.getKey())) {
                this.returnSet.put(task.getKey(), result);
            }
            if (task.getTask() != null) {
                Task subTask = task.getTask();
                String subParam = evalJsResult(task.getJsCode(), result); //动态参数  ,以后可以用lampda 实现
                subTask.setParam(subParam);//向后传递参数
                excuteTask(subTask);
            }
        }

    }
}
