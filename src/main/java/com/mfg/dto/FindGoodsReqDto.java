package com.mfg.dto;

import java.io.Serializable;

public class FindGoodsReqDto implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int page;
  private int size;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
