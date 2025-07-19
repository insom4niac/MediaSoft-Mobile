import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;

public class AnnotationAndReflection {
    public static void main(String[] args) throws IllegalAccessException {
        // 1. кастомная аннотация @DeprecatedEx
        System.out.println("1. DeprecatedEx annotation");
        Class<?> testClass = TestClass.class;
        processDeprecatedEx(testClass);

        // 2. кастомная сериализация в JSON с аннотацией @JsonField
        System.out.println("2. JsonField annotation");
        TestSerialization testSer = new TestSerialization("Ivanov Ivan", 31, "ivanov.ivan@gmail.com");
        String json = JsonSerializer.serialize(testSer);
        System.out.println(" > JSON: " + json);
    }

    // ===================================
    // вспомогательные классы и интерфейсы
    // ===================================
    // 1. аннотация @DeprecatedEx
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface DeprecatedEx {
        String message();
    }
    @DeprecatedEx(message = "Use NewTestClass instead")
    static class TestClass {
        @DeprecatedEx(message = "Use NewMethod instead")
        public void deprecatedMethod() {
            System.out.println("Deprecated method called!");
        }
        public void newMethod() {
            System.out.println("New method called!");
        }
    }
    public static void processDeprecatedEx(Class<?> cl) {
        // проверка нерекомендуемого класса
        if (cl.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx annotation = cl.getAnnotation(DeprecatedEx.class);
            System.out.println(" > Class \"" + cl.getSimpleName() + "\" is outdated. " + annotation.message());
        }
        // проверка методов
        Arrays.stream(cl.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(DeprecatedEx.class))
                .forEach(method -> {
                    DeprecatedEx annotation = method.getAnnotation(DeprecatedEx.class);
                    System.out.println(" > Method \"" + method.getName() + "\" is outdated. " + annotation.message());
                });
    }

    // 2. сериализация в JSON
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface JsonField {
        String name();
    }
    static class TestSerialization {
        @AnnotationAndReflection.JsonField(name = "fullName")
        private String name;
        @AnnotationAndReflection.JsonField(name = "age")
        private int age;
        @AnnotationAndReflection.JsonField(name = "emailAddress")
        private String email;

        public TestSerialization(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
    }
    static class JsonSerializer {
        public static String serialize(Object obj) throws IllegalAccessException {
            Class<?> cl = obj.getClass();
            StringBuilder jsonBuilder = new StringBuilder("{");
            Field[] fields = cl.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(AnnotationAndReflection.JsonField.class)) {
                    AnnotationAndReflection.JsonField jsonField = field.getAnnotation(AnnotationAndReflection.JsonField.class);
                    String jsonName = jsonField.name();
                    field.setAccessible(true);      // для доступа к приватным полям
                    Object value = field.get(obj);

                    jsonBuilder.append(jsonName).append(": ");
                    if (value instanceof String) {
                        jsonBuilder.append("\"").append(value).append("\"");
                    } else {
                        jsonBuilder.append(value);
                    }

                    if (i < fields.length - 1) {
                        jsonBuilder.append(", ");
                    }
                }
            }

            jsonBuilder.append("}");
            return jsonBuilder.toString();
        }
    }
}
