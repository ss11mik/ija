package ija;

import ija.data_structures.*;
import java.io.IOException;

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.Reader;
import java.io.FileReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class ImportExport {

    public static void save (UML_Diagram d, File file) {

        try (Writer writer = new FileWriter(file)) {

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

            mapper.writeValue(writer, d);

            //debug
//         System.out.println(mapper.writeValueAsString(d));

        }
        catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }


    public static UML_Diagram load (File file) {
        UML_Diagram d = null;
        try (Reader writer = new FileReader(file)) {

            ObjectMapper mapper = new ObjectMapper();

            d = mapper.readValue(writer, UML_Diagram.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return d;
    }

}
