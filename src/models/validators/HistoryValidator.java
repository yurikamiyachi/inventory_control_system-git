package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.History;
import models.Inventory;
import utils.DBUtil;

public class HistoryValidator {
    public static List<String> validate(History h){
        List<String> errors = new ArrayList<String>();

        String receiving_error=validateReceiving(h.getReceiving());
        if(!receiving_error.equals("")){
            errors.add(receiving_error);
        }
        String shiping_error=validateShiping(h.getShiping());
        if(!shiping_error.equals("")){
            errors.add(shiping_error);
        }

        String stock_error=validateInventory(h.getInventory(),h.getReceiving(),h.getShiping());
        if(!stock_error.equals("")){
            errors.add(stock_error);
        }
        return errors;
    }

    private static String validateReceiving(Integer receiving) {
        if(receiving==null|| receiving.equals("")){
            return "入庫数を入力してください。";
        }
        return "";
    }

    private static String validateInventory(Inventory inventory,Integer receiving,Integer shiping) {
        if(!(receiving==null)&&!(shiping==null)){
            EntityManager em =DBUtil.createEntityManager();

            List<History> histories = em.createNamedQuery("getHistories", History.class)
                    .setParameter("inventory", inventory)
                    .getResultList();

            int receiving2 = 0;
            int shiping2 = 0;
            int stock = 0;

            for(History his: histories){
                receiving2 += his.getReceiving();
                shiping2 += his.getShiping();
            }
            stock=receiving2+receiving-shiping2-shiping;
            if(stock < 0 ){
                return "出庫数が在庫数を超えています。";
            }
        }
        return "";
    }


    private static String validateShiping(Integer shiping) {
        if(shiping==null|| shiping.equals("")){
            return "出庫数を入力してください。";
        }
        return "";
    }
}
