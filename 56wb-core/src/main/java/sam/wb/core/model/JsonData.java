package sam.wb.core.model;

import java.util.List;

public class JsonData<T> {

	private Long count;
	private List<T> rows;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	public JsonData (long num,List<T> list){
		
		this.count=num;
		this.rows=list;
	}
	
	public JsonData(){
		
	}
}
