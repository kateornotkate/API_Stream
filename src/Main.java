import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Alex");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        System.out.println("Перепись населения Лондона.\n");

        System.out.println("Количество несовершеннолетних жителей: ");
        long underAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println(underAge);

        System.out.println("\nСписок фамилий призывников: ");
        persons.stream()
                .filter(p -> p.getSex().equals(Sex.MAN))
                .filter(p -> p.getAge() >= 18 && p.getAge() < 27)
                .limit(10) // java отфильтрует весь список, но выведет в консоль только 10 случайных фамилий;
                .map(Person::getFamily).toList() // возвращаем результат в виде фамилий и сохраняем отсортированные данные в новый лист;
                .forEach(System.out::println);

        System.out.println("\nСписок потенциально трудоспособного населения: ");
        persons.stream()
                .filter(p -> p.getEducation().equals(Education.HIGHER))
                .filter(p -> p.getAge() > 18)
                .filter(p -> p.getSex().equals(Sex.WOMAN) && p.getAge() < 60
                        || p.getSex().equals(Sex.MAN) && p.getAge() < 65)
                .limit(15) // лимит для вывода;
                .sorted(Comparator.comparing(Person::getFamily)).toList() // отсортирует людей по фамилиям в алфавитном порядке и сохранит результат в лист;
                .forEach(System.out::println);
    }
}