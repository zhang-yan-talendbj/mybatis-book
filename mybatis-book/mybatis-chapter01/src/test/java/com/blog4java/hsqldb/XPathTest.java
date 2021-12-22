package zy.study.mb;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by yan.zhang on 2-11.
 */
public class XPathTest {

    @Test
    public void testXPath() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();

        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                //System.out.println(exception);
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                //System.out.println(exception);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                //System.out.println(exception);
            }
        });
        Document doc = builder.parse("src/test/java/resources/inventory.xml");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        XPathExpression expr = xPath.compile("//book[author='Neal Stepherson']/title/text()");
        Object evaluate = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) evaluate;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            System.out.println(item.getNodeName() + "," + item.getNodeType() + "," + item.getNodeValue());
        }
    }
}
