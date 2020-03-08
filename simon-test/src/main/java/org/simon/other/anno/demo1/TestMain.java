package org.simon.other.anno.demo1;

import org.junit.Test;
import org.simon.mybatis.Sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-18 21:12
 */
public class TestMain {
	public static void main(String[] args) {

		Class<Sub> clazz = Sub.class;

		System.out.println("============================Field===========================");
		System.out.println(Arrays.toString(clazz.getFields()));
		System.out.println(Arrays.toString(clazz.getDeclaredFields()));  //all + 自身
		System.out.println("============================Method===========================");
		System.out.println(Arrays.toString(clazz.getMethods()));   //public + 继承
		//all + 自身
		System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
		System.out.println("============================Constructor===========================");
		System.out.println(Arrays.toString(clazz.getConstructors()));
		System.out.println(Arrays.toString(clazz.getDeclaredConstructors()));
		System.out.println("============================AnnotatedElement===========================");
		//注解DBTable2是否存在于元素上
		System.out.println(clazz.isAnnotationPresent(BTable.class));
		//如果存在该元素的指定类型的注释DBTable2，则返回这些注释，否则返回 null。
		System.out.println(clazz.getAnnotation(BTable.class));
		//继承
		System.out.println(Arrays.toString(clazz.getAnnotations()));
		System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));  ////自身
	}

	@Test
	public void t1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class clazz = Super.class;
		Method method = clazz.getMethod("funa", int.class, int.class);
		System.out.println(method.isAnnotationPresent(Sql.class));

		method.invoke(new Sub(10), 10, 20);
	}
}
