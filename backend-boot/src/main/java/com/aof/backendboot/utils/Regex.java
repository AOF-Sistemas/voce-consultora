package com.aof.backendboot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static List<String> calcular(String regexPadrao, String text) {
        Pattern pattern;
        Matcher matcher;
        List list = new ArrayList<>();

        pattern = Pattern.compile(regexPadrao);
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            list.add(matcher.group(1));
            return list;
        }
        list.add("Não Informado");
        return list;
    }
}


