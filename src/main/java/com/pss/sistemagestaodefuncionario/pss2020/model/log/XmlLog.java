package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlLog implements ILog {

    private Document document;

    public XmlLog() throws Exception {
        createFile();
    }

    @Override
    public void write(Employee employee, String action) throws Exception {
        createAppends(employee, action);
        writeDocument();
    }

    @Override
    public void write(Employee employee) throws Exception {
        createAppends(employee);
        writeDocument();
    }

    @Override
    public void write(EmployeeCollection employeeCollection) throws Exception {
        createAppends(employeeCollection);
        writeDocument();
    }

    @Override
    public void write(String errorMessage) throws Exception {
        createAppends(errorMessage);
        writeDocument();
    }

    private void createFile() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        document = documentBuilder.newDocument();
        Element system = document.createElement("SistemaGestaoPessoas");
        document.appendChild(system);
    }

    private void createAppends(Employee employee, String action) throws Exception {
        Node system = document.getFirstChild();

        Element element = document.createElement("Element");
        system.appendChild(element);

        Attr employeeNode = document.createAttribute("Employee");
        employeeNode.setValue(employee.getName());
        element.setAttributeNode(employeeNode);

        Attr actionNode = document.createAttribute("Action");
        actionNode.setValue(action);
        element.setAttributeNode(actionNode);
    }

    private void createAppends(Employee employee) throws Exception {
        Node system = document.getFirstChild();

        Element element = document.createElement("ConsultedBonusFor");
        system.appendChild(element);

        Attr employeeNode = document.createAttribute("Employee");
        employeeNode.setValue(employee.getName());
        element.setAttributeNode(employeeNode);
    }

    private void createAppends(EmployeeCollection employeeCollection) throws Exception {
        Node system = document.getFirstChild();

        Element element = document.createElement("CalculatedSalaryList");
        system.appendChild(element);

        for (Employee employee : employeeCollection.getEmployees()) {
            Element employeeNode = document.createElement("Employee");
            employeeNode.appendChild(document.createTextNode(employee.getName()));
            element.appendChild(employeeNode);
        }
    }

    private void createAppends(String errorMessage) throws Exception {
        Node system = document.getFirstChild();

        Element element = document.createElement("Error");
        system.appendChild(element);

        Attr errorNode = document.createAttribute("ErrorMessage");
        errorNode.setValue(errorMessage);
        element.setAttributeNode(errorNode);
    }

    private void writeDocument() throws Exception {
        TransformerFactory tFactory = TransformerFactory.newInstance();

        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(new File("xmlog.xml"));

        transformer.transform(source, result);
    }

}
