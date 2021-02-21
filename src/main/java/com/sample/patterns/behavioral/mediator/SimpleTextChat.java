package com.sample.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class SimpleTextChat implements Chat {

    private User admin;
    private List<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void addUserToChat(User user){
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        users.forEach(user1 -> {
            if (user1 != user){
                user1.getMessage(message);
            }
        });


        if (user != admin)  admin.getMessage(message);
    }
}
