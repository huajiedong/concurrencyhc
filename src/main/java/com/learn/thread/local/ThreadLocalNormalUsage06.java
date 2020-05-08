package com.learn.thread.local;

/**
 * @Author: HuaChenG
 * @Description: 避免传递参数的麻烦
 * @Date: Create in 2020/04/23
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process();
    }
}
class Service1 {
    public void process() {
        User user = new User("小明");
        UserCOntextHolder.holder.set(user);
        new Service2().process();
    }
}
class Service2 {
    public void process() {
        User user = UserCOntextHolder.holder.get();
        System.out.println(user.getName());
    }
}
class Service3 {
    public void process() {
        User user = UserCOntextHolder.holder.get();
        System.out.println(user);
    }
}
class UserCOntextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}