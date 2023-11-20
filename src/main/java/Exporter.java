import java.io.IOException;
import java.util.Map;

public interface Exporter
{
    void export(Object data, String filePath) throws IOException;

}