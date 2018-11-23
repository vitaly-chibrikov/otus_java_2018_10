import ru.otus.generics.bounds.Animal;
import ru.otus.generics.bounds.Cat;
import ru.otus.generics.bounds.HomeCat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class ArrayListDemo {
    public static void main(String[] args) {

        Cat[] animalArr = new Cat[] {new HomeCat("Мурзик"), new HomeCat("Васька")};
        List<? extends Cat> animalList = Arrays.asList(animalArr);
        System.out.println(animalList);

        animalArr[0] = new HomeCat("1");
        System.out.println(animalList);

        //animalList.add(new HomeCat("Мурка")); //Ошибка

        Animal[] catsArr = animalList.toArray(new Animal[0]);
        System.out.println("catsArr:" + Arrays.toString(catsArr));

        //copy(List<? super T> dest, List<? extends T> src)
        List<? super Cat> animalListDest = new ArrayList<>(animalList);
        Collections.copy(animalListDest, animalList);
        System.out.println("homeCats:" + animalListDest);
    }
}
