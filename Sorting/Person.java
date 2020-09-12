package homework1;

import java.util.Random;

public class Person implements Comparable<Person> {
    private Double age;
    Random random = new Random();
    
    public Person() {
	age = random.nextDouble();
    }

    @Override
    public int compareTo(Person o) {
	if (age == o.age) return 0;
	return age < o.age ? -1 : 1;
    }

    public Double getAge() { return age; }
}
