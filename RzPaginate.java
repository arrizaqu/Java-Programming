package com.soft.arrizaqu.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RzPaginate<T>{

	private int startSize;
	private int pageSize;
	private int totalPage;
	private int currentPage;
	private long totalCount;
	private int firstPage = 1;
	private int prevLink;
	private int nextLink;
	private long lastPage;
	private List<T> dataPopulate;
	private List<String> linkPages = new ArrayList();
	private String completeLinkePage;
	private String baseUrl;
	
	public RzPaginate(){}

	public RzPaginate(int startSize, String baseUrl, int lastPage, int totalPage, int currentPage, int pageSize, long totalCount, List<T> dataPopulate, List<String> linkPages) {
		super();
		this.totalPage = totalPage;
		this.startSize = startSize;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.dataPopulate = dataPopulate;
		this.lastPage = lastPage;
		this.linkPages = linkPages;
		this.baseUrl = baseUrl;
	}
	
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public int getFirstPage() {
		return firstPage;
	}
	
	public List<String> getLinkPages() {
		return linkPages;
	}
	
	public void setLinkPages(List<String> linkPages) {
		this.linkPages = linkPages;
	}
	
	public void setLastPage(long lastPage) {
		this.lastPage = lastPage;
	}
	
	public long getLastPage() {
		return lastPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalPage() {
		this.totalPage = (int) Math.ceil(getTotalCount() / getPageSize());
		return totalPage;
	}
	

	public void setStartSize(int startSize) {
		this.startSize = startSize;
	}
	
	public int getStartSize() {
		return startSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getDataPopulate() {
		return dataPopulate;
	}

	public void setDataPopulate(List<T> dataPopulate) {
		this.dataPopulate = dataPopulate;
	}

	public void setLinkPages() {
		// TODO Auto-generated method stub
		int index = 0;
		int startPaging = 1;
		int longPaging = 5;
		
		int lastPaging = getTotalPage();
		int condFirst = getCurrentPage() - 5;
		int endPaging = getCurrentPage() + 5;
		
		int nextPage = getCurrentPage() + 1;
		int beforePage = getCurrentPage() - 1;
	
		// for over next
		
		//for over from 1
		if(condFirst <= 1){
			startPaging = 1;
		} else {
			startPaging = condFirst;
		}
		
		//for over to the max totalpage
		if(endPaging >= getTotalPage()){
			lastPaging = getTotalPage();
		} else {
			lastPaging = endPaging;
		}
		
		if( beforePage >= 1){
			linkPages.add("<li class='page-item'><a href='"+getBaseUrl()+"/"+beforePage+"' class='page-link' >Before page</a></li>");
		} 
		
		for(int i = startPaging; i <= lastPaging; i++){		
			String active = "";

			if(i == currentPage){
				active = "active";
			}
			
			/*if(i == this.getFirstPage()){
				linkPages.add("<li class='page-item "+active+"'><a href='"+url+"/1' class='page-link' >Fist Page</a></li>");
			}
			else if(i == totalPage)
				linkPages.add("<li class='page-item "+active+"'><a href='"+url+"/"+i+"' class='page-link' >Last Page</a></li>");
			else {*/
				linkPages.add("<li class='page-item "+active+"'><a href='"+getBaseUrl()+"/"+i+"' class='page-link' >"+i+"</a></li>");
			//} 
			index++;
		}
		
		if( nextPage <= totalPage){
			linkPages.add("<li class='page-item'><a href='"+getBaseUrl()+"/"+nextPage+"' class='page-link' >Next Page</a></li>");
		} 
	}
	
	public String getCompleteLinkePage() {
		completeLinkePage = "<ul class='pagination'>";
		for(String link : linkPages){
			completeLinkePage += link;
		}
		completeLinkePage += "</ul>";
		
		return completeLinkePage;
	}
}
