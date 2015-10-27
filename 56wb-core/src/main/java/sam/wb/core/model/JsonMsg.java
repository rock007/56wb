package sam.wb.core.model;

public class JsonMsg {
	private boolean success;
	private String msg;
	
	private String data;
	
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
	
	public JsonMsg(boolean b,String m){
		
		this.success=b;
		this.msg=m;
	}
	
	public JsonMsg(boolean b,String m,String data){
		
		this.success=b;
		this.msg=m;
		this.data=data;
	}
	
	public JsonMsg(){
		
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
