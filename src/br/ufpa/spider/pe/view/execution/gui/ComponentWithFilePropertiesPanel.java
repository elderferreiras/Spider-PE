package br.ufpa.spider.pe.view.execution.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.execution.logic.ModellingController;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;

public class ComponentWithFilePropertiesPanel extends javax.swing.JPanel {

    private int componentId;

    public ComponentWithFilePropertiesPanel() {
        initComponents();
    }

    public void setComponent(Component component) {
        componentId = component.getId();
        pathTextField.setText(component.getDetails());
        nameTextField.setText(component.getName());
        descriptionTextArea.setText(component.getDescription());
        objectivesTextArea.setText(component.getObjectives());
        additionalInformationsTextArea.setText(component.getAdditionalInformations());
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : component.getStereotypesIds()) {
            stringBuilder.append(Modelling.getModelling().getStereotype(i).getName());
            stringBuilder.append("\n");
        }
        stereotypesTextArea.setText(stringBuilder.toString());
        openButton.setEnabled(false);
        if (pathTextField.getText() != null && !pathTextField.getText().equals("")) {
            openButton.setEnabled(true);
        }
        stereotypesButton.setEnabled(false);
        for(StereotypesGroup stereotypesGroup : Modelling.getModelling().getStereotypesGroups()) {
            if(stereotypesGroup.getType().equalsToComponentOrRelationshipType(component.getType())) {
                stereotypesButton.setEnabled(true);
                break;
            }
        }
    }

    @Override
    public boolean requestFocusInWindow() {
        if (super.requestFocusInWindow()) {
            if (nameTextField.requestFocusInWindow()) {
                nameTextField.setSelectionStart(0);
                nameTextField.setSelectionEnd(nameTextField.getText().length());
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        objectivesLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        additionalInformationsLabel = new javax.swing.JLabel();
        pathLabel = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        stereotypesLabel = new javax.swing.JLabel();
        objectivesButton = new javax.swing.JButton();
        stereotypesButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        objectivesScrollPane = new javax.swing.JScrollPane();
        objectivesTextArea = new javax.swing.JTextArea();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        stereotypesScrollPane = new javax.swing.JScrollPane();
        stereotypesTextArea = new javax.swing.JTextArea();
        additionalInformationsScrollPane = new javax.swing.JScrollPane();
        additionalInformationsTextArea = new javax.swing.JTextArea();

        setName("Form"); // NOI18N
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.ufpa.spider.pe.view.execution.gui.Application.class).getContext().getResourceMap(ComponentWithFilePropertiesPanel.class);
        nameLabel.setText(resourceMap.getString("nameLabel.text")); // NOI18N
        nameLabel.setName("nameLabel"); // NOI18N

        nameTextField.setName("nameTextField"); // NOI18N
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        nameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameTextFieldFocusLost(evt);
            }
        });

        objectivesLabel.setText(resourceMap.getString("objectivesLabel.text")); // NOI18N
        objectivesLabel.setName("objectivesLabel"); // NOI18N

        descriptionLabel.setText(resourceMap.getString("descriptionLabel.text")); // NOI18N
        descriptionLabel.setName("descriptionLabel"); // NOI18N

        additionalInformationsLabel.setText(resourceMap.getString("additionalInformationsLabel.text")); // NOI18N
        additionalInformationsLabel.setName("additionalInformationsLabel"); // NOI18N

        pathLabel.setText(resourceMap.getString("pathLabel.text")); // NOI18N
        pathLabel.setName("pathLabel"); // NOI18N

        pathTextField.setName("pathTextField"); // NOI18N
        pathTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathTextFieldActionPerformed(evt);
            }
        });
        pathTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pathTextFieldFocusLost(evt);
            }
        });

        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setName("searchButton"); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        stereotypesLabel.setText(resourceMap.getString("stereotypesLabel.text")); // NOI18N
        stereotypesLabel.setName("stereotypesLabel"); // NOI18N

        objectivesButton.setText(resourceMap.getString("objectivesButton.text")); // NOI18N
        objectivesButton.setName("objectivesButton"); // NOI18N
        objectivesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectivesButtonActionPerformed(evt);
            }
        });

        stereotypesButton.setText(resourceMap.getString("stereotypesButton.text")); // NOI18N
        stereotypesButton.setName("stereotypesButton"); // NOI18N
        stereotypesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stereotypesButtonActionPerformed(evt);
            }
        });

        openButton.setText(resourceMap.getString("openButton.text")); // NOI18N
        openButton.setName("openButton"); // NOI18N
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        objectivesScrollPane.setName("objectivesScrollPane"); // NOI18N

        objectivesTextArea.setColumns(20);
        objectivesTextArea.setEditable(false);
        objectivesTextArea.setFont(resourceMap.getFont("objectivesTextArea.font")); // NOI18N
        objectivesTextArea.setLineWrap(true);
        objectivesTextArea.setRows(5);
        objectivesTextArea.setName("objectivesTextArea"); // NOI18N
        objectivesScrollPane.setViewportView(objectivesTextArea);

        descriptionScrollPane.setName("descriptionScrollPane"); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(resourceMap.getFont("descriptionTextArea.font")); // NOI18N
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setName("descriptionTextArea"); // NOI18N
        descriptionTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionTextAreaFocusLost(evt);
            }
        });
        descriptionScrollPane.setViewportView(descriptionTextArea);

        stereotypesScrollPane.setName("stereotypesScrollPane"); // NOI18N

        stereotypesTextArea.setColumns(20);
        stereotypesTextArea.setEditable(false);
        stereotypesTextArea.setFont(resourceMap.getFont("stereotypesTextArea.font")); // NOI18N
        stereotypesTextArea.setLineWrap(true);
        stereotypesTextArea.setRows(5);
        stereotypesTextArea.setName("stereotypesTextArea"); // NOI18N
        stereotypesScrollPane.setViewportView(stereotypesTextArea);

        additionalInformationsScrollPane.setName("additionalInformationsScrollPane"); // NOI18N

        additionalInformationsTextArea.setColumns(20);
        additionalInformationsTextArea.setFont(resourceMap.getFont("additionalInformationsTextArea.font")); // NOI18N
        additionalInformationsTextArea.setLineWrap(true);
        additionalInformationsTextArea.setRows(5);
        additionalInformationsTextArea.setName("additionalInformationsTextArea"); // NOI18N
        additionalInformationsTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                additionalInformationsTextAreaFocusLost(evt);
            }
        });
        additionalInformationsScrollPane.setViewportView(additionalInformationsTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addGap(14, 14, 14)
                        .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                    .addComponent(pathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pathLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(objectivesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objectivesButton))
                    .addComponent(objectivesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stereotypesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stereotypesButton))
                    .addComponent(stereotypesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(additionalInformationsLabel)
                    .addComponent(additionalInformationsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathLabel)
                    .addComponent(searchButton)
                    .addComponent(openButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectivesLabel)
                    .addComponent(objectivesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(objectivesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stereotypesLabel)
                    .addComponent(stereotypesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stereotypesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(additionalInformationsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(additionalInformationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateComponent() {
        Component component = Modelling.getModelling().getComponentToUpdate(ModellingController.getElementShowingPropertiesId());
        if (componentId == component.getId()) {
            component.setName(nameTextField.getText());
            component.setDescription(descriptionTextArea.getText());
            component.setObjectives(objectivesTextArea.getText());
            component.setAdditionalInformations(additionalInformationsTextArea.getText());
            component.setDetails(pathTextField.getText());
            if (!ModellingController.commitComponent(component)) {
                setComponent(Modelling.getModelling().getComponent(component.getId()));
            }
            openButton.setEnabled(false);
            if (pathTextField.getText() != null && !pathTextField.getText().equals("")) {
                openButton.setEnabled(true);
            }
        }
    }

    private void nameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusLost
        updateComponent();
}//GEN-LAST:event_nameTextFieldFocusLost

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        updateComponent();
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void objectivesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objectivesButtonActionPerformed
        /*
    	View view = (View) Application.getApplication().getMainView();
        view.getListDialog().reset();
        view.getListDialog().setTitle("Definir lista de objetivos");
        view.getListDialog().setItemsText("Lista de objetivos:");
        view.getListDialog().setNewItemText("Objetivo:");
        view.getListDialog().setData(Modelling.getModelling().getComponentToUpdate(ModellingController.getElementShowingPropertiesId()).getObjectives());
        view.showListDialog();
        if (view.getListDialog().getClickedButton() == ListDialog.ClickedButton.OK) {
            objectivesTextArea.setText(view.getListDialog().getData());
        }
        updateComponent();
        */
    }//GEN-LAST:event_objectivesButtonActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        updateComponent();
    }//GEN-LAST:event_formFocusLost

    private void descriptionTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionTextAreaFocusLost
        updateComponent();
    }//GEN-LAST:event_descriptionTextAreaFocusLost

    private void additionalInformationsTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_additionalInformationsTextAreaFocusLost
        updateComponent();
    }//GEN-LAST:event_additionalInformationsTextAreaFocusLost

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Abrir arquivo");
        JPanel mainPanel = Spider_PE_Home.execution.getMainPanel();
        int option = jFileChooser.showOpenDialog(mainPanel);
        String path = null;
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                path = jFileChooser.getSelectedFile().getAbsolutePath();
                pathTextField.setText(path);
                updateComponent();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Ocorreu um erro ao acessar o arquivo selecionado.",
                        "Erro nos dados da modelagem",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        updateComponent();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void pathTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathTextFieldActionPerformed
        updateComponent();
    }//GEN-LAST:event_pathTextFieldActionPerformed

    private void pathTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pathTextFieldFocusLost
        updateComponent();
    }//GEN-LAST:event_pathTextFieldFocusLost

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        if (pathTextField.getText() != null && !pathTextField.getText().equals("")) {
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI(pathTextField.getText());
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        try {
                            desktop.browse(uri);
                        } catch (IOException exception) {
                            JOptionPane.showMessageDialog(Spider_PE_Home.execution.getMainPanel(),
                                    "Erro ao acessar a URL indicada.",
                                    "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Spider_PE_Home.execution.getMainPanel(),
                                "Não foi possível acessar o navegador web padrão do sistema.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (URISyntaxException exception) {
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        File file = new File(pathTextField.getText());
                        try {
                            desktop.open(file);
                        } catch (IOException exception1) {
                            JOptionPane.showMessageDialog(Spider_PE_Home.execution.getMainPanel(),
                                    "Erro ao abrir o arquivo indicado.",
                                    "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Spider_PE_Home.execution.getMainPanel(),
                                "Não é possivel abrir o arquivo indicado diretamente.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(Spider_PE_Home.execution.getMainPanel(),
                        "O sistema atual não permite que a ferramenta possa acessar o caminho indicado.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Spider_PE_Home.execution.getMainPanel(),
                        "Digite o caminho de um arquivo ou uma URL",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void stereotypesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stereotypesButtonActionPerformed
       /*
    	View view = (View) Application.getApplication().getMainView();
        view.showElementStereotypesDialog();
        */
    }//GEN-LAST:event_stereotypesButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel additionalInformationsLabel;
    private javax.swing.JScrollPane additionalInformationsScrollPane;
    private javax.swing.JTextArea additionalInformationsTextArea;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton objectivesButton;
    private javax.swing.JLabel objectivesLabel;
    private javax.swing.JScrollPane objectivesScrollPane;
    private javax.swing.JTextArea objectivesTextArea;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton stereotypesButton;
    private javax.swing.JLabel stereotypesLabel;
    private javax.swing.JScrollPane stereotypesScrollPane;
    private javax.swing.JTextArea stereotypesTextArea;
    // End of variables declaration//GEN-END:variables
}
