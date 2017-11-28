package org.yanhuang.springcloud.rest.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ObjectUtils {
	
	/**
	 * wrapper Exception to RuntimeException
	 * 
	 * @param e
	 */
	public static RuntimeException runtimeException(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException(e);
	}

	public static Field[] getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		Class<?> cls = clazz;
		while (cls != null) {
			Field[] fs = cls.getDeclaredFields();
			if (fs != null) {
				fields.addAll(0, Arrays.asList(fs));
			}
			cls = cls.getSuperclass();
		}
		return fields.toArray(new Field[fields.size()]);
	}

	public static Method[] getAllMethods(Class<?> clazz) {
		List<Method> methods = new ArrayList<Method>();
		Class<?> cls = clazz;
		while (cls != null) {
			Method[] ms = cls.getDeclaredMethods();
			if (ms != null) {
				methods.addAll(0, Arrays.asList(ms));
			}
			cls = cls.getSuperclass();
		}
		return methods.toArray(new Method[methods.size()]);
	}

	public static void setField(Object bean, String field, Object value) {
		Class<?> cls = bean.getClass();
		Method m = null;
		String mname = "set" + Character.toUpperCase(field.charAt(0)) + field.substring(1);
		while (m == null && !Object.class.equals(cls)) {
			Method[] ms = cls.getDeclaredMethods();
			for (Method mm : ms) {
				if (mm.getName().equals(mname) && mm.getParameterCount() == 1) {
					m = mm;
					break;
				}
			}
			cls = cls.getSuperclass();
		}
		if (m != null) {
			try {
				m.setAccessible(true);
				m.invoke(bean, value);
			} catch (Exception e) {
				throw runtimeException(e);
			}
			return;
		}
		cls = bean.getClass();
		Field f = null;
		while (f == null && !Object.class.equals(cls)) {
			try {
				f = cls.getDeclaredField(field);
			} catch (Exception e) {
				f = null;
			}
			cls = cls.getSuperclass();
		}
		try {
			if (f == null) {
				throw new NoSuchFieldException(field);
			}
			f.setAccessible(true);
			f.set(bean, value);
		} catch (Exception e) {
			throw runtimeException(e);
		}
	}

	public static <T extends Enum<T>> T valueOfIngoreCase(T[] enumValues, String name) {
		if (name == null) {
			throw new NullPointerException("Name is null");
		}
		for (T t : enumValues) {
			if (name.equalsIgnoreCase(t.name())) {
				return t;
			}
		}
		throw new IllegalArgumentException("No enum constant " + name);
	}
	
	public static <P> P getField(Object o, Class<P> type, String propertyName) {
		return getField(o.getClass(), propertyName, type, o);
	}

	public static <P> P getField(Class<?> clazz, String propertyName, Class<P> type, Object o) {
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(propertyName);
				field.setAccessible(true);
				return (P) field.get(o);
			} catch (Exception e) {
			}
			clazz = clazz.getSuperclass();
		}
		throw new RuntimeException(o.getClass().getName() + " not found the property: " + propertyName);
	}

	public static void setField(String propertyName, Object property, Object o) {
		setField(o.getClass(), propertyName, property, o);
	}

	public static void setField(Class<?> clazz, String propertyName, Object property, Object o) {
		try {
			Field field = clazz.getDeclaredField(propertyName);
			field.setAccessible(true);
			field.set(o, property);
		} catch (Exception e) {
			throw runtimeException(e);
		}
	}

	public static Object invoke(String methodName, Class<?>[] paramTypes, Object[] params, Object o) {
		return invoke(o.getClass(), methodName, paramTypes, params, o);
	}

	public static Object invoke(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object[] params, Object o) {
		try {
			Method method = clazz.getDeclaredMethod(methodName, paramTypes);
			method.setAccessible(true);
			return method.invoke(o, params);
		} catch (Exception e) {
			throw runtimeException(e);
		}
	}
}
