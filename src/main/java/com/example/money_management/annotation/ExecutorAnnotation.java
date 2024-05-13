package com.example.money_management.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;


public class ExecutorAnnotation {
    
    public static Object dtoToEntity(Object obj) 
            throws ClassNotFoundException   ,      NoSuchMethodException    ,
                   SecurityException        ,      InstantiationException   ,
                   IllegalAccessException   ,      IllegalArgumentException ,
                   InvocationTargetException
        {

        ExecutorAnnotation executor = new ExecutorAnnotation();
        Field[] fields = obj.getClass().getDeclaredFields();
        Object[] fields_value = executor.getFields(obj, fields.length);
        Annotation[] annotations = obj.getClass().getDeclaredAnnotations();
        Class[] cArgs = new Class[fields.length];

        for(Annotation a : annotations){
            if(a instanceof ToEntity){
                ToEntity dte = (ToEntity) a;
                Class<?> c = Class.forName(dte.entity().getName());
                for(int i = 0 ; i < fields.length; i++){
                    cArgs[i] = fields[i].getType();
                }

                Constructor<?> ccc = c.getDeclaredConstructor(cArgs);
                
                Long l = ( (Number) fields_value[0] ).longValue();
                String s = (String) fields_value[1];
                Object o = ccc.newInstance(fields_value);
                
                System.out.println(o);
            }

        }

        return new Object();
    }
    private Object[] getFields(Object obj, int len){

        String fieldName;
        Object value;
        Field field;
        Object[] objs = new Object[len];
        
        int i = 0;
        for(Field f : obj.getClass().getDeclaredFields()){
            try {

                fieldName = f.getName();
                field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                value = field.get(obj);

                if(value instanceof Number)
                    objs[i++] = ( ((Number) value).longValue() );
                else
                    objs[i++] = ((String) value);

            } catch (Exception e) {}
            
        }
        return objs;
    }
    public static void main(String[] args){

        SomeDto dto = new SomeDto(1234L, "helloWorld!");
        try {
            dtoToEntity(dto);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
}
