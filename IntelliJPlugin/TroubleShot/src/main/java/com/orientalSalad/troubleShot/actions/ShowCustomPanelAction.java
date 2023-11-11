//package com.orientalSalad.troubleShot.actions;
//
//import com.intellij.openapi.actionSystem.AnAction;
//import com.intellij.openapi.actionSystem.AnActionEvent;
//import com.intellij.openapi.actionSystem.CommonDataKeys;
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.wm.ToolWindowManager;
//import com.intellij.ui.content.Content;
//import com.orientalSalad.troubleShot.MainPanel;
//
//public class ShowCustomPanelAction extends AnAction {
//
//    @Override
//    public void actionPerformed(AnActionEvent e) {
//        Project project = e.getProject();
//        if (project != null) {
//            ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
//            MainPanel customPanel = null;
//            customPanel = new MainPanel(e.getData(CommonDataKeys.EDITOR));
//
//            toolWindowManager.getToolWindow("MyCustomToolWindow").getContentManager().addContent((Content) customPanel);
//        }
//    }
//}
