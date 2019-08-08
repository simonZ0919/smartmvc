package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class TestCase {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException  {
		Scanner scanner=new Scanner(System.in);
		String className=scanner.nextLine();
		// load class object
		Class clazz=Class.forName(className);
		// instance class
		Object object=clazz.getDeclaredConstructor().newInstance();
		// get method object
		Method[] methods=clazz.getDeclaredMethods();
		Object result=null;
		for (Method method : methods) {
			//get parameter types of method
			Class[] types=method.getParameterTypes();
			Object[] params=new Object[types.length];
			String methodName=method.getName();
			
			// if method has parameters, start with metho
			if(types.length>0) {
				// assign params for different types
				for (int i = 0; i < types.length; i++) {
					if(types[i]==String.class) {
						params[i]="Mike";
					}
					if(types[i]==int.class) {
						params[i]=10;
					}
				}
				// execute method with @Test
				Test test=method.getDeclaredAnnotation(Test.class);
				// get attribute of @Test
				if ("abc".equals(test.value())) {				
					result=method.invoke(object, params);		
				}
			}else {// no parameter
				result=method.invoke(object);
			}
			
			System.out.println(method.getName()+": "+result);
		}
	}

}
