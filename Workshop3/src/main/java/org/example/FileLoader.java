package org.example;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;



public class FileLoader {
    public static List<Product> readFile (){
       //Use FileReade class and Buffered Reader to lead the file
       //Loop through the file Line by line
       //Skip the first line of te because  the header

        try{
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                bufferedReader.readLine();

                String input;
                List<Product> productList = new ArrayList<>();
                while((input = bufferedReader.readLine()) != null){
                    String[] row = input.split("\\|");

                    String sku = row[0];
                    String productName = row[1];
                    double price = Double.parseDouble(row[2]);
                    String department = row[3];
                    Product product = new Product(sku, productName, department, price);
                    productList.add(product);


                }
                bufferedReader.close();
                return productList;

        }
        catch(IOException ex){
            System.out.println("Failed to load csv file.");
            ex.printStackTrace();
            return new ArrayList<>();


        }

    }
}
