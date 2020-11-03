package id.aldente.socket.util;

public class BaseResponse<T> {

	/*private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}*/

	private T data;

	private boolean success;

	private String message;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
