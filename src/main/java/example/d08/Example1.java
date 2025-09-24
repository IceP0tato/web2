package example.d08;

class TestService {
    public void enter1() {
        System.out.println("식당 입장");
    }

    public void enter2() {
        System.out.println("학원 입장");
    }
}

public class Example1 {
    public static void main(String[] args) {
        TestService service = new TestService();
        service.enter1();
        service.enter2();
    }
}
