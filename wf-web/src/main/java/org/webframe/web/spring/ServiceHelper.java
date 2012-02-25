
package org.webframe.web.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.webframe.support.util.ApplicationContextSupport;

/**
 * 提供根据spring注入bean的name，获取该bean对象
 * 
 * @author <a href="mailto:guoqing.huang@foxmail.com">黄国庆</a>
 * @version $Id: ServiceHelper.java,v 1.1.2.1.8.2 2010/08/06 08:46:31 huangguoqing Exp $ Create:
 *          2008-10-17 下午05:30:24
 */
public abstract class ServiceHelper extends ApplicationContextSupport {

	private static GenericApplicationContext	gtx	= null;

	/**
	 * 默认包访问，控制其他对象初始化上下文
	 * 
	 * @param context
	 * @author: 黄国庆 2010-12-20 下午01:23:23
	 */
	static void init(ApplicationContext context) {
		initAc(context);
	}

	public static ApplicationContext getApplicationContext() {
		return getAc();
	}

	/**
	 * @param serviceName
	 * @return
	 * @function: 获得spring 中注入的SERVICE
	 * @author: 黄国庆 2008-10-17 下午05:35:28
	 */
	public static Object getService(String serviceName) {
		return getAc().getBean(serviceName);
	}

	public static Object createBean(String beanName, String parentBeanName) {
		initGenericApplicationContext();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
		beanDefinitionBuilder.setParentName(parentBeanName);
		gtx.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
		return gtx.getBean(beanName);
	}

	public static <X> X createBean(String beanName, Class<X> clazz) {
		initGenericApplicationContext();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		gtx.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
		return gtx.getBean(beanName, clazz);
	}

	private static void initGenericApplicationContext() {
		if (gtx == null) {
			gtx = new GenericApplicationContext();
			gtx.setParent(getAc());
		}
	}
}
