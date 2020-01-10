package com.prabhakar;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//import java.io.IOException;

public class CsvReader {
    CSVReader csvreader;
    List<String[]> data ;

    public CsvReader() throws FileNotFoundException {
        String CSV_PATH = "/home/shivap/IdeaProjects/Testing1/src/test/resources/testCSV.csv";
        csvreader = new CSVReader(new FileReader(CSV_PATH));
    }

    public List<String[]> readData() throws IOException {

        data = csvreader.readAll();
        return data;
//        System.out.println(data.size());
//        for(int j=1;j<data.size();j++){
//            String[] subData = data.get(j);
//            System.out.println(" ");
//            for(int i=0; i<subData.length; i++)
//                System.out.print(subData[i]);
//        }

    }
}
