package lkd.namsic.cnkb.base;

@FunctionalInterface
public interface DoubleFunction<T1, T2, R> {
    
    R apply(T1 t1, T2 t2);
    
}
