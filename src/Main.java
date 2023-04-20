import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("Количество несовершеннолетних " +
                persons.stream()
                .filter(person -> person.getAge() < 18)
                .count());
        System.out.println("Список фамилий призывников, ограничено до 5-ти, для удобства " + persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() <= 27 && person.getAge() >= 18)
                .limit(5)
                .map(Person::getSurname)
                .toList() +
                " Записей всего: " + persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() <= 27 && person.getAge() >= 18)
                .count());
        System.out.println("Отсортированный список фамилий потенциально работоспособных людей с высшим образованием, ограничено до 5-ти " + persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER && person.getAge() >= 18 && ((person.getSex() == Sex.MAN && person.getAge() <= 65) || (person.getSex() == Sex.WOMAN && person.getAge() <= 60)))
                .limit(5)
                .sorted(Comparator.comparing(Person::getSurname))
                .map(Person::getSurname)
                .toList() +
                " Записей всего: " + persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER && person.getAge() >= 18 && ((person.getSex() == Sex.MAN && person.getAge() <= 65) || (person.getSex() == Sex.WOMAN && person.getAge() <= 60)))
                .count());
    }
}