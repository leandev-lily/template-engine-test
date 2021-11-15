# POC of docmosis

## How to test on docker
```
docker build -t test -f test.Dockerfile .
docker run --rm -it -v "$(pwd):/leandev" test bash
cd /leandev
javac DocmosisRender.java Helper.java Formatter.java -cp ./docmosis4.6.0.jar
java -cp ./docmosis4.6.0.jar:./barcode4j.jar:.  DocmosisRender

javac IXDocReportRenderSample.java Helper.java Formatter.java -cp ./lib/fr.opensagres.xdocreport.document-2.0.2.jar:./lib/jackson-databind-2.11.4.jar:./lib/fr.opensagres.odfdom.converter.pdf-2.0.2.jar:./lib/fr.opensagres.xdocreport.converter-2.0.2.jar:./lib/fr.opensagres.xdocreport.template-2.0.2.jar:./lib/fr.opensagres.xdocreport.document-2.0.2.jar:./lib/fr.opensagres.xdocreport.core-2.0.2.jar:./lib/fr.opensagres.poi.xwpf.converter.pdf-2.0.2.jar:./lib/fr.opensagres.odfdom.converter.core-2.0.2.jar:./lib/jackson-core-2.11.4.jar

java -cp ./lib/fr.opensagres.xdocreport.document-2.0.2.jar:./lib/jackson-databind-2.11.4.jar:./lib/fr.opensagres.odfdom.converter.pdf-2.0.2.jar:./lib/fr.opensagres.xdocreport.converter-2.0.2.jar:./lib/fr.opensagres.xdocreport.template-2.0.2.jar:./lib/fr.opensagres.xdocreport.document-2.0.2.jar:./lib/fr.opensagres.xdocreport.core-2.0.2.jar:./lib/fr.opensagres.poi.xwpf.converter.pdf-2.0.2.jar:./lib/fr.opensagres.odfdom.converter.core-2.0.2.jar:./lib/jackson-core-2.11.4.jar:.:./lib/fr.opensagres.xdocreport.document.odt-2.0.2.jar:./lib/fr.opensagres.xdocreport.template.velocity-2.0.2.jar:./lib/velocity-1.7.jar:./lib/jackson-annotations-2.11.4.jar:./lib/fr.opensagres.xdocreport.itext.extension-2.0.2.jar:./lib/itext-2.1.7.jar:./lib/fr.opensagres.xdocreport.converter.odt.odfdom-2.0.2.jar:./lib/odfdom-java-0.8.7.jar:./lib/fr.opensagres.odfdom.converter.xhtml-2.0.2.jar:./lib/commons-collections-3.2.2.jar:./lib/commons-lang-2.4.jar:./lib/oro-2.0.8.jar:./lib/xercesImpl-2.9.1.jar  IXDocReportRenderSample


```

File will be generated in name newDocument.pdf

## How to test locally
(You have to have libreOffice installed to run this locally)

Modify the class SimpleRender to point to the installation path of your libreOffice (check [readme.txt](readme.txt) for detail)

Use IDEA to open, add docmosis4.6.0.jar to project lib and click run on SimpleRender




