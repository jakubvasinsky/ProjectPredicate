package sk.kosickaakademia.vasinsky.projectPredicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    private static List<City> cityList;
    public static void main(String[] args) throws IOException {
        

        URL url = new URL("http://itsovy.sk:5000/data");
        URLConnection request = url.openConnection();
        System.out.println(" \n\n\n " + "Connecting... ");
        request.connect();
        System.out.println(" \n\n\n " + "Successfully Connected!");
        JsonParser theJsonParser = new JsonParser();
        JsonElement theJsonElement = theJsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject theJsonObject = theJsonElement.getAsJsonObject();
        JsonArray worldx = theJsonObject.get("world_x").getAsJsonArray();


        if (cityList == null) {
            cityList = new ArrayList<>();
            for (int i = 0; i < worldx.size(); i++) {
                City tempCity = new City(((JsonObject) worldx.get(i)).get("pop").getAsInt(),
                        ((JsonObject) worldx.get(i)).get("code").getAsString(),
                        ((JsonObject) worldx.get(i)).get("district").getAsString(),
                        ((JsonObject) worldx.get(i)).get("name").getAsString());
                cityList.add(tempCity);
            }
                for (int i=1;i<=3;i++){ //odriadkovanie
                    System.out.println();
                }
        }

        System.out.println("Searching for Cities where Country code is 'NLD' and Population is over 100 000");
        System.out.println(); //odriadkovanie


                //1. Predikat s pouzitim triedy
                predicate1Class predicateCountryCode = new predicate1Class();

                //2. Predikat s pouzitim lambda vyrazov
                Predicate<City> populationAbove = city -> city.getPop()>100000;
                cityList.stream().filter(predicateCountryCode.and(populationAbove))
                        .forEach(System.out::println);

        //odriadkovanie
            System.out.println();

            long resultNumber = cityList.stream().filter(predicateCountryCode.and(populationAbove)).count(); //pocet vysledkov

        System.out.println("Found "+ resultNumber + " results");






    }


}
