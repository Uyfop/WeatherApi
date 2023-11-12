import java.io.IOException;

public interface Exporter
{
    void export(Object data, String filePath) throws IOException;
}