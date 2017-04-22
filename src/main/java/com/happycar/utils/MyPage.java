package com.happycar.utils;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class MyPage<T> implements Page<T> {
	
	private int number;
	private int numberOfElements;
	private int size;
	private Sort sort;
	private long totalElements;
	private int totalPages;
	private List<T> content;
	private boolean hasContent;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private boolean isFirstPage;
	private boolean isLastPage;
	
	public MyPage(Page<T> page) {
		this.number = page.getNumber();
		this.numberOfElements = page.getNumberOfElements();
		this.size = page.getSize();
		this.sort = page.getSort();
		this.totalElements = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.content = page.getContent();
		this.hasContent = page.hasContent();
		this.hasNextPage = page.hasNextPage();
		this.hasPreviousPage = page.hasPreviousPage();
		this.isFirstPage = page.isFirstPage();
		this.isLastPage = page.isLastPage();
	}

	public MyPage(int number, int numberOfElements, int size, Sort sort,
			long totalElements, int totalPages, List<T> content,
			boolean hasContent, boolean hasNextPage, boolean hasPreviousPage,
			boolean isFirstPage, boolean isLastPage) {
		super();
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.sort = sort;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.content = content;
		this.hasContent = hasContent;
		this.hasNextPage = hasNextPage;
		this.hasPreviousPage = hasPreviousPage;
		this.isFirstPage = isFirstPage;
		this.isLastPage = isLastPage;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public boolean isHasContent() {
		return hasContent;
	}
	public void setHasContent(boolean hasContent) {
		this.hasContent = hasContent;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean hasNextPage() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean hasPreviousPage() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
