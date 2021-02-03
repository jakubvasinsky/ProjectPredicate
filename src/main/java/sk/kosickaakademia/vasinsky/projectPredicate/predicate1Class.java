package sk.kosickaakademia.vasinsky.projectPredicate;

import java.util.function.Predicate;

public class predicate1Class implements Predicate<City> {
    @Override
    public boolean test(City city) {
        return city.getCode().equals("NLD");
    }


}
