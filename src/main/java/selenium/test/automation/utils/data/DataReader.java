package selenium.test.automation.utils.data;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import selenium.test.automation.utils.BaseConfiguration;
import selenium.test.automation.utils.files.FileHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataReader {

    public Object[][] readVersionData(String fileName) {
        Object[][] data = null;
        try {
            CSVReader csvReader = new CSVReaderBuilder(
                    new FileReader(new FileHelper().getResourceFilePath(BaseConfiguration.DATA_DIRECTORY + fileName))
            ).withSkipLines(1).build();
            List<String[]> dataRead = csvReader.readAll();
            data = new Object[dataRead.size()][];
            int i = 0;
            for (String[] d : dataRead) {
                String[] dataRecord = new String[2];
                dataRecord[0] = d[0];
                dataRecord[1] = d[1];
                data[i] = dataRecord;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return data;
    }
}
