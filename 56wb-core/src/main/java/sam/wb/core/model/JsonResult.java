package sam.wb.core.model;

public class JsonResult<T> {
	
	private boolean success;
	private String msg;
	
	private T data;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public JsonResult(boolean b,String m){
		
		this.success=b;
		this.msg=m;
	}
	
	public JsonResult(boolean b,String m,T data){
		
		this.success=b;
		this.msg=m;
		this.data=data;
	}
	
	public JsonResult(){
		
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
