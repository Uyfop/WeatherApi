import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.FileWriter;
import java.io.IOException;

public class XMLSerialize implements Exporter {
    @Override
    public void export(Object data, String filePath) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(WeatherDataWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            WeatherDataWrapper wrapper = new WeatherDataWrapper((WeatherData) data);

            try (FileWriter writer = new FileWriter(filePath)) {
                marshaller.marshal(wrapper, writer);
            }

            System.out.println("Data exported to XML successfully.");
        } catch (JAXBException e) {
            throw new IOException("Error exporting data to XML.", e);
        }
    }
}