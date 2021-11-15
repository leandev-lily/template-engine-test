import com.fasterxml.jackson.databind.ObjectMapper;
import fr.opensagres.odfdom.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IXDocReportRenderSample {

    public static void main(String[] args) {

        try(InputStream in = new FileInputStream("IDocTestTemplate.odt")) {
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, "templateName", TemplateEngineKind.Velocity, true);
            IContext context = report.createContext();
            context.put("amount", "2000.00");
            Map map = new HashMap();
            map.put("amount", "2000.00");
            context.putMap(map);
            context.put("acc.fundingSource.name", "Erik Penser Bank");
            context.putMap(new ObjectMapper().readValue("{\"acc\":{\"interest\":24.9}}", Map.class));
            //context.put("helper", new Helper() );
            context.put("formatter", new Formatter() );
            long start = System.currentTimeMillis();
            for (int i = 0; i < 200; i++) {
                OutputStream outputStream = new FileOutputStream("./pdf/IXDocReportTestResult" + i + ".pdf");
                Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
                PdfOptions pdfOptions = PdfOptions.create();
                options.subOptions(pdfOptions);
                report.convert(context, options, outputStream);
            }
            long end = (System.currentTimeMillis() - start);
            System.out.println("Run " + end + " millis " + end / 1000 + "s for 200 files.....");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }


    }
}
