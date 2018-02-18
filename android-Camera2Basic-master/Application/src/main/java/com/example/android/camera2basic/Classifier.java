package com.example.android.camera2basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jin Woo Won
 * @See https://github.com/googlesamples/android-Camera2Basic
 * This class classifies ingredients to inform the user of the properties of the input food.
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
    private String[] dairy;
    private String[] egg;
    private String[] nuts;
    private String[] treeNuts;
    private String[] soy;
    private String[] wheat;
    private String[] fish;
    private String[] meat;

    private String[][][] words = {{dairy}, {egg}, {nuts}, {treeNuts}, {soy}, {wheat}, {fish},
                                    {meat}, {dairy, egg, fish, meat}, {nuts, treeNuts, dairy,
                                    soy, wheat, egg, fish}};


    /* This constructor reads ingredient files for specific allergies and eating style and
     * has the pipeline system ready to process input raw data from server.
     */
    public Classifier(){
        dairy = parseList("milkwords.txt");
        egg = parseList("eggwords.txt");
        nuts = parseList("peanutwords.txt");
        treeNuts = parseList("treenutwords.txt");
        soy = parseList("sorywords.txt");
        wheat = parseList("wheatwords.txt");
        fish = parseList("fishwords.txt");
        meat = parseList("meatwords.txt");
    }

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

    /*
     * @param ingredients, words, testedProperty
     * Input testedProperty in the form "Nuts", "treeNuts", etc.
     */
    public String checkOneProperty(String[] ingredients, String[] words, String testedProperty){
        String property = "";
        for (String ingredient : ingredients){
            for (String word : words){
                if(ingredient.equals(word)){
                    property = testedProperty;
                }
            }
        }
        return "";
    }

    public String[] checkIngredients(String rawStr){
        String[] ingredients = webToArray(rawStr);
        List<String> markedIngredients = new ArrayList<>();

        for( String ingredient : ingredients){
            for( String[][] arr2D : words){
                for ( String[] arr : arr2D ){
                    String property = checkOneProperty(ingredients, arr, "nuts");
                }
            }
        }

    }

}
