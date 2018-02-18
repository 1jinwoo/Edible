package com.example.android.camera2basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin Won on 2/18/2018.
 */

public class Classifier {
    private String[] wordsToRemove = {"and", "or", "the", "but", "or", "nor", "for", "yet",
            "so", "prepare", "preparation", "high", "protein", "acid", "products", "half",
            "all", "some", "concentrate", "replacer", "seasoning", "powder", "ground", "black",
            "slice", "divided", "into", "taste", "as"};
    private String[] measurements = {"teaspoon", "teaspoons", "tablespoon", "tablespoons",
            "slice", "slices", "cup", "gallon", "cupts", "gallons", "pinch", "spoonful"};
    private char[] charsToRemove = {'(', ')', '*', '&', '^', '%', '$', '#', '@', '!', '~',
            '`', '.', '/', '<', '>', '?', ';', '\'', ':', '"',
            '_', '-', '=', '+', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '0'};

    public String[] webToArray(String rawStr) {
        String[] result = rawStr.split("\n");
        for (String str : result) {
            str = str.toLowerCase();
            // str = str.replaceAll(" ", "").replaceAll("\t", ""); // wrong implementation of split() in python
            for (char character : charsToRemove) {
                str.replaceAll(Character.toString(character), "");
            }
            str = str.split(",")[0];
            String[] strings = str.split(" ");
            for (String measurement : measurements) {
                for (int i = 0; i < strings.length; i++) {
                    if (measurement.equals(strings[i])) {
                        strings[i] = "";
                    }
                }
            }
            for (String word : strings) {
                str += word;
            }

        }
        return result;
    }

    public String[] parseList(String fileName){
        BufferedReader br = null;
        FileReader fr = null;
        List<String> results = new ArrayList<>();
        try{
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while((sCurrentLine = br.readLine()) != null){
                results.add(sCurrentLine);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(br != null){
                    br.close();
                }
                if(fr != null){
                    fr.close();
                }
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        for(String result : results){
            result = result.toLowerCase();
            for (char character : charsToRemove) {
                result.replaceAll(Character.toString(character), "");
            }

        }
        return (String[]) results.toArray();
    }

    

}
