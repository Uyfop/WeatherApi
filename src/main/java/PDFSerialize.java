import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PDFSerialize implements Exporter {
    @Override
    public void export(Object data, String filePath) throws IOException {
        if (!(data instanceof WeatherData)) {
            throw new IllegalArgumentException("Data must be an instance of WeatherData.");
        }
        WeatherData weatherData = (WeatherData) data;
        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {
            document.add(new Paragraph(getWeatherDataAsString(weatherData)));

        } catch (FileNotFoundException e) {
            throw new IOException("Error creating PDF file.", e);
        }
        System.out.println("Data exported to PDF successfully.");
    }
    private String getWeatherDataAsString(WeatherData weatherData) {
        return String.format("City: %s\nTemperature: %.2f\nPressure: %d\nHumidity: %d",
                weatherData.name, weatherData.main.temp, weatherData.main.pressure, weatherData.main.humidity);
    }
}
