package webUITestTool;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class seleniumSafari {
    private WebDriver _driver;

    public WebDriver getDriver() {

        WebDriver driver = (WebDriver) SafariDriverService.createDefaultService();

        return driver;
    }

    public seleniumSafari() {

        _driver = new SafariDriver();
    }

    public WebDriver currentWebDriver() {
        return _driver;
    }

    /**
     * get html WebElement get 控件 标签
     */
    public WebElement getHtmlByIdOrName(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();
        if (pageSource.contains("name=" + input))
            return _driver.findElement(By.name(input));
        else return _driver.findElement(By.id(input));
    }

    /**
     * get html CssSeletor  控件 标签
     */
    public WebElement getHtmlByCssSeletor(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();
        return _driver.findElement(By.cssSelector(input));
    }

    /**
     * get html CssSeletor  多个控件 需要一起获得
     */
    public List<WebElement> getHtmlSByCssSeletor(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();
        return _driver.findElements(By.cssSelector(input));
    }

    /**
     * get html CssSeletor  获取当前路径
     */
    public String getUrlPath() {
        String pageSource = _driver.getPageSource().toLowerCase();
        return _driver.getCurrentUrl();
    }

    /*
    *可以检查跳转目标是否正确 base check 只是检查 目标是否正确
    *
     */
    public Boolean checkUrlIsOk(String targeturl) {
        String pageSource = _driver.getPageSource().toLowerCase();
        String checkUrl = targeturl.toLowerCase();
        return _driver.getCurrentUrl().contains(checkUrl);
    }

    /**
     * 1. 查找文本 ，判断url
     */
    public Boolean checkUrlIsOk(String targeturl, String findOkStr) {
        String pageSource = _driver.getPageSource().toLowerCase();
        String checkUrl = targeturl.toLowerCase();
        Boolean isurl = _driver.getCurrentUrl().contains(checkUrl);
        return isurl && pageSource.contains(findOkStr);
    }


    /**
     * 1. link text 查找待链接的 HTMl
     */
    public WebElement getHtmlByLinkText(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();
        return _driver.findElement(By.linkText(input));
    }

    /*
    *1.  partialLinkText 查找待链接的 HTMl
    */
    public WebElement getHtmlBypartialLinkText(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();
        return _driver.findElement(By.partialLinkText(input));
    }

    /*
    *1.  进行模糊查找
    */
    public WebElement getHtmlMohu(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();

        if (!pageSource.contains(input))
            return null;
        if (pageSource.contains("name=" + input))
            return _driver.findElement(By.name(input));
        if (pageSource.contains("id=" + input))
            return _driver.findElement(By.id(input));
        if (pageSource.contains("classname=" + input))
            return _driver.findElement(By.className(input));
        return _driver.findElement(By.partialLinkText(input));
    }


    /***
     * 1. type =1 接受，
     * 2. type =2 取消
     *
     */
    public void AlertDone(Integer type) {

        Alert a = _driver.switchTo().alert();
        if (type == 1)
            a.accept();
        else
            a.dismiss();
    }

    /**
     * classname
     */
    public WebElement getHtmlByclassName(String input) {

        return _driver.findElement(By.className(input));
    }

    public WebDriver doJsCode(String input) {
        //"alert(\"hello,this is a alert!\")”;
        // 执行js脚本
        return (WebDriver) ((JavascriptExecutor) _driver).executeScript(input);
    }

    /**
     * 1.“arguments[0].click();”, driver.findElement(By.id(“su”)
     */
    public WebDriver doJsCodeGaoji(String jsCode, String param) {
        //"alert(\"hello,this is a alert!\")”;
        // ((JavascriptExecutor) driver).executeScript(String js, Object args);
        //此方法有两个参数，第一个是js脚本，至于js脚本你像写点击的或者输入的脚本都可以，我们这里举例为点击操作。第二个参数是：要点击的元素。
        // 比如我要点击百度搜索的搜索按钮，可以这样写：
        //((JavascriptExecutor) driver).executeScript(“arguments[0].click();”, driver.findElement(By.id(“su”)));
        return (WebDriver) ((JavascriptExecutor) _driver).executeScript(jsCode, param);
    }

    public WebElement getHtmlByTag(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();

        if (!pageSource.contains(input))
            return null;
        return _driver.findElement(By.tagName(input));
    }

    public List<WebElement> getHtmlsByTag(String input) {
        String pageSource = _driver.getPageSource().toLowerCase();

        if (!pageSource.contains(input))
            return null;
        return _driver.findElements(By.tagName(input));
    }

    /*
   *1.  进行模糊查找
   */
    public WebElement getHtmlMohu(String tagName, String atrrName) {
        String pageSource = _driver.getPageSource().toLowerCase();

        if (!pageSource.contains(tagName) || !pageSource.contains(atrrName))
            return null;
        if (pageSource.contains("name=" + atrrName))
            return _driver.findElement(By.name(atrrName));
        if (pageSource.contains("id=" + atrrName))
            return _driver.findElement(By.id(atrrName));
        if (pageSource.contains("classname=" + atrrName))
            return _driver.findElement(By.className(atrrName));
        return _driver.findElement(By.partialLinkText(atrrName));
    }

    /*
       *1.  进行模糊查找
       */
    public List<WebElement> getHtmlsMohu(String tagName, String atrrName) {
        String pageSource = _driver.getPageSource().toLowerCase();

        if (!pageSource.contains(tagName) || !pageSource.contains(atrrName))
            return null;
        if (pageSource.contains("name=" + atrrName))
            return _driver.findElements(By.name(atrrName));
        if (pageSource.contains("id=" + atrrName))
            return _driver.findElements(By.id(atrrName));
        if (pageSource.contains("classname=" + atrrName))
            return _driver.findElements(By.className(atrrName));
        return _driver.findElements(By.partialLinkText(atrrName));
    }

    public List<WebElement> getSelectOptionsCtrl(String atrrName) {
        String pageSource = _driver.getPageSource().toLowerCase();
        Select select = null;
        // 找到元素
        if (!pageSource.contains(atrrName))
            return null;
        if (pageSource.contains("name=" + atrrName))
            select = new Select(_driver.findElement(By.name(atrrName)));
        if (pageSource.contains("id=" + atrrName))
            select = new Select(_driver.findElement(By.id(atrrName)));
        if (pageSource.contains("classname=" + atrrName))
            select = new Select(_driver.findElement(By.className(atrrName)));
        else
            select = new Select(_driver.findElement(By.partialLinkText(atrrName)));

        // 获取所有的选项
        List<WebElement> options = select.getOptions();
        return options;
    }

    public List<WebElement> getHtmlCtrlsXpath(String xpath) {
        return _driver.findElements(By.xpath(xpath));
    }

    public WebElement getHtmlCtrlXpath(String xpath) {
        return _driver.findElement(By.xpath(xpath));
    }

    //页面等待
    public void pageDengDai() {

        _driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void closePage() {
        _driver.close();
        System.gc();
        //so do log
    }

    public void quitBower() {
        _driver.quit();
        System.gc();
        //so do log
    }

    public void reStartBower() {
        _driver.quit();
        System.gc();
        //so do log
        _driver = _driver = new SafariDriver();
    }

    public void closePage(WebDriver driver) {
        driver.close();
        System.gc();
        //so do log
    }

    public void gotoIframe(String frameName) {
        _driver.switchTo().frame(frameName);
    }

    public void gotoIfarme() {
        _driver.switchTo().defaultContent();
        ;
    }
}
