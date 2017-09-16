package com.mfg.result;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SpringDataPageable implements Serializable, Pageable {  
    // ��ǰҳ  
    private Integer pagenumber = 1;  
    // ��ǰҳ������  
    private Integer pagesize = 10;  
    //��������  
    private Sort sort;  
      
    public void setSort(Sort sort) {  
        this.sort = sort;  
    }  
    // ��ǰҳ��  
      
    public int getPageNumber() {  
        return getPagenumber();  
    }  
    // ÿһҳ��ʾ������  
      
    public int getPageSize() {  
        return getPagesize();  
    }  
    // �ڶ�ҳ����Ҫ���ӵ�����  
      
    public int getOffset() {  
        return (getPagenumber() - 1) * getPagesize();  
    }  
      
    public Sort getSort() {  
        return sort;  
    }  
    public Integer getPagenumber() {  
        return pagenumber;  
    }  
    public void setPagenumber(Integer pagenumber) {  
        this.pagenumber = pagenumber;  
    }  
    public Integer getPagesize() {  
        return pagesize;  
    }  
    public void setPagesize(Integer pagesize) {  
        this.pagesize = pagesize;  
    }
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}  
}  