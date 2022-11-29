public class Main {
    public static void main(String[] args) {
        Computer computer1 = new Computer(1 );
        Computer.Specification OS = computer1.new Specification("Операционная система", "Linux");
        Computer.Specification RAM = computer1.new Specification("Оперативная память", "8Гб");
        Computer.Specification processor = computer1.new Specification("Процессор", "i5 8300h");

        OS.print();
        RAM.print();
        processor.print();

    }
}
class Computer  {

    private int id;

    public Computer(int id) {
        this.id = id;
    }
    public class Specification {
        String name;
        String value;
        public Specification(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public void print() {
            System.out.println("Характеристика: " + name + "\n Значение: "  + value);
        }
    }
}