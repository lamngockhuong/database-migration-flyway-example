package com.ngockhuong.flyway.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtils {
    public static <T extends Annotation> T findAnnotation(Class<? extends T> annotationClass, String atMethod, Class<?> inClass) {
        T annotation = null;

        try {
            // try to find annotation at method level
            Method method = inClass.getDeclaredMethod(atMethod);
            annotation = method.getAnnotation(annotationClass);

            // try to find annotation at class level
            if (annotation == null) {
                annotation = inClass.getAnnotation(annotationClass);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return annotation;
    }
}
