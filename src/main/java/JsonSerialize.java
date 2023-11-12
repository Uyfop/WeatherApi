import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonSerialize implements Exporter
{
    @Override
    public void export(Object data, String filePath) throws IOException
    {
        try (Writer writer = new FileWriter(filePath))
        {
            Gson gson = new Gson();
            gson.toJson(data, writer);
        }
    }
}