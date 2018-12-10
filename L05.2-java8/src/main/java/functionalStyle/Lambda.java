package functionalStyle;

import java.util.function.Function;

/**
 * @author sergey
 * created on 09.12.18.
 */
public class Lambda {
    public static void main(String[] args) {
        Lambda lambda = new Lambda();

        String result = lambda.func(str -> str + "+mod", "testStr");
        System.out.println(result);

        Integer result2 = lambda.func(val -> val * 10, 5);
        System.out.println(result2);
    }

    private <T,R> R func(Function<T, R> func, T param) {
        return func.apply(param);
    }
}
