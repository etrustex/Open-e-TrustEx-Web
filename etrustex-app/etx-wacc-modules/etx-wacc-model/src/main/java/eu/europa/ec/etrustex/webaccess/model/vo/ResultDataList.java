package eu.europa.ec.etrustex.webaccess.model.vo;

import java.util.List;

public class ResultDataList<T> {

	private List<T> data;
	
	private long totalRowCount;
	
	public ResultDataList(List<T> data, long count){
		this.data = data;
		this.totalRowCount = count;
	}

	public List<T> getData() {
		return data;
	}

	public long getTotalRowCount() {
		return totalRowCount;
	}
	
	
}
