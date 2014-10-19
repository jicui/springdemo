import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import ognl.Ognl;
import ognl.OgnlException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jicui on 10/18/14.
 */
@Test
public class OGNLTEST {
    private static class Request{
        private Map<String,String> headers;
        private String method;
        private String content;

        public void setMethod(String method) {
            this.method = method;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        public String getHeader(String name){
            return headers.get(name);
        }
        public void setHeader(String name,String value){
            headers.put(name,value);
        }

        public String getMethod(){
            return method;
        }
        public String getJson(String jsonPath){
            Object jsonDoc = Configuration.defaultConfiguration().getProvider().parse(this.content);
            Object value = JsonPath.read(jsonDoc, jsonPath);
            if(value instanceof String){
                return String.valueOf(value);
            }else{
                return "NA";
            }
        }
        public void setJson(String name,String value){
            throw new UnsupportedOperationException("not support set");
        }
        public String getXml(String xpathExpression) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true); // never forget this!
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(this.content)));
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xpath = xPathFactory.newXPath();
                XPathExpression expr = xpath.compile(xpathExpression);
                String result =(String) expr.evaluate(doc, XPathConstants.STRING);
                return result;
            } catch (SAXException e) {
                e.printStackTrace();
            }catch (XPathExpressionException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "NA";

        }
        public void setXml(String name,String value){
            throw new UnsupportedOperationException("not support set");
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @Test
    void test_normal_property_access() throws OgnlException {
        Request request=new Request();
        Map<String,String> headers=new HashMap<String,String>();
        headers.put("h1","h1value");
        headers.put("h2","h2value");
        request.setHeaders(headers);
        request.setMethod("GET");
        String value=(String)Ognl.getValue("method",request);
        Assert.assertEquals(value,"GET");

    }

    @Test
    void test_map_property_access() throws OgnlException {
        Request request=new Request();
        ImmutableMap<String,String> headers=ImmutableMap.of("h1","h1value","h2","h2value");
        request.setHeaders(headers);
        request.setMethod("GET");
        String value=(String)Ognl.getValue("getHeader('h1')",request);
        Assert.assertEquals(value,"h1value");
        String value1=(String)Ognl.getValue("header['h1']",request);
        Assert.assertEquals(value1,"h1value");
        String value2=(String)Ognl.getValue("header['h2']",request);
        Assert.assertEquals(value2,"h2value");
        //Assert.assertEquals("header.h2","h2value");
    }

    @Test
    void test_json_property_access() throws OgnlException {
        Request request=new Request();
        ImmutableMap<String,String> headers=ImmutableMap.of("h1","h1value","h2","h2value");
        request.setHeaders(headers);
        request.setMethod("GET");
        request.setContent(getJsonString());
        String value=(String)Ognl.getValue("json['$.store.book[0].author']",request);
        Assert.assertEquals(value,"Nigel Rees");
        String value1=(String)Ognl.getValue("json['$.store.book[0,1].author']",request);
        Assert.assertEquals(value1,"NA");
    }

    @Test
    void test_xml_property_access() throws OgnlException {
        Request request=new Request();
        ImmutableMap<String,String> headers=ImmutableMap.of("h1","h1value","h2","h2value");
        request.setHeaders(headers);
        request.setMethod("GET");
        request.setContent(getXmlString());
        String value=(String)Ognl.getValue("xml['/bookstore/book[price>40]/title/text()']",request);
        Assert.assertEquals(value,"XQuery Kick Start");
        String value1=(String)Ognl.getValue("xml['//bookstore/book[price<40]/title']",request);
        Assert.assertEquals(value1,"Everyday Italian");
        String value2=(String)Ognl.getValue("xml['//bookstore/book[author=\"Erik T. Ray\"]/title/text()']",request);
        Assert.assertEquals(value2,"Learning XML");

    }


    private String getJsonString(){
        return "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";
    }

    private String getXmlString(){
        return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "\n" +
                "<bookstore>\n" +
                "\n" +
                "<book category=\"COOKING\">\n" +
                "  <title lang=\"en\">Everyday Italian</title>\n" +
                "  <author>Giada De Laurentiis</author>\n" +
                "  <year>2005</year>\n" +
                "  <price>30.00</price>\n" +
                "</book>\n" +
                "\n" +
                "<book category=\"CHILDREN\">\n" +
                "  <title lang=\"en\">Harry Potter</title>\n" +
                "  <author>J K. Rowling</author>\n" +
                "  <year>2005</year>\n" +
                "  <price>29.99</price>\n" +
                "</book>\n" +
                "\n" +
                "<book category=\"WEB\">\n" +
                "  <title lang=\"en\">XQuery Kick Start</title>\n" +
                "  <author>James McGovern</author>\n" +
                "  <author>Per Bothner</author>\n" +
                "  <author>Kurt Cagle</author>\n" +
                "  <author>James Linn</author>\n" +
                "  <author>Vaidyanathan Nagarajan</author>\n" +
                "  <year>2003</year>\n" +
                "  <price>49.99</price>\n" +
                "</book>\n" +
                "\n" +
                "<book category=\"WEB\">\n" +
                "  <title lang=\"en\">Learning XML</title>\n" +
                "  <author>Erik T. Ray</author>\n" +
                "  <year>2003</year>\n" +
                "  <price>39.95</price>\n" +
                "</book>\n" +
                "\n" +
                "</bookstore>";
    }
}
