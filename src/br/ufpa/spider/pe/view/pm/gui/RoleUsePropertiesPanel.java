package br.ufpa.spider.pe.view.pm.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import br.ufpa.spider.pe.view.pm.logic.GenericComparator;
import br.ufpa.spider.pe.view.pm.logic.ModellingController;
import br.ufpa.spider.pe.view.pm.model.Component;
import br.ufpa.spider.pe.view.pm.model.HumanResource;
import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.model.StereotypesGroup;

public class RoleUsePropertiesPanel extends javax.swing.JPanel {

    private boolean updating = false;
    private int componentId;

    public RoleUsePropertiesPanel() {
        initComponents();
    }

    public void setComponent(Component component) {
        componentId = component.getId();
        nameTextField.setText(component.getName());
        descriptionTextArea.setText(component.getDescription());
        objectivesTextArea.setText(component.getObjectives());
        additionalInformationsTextArea.setText(component.
                getAdditionalInformations());
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : component.getStereotypesIds()) {
            stringBuilder.append(Modelling.getModelling().getStereotype(i).
                    getName());
            stringBuilder.append("\n");
        }
        stereotypesTextArea.setText(stringBuilder.toString());
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) humanResourceComboBox.
                getModel();
        comboBoxModel.removeAllElements();
        humanResourceComboBox.setSelectedItem(null);
        humanresourceCapabilitiesTextArea.setText(null);
        boolean humanResourceDefined = false;
        for (HumanResource humanResource : Modelling.getModelling().
                getHumanResources()) {
            comboBoxModel.addElement(humanResource.getName());
            if (component.getDetails() != null && humanResource.getName().
                    compareToIgnoreCase(component.getDetails()) == 0) {
                humanResourceComboBox.setSelectedItem(humanResource.getName());
                humanresourceCapabilitiesTextArea.setText(humanResource.
                        getCapabilities());
                humanResourceDefined = true;
            }
        }
        List<Object> itens = new ArrayList<Object>();
        for (int i = 0; i < comboBoxModel.getSize(); ++i) {
            itens.add(comboBoxModel.getElementAt(i));
        }
        Collections.sort(itens, new GenericComparator());
        comboBoxModel.removeAllElements();
        for (Object object : itens) {
            comboBoxModel.addElement(object);
        }
        if (!humanResourceDefined && ((DefaultComboBoxModel) humanResourceComboBox.
                getModel()).getSize() > 0) {
            humanResourceComboBox.setSelectedIndex(-1);
            humanresourceCapabilitiesTextArea.setText(null);
        } else {
            for (int i = 0; i < comboBoxModel.getSize(); ++i) {
                if (((String) comboBoxModel.getElementAt(i)).compareToIgnoreCase(
                        component.getDetails()) == 0) {
                    humanResourceComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
        stereotypesButton.setEnabled(false);
        for (StereotypesGroup stereotypesGroup : Modelling.getModelling().
                getStereotypesGroups()) {
            if (stereotypesGroup.getType().equalsToComponentOrRelationshipType(component.
                    getType())) {
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
        objectivesButton = new javax.swing.JButton();
        stereotypesLabel = new javax.swing.JLabel();
        stereotypesButton = new javax.swing.JButton();
        humanResourceLabel = new javax.swing.JLabel();
        humanResourceComboBox = new javax.swing.JComboBox();
        objectivesScrollPane = new javax.swing.JScrollPane();
        objectivesTextArea = new javax.swing.JTextArea();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        stereotypesScrollPane = new javax.swing.JScrollPane();
        stereotypesTextArea = new javax.swing.JTextArea();
        additionalInformationsScrollPane = new javax.swing.JScrollPane();
        additionalInformationsTextArea = new javax.swing.JTextArea();
        humanResourceCapabilitiesLabel = new javax.swing.JLabel();
        humanresourceCapabilitiesScrollPane = new javax.swing.JScrollPane();
        humanresourceCapabilitiesTextArea = new javax.swing.JTextArea();

        setName("Form"); // NOI18N
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.ufpa.spider.pe.view.pm.gui.Application.class).getContext().getResourceMap(RoleUsePropertiesPanel.class);
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

        objectivesButton.setText(resourceMap.getString("objectivesButton.text")); // NOI18N
        objectivesButton.setName("objectivesButton"); // NOI18N
        objectivesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectivesButtonActionPerformed(evt);
            }
        });

        stereotypesLabel.setText(resourceMap.getString("stereotypesLabel.text")); // NOI18N
        stereotypesLabel.setName("stereotypesLabel"); // NOI18N

        stereotypesButton.setText(resourceMap.getString("stereotypesButton.text")); // NOI18N
        stereotypesButton.setName("stereotypesButton"); // NOI18N
        stereotypesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stereotypesButtonActionPerformed(evt);
            }
        });

        humanResourceLabel.setText(resourceMap.getString("humanResourceLabel.text")); // NOI18N
        humanResourceLabel.setName("humanResourceLabel"); // NOI18N

        humanResourceComboBox.setName("humanResourceComboBox"); // NOI18N
        humanResourceComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                humanResourceComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        humanResourceComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                humanResourceComboBoxFocusLost(evt);
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

        humanResourceCapabilitiesLabel.setText(resourceMap.getString("humanResourceCapabilitiesLabel.text")); // NOI18N
        humanResourceCapabilitiesLabel.setName("humanResourceCapabilitiesLabel"); // NOI18N

        humanresourceCapabilitiesScrollPane.setName("humanresourceCapabilitiesScrollPane"); // NOI18N

        humanresourceCapabilitiesTextArea.setColumns(20);
        humanresourceCapabilitiesTextArea.setEditable(false);
        humanresourceCapabilitiesTextArea.setFont(resourceMap.getFont("humanresourceCapabilitiesTextArea.font")); // NOI18N
        humanresourceCapabilitiesTextArea.setLineWrap(true);
        humanresourceCapabilitiesTextArea.setRows(5);
        humanresourceCapabilitiesTextArea.setName("humanresourceCapabilitiesTextArea"); // NOI18N
        humanresourceCapabilitiesScrollPane.setViewportView(humanresourceCapabilitiesTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(humanresourceCapabilitiesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(humanResourceLabel)
                            .addComponent(nameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addComponent(humanResourceComboBox, 0, 292, Short.MAX_VALUE)))
                    .addComponent(humanResourceCapabilitiesLabel)
                    .addComponent(objectivesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(objectivesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objectivesButton))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(humanResourceLabel)
                    .addComponent(humanResourceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(humanResourceCapabilitiesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(humanresourceCapabilitiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        updating = true;
        Component component = Modelling.getModelling().getComponentToUpdate(ModellingController.
                getElementShowingPropertiesId());
        if (componentId == component.getId()) {
            component.setName(nameTextField.getText());
            component.setDescription(descriptionTextArea.getText());
            component.setObjectives(objectivesTextArea.getText());
            component.setAdditionalInformations(additionalInformationsTextArea.
                    getText());
            component.setDetails(
                    (String) humanResourceComboBox.getSelectedItem());
            if (!ModellingController.commitComponent(component)) {
                setComponent(Modelling.getModelling().getComponent(component.
                        getId()));
            }
        }
        if (component.getDetails() != null) {
            for (HumanResource humanResource : Modelling.getModelling().
                    getHumanResources()) {
                if (humanResource.getName().equalsIgnoreCase(component.
                        getDetails())) {
                    humanresourceCapabilitiesTextArea.setText(humanResource.
                            getCapabilities());
                }
            }
        } else {
            humanResourceComboBox.setSelectedIndex(-1);
            humanresourceCapabilitiesTextArea.setText(null);
        }
        updating = false;
    }

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        if (!updating) {
            updateComponent();
        }
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void nameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusLost
        if (!updating) {
            updateComponent();
        }
    }//GEN-LAST:event_nameTextFieldFocusLost

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        if (!updating) {
            updateComponent();
        }
    }//GEN-LAST:event_formFocusLost

    private void descriptionTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionTextAreaFocusLost
        if (!updating) {
            updateComponent();
        }
    }//GEN-LAST:event_descriptionTextAreaFocusLost

    private void additionalInformationsTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_additionalInformationsTextAreaFocusLost
        if (!updating) {
            updateComponent();
        }
    }//GEN-LAST:event_additionalInformationsTextAreaFocusLost

    private void humanResourceComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_humanResourceComboBoxPopupMenuWillBecomeInvisible
        if (!updating) {
            updateComponent();
        }
    }//GEN-LAST:event_humanResourceComboBoxPopupMenuWillBecomeInvisible

    private void stereotypesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stereotypesButtonActionPerformed
        /*
    	View view = (View) Application.getApplication().getMainView();
        view.showElementStereotypesDialog();
        */
    }//GEN-LAST:event_stereotypesButtonActionPerformed

    private void objectivesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objectivesButtonActionPerformed
       /*
    	if (!updating) {
            View view = (View) Application.getApplication().getMainView();
            view.getListDialog().reset();
            view.getListDialog().setTitle("Definir lista de objetivos");
            view.getListDialog().setItemsText("Lista de objetivos:");
            view.getListDialog().setNewItemText("Objetivo:");
            view.getListDialog().setData(Modelling.getModelling().
                    getComponentToUpdate(ModellingController.
                    getElementShowingPropertiesId()).getObjectives());
            view.showListDialog();
            if (view.getListDialog().getClickedButton()
                == ListDialog.ClickedButton.OK) {
                objectivesTextArea.setText(view.getListDialog().getData());
            }
            updateComponent();
        }
        */
    }//GEN-LAST:event_objectivesButtonActionPerformed

    private void humanResourceComboBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_humanResourceComboBoxFocusLost
        if (!updating) {
            updateComponent();
        }
    }//GEN-LAST:event_humanResourceComboBoxFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel additionalInformationsLabel;
    private javax.swing.JScrollPane additionalInformationsScrollPane;
    private javax.swing.JTextArea additionalInformationsTextArea;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel humanResourceCapabilitiesLabel;
    private javax.swing.JComboBox humanResourceComboBox;
    private javax.swing.JLabel humanResourceLabel;
    private javax.swing.JScrollPane humanresourceCapabilitiesScrollPane;
    private javax.swing.JTextArea humanresourceCapabilitiesTextArea;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton objectivesButton;
    private javax.swing.JLabel objectivesLabel;
    private javax.swing.JScrollPane objectivesScrollPane;
    private javax.swing.JTextArea objectivesTextArea;
    private javax.swing.JButton stereotypesButton;
    private javax.swing.JLabel stereotypesLabel;
    private javax.swing.JScrollPane stereotypesScrollPane;
    private javax.swing.JTextArea stereotypesTextArea;
    // End of variables declaration//GEN-END:variables
}
