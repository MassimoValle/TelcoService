package it.polimi.telco.web.utils;

import java.util.Random;

public class ExternalService {

    public static boolean checkValidation(Boolean testVal){

        if(testVal != null) return testVal;

        else {
            Random rd = new Random(); // creating Random object
            return rd.nextBoolean();
        }

    }
}
