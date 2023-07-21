package com.saurav.InstagramBackendApp.service.utility.emailUtility;

import java.time.LocalDate;

public class MailConstant {
    public static final String SENDER = "saurav24021998@gmail.com";
//    single Receiver
//    public static final String RECEIVER = "ajinkya12051999@gmail.com";

    public static final String SUBJECT = "Testing java \uD83D\uDCE7 mail  : \uD83D\uDCC6 - Date - " + LocalDate.now();
    public static final String MESSAGE = "Hii \uD83D\uDC4B , This message \uD83D\uDCE9 is for study purpose \uD83D\uDCDA - Please confirm -  ... !!!";

    //    Multiple receivers stored in array.
    public static final String RECEIVER[] = {"sauravnhk@gmail.com","subhamtitr@gmail.com" };
}
