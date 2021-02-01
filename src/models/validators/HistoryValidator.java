package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.History;

public class HistoryValidator {
    public static List<String> validate(History h,Boolean receivingDuplicateCheckFlag){
        List<String> errors = new ArrayList<String>();

        String receiving_error=validateReceiving(h.getReceiving());
        if(!receiving_error.equals("")){
            errors.add(receiving_error);
        }

        String shiping_error=validateShiping(h.getShiping());
        if(!shiping_error.equals("")){
            errors.add(shiping_error);
        }
        return errors;
    }


    private static String validateReceiving(Integer receiving) {
        if(receiving==null || receiving.equals("")){
            return "入庫数を入力してください。";
        }
        return "";
    }

    private static String validateShiping(Integer shiping) {
        if(shiping==null || shiping.equals("")){
            return "出庫数を入力してください。";
        }
        return "";
    }
}
