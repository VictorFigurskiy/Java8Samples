package com.sample.patterns.behavioral.mediator;

public class SimpleChatRunner {

    public static void main(String[] args) {
        SimpleTextChat chat = new SimpleTextChat();

        Admin admin = new Admin(chat, "Admin");
        SimpleUser user1 = new SimpleUser(chat, "User 1");
        SimpleUser user2 = new SimpleUser(chat, "User 2");

        chat.setAdmin(admin);
        chat.addUserToChat(user1);
        chat.addUserToChat(user2);

        user1.sendMessage("Hello I'm user 1");
        admin.sendMessage("Roger that. I am Admin!");
    }
}
