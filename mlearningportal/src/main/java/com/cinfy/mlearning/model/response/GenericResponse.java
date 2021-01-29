package com.cinfy.mlearning.model.response;

public class GenericResponse extends ResponseCommon{

	private Object data;
	
	private Object error;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "[data]";
	}
	
	
}
