package com.hunliji.components;

import com.hunliji.config.ConfigPersistence;
import com.hunliji.config.MyPersistenceConfiguration;
import com.hunliji.dto.ConfigDTO;
import com.intellij.openapi.options.Configurable;
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


/**
 * 配置界面
 */
public class ApplicationSettingsConfigurable implements Configurable {
    protected MyPersistenceConfiguration config;
    private JBTable table;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "yapi2ts";
    }

    private List<ConfigDTO> newConfigDTOS;

    public ApplicationSettingsConfigurable() {
        config = ConfigPersistence.getInstance().getState();
        newConfigDTOS = new ArrayList<>(config.getConfigs());
    }

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
                if (config.getConfigs().contains(configDTO)) {
                    //已存在弹出警告
                    Messages.showMessageDialog("不能添加重复的项目", "Error", Messages.getInformationIcon());
                    return;
                }
                newConfigDTOS.add(configDTO);
            }
        }
    }

    private void removeAction() {
       int row = this.table.getSelectedRow();
        List<ConfigDTO> afterDeleteConfigDTOS = new ArrayList<>();
        for (int i = 0; i < newConfigDTOS.size(); i++) {
            if (i != row) {
                afterDeleteConfigDTOS.add(newConfigDTOS.get(i));
            }
        }
        newConfigDTOS = afterDeleteConfigDTOS;
        table.setModel(new ListTableModel<>(getColumnInfo(), newConfigDTOS));
    }

    public boolean judgeEqual(List<ConfigDTO> list1, List<ConfigDTO> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            ConfigDTO dataMapping1 = list1.get(i);
            ConfigDTO dataMapping2 = list2.get(i);
            if (!dataMapping1.getToken().equals(dataMapping2.getToken())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isModified() {
        return !judgeEqual(this.newConfigDTOS, config.getConfigs());
    }

    @Override
    public void apply() {
        config.setConfigs(newConfigDTOS);
    }

    public JBTable createTable() {
        ColumnInfo<Object, Object>[] columns = getColumnInfo();
        ListTableModel<ConfigDTO> model = new ListTableModel<>(columns, newConfigDTOS);
        this.table = new JBTable(model) {
            @Override
            public Object getValueAt(int row, int column) {
                if (newConfigDTOS.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                ConfigDTO configDTO = newConfigDTOS.get(row);
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
