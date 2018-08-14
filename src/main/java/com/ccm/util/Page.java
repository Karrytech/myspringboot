package com.ccm.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Page implements Serializable
{
	private static final long serialVersionUID = -3017723240787536531L;

	private static int DEFAULT_PAGE_SIZE = 20;

	private int pageSize = DEFAULT_PAGE_SIZE; 

	private long start; 

	private List<Object> data; 

	private long totalCount; 

	
	public Page()
	{
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	
	public Page(long start, long totalSize, int pageSize, List data)
	{
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}
	
	public Page(long start, long totalSize, int pageSize, List data,String flag)
	{
		this.data = data;
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = getResultsByDataMode();
	}

	
	public long getTotalCount()
	{
		return this.totalCount;
	}

	
	public long getTotalPageCount()
	{
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	
	public int getPageSize()
	{
		return pageSize;
	}

	
	public List getResult()
	{
		return data;
	}

	
	public long getCurrentPageNo()
	{
		//return start / pageSize + 1;
		return start;
	}

	
	public boolean isHasNextPage()
	{
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	
	public boolean isHasPreviousPage()
	{
		return this.getCurrentPageNo() > 1;
	}

	protected static int getStartOfPage(int pageNo)
	{
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	public static int getStartOfPage(int pageNo, int pageSize)
	{
		return (pageNo - 1) * pageSize;
	}
	private List<Object> getResultsByDataMode() {
		if (this.data == null) {
			this.start = 1;
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> source = (List<Object>) this.data;
		this.totalCount = source.size();
		resetErrorPage();
		int first = getStartOfPage2(start, pageSize);
		int max = this.pageSize;
		int count = 0;
		for (int i = first; i < getTotalCount(); i++) {
			count++;
			list.add(source.get(i));
			if (count == max) {
				break;
			}

		}
		if (list != null) {

			return list;
		}

		return new ArrayList<Object>();
	}
	private void resetErrorPage() {
		if (this.start > getTotalPageCount()) {
			this.start = (int) getTotalPageCount();
		}
		if (this.start < 1 ) {
			this.start = 1;
		}
	}
	public int getStartOfPage2(long pageNo, int pageSize) {
		return Integer.valueOf((pageNo - 1l) * pageSize+"");
	}

}