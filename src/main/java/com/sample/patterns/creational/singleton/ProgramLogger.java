package com.sample.patterns.creational.singleton;


// В тех случаях когда нам нужен исключительно один екземпляр класса

//1. Для запрета создания объекта снаружи, мы объявляем констрктор приватным, теперь другие классы не смогут создать новый объект через new ProgramLogger();
//2. Для того, чтобы хранить состояние класса, нам нужна глобальная переменная (т.к. у локальных видимость только в пределах метода) и для того, чтобы к ней можно было обращаться без создания экземпляра ProgramLogger(), объявляем её static. Почему private ? чтобы сокрыть от других классов.
//3.  Теперь нам нужен метод, при вызове которого мы будем получать объект класса или создавать его, если он не создан (для этого проверка на null). Почему метод статик? см. пункт 2. Почему public ? чтобы другие классы могли его вызвать.

public class ProgramLogger {

    private static volatile ProgramLogger progLogger;
    private static String logFile = "This is log file. \n\n";

    private ProgramLogger() {
    }

    // Как вариант можно просто synchronized весь метод, но по факту мы зашитим только создаание в первый раз для многопоточной среды, но в случае частого обращения к синглтону
    // будет дорого стоять трата времени на ожидание других потоков а смысла с synchronized уже не будет.
    // Вторым вариантом будет синхронизировать только блок инициализации объекта, пример ниже. Но обязательно нужно применить volatile во избежание работы с кешем.

    public static ProgramLogger getProgramLogger(){
        if (progLogger == null){
            synchronized (ProgramLogger.class) {
                if (progLogger == null) {
                    progLogger = new ProgramLogger();
                }
            }
        }

        return progLogger;
    }

    public void addLogInfo(String logMessage){
        logFile += logMessage + "\n";
    }

    public void showLogFile(){
        System.out.println(logFile);
    }
}

// Еще одним вариантом будет использовать паттерн “Lazy Initialization Holder”.
// Это решение основано на том, что вложенные классы не инициализируются до первого их использования (как раз то, что нам нужно):

//public class Singleton {
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        return SingletonHolder.instance;
//    }
//
//    private static class SingletonHolder {
//        private static final Singleton instance = new Singleton();
//    }
//}

// Также вариант получить еще один инстанс через рефлексию:
//Class clazz = Singleton.class;
//Constructor constructor = clazz.getDeclaredConstructor();
//constructor.setAccessible(true);