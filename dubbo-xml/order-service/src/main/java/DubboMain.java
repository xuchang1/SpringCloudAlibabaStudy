import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/spring/user-provider.xml");
        IUserService userService = (IUserService) context.getBean("userService");
        System.out.println(userService.getNameById("1001"));
    }
}
