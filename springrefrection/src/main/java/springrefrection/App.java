package springrefrection;

import java.lang.reflect.*;
import java.util.Arrays;

public class App {

    public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class<Book> bookClass = Book.class;

        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

       // Class<?> springrefrection = Class.forName("springrefrection");

        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        System.out.println(" ==================== ");

        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        System.out.println(" ==================== ");

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f ->
                {
                    try {
                        f.setAccessible(true);
                        System.out.printf("%s %s\n", f, f.get(book));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                );

        System.out.println(" ==================== ");

        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        System.out.println(" ==================== ");

        Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);

        System.out.println(" ==================== ");

        System.out.println(MyBook.class.getSuperclass());

        System.out.println(" ==================== ");

        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);

        System.out.println(" ==================== ");

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });

        System.out.println(" ==================== ");

        Arrays.stream(bookClass.getAnnotations()).forEach(System.out::println);

        System.out.println(" ==================== ");

        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);

        System.out.println(" ==================== ");

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                MyAnnotation myAnnotation = (MyAnnotation) a;
                System.out.println("myAnnotation.name() = " + myAnnotation.name());
                System.out.println("myAnnotation.number() = " + myAnnotation.number());
            });
        });

        System.out.println(" =========V2=========== ");

        Class<BookV2> bookV2Class = BookV2.class;
        Constructor<?> constructors = bookV2Class.getConstructor(String.class);
        BookV2 bookV2 = (BookV2) constructors.newInstance("myBook");
        System.out.println("bookV2 = " + bookV2);

        Field a = bookV2Class.getDeclaredField("A");
        System.out.println("a.get(null) = " + a.get(null));
        a.set(null, "A변경");
        System.out.println("a.get(null) = " + a.get(null));

        Field b = bookV2Class.getDeclaredField("B");
        b.setAccessible(true);
        System.out.println("b.get(bookV2) = " + b.get(bookV2));
        b.set(bookV2, "BBBBB");
        System.out.println("b.get(bookV2) = " + b.get(bookV2));

        Method c = bookV2Class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(bookV2);

        Method sum = bookV2Class.getDeclaredMethod("sum", int.class, int.class);
        sum.setAccessible(true);
        int invoke = (int) sum.invoke(bookV2, 1, 2);
        System.out.println("invoke = " + invoke);

    }
}
