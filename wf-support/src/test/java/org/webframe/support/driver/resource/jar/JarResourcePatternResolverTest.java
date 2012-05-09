/*
 * wf-support
 * Created on 2011-5-5-下午06:31:53
 */

package org.webframe.support.driver.resource.jar;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.webframe.support.driver.resource.jar.JarResourceLoader.JarResource;
import org.webframe.support.util.SystemLogUtils;

/**
 * @author <a href="mailto:guoqing.huang@foxmail.com">黄国庆 </a>
 * @version $Id: codetemplates.xml,v 1.1 2009/09/07 08:48:12 Exp $ Create: 2011-5-5 下午06:31:53
 */
public class JarResourcePatternResolverTest {

	/**
	 * Test method for
	 * {@link org.webframe.support.driver.resource.jar.JarResourcePatternResolver#getResources(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetResourcesString() {
		try {
			JarResourcePatternResolver resolver = JarResourcePatternResolver.getInstance(getClass());
			Resource resource = resolver.getResource("/modulePlugin.properties");
			Assert.assertTrue("modulePlugin.properties文件Resource不为FileSystemResource!",
				resource instanceof FileSystemResource);
			resolver = new JarResourcePatternResolver(Test.class);
			resource = resolver.getResource("/LICENSE.txt");
			Assert.assertTrue("/LICENSE.txt文件应该存在!", resource.exists());
			Assert.assertTrue("/LICENSE.txt文件Resource应该为JarResource!", resource instanceof JarResource);
			Resource[] resources = resolver.getResources("/org/junit/*.class");
			Assert.assertEquals("/org/junit/包下，应该只有12个类文件！", 12, resources.length);
			resolver = new JarResourcePatternResolver(ClassUtils.class);
			resources = resolver.getResources("/org/springframework/util/**/*.class");
			Assert.assertEquals("/org/springframework/util中class数目不正确！", 92, resources.length);
			SystemLogUtils.println("/org/springframework/util包中共(" + resources.length + ")class");
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
}
