package backgroundServices.resourceReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class reader {
    /**
     *
     * @param filename String parameter containing the filepath of the desired properties file with respect to the
     *                 resources directory.
     * @return returns object of Properties type containing the values of the properties stored within the file.
     */
    public Properties readFromResources(String filename){
        Properties prop = new Properties();
        InputStream input = reader.class.getClassLoader().getResourceAsStream(filename);
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
