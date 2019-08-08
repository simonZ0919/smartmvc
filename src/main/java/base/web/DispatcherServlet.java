package base.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import base.common.Handler;
import base.common.HandlerMapping;
// configure servlet
@WebServlet(urlPatterns = {"*.do"},
			loadOnStartup = 1,
			initParams = {
				@WebInitParam(name = "configLocation",value = "smartmvc.xml")})

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	// read smartmvc.xml, create controller instance, HandlerMapping
	@Override
	public void init() throws ServletException {
		/**
		 * parse xml file
		 */
		// read filename from servletconfig
		String fileName=getServletConfig().getInitParameter("configLocation");
		SAXReader reader=new SAXReader();
		// get inputstream
		InputStream in=getClass().getClassLoader().
				getResourceAsStream(fileName);
		try {
			Document document=reader.read(in);
			Element root=document.getRootElement();// get root
			List<Element> elements=root.elements();// list of element
			
			// store controller object in list
			List beans=new ArrayList();
			for (Element element : elements) {
				// get attribute
				String className=element.attributeValue("class");
				Object object=Class.forName(className).newInstance();
				beans.add(object);// add to list
			}

			// map request path to controller method
			handlerMapping=new HandlerMapping();
			handlerMapping.process(beans);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uri=request.getRequestURI();
		// get application name
		String contextPath=request.getContextPath();
		// extract relative path
		String path=uri.substring(contextPath.length());

		// get handler from map
		Handler handler=handlerMapping.getHandler(path);
		Object returnVal=null;
		try {// invoke method
			returnVal=handler.getMethod().invoke(handler.getObject());
			String viewName=returnVal.toString();
			// redirect
			if(viewName.startsWith("redirect:")){
				String redirecPath=contextPath+"/"+
						viewName.substring("redirect:".length());
				response.sendRedirect(redirecPath);
				System.out.println(redirecPath);
			}else {
				// forward to *.jsp
				String jspPath="/WEB-INF/"+viewName+".jsp";
				request.getRequestDispatcher(jspPath).
					forward(request, response);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} 
		
	}

}
