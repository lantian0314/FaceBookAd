package com.example.nativeads;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * @author peidw
 * 
 */

public class MyReflection {
	/**
	 * 实例化有参构造
	 * 
	 * @param className
	 * @param argsType
	 * @param args
	 * @return
	 */
	public static Object getInstance(Object obj, Class<?>[] argsType,
			Object[] args) {
		try {
			Class<?> classType = obj.getClass();
			Constructor<?> constructor = classType
					.getDeclaredConstructor(argsType);
			constructor.setAccessible(true);//
			return constructor.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实例化有参构造
	 * 
	 * @param className
	 * @param argsType
	 * @param args
	 * @return
	 */
	public static Object getInstance(String className, Class<?>[] argsType,
			Object[] args) {
		try {
			Class<?> classType = Class.forName(className);
			Constructor<?> constructor = classType
					.getDeclaredConstructor(argsType);
			constructor.setAccessible(true);//
			return constructor.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实例化有参构造
	 * 
	 * @param className
	 * @param argsType
	 * @param args
	 * @return
	 */
	public static Object getInstance(Object obj) {
		try {
			Class<?> classType = obj.getClass();
			Constructor<?> constructor = classType.getDeclaredConstructor();
			constructor.setAccessible(true);//
			return constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实例化有参构造
	 * 
	 * @param className
	 * @param argsType
	 * @param args
	 * @return
	 */
	public static Object getInstance(String className) {
		try {
			Class<?> classType = Class.forName(className);
			Constructor<?> constructor = classType.getDeclaredConstructor();
			constructor.setAccessible(true);//
			return constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// /**
	// * 设置类的私有属性值
	// * @param owner
	// * @param fieldName
	// * @return
	// */
	// public static void setPrivateProperty(String className,String
	// fieldName,Object value){
	// try{
	// Class<?> ownerClass = Class.forName(className);
	// Object obj = ownerClass.newInstance();
	// Field field=ownerClass.getDeclaredField(fieldName);
	// field.setAccessible(true);
	// field.set(obj,value);
	// field.setAccessible(false);
	// }catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// /**
	// * 获取类的属性值
	// * @param owner
	// * @param fieldName
	// * @return
	// */
	// public static void setPublicProperty(String className,String
	// fieldName,Object value){
	// try{
	// Class<?> ownerClass = Class.forName(className);
	// Object obj = ownerClass.newInstance();
	// Field field=ownerClass.getField(fieldName);
	// field.setAccessible(true);
	// field.set(obj,value);
	// }catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	/**
	 * 获取一个实现类的接口
	 * 
	 * @param path
	 * @return
	 */
	public static Class<?>[] getAllImpl(String path) {
		Class<?>[] clss = null;
		try {
			Class<?> c = Class.forName(path);
			clss = c.getInterfaces();// 获取类实现的所有接口
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clss;
	}

	/**
	 * 获取类的私有属性值
	 * 
	 * @param owner
	 * @param fieldName
	 * @return
	 */
	public static Object getPrivateProperty(Object obj, String fieldName) {
		try {
			Class<?> ownerClass = obj.getClass();
			Field field = ownerClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			Object property = field.get(obj);
			field.setAccessible(false);
			return property;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取类的私有属性值
	 * 
	 * @param owner
	 * @param fieldName
	 * @return
	 */
	public static Object getPrivateProperty(String className, String fieldName) {
		try {
			Class<?> ownerClass = Class.forName(className);
			Object obj = ownerClass.newInstance();
			Field field = ownerClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			Object property = field.get(obj);
			field.setAccessible(false);
			return property;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取类的公有属性值
	 * 
	 * @param owner
	 * @param fieldName
	 * @return
	 */
	public static Object getPublicProperty(Object obj, String fieldName) {
		try {
			Class<?> ownerClass = obj.getClass();
			Field field = ownerClass.getField(fieldName);
			Object property = field.get(obj);
			return property;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取类的公有属性值
	 * 
	 * @param owner
	 * @param fieldName
	 * @return
	 */
	public static Object getPublicProperty(String className, String fieldName) {
		try {
			Class<?> ownerClass = Class.forName(className);
			Object obj = ownerClass.newInstance();
			Field field = ownerClass.getField(fieldName);
			Object property = field.get(obj);
			return property;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置私有静态属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static void setPrivateStaticProperty(Object obj, String fieldName,
			Object value) {
		try {
			Class<?> ownerClass = obj.getClass();
			Object owner = ownerClass.newInstance();
			Field fd2 = ownerClass.getDeclaredField(fieldName);
			fd2.setAccessible(true);// 设置私有属性可访问
			fd2.set(owner, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setObjProperty(Object obj, String fieldName, Object value) {
		Method method = getSetMethod(obj.getClass(), fieldName);

		try {
			method.invoke(obj, new Object[] { value });
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void setObjProperty(String cls, String fieldName, Object value) {
		try {
			Class<?> classs = Class.forName(cls);
			Object obj = classs.newInstance();
			Method method = getSetMethod(obj.getClass(), fieldName);
			method.invoke(obj, new Object[] { value });
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static Method getSetMethod(Class objectClass, String fieldName) {
		try {
			Class[] parameterTypes = new Class[1];
			Field field = objectClass.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			StringBuffer sb = new StringBuffer();
			sb.append("set");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			Method method = objectClass
					.getMethod(sb.toString(), parameterTypes);
			return method;
		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	/**
	 * 设置私有静态属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static void setPrivateStaticProperty(String className,
			String fieldName, Object value) {
		try {
			Class<?> ownerClass = Class.forName(className);
			Object owner = ownerClass.newInstance();
			Field fd2 = ownerClass.getDeclaredField(fieldName);
			fd2.setAccessible(true);// 设置私有属性可访问
			fd2.set(owner, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取私有静态属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static Object getPrivateStaticProperty(Object clObj, String fieldName) {
		try {
			Class<?> ownerClass = clObj.getClass();
			Object owner = ownerClass.newInstance();
			Field fd2 = ownerClass.getDeclaredField(fieldName);
			fd2.setAccessible(true);// 设置私有属性可访问
			return fd2.get(owner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取私有静态属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static Object getPrivateStaticProperty(String className,
			String fieldName) {
		try {
			Class<?> ownerClass = Class.forName(className);
			Object owner = ownerClass.newInstance();
			Field fd2 = ownerClass.getDeclaredField(fieldName);
			fd2.setAccessible(true);// 设置私有属性可访问
			return fd2.get(owner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置静态公有属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static void setPublicStaticProperty(Object clObj, String fieldName,
			Object value) {
		try {
			Class<?> ownerClass = clObj.getClass();
			Object obj = ownerClass.newInstance();
			Field field = ownerClass.getField(fieldName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置静态公有属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static void setPublicStaticProperty(String className,
			String fieldName, Object value) {
		try {
			Class<?> ownerClass = Class.forName(className);
			Object obj = ownerClass.newInstance();
			Field field = ownerClass.getField(fieldName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取静态公有属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static Object getPublicStaticProperty(Object clObj, String fieldName) {
		try {
			Class<?> ownerClass = clObj.getClass();
			Field field = ownerClass.getField(fieldName);
			Object property = field.get(ownerClass);
			return property;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取静态公有属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public static Object getPublicStaticProperty(String className,
			String fieldName) {
		try {
			Class<?> ownerClass = Class.forName(className);
			Field field = ownerClass.getField(fieldName);
			Object property = field.get(ownerClass);
			return property;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(Object clObj, String methodName,
			Class<?>[] argclass, Object[] args) {
		try {
			Class<?> cls = clObj.getClass();
			if (argclass == null || argclass.length == 0 || args == null
					|| args.length == 0) {
				Method method = cls.getMethod(methodName);
				return method.invoke(null);
			} else {
				Method method = cls.getMethod(methodName, argclass);
				return method.invoke(null, args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(String className,
			String methodName, Class<?>[] argclass, Object[] args) {
		try {
			Class<?> cls = Class.forName(className);
			if (argclass == null || argclass.length == 0 || args == null
					|| args.length == 0) {
				Method method = cls.getMethod(methodName);
				return method.invoke(null);
			} else {
				Method method = cls.getMethod(methodName, argclass);
				return method.invoke(null, args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(Object clObj, String methodName,
			Object[] args) {
		try {
			Class<?> cls = clObj.getClass();
			Class<?>[] argsType = null;
			if (args != null && args.length > 0) {
				argsType = new Class<?>[args.length];
				for (int i = 0; i < args.length; i++) {
					Object obj = args[i];
					argsType[i] = obj.getClass();
				}
				Method method = cls.getMethod(methodName, argsType);
				return method.invoke(null, args);
			} else {
				Method method = cls.getMethod(methodName);
				return method.invoke(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(String className,
			String methodName, Object[] args) {
		try {
			Class<?> cls = Class.forName(className);
			Class<?>[] argsType = null;
			if (args != null && args.length > 0) {
				argsType = new Class<?>[args.length];
				for (int i = 0; i < args.length; i++) {
					Object obj = args[i];
					argsType[i] = obj.getClass();
				}
				Method method = cls.getMethod(methodName, argsType);
				return method.invoke(null, args);
			} else {
				Method method = cls.getMethod(methodName);
				return method.invoke(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Object clObj, String methodName,
			Class<?>[] argclass, Object[] args) {
		try {
			Class<?> cls = clObj.getClass();
			if (argclass == null || argclass.length == 0 || args == null
					|| args.length == 0) {
				Method method = cls.getMethod(methodName);
				return method.invoke(clObj);
			} else {
				Method method = cls.getMethod(methodName, argclass);
				return method.invoke(clObj, args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(String className, String methodName,
			Class<?>[] argclass, Object[] args) {
		try {
			Class<?> cls = Class.forName(className);
			Object clObj = cls.newInstance();
			if (argclass == null || argclass.length == 0 || args == null
					|| args.length == 0) {
				Method method = cls.getMethod(methodName);
				return method.invoke(clObj);
			} else {
				Method method = cls.getMethod(methodName, argclass);
				return method.invoke(clObj, args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Object clObj, String methodName,
			Object[] args) {
		try {
			Class<?> cls = clObj.getClass();
			Class<?>[] argsType = null;
			if (args != null && args.length > 0) {
				argsType = new Class<?>[args.length];
				for (int i = 0; i < args.length; i++) {
					Object obj = args[i];
					argsType[i] = obj.getClass();
				}
				Method method = cls.getMethod(methodName, argsType);
				return method.invoke(clObj, args);
			} else {
				Method method = cls.getMethod(methodName);
				return method.invoke(clObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(String className, String methodName,
			Object[] args) {
		try {
			Class<?> cls = Class.forName(className);
			Object clObj = cls.newInstance();
			Class<?>[] argsType = null;
			if (args != null && args.length > 0) {
				argsType = new Class<?>[args.length];
				for (int i = 0; i < args.length; i++) {
					Object obj = args[i];
					argsType[i] = obj.getClass();
				}
				Method method = cls.getMethod(methodName, argsType);
				return method.invoke(clObj, args);
			} else {
				Method method = cls.getMethod(methodName);
				return method.invoke(clObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行父类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeSuperMethod(Object clObj, String methodName,
			Object[] args) {
		try {
			Class<?> pcls = clObj.getClass();
			Class<?> cls = pcls.getSuperclass();
			Class<?>[] argsType = null;
			if (args != null && args.length > 0) {
				argsType = new Class<?>[args.length];
				for (int i = 0; i < args.length; i++) {
					Object obj = args[i];
					argsType[i] = obj.getClass();
				}
				Method method = cls.getDeclaredMethod(methodName, argsType);
				method.setAccessible(true);
				return method.invoke(clObj, args);
			} else {
				Method method = cls.getDeclaredMethod(methodName, argsType);
				method.setAccessible(true);
				return method.invoke(clObj, args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行父类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeSuperMethod(Object clObj, String methodName,
			Class<?>[] argsCls, Object[] args) {
		try {
			Class<?> pcls = clObj.getClass();
			Class<?> cls = pcls.getSuperclass();
			Method method = cls.getDeclaredMethod(methodName, argsCls);
			method.setAccessible(true);
			return method.invoke(clObj, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行父类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeSuperMethod(String cls, String methodName,
			Class<?> [] argsCls, Object[] args) {
		try {
			Class<?> pcls = Class.forName(cls);
			Object obj = pcls.newInstance();
			return invokeSuperMethod(obj, methodName, argsCls, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行父类的方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeSuperMethod(String cls, String methodName,
			Object[] args) {
		try {
			Class<?> pcls = Class.forName(cls);
			Object obj = pcls.newInstance();
			return invokeSuperMethod(obj, methodName, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public interface ProxyListener {
		public void onRun(String methodName, Object[] args, Class<?>[] argsCls);
	}

	/**
	 * 实现接口类的方法,会讲信息自动调用callBack类的同名方法
	 * 
	 * @param interfaceClass
	 * @param callBack
	 * @return
	 */
	public static Object getInterfaceProxy(String interfaceClass,
			final Object callBack) {
		return getInterfaceProxy(interfaceClass, new ProxyListener() {
			@Override
			public void onRun(String methodName, Object[] args,
					Class<?>[] argsCls) {
				MyReflection.invokeMethod(callBack, methodName, argsCls, args);
			}
		});
	}

	/**
	 * 代理实现接口类的实现
	 * 
	 * @param listener
	 * @return
	 */
	public static Object getInterfaceProxy(String interfaceClass,
			final ProxyListener listener) {
		try {
			Class<?> cls = Class.forName(interfaceClass);
			Object robot = java.lang.reflect.Proxy.newProxyInstance(
					cls.getClassLoader(), new java.lang.Class[] { cls },
					new java.lang.reflect.InvocationHandler() {

						@Override
						public Object invoke(Object proxy,
								java.lang.reflect.Method method, Object[] args)
								throws java.lang.Throwable {
							String method_name = method.getName();
							Class<?>[] classes = method.getParameterTypes();
						listener.onRun(method_name, args, classes);
							return null;
						}
					});
			return robot;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}