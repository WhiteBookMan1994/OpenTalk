package serializable;

/**
 * @author dingchenchen
 * @since 2020/9/21
 */
@FunctionalInterface
public interface Condition<R> {

    /**
     * Conditions for transition
     * @param context context object
     * @return whether the context satisfied current condition
     */
    boolean isSatisfied(R context);
}
