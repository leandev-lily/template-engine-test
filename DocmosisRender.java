
import com.docmosis.SystemManager;
import com.docmosis.document.DocumentProcessor;
import com.docmosis.template.population.DataProviderBuilder;
import com.docmosis.util.Configuration;
import org.json.com.docmosis.JSONException;

import java.math.BigDecimal;
import java.util.*;

import java.io.File;

/**
 * A simple example showing Docmosis creating a PDF with dynamic data from a
 * DOC template.
 */
public class DocmosisRender {
    public static void main(String[] args) throws JSONException {
        String key = new String("");
        String site = new String("");
//        String officePath = new String("/Applications/LibreOffice.app/Contents");
        String officePath = new String("/usr/lib/libreoffice/");

        if (key.startsWith("XXXX")) {
            System.err.println("\nPlease set your license key");
            System.exit(1);
        }
        if (!new File(officePath).isDirectory() || !new File(officePath).canRead()) {
            System.err.println("\nPlease check \"officePath\" is set to the " +
                    "install dir for OpenOffice or LibreOffice");
            System.exit(1);
        }

        // Create the initialisation configuration
        Configuration config = new Configuration(key, site, officePath);

        // Tell Docmosis to use one embedded converter
        config.setConverterPoolConfiguration("1");

        // Use the DataProviderBuilder to build the data provider from a String array.
        DataProviderBuilder dpb = new DataProviderBuilder();

        Map map = new HashMap();
        map.put("amount", "2000.00");
        dpb.addAll(map);
        dpb.add("acc.fundingSource.name", "Erik Penser Bank");
        dpb.addJSONString("{\"acc\":{\"interest\":24.9}}");
        dpb.addJavaObject(new Helper(), "helper");
        dpb.addJavaObject(new Formatter(), "formatter");
       

        try {

            // Initialise the system based on configuration
            SystemManager.initialise(config);

            File templateFile = new File("DocmosisTestTemplate.odt");

            if (!templateFile.canRead()) {
                System.err.println("\nCannot find '" + templateFile + "' in: " + new File("").getCanonicalPath());
            } else {
                // Create the document
                long start = System.currentTimeMillis();
                for (int i = 0; i < 200; i++) {
                    DocumentProcessor.renderDoc(templateFile, new File("./pdf/DocmosisTestResult" + i + ".pdf"), dpb.getDataProvider());
                }
                long end = (System.currentTimeMillis() - start);
                System.out.println("Run " + end + " millis " + end / 1000 + "s for 200 files...");
            }
        } catch (Exception e) {
            System.err.println("\nPlease check the following: " + e.getMessage());
        } finally {
            // shutdown the system
            SystemManager.release();
        }
    }
}

