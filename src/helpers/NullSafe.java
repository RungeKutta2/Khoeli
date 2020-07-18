package helpers;

import java.util.Objects;
import java.util.function.Function;
import com.google.gson.JsonNull;

public final class NullSafe<T> {

    private T element;

    private NullSafe(T element) {
        this.element = element;
    }

    public static <T> NullSafe<T> of(T t) {
        return new NullSafe<>(t);
    }

    public <U> NullSafe<U> call(Function<T, U> methodRef) {
        U nextStep = get(methodRef);
        return of(nextStep);
    }

    public T get() {
        return element;
    }

    public <U> U get(Function<T, U> methodRef) {
        Objects.requireNonNull(methodRef);
        return (element == null || element instanceof JsonNull) ? null : methodRef.apply(element);
    }

}