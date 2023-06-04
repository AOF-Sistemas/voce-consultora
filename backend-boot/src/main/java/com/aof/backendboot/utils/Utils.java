package com.aof.backendboot.utils;

import com.aof.backendboot.DTO.ProductDTO;
import com.aof.backendboot.entities.Product;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Utils {

    public static void setTimeout(Runnable task) {
       Long delay= Long.valueOf(5000);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
                timer.cancel();
            }
        }, delay);
    }

    public static boolean compareIsEquals(ProductDTO novo, List<ProductDTO> baseAntiga) {
        boolean result = false;

        for (ProductDTO antigo : baseAntiga) {
            if (
                    novo.getName().equals(antigo.getName()) &&
                            novo.getClassification().equals(antigo.getClassification()) &&
                            novo.getDescription().equals(antigo.getDescription()) &&
                            novo.getPrice().equals(antigo.getPrice()) &&
                            novo.getImgUrl().equals(antigo.getImgUrl())
            ) {
                result = true;
            }
        }

        return result;
    }
}
