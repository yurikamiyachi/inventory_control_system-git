package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Inventory;
import utils.DBUtil;

public class InventoryValidator {
    public static List<String> validate(Inventory e,Boolean trade_codeDuplicateCheckFlag){
        List<String> errors = new ArrayList<String>();

        String trade_code_error=validateTrade_code(e.getTrade_code(),trade_codeDuplicateCheckFlag);
        if(!trade_code_error.equals("")){
            errors.add(trade_code_error);
        }

        String trade_name_error=validateTrade_name(e.getTrade_name());
        if(!trade_name_error.equals("")){
            errors.add(trade_name_error);
        }

        return errors;
    }

    private static String validateTrade_code(String trade_code,Boolean trade_codeDuplicateCheckFlag){
        if(trade_code==null || trade_code.equals("")){
            return "品番を入力してください。";
        }

        if(trade_codeDuplicateCheckFlag){
            EntityManager em = DBUtil.createEntityManager();
            long inventories_count=(long)em.createNamedQuery("checkRegisteredTrade_code",Long.class)
                    .setParameter("trade_code", trade_code)
                    .getSingleResult();
            em.close();
            if(inventories_count>0){
                return "入力された品番情報はすでに存在しています。";
            }
        }
        return "";
    }

    private static String validateTrade_name(String trade_name){
        if(trade_name==null || trade_name.equals("")){
            return "品名を入力してください。";
        }

        return "";
    }

}
