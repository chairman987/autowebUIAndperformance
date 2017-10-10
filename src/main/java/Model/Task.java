package Model;

public class Task {

    private String key;

    private String url;

    private String param;

    private String method = "get";

    private String getResult;

    private Task next;


    private String jsCode;


    //是否向后专递参数
    private boolean through;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return getResult;
    }

    public void setResult(String getResult) {
        this.getResult = getResult;
    }

    public Task getTask() {
        return next;
    }

    public void setTask(Task next) {
        this.next = next;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }
}
