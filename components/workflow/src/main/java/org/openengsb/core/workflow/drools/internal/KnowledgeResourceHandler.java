package org.openengsb.core.workflow.drools.internal;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentConfiguration;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.ResourceChangeNotifier;
import org.drools.io.ResourceChangeScanner;
import org.drools.io.ResourceChangeScannerConfiguration;
import org.drools.io.ResourceFactory;
import org.openengsb.core.workflow.api.WorkflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnowledgeResourceHandler {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(KnowledgeResourceHandler.class);

    private static final String CHANGE_SET_TEMPLATE =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
                + "<change-set xmlns=\"http://drools.org/drools-5.0/change-set\" "
                + "xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xs:schemaLocation=\"http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd\" >"
                + "<add> "
                + "<resource source=\"%s\" type=\"DRL\"/> "
                + "<resource source=\"%s\" type=\"DRF\"/> "
                + "<resource source=\"%s\" type=\"BPMN2\"/> "
                + "</add>"
                + "</change-set>";

    private KnowledgeAgent kAgent;
    private ResourceChangeScanner changeScanner;
    private ResourceChangeNotifier changeNotifier;

    private File bpmnFolder;
    private File drfFolder;
    private File drlFolder;

    public void setUp() {
        setupFolders();
        setupKAgent();
        setupFileScanner();
        kAgent.applyChangeSet(ResourceFactory
            .newClassPathResource("changeset.xml"));
        changeNotifier.start();
        changeScanner.start();
    }

    public void destroy() {
        changeNotifier.stop();
        changeScanner.stop();
    }

    public KnowledgeBase getKnowledgeBase() {
        return kAgent.getKnowledgeBase();
    }

    private void setupKAgent() {
        KnowledgeAgentConfiguration aconf = KnowledgeAgentFactory
            .newKnowledgeAgentConfiguration();
        aconf.setProperty("drools.agent.scanDirectories", "true");
        aconf.setProperty("drools.agent.scanResources", "true");
        aconf.setProperty("drools.agent.monitorChangeSetEvents", "true");
        aconf.setProperty("drools.agent.newInstance", "false");
        KnowledgeAgent kAgent = KnowledgeAgentFactory.newKnowledgeAgent(
            "OpenengsbAgent", aconf);
        kAgent.addEventListener(new LogKnowledgeAgentEventListener());
        this.kAgent = kAgent;
    }

    private void setupFileScanner() {
        changeScanner = ResourceFactory.getResourceChangeScannerService();
        changeNotifier = ResourceFactory.getResourceChangeNotifierService();

        ResourceChangeScannerConfiguration scannerConf = changeScanner
            .newResourceChangeScannerConfiguration();
        scannerConf.setProperty("drools.resource.scanner.interval", "10");
        changeScanner.configure(scannerConf);
    }

    private void setupFolders() {
        try {
            FileUtils.forceMkdir(bpmnFolder);
            FileUtils.forceMkdir(drfFolder);
            FileUtils.forceMkdir(drlFolder);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new WorkflowException(e);
        }
    }

    public void setBpmnFolderPath(String bpmnFolderPath) {
        bpmnFolder = new File(bpmnFolderPath);
    }

    public void setDrfFolderPath(String drfFolderPath) {
        drfFolder = new File(drfFolderPath);
    }

    public void setDrlFolderPath(String drlFolderPath) {
        drlFolder = new File(drlFolderPath);
    }
}
