public interface Orderable<T> {
    boolean isLessThan(T other);
    boolean match(T other);
}