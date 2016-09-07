package com.mfg.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class GoodPageable implements Pageable {

	private Integer pageNumber;
	private Integer pageSize;
	private Sort sort;
	
	public GoodPageable(Integer pageNumber, Integer pageSize, Sort sort) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sort = sort;
	}
	
	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public int getOffset() {
		return pageNumber * pageSize;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}
}
