package org.openengsb.core.workflow.drools.internal;

import org.drools.event.knowledgeagent.AfterChangeSetAppliedEvent;
import org.drools.event.knowledgeagent.AfterChangeSetProcessedEvent;
import org.drools.event.knowledgeagent.AfterResourceProcessedEvent;
import org.drools.event.knowledgeagent.BeforeChangeSetAppliedEvent;
import org.drools.event.knowledgeagent.BeforeChangeSetProcessedEvent;
import org.drools.event.knowledgeagent.BeforeResourceProcessedEvent;
import org.drools.event.knowledgeagent.KnowledgeAgentEventListener;
import org.drools.event.knowledgeagent.KnowledgeBaseUpdatedEvent;
import org.drools.event.knowledgeagent.ResourceCompilationFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogKnowledgeAgentEventListener implements
        KnowledgeAgentEventListener {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(LogKnowledgeAgentEventListener.class);

    @Override
    public void beforeChangeSetApplied(BeforeChangeSetAppliedEvent event) {
        LOGGER.debug(event.toString());
    }

    @Override
    public void afterChangeSetApplied(AfterChangeSetAppliedEvent event) {
        LOGGER.debug(event.toString());
    }

    @Override
    public void beforeChangeSetProcessed(BeforeChangeSetProcessedEvent event) {
        LOGGER.debug(event.toString());
    }

    @Override
    public void afterChangeSetProcessed(AfterChangeSetProcessedEvent event) {
        LOGGER.debug(event.toString());
    }

    @Override
    public void beforeResourceProcessed(BeforeResourceProcessedEvent event) {
        LOGGER.debug(event.toString());
    }

    @Override
    public void afterResourceProcessed(AfterResourceProcessedEvent event) {
        LOGGER.debug(event.toString());
    }

    @Override
    public void knowledgeBaseUpdated(KnowledgeBaseUpdatedEvent event) {
        LOGGER.info(event.toString());
    }

    @Override
    public void resourceCompilationFailed(ResourceCompilationFailedEvent event) {
        StringBuilder builder = new StringBuilder();
        builder.append(event.getKnowledgeBuilder().getErrors().toString());
        builder.append("\n");
        builder.append(event.getResource().toString());
        LOGGER.error(builder.toString());
    }

}
