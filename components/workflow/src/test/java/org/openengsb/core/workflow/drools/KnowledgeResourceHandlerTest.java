package org.openengsb.core.workflow.drools;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.openengsb.core.workflow.drools.internal.KnowledgeResourceHandler;

public class KnowledgeResourceHandlerTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();
    private KnowledgeResourceHandler handler;
    private File bpmnFolder;
    private File drfFolder;
    private File drlFolder;
    

    @Before
    public void setUp() throws Exception {
        bpmnFolder = temp.newFolder("bpmn");
        drfFolder = temp.newFolder("drf");
        drlFolder = temp.newFolder("drl");

        handler = new KnowledgeResourceHandler();
        handler.setBpmnFolderPath(bpmnFolder.getPath());
        handler.setDrfFolderPath(drfFolder.getPath());
        handler.setDrlFolderPath(drlFolder.getPath());
        handler.setScanInterval(2); 
    }

    @After
    public void tearDown() throws Exception {
        handler.destroy();
    }

    @Test
    public void testDeployDRL_shouldDeployGlobals() {
        handler.setUp();
    }


}
