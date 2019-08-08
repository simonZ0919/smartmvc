package base.common;

import java.lang.reflect.Method;

// wrapper the class for method reflect  
public class Handler {
	private Method method;
	private Object object;
	
	public Handler(Method method, Object object) {
		this.method = method;
		this.object = object;
	}
	
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

	
	
	
}
