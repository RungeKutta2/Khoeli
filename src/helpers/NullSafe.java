package helpers;

import java.util.Objects;
import java.util.function.Function;
import com.google.gson.JsonNull;

public final class NullSafe<T> {

    private T t;

    private NullSafe(T t) {
        this.t = t;
    }

    public static <T> NullSafe<T> of(T t) {
        return new NullSafe<>(t);
    }

    public <U> NullSafe<U> call(Function<T, U> methodRef) {
        U nextStep = get(methodRef);
        return of(nextStep);
    }

    public T get() {
        return t;
    }

    public <U> U get(Function<T, U> methodRef) {
        Objects.requireNonNull(methodRef);
        return (t == null || t instanceof JsonNull) ? null : methodRef.apply(t);
    }

}