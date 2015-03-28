/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpa.spider.pe.view.execution.logic;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.execution.gui.Application;
import br.ufpa.spider.pe.view.execution.gui.Spider_PE_Execution;

/**
 *
 * @author Specktro
 */
class ValidationMessage {

    private String message;

    private ValidationMessageType type;

    ValidationMessage(String message, ValidationMessageType type) {
        this.message = message;
        this.type = type;
    }

    String getMessage() {
        return message;
    }

    ValidationMessageType getType() {
        return type;
    }

    void show() {
        JPanel mainPanel = Spider_PE_Home.execution.getMainPanel();
        if(type == ValidationMessageType.ERROR) {
            JOptionPane.showMessageDialog(mainPanel, message, "Erro nos dados da modelagem", JOptionPane.ERROR_MESSAGE);
        }else if(type == ValidationMessageType.CORRECTION) {
            JOptionPane.showMessageDialog(mainPanel, message, "Correção de erro nos dados da modelagem", JOptionPane.INFORMATION_MESSAGE);
        }else if(type == ValidationMessageType.OBLIGATION) {
            JOptionPane.showMessageDialog(mainPanel, message, "Ação obrigatória", JOptionPane.INFORMATION_MESSAGE);
        }else if(type == ValidationMessageType.CONSTRAINT) {
            JOptionPane.showMessageDialog(mainPanel, message, "Ação não permitida", JOptionPane.INFORMATION_MESSAGE);
        }else if (type == ValidationMessageType.WARNING) {
            JOptionPane.showMessageDialog(mainPanel, message, "Possível inconsistência nos dados da modelagem", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainPanel, message, "Ação automática", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
