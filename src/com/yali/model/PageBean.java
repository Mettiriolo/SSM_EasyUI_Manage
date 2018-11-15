package com.yali.model;

public class PageBean {
    private Integer pageSize=3;//每页显示的数目
    private Integer currentPage;//当前页
    private Integer totalPage;//总页数
    private Integer totalNum;//总记录数
    private Integer indexStart;//开始的记录数

    public PageBean() {
    	super();
    }
    
    public PageBean(Integer totalNum,Integer  currentPage){
        this.totalNum=totalNum;
        this.currentPage=currentPage;
        Caculate();
    }

	//计算参数的方法
    public void Caculate(){
    //总页数
        totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum%pageSize+1;
    //开始的记录数
        if(currentPage<=1){
            currentPage=1;
        }
        if(currentPage>=totalPage){
            currentPage=totalPage;
        }
        indexStart=(currentPage-1)*pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getIndexStart() {
        return indexStart;
    }

    public void setIndexStart(Integer indexStart) {
        this.indexStart = indexStart;
    }
}
