
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
        dpb.addJSONString("{\n" +
                "  \"statement\":{\n" +
                "\"interest\":24.6," +
                "      \"statementId\": 1000,\n" +
                "      \"id\": \"1000\",\n" +
                "      \"mainHolder\": {\n" +
                "        \"ssn\": \"900825-2398\",\n" +
                "        \"name\": \"Oskar Johansson\",\n" +
                "        \"streetAddress\": \"Hagvägen\",\n" +
                "        \"zipCode\": \"37024\",\n" +
                "        \"city\": \"NÄTTRABY\",\n" +
                "        \"country\": \"Sverige\"\n" +
                "      },\n" +
                "      \"coHolder\": {\n" +
                "        \"ssn\": \"350404-9135\",\n" +
                "        \"name\": \"Filip Walden\",\n" +
                "        \"streetAddress\": \"Hagvägen\",\n" +
                "        \"zipCode\": \"37024\",\n" +
                "        \"city\": \"NÄTTRABY\",\n" +
                "        \"country\": \"Sverige\"\n" +
                "      },\n" +
                "      \"statementDate\": \"2017-12-15\",\n" +
                "      \"dueDate\": \"2017-12-28\",\n" +
                "      \"ocr\": \"6107015\",\n" +
                "      \"customerTitle\": \"Personnummer\",\n" +
                "      \"totalAmount\": \"711,00\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"name\": \"Amortering\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"amount\": \"99,96\",\n" +
                "          \"description\": \"(rev)\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Amortering\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"amount\": \"500,12\",\n" +
                "          \"description\": \"(rev)\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Ränta\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"amount\": \"0,25\",\n" +
                "          \"description\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Ränta\",\n" +
                "          \"amount\": \"0,92\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"description\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Ränta, del av ränteperiod\",\n" +
                "          \"amount\": \"0,12\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"description\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Aviavgift\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"amount\": \"10,00\",\n" +
                "          \"description\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Uppläggningsavgift\",\n" +
                "          \"amount\": \"100,00\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"description\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Öresavrundning\",\n" +
                "          \"amount\": \"-0,37\",\n" +
                "          \"startDate\": \"2017-12-01\",\n" +
                "          \"endDate\": \"2017-12-31\",\n" +
                "          \"description\": \"\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"remainingDebt\": \"4 200,64\",\n" +
                "      \"autogiroStatement\": false,\n" +
                "      \"transaction\": {\n" +
                "        \"ingoingDate\": \"2017-11-16\",\n" +
                "        \"ingoingRemainingDebts\": \"4 200,64\",\n" +
                "        \"ingoingCurrency\": \"SEK\",\n" +
                "        \"outgoingDate\": \"2017-12-15\",\n" +
                "        \"outgoingCurrency\": \"SEK\",\n" +
                "        \"outgoingRemainingDebts\": \"4 200,64\",\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"amount\": \"100,00\",\n" +
                "            \"transactionDate\": \"2017-12-15\",\n" +
                "            \"transactionType\": \"Uppläggningsavgift\",\n" +
                "            \"transactionName\": \"StatementStartupFeeTransaction\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"amount\": \"0,12\",\n" +
                "            \"transactionDate\": \"2017-12-15\",\n" +
                "            \"transactionType\": \"Ränta, del av ränteperiod\",\n" +
                "            \"transactionName\": \"StatementInterestBeforeFirstStatementTransaction\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"amount\": \"0,92\",\n" +
                "            \"transactionDate\": \"2017-12-15\",\n" +
                "            \"transactionType\": \"Ränta\",\n" +
                "            \"transactionName\": \"StatementInterestTransaction\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"amount\": \"0,25\",\n" +
                "            \"transactionDate\": \"2017-12-15\",\n" +
                "            \"transactionType\": \"Ränta\",\n" +
                "            \"transactionName\": \"StatementInterestTransaction\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"amount\": \"10,00\",\n" +
                "            \"transactionDate\": \"2017-12-15\",\n" +
                "            \"transactionType\": \"Aviseringsavgift\",\n" +
                "            \"transactionName\": \"StatementFeeTransaction\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"amount\": \"-0,37\",\n" +
                "            \"transactionDate\": \"2017-12-15\",\n" +
                "            \"transactionType\": \"Öresavrundning\",\n" +
                "            \"transactionName\": \"StatementRoundingTransaction\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"disbursementDate\": \"2017-11-08\",\n" +
                "      \"formattedTrancheList\":[{\n" +
                "        \"remainingPrincipal\":\"1000,00\",\n" +
                "        \"createDate\":\"2018-01-01\",\n" +
                "        \"state\":\"OPEN\",\n" +
                "        \"remainingTenorInMonth\":\"3\",\n" +
                "        \"remainingPrincipalExcludeStatement\":\"100,00\",\n" +
                "        \"firstStatementDueDate\":\"2018-01-01\",\n" +
                "        \"disbursementDate\":\"2018-01-01\",\n" +
                "        \"paymentPlan\":\"200,00\"\n" +
                "      }]\n" +
                "    },\n" +
                "  \"stmt\": {\n" +
                "    \"dueDate\": \"2017-12-28\",\n" +
                "    \"totalAmount\": \"711\",\n" +
                "    \"fileCreateDate\": \"2019-08-28T15:48:01.527\"\n" +
                "  },\n" +
                "  \"acc\": {\n" +
                "    \"main\": {\n" +
                "      \"id\": 123456789\n" +
                "    }\n" +
                "  },\n" +
                "  \"bgNo\": \"1111\"\n" +
                "}");

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

