public class UserServiceImpl implements IUserService{
    public String getNameById(String uid) {
        System.out.println("获取用户名称 : " + uid);
        return "Mic";
    }
}
