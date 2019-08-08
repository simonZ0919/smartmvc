package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.annotation.RequestMapping;

/**
 * map request path with controller
 * @author simon
 *
 */
public class HandlerMapping {
	// key: request path, value: wrapper class of controller and method
	private Map<String, Handler> handlerMap=new HashMap<String, Handler>();
	
	public Handler getHandler(String path) {
		// return handller object by path
		return handlerMap.get(path);
	}
	
	// get path from annotation, store key-value pair to handlerMap
	public void process(List beans) {
		for (Object bean : beans) {
			// get class object
			Class clazz=bean.getClass();
			// get list of method
			Method[] methods=clazz.getDeclaredMethods();
			for (Method method : methods) {
				// get annotation 
				RequestMapping rm=method.
						getDeclaredAnnotation(RequestMapping.class);
				// request path
				String path=rm.value();
				// put key-value into map
				handlerMap.put(path, new Handler(method, bean));
			}
		}
		System.out.println(handlerMap);
	}
}