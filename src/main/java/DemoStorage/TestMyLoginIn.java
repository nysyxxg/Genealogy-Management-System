package DemoStorage;

import dataLayer.User;
import javafx.application.Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.err;

public class TestMyLoginIn {
    static ArrayList<User> list = new ArrayList<User>();  //存放用户数据的列表
    
    public static void main(String[] args) {
        LoadUser(); //从文件中载入用户数据
        Application.launch(UserLogin.class, args); //启动javafx Application程序
    }
    
    public static void LoadUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/user.txt")));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                list.add(new User(data[0], data[1], false));
            }
            reader.close();
        } catch (IOException e) {
            err.println("用户文件数据 user.txt找不到");
            e.printStackTrace();
        }
    }
    
    public static boolean findUser(String account, String pwd) {
        return list.contains(new User(account, pwd, false));
    }
    
    public static User findAccount(String account) {
        for (User u : list)
            if (u.getAccount().equalsIgnoreCase(account))
                return u;
        return null;
    }
    
    public static User newUser() {
        User user = new User("xxg", "123456");
        if (list.size() == 0) {
            user = new User("1", "123456");
            list.add(user);
        } else {
            user = list.get(list.size() - 1);
        }
        
        return new User(Integer.parseInt(user.getAccount()) + 1 + "", "123456", false);
    }
    
    public static void addNewUser(User user) {
        list.add(user);
        LoadUser();
    }
}
