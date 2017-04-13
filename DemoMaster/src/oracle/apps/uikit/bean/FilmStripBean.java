package oracle.apps.uikit.bean;

/*
 * Copyright (c) 2016, 2013, Oracle and/or its affiliates. All rights reserved.
 *
**/

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.controller.binding.TaskFlowBindingAttributes;
import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.layout.RichPanelSpringboard;

import oracle.apps.uikit.common.bean.UtilsBean;
import oracle.apps.uikit.data.Node;
import oracle.apps.uikit.memoryCache.SessionState;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class FilmStripBean implements Serializable {
    @SuppressWarnings("compatibility:-9141691170006722417")
    private static final long serialVersionUID = 1L;
    private UtilsBean _utils = new UtilsBean();
    private List<TaskFlowBindingAttributes> taskFlowBindingsList = new ArrayList<TaskFlowBindingAttributes>();
    private ComponentReference _filmStripSpringboard;

    public void setFilmStripSpringboard(RichPanelSpringboard s) {
        _filmStripSpringboard = ComponentReference.newUIComponentReference(s);
    }

    public RichPanelSpringboard getFilmStripSpringboard() {
        if (_filmStripSpringboard == null) {
            return null;
        }

        return (RichPanelSpringboard) _filmStripSpringboard.getComponent();
    }

    public void buildTaskFlowBindingsList() {
        int idx = 0;
        TaskFlowBindingAttributes tfba = new TaskFlowBindingAttributes();
        SessionState sessionState = (SessionState) _utils.getSessionScope().get("SessionState");
        for (Node node : sessionState.getFilmStripNodeList()) {
            tfba = new TaskFlowBindingAttributes();
            tfba.setId("region" + idx++);
            tfba.setTaskFlowId(new TaskFlowId(node.getTaskFlowId(), node.getTaskFlowName()));
            taskFlowBindingsList.add(tfba);
        } //loop through nodes list
    } //buildTaskFlowBindingsList

    public List<TaskFlowBindingAttributes> getTaskFlowBindingsList() {
        if (taskFlowBindingsList.size() == 0)
            buildTaskFlowBindingsList();
        return taskFlowBindingsList;
    } //getTaskFlowBindingsList

    public TaskFlowId getDynamicTaskFlowId() {
        TaskFlowId taskFlowId = new TaskFlowId("/WEB-INF/oracle/apps/uikit/flow/NotImplementedFlow.xml", "NotImplementedFlow");
        SessionState sessionState = (SessionState) _utils.getSessionScope().get("SessionState");
        for (Node node : sessionState.getNodeList()) {
            if (node.getId() == sessionState.getAccessedNodeId()) {
                taskFlowId = new TaskFlowId(node.getTaskFlowId(), node.getTaskFlowName());
                break;
            } //check accessed node
        } //loop through nodes
        return taskFlowId;
    } //getDynamicTaskFlowId

    public void toggleFilmStrip(ActionEvent actionEvent) {
        SessionState sessionState = (SessionState) _utils.getSessionScope().get("SessionState");
        if (sessionState.getFilmStripShowStrip().length() == 0)
            sessionState.setFilmStripShowStrip("noShow");
        else
            sessionState.setFilmStripShowStrip("");
        _utils.refresh(getFilmStripSpringboard());
    } //toggleFilmStrip

}//FilmStripBean
