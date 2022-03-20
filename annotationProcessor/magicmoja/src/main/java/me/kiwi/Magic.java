package me.kiwi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 컴파일때 쓰이고 바이트 코드일때는 사용하지 않는다.
 */
@Target(ElementType.TYPE) // Interface, Class, Enum
@Retention(RetentionPolicy.SOURCE)
public @interface Magic {
}
