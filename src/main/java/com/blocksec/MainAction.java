package com.blocksec;

import com.blocksec.components.JsonDialog;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;


public class MainAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        boolean visible = isActionAvailable(e);
        final Presentation presentation = e.getPresentation();
        presentation.setEnabled(visible);
        presentation.setVisible(visible);
    }

    /**
     * Action是否可用
     */
    private boolean isActionAvailable(AnActionEvent e) {
        final VirtualFile file = getVirtualFiles(e);
        if (getEventProject(e) != null && file != null) {
            final FileType fileType = file.getFileType();
            return fileType.toString().contains("JSXHarmonyFileType")
                    || fileType.toString().contains("TypeScriptJSXFileType")
                    || FileTypeManager.getInstance().getStdFileType("TypeScript").equals(fileType)
                    || StdFileTypes.JS.equals(fileType);
        }
        return false;
    }

    private VirtualFile getVirtualFiles(AnActionEvent e) {
        return PlatformDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        // TIP: 有时候debug的时候经常拿不到editor,可能是idea的bug,正常运行模式下是没问题的
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        JsonDialog dialog = new JsonDialog(project, editor);
        dialog.show();
    }

}
