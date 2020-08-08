package DemoStorage;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void registerModule(Module module) {
        objectMapper.registerModule(module);
    }

    public static <T> String toJson(T t) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
        }
        return result;
    }

    public static <T> T fromJson(Object value, Class<T> t) {
        T result = null;
        try {
            result = objectMapper.readValue(value.toString(), t);
        } catch (IOException e) {
        }
        return result;
    }

    public static <T> T fromJsonWithType(Object value, TypeReference<T> t) {
        T result = null;
        try {
            result = objectMapper.readValue(value.toString(), t);
        } catch (IOException e) {
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static <T> List<T> fromJson(Object value, Class<? extends Collection> cls, Class<T> t) {
        List<T> result = null;
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(cls, t);
            result = objectMapper.readValue(value.toString(), type);
        } catch (IOException e) {
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static <K, V> Map<K, V> fromJson(Object object, Class<? extends Map> cls, Class<K> key, Class<V> value) {
        Map<K, V> result = null;
        try {
            JavaType type = objectMapper.getTypeFactory().constructMapLikeType(cls, key, value);
            result = objectMapper.readValue(object.toString(), type);
        } catch (IOException e) {

        }
        return result;
    }

    /**
     * 利用反射将map集合封装成bean对象
     *
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws Exception {
        T obj = clazz.newInstance();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();    // 属性名
                Object value = entry.getValue();        // 属性值
                String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                Field field = getClassField(clazz, propertyName);    //获取和map的key匹配的属性名称
                if (field == null) {
                    continue;
                }
                Class<?> fieldTypeClass = field.getType();
                if(value == null){
                    continue;
                }
                value = convertValType(value, fieldTypeClass);
                try {
                    clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * 根据给定对象类匹配对象中的特定字段
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        Class<?> superClass = clazz.getSuperclass();    //如果该类还有父类，将父类对象中的字段也取出
        if (superClass != null) {                        //递归获取
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    /**
     * 将map的value值转为实体类中字段类型匹配的方法
     *
     * @param value
     * @param fieldTypeClass
     * @return
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal = null;

        if (Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }

}
