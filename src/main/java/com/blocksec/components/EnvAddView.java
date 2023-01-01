package com.blocksec.components;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class EnvAddView extends DialogWrapper {
    private JPanel panel;
    private JTextField projectNameInput;
    private JTextField projectIdInput;
    private JTextField projectTokenInput;
    private JLabel projectNameLable;
    private JLabel projectIdLable;
    private JLabel projectTokenLable;

    private String title;
    private String validMsg;


    public EnvAddView(String title, String validMsg) {
        super(false);
        this.title = title;
        this.validMsg = validMsg;
        init();
        setTitle(title);
    }



    @Override
    protected @Nullable JComponent createCenterPanel() {
        return panel;
    }

    protected ValidationInfo doValidate() {
        if (getProjectName() == null || getProjectName().length() <= 0) {
            return new ValidationInfo(getValidMsg());
        }
        if (getProjectId() == null || getProjectId().length() <= 0) {
            return new ValidationInfo(getValidMsg());
        }
        if (getProjectToken() == null || getProjectToken().length() <= 0) {
            return new ValidationInfo(getValidMsg());
        }
        return super.doValidate();
    }

    public JTextField getProjectNameInput() {
        return projectNameInput;
    }

    public JTextField getProjectIdInput() {
        return projectIdInput;
    }

    public JTextField getProjectTokenInput() {
        return projectTokenInput;
    }

    public JLabel getProjectNameLable() {
        return projectNameLable;
    }

    public JLabel getProjectIdLable() {
        return projectIdLable;
    }

    public JLabel getProjectTokenLable() {
        return projectTokenLable;
    }

    public String getProjectName() {
        return this.getProjectNameInput().getText();
    }

    public String getProjectId() {
        return this.getProjectIdInput().getText();
    }

    public String getProjectToken() {
        return this.getProjectTokenInput().getText();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public String getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(String validMsg) {
        this.validMsg = validMsg;
    }
}
