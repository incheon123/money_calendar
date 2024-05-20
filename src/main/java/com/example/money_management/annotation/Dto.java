package com.example.money_management.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


public class Dto {
    
    public static Object toEntity(Object obj){

        Object o = null;
        try {
            Dto executor = new Dto();
            Field[] fields = obj.getClass().getDeclaredFields();
            Object[] fields_value = executor.getFields(obj, fields.length);
            Annotation[] annotations = obj.getClass().getDeclaredAnnotations();
            Class[] cArgs = new Class[fields.length];

            
            for(Annotation a : annotations){
                if(a instanceof DtoToEntity){
                    DtoToEntity dte = (DtoToEntity) a;
                    Class<?> c = Class.forName(dte.entity().getName());
                    for(int i = 0 ; i < fields.length; i++){
                        cArgs[i] = fields[i].getType();
                    }

                    Constructor<?> ccc = c.getDeclaredConstructor(cArgs);
                    
                    o = ccc.newInstance(fields_value);
                    
                    System.out.println(o);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return o;
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
}
