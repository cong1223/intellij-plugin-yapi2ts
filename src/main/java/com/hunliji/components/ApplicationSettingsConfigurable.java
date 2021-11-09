package com.hunliji.components;

import com.hunliji.config.ConfigPersistence;
import com.hunliji.dto.ConfigDTO;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * 配置界面
 */
public class ApplicationSettingsConfigurable implements SearchableConfigurable {

    private ConfigPersistence configPersistence = ConfigPersistence.getInstance();
    private JBTable table;

    @NotNull
    @Override
    public String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "yapi2ts";
    }

    private List<ConfigDTO> configDTOS = configPersistence.getConfigs();

    @NotNull
    @Override
    public JComponent createComponent() {
        JBTable table = this.createTable();
        // 工具栏
        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(table);
        decorator.setPreferredSize(new Dimension(0, 300));
        // 新增
        decorator.setAddAction(actionButton -> addAction());
        // 删除
        decorator.setRemoveAction(actionButton -> removeAction());
        return decorator.createPanel();
    }

    private void addAction() {
        //add界面
        EnvAddView envAddView = new EnvAddView("添加token配置", "value cannot be empty!");

        if (envAddView.showAndGet()) {
            if (envAddView.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
                String projectName = envAddView.getProjectName();
                String projectId = envAddView.getProjectId();
                String projectToken = envAddView.getProjectToken();
                ConfigDTO configDTO = new ConfigDTO(Integer.parseInt(projectId), projectToken, projectName);
                if (configDTOS.contains(configDTO)) {
                    //已存在弹出警告
                    Messages.showMessageDialog("不能添加重复的项目", "Error", Messages.getInformationIcon());
                    return;
                }
                configDTOS.add(configDTO);
                configPersistence.setConfigs(configDTOS);
            }
        }
    }

    private void removeAction() {
       int row = this.table.getSelectedRow();
        List<ConfigDTO> newConfigDTOS = new ArrayList<>();
        for (int i = 0; i < configDTOS.size(); i++) {
            if (i != row) {
                newConfigDTOS.add(configDTOS.get(i));
            }
        }
        configDTOS = newConfigDTOS;
        table.setModel(new ListTableModel<>(getColumnInfo(), configDTOS));
        configPersistence.setConfigs(configDTOS);
    }

    @Override
    public boolean isModified() {
        if (configPersistence.getConfigs() == null) {
            return true;
        }
        //当用户修改配置参数后，在点击“OK”“Apply”按钮前，框架会自动调用该方法，判断是否有修改，进而控制按钮“OK”“Apply”的是否可用。
        return configDTOS.size() == configPersistence.getConfigs().size();
    }

    @Override
    public void apply() throws ConfigurationException {
        configPersistence.setConfigs(configDTOS);
    }

    public JBTable createTable() {
        ColumnInfo<Object, Object>[] columns = getColumnInfo();
        ListTableModel<ConfigDTO> model = new ListTableModel<>(columns, this.configDTOS);
        this.table = new JBTable(model) {
            @Override
            public Object getValueAt(int row, int column) {
                if (configDTOS.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                ConfigDTO configDTO = configDTOS.get(row);
                if (configDTO == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return configDTO.getCname();
                } else if (column == 1){
                    return configDTO.getProjectId();
                } else {
                    return configDTO.getToken();
                }
            }
        };
        table.setVisible(true);
        return this.table;
    }

    public ColumnInfo<Object, Object>[] getColumnInfo() {
        List<String> columnListName = Arrays.asList("项目名", "项目id", "token");
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[columnListName.size()];
        for (int i = 0; i < columnListName.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(columnListName.get(i)) {
                @Override
                public @Nullable Object valueOf(Object o) {
                    return o;
                }
            };
            columnArray[i] = envColumn;
        }
        return columnArray;
    }
}
