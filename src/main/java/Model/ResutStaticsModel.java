package Model;

import java.util.List;

public class ResutStaticsModel {

    /**
     * 1.mark 备注信息
     * */
    private  Object mark ;

    public ResutStaticsModel(){

        this.mark = "";
        this.suce = -1;
        this.fail = -1;
        this.all = -1;
        this.batchId = "";
    }
    public Object getMark() { //mark的可读属性
        return mark;
    }
    public void setMark(String mark) { //mark的可写属性
        this.mark = mark;
    }

    private Integer suce;

    public Integer getSuce() { //suce的可读属性
        return suce;
    }
    public void setSuce(Integer suce) { //suce的可写属性
        this.suce = suce;
    }

    private Integer fail;

    public Integer getFail() { //fail的可读属性
        return fail;
    }
    public void setFail(Integer fail) { //fail的可写属性
        this.fail = fail;
    }
    private Integer all;

    public Integer getAll() { //all的可读属性
        return all;
    }
    public void setAll(Integer all) { //all的可写属性
        this.all = all;
    }

    private  String  batchId ;

    public  String getBatchId() { //all的可读属性
        return batchId;
    }

    public void setBatchId(String batchId) { //all的可写属性
        this.batchId = batchId;
    }

    public List<ResutStaticsModel>  subList ;

    public List<ResutStaticsModel> getSubList(){
        return subList;
    }
    public void setSubList(List<ResutStaticsModel> inputList) {
       this.subList = inputList;
    }

    public  String getStatisMsg()
    {
        String str = "批次 %1$s  ,总共 %2$s 个,成功 %3$s,失败 %4$s,备注 %5$s";
        if(this.subList !=null && !this.subList.isEmpty()){
            for (ResutStaticsModel model : subList) {

                str += String.format(str,model.batchId,model.all,model.suce,model.fail, model.mark  );
            }
        }
        return String.format(str,this.batchId,this.all,this.suce,this.fail, this.mark  );
    }
}
