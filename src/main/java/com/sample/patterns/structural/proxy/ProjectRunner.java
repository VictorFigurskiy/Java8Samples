package com.sample.patterns.structural.proxy;

public class ProjectRunner {
    public static void main(String[] args) {
        Project project = new ProxyProject("https://www.github.com/someName/realProject");

        project.run();
        // в случае запуска через прокси мы отложили заранее предопределонное скачивание
        // проекта и фактически выполняем его только в момент запуска run();. То есть имеем
        // больше гибкости при вызове когда нам это нужно.
    }
}
