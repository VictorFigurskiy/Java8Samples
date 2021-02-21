package com.sample.patterns.behavioral.chain;

public class BugTracker {

    public static void main(String[] args) {
        Notifier reportNotifier = new SimpleReportNotifier(Priority.ROUTINE.getLevelValue());
        EmailNotifier emailNotifier = new EmailNotifier(Priority.IMPORTANT.getLevelValue());
        SMSNotifier smsNotifier = new SMSNotifier(Priority.ASAP.getLevelValue());

        reportNotifier.setNextNotifier(emailNotifier);
        emailNotifier.setNextNotifier(smsNotifier);

        reportNotifier.notifyManager("Everything is OK.", Priority.ROUTINE.getLevelValue());
        reportNotifier.notifyManager("Something went wrong!", Priority.IMPORTANT.getLevelValue());
        reportNotifier.notifyManager("Houston, we have a problem here!!!", Priority.ASAP.getLevelValue());
    }
}
