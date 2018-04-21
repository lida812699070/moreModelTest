package com.careagle.commlib.my_route;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lida on 2018/4/21.
 */

public class MyRouteController {

    private static HashMap<String, Class> hasAnnotationServiceMap = new HashMap<>();

    public static void startServiceByMyRoute(Context context, String myServicePath) {
        if (!TextUtils.isEmpty(myServicePath) && hasAnnotationServiceMap.get(myServicePath) != null) {
            context.startService(new Intent(context, hasAnnotationServiceMap.get(myServicePath)));
        }
    }

    public static void initMyRoute(Context context) {
        if (hasAnnotationServiceMap.size() > 0) {
            return;
        }
        List<Class> excludeList = new ArrayList<Class>();
        excludeList.add(context.getClass());
        List<Class> serviceClass = ClassUtils.getServiceClass(context, context.getPackageName(), excludeList);
        for (Class service : serviceClass) {
            getAnnotationInClass(service);
        }
    }

    public static void getAnnotationInClass(Class clazz) {
        Annotation annotation = clazz.getAnnotation(MyRoute.class);
        if (annotation != null) {
            Method[] met = annotation.annotationType().getDeclaredMethods();
            for (Method me : met) {
                if (!me.isAccessible()) {
                    me.setAccessible(true);
                }
                try {
                    String routePath = (String) me.invoke(annotation, null);
                    if (!TextUtils.isEmpty(routePath)) {
                        hasAnnotationServiceMap.put(routePath, clazz);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
