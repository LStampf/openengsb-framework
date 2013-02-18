package org.openengsb.core.workflow.drools;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.drools.KnowledgeBase;
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
        handler.setScanInterval(1);
    }

    @After
    public void tearDown() throws Exception {
        handler.destroy();
    }

    @Test
    public void testStartup_shouldDeployArtifacts() throws IOException {
        copyResources();
        handler.setUp();

<<<<<<< Updated upstream
        KnowledgeBase kbase = handler.getKnowledgeBase();
=======
        KnowledgeBase kbase = handler.getKnowledgeBase(); 
>>>>>>> Stashed changes
        assertNotNull(kbase.getProcess("simpleFlow"));
        assertNotNull(kbase.getProcess("HelloWorld"));
        assertNotNull(kbase.getRule("org.openengsb", "Hello1"));
    }

    @Test
    public void testRunningInstance_shouldDeployArtifacts() throws IOException, InterruptedException {
        handler.setUp();
<<<<<<< Updated upstream

        copyResources();
        Thread.sleep(1500);

=======
        copyResources();
        Thread.sleep(1500);
        
>>>>>>> Stashed changes
        KnowledgeBase kbase = handler.getKnowledgeBase();
        assertNotNull(kbase.getProcess("simpleFlow"));
        assertNotNull(kbase.getProcess("HelloWorld"));
        assertNotNull(kbase.getRule("org.openengsb", "Hello1"));
    }

    @Test
    public void testRunningInstance_shouldUpdateRule() throws IOException, InterruptedException {
        copyResources();
        handler.setUp();

        KnowledgeBase kbase = handler.getKnowledgeBase();
        assertNotNull(kbase.getRule("org.openengsb", "Hello1"));

        File rule = new File(drlFolder.getPath() + File.separator + "hello1.rule");
        String content = FileUtils.readFileToString(rule);
        content = content.replace("Hello1", "Hello2");
        FileUtils.writeStringToFile(rule, content);
        Thread.sleep(1500);
        assertNull(kbase.getRule("org.openengsb", "Hello1"));
        assertNotNull(kbase.getRule("org.openengsb", "Hello2"));
    }

    
    @Test
    public void testUpdateWithError_shouldRemoveRule() throws IOException, InterruptedException {
        copyResources();
        handler.setUp();

        KnowledgeBase kbase = handler.getKnowledgeBase();
        assertNotNull(kbase.getRule("org.openengsb", "Hello1"));

        File rule = new File(drlFolder.getPath() + File.separator + "hello1.rule");
        String content = FileUtils.readFileToString(rule);
        content = content.replace(";", "");
        FileUtils.writeStringToFile(rule, content);
        Thread.sleep(1500);
        assertNull(kbase.getRule("org.openengsb", "Hello1"));
    }
    
    private void copyResources() throws IOException {
        FileUtils.copyDirectory(new File(KnowledgeResourceHandlerTest.class.getClassLoader().getResource("drl/")
            .getPath()), drlFolder);
        FileUtils.copyDirectory(new File(KnowledgeResourceHandlerTest.class.getClassLoader().getResource("bpmn/")
            .getPath()), bpmnFolder);
        FileUtils.copyDirectory(new File(KnowledgeResourceHandlerTest.class.getClassLoader().getResource("drf/")
            .getPath()), drfFolder);
    }
}
