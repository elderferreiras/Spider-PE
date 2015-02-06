package br.ufpa.spider.pe.view.pm.logic;

import java.util.ArrayList;
import java.util.List;

class ValidationMessagesGroup {

    private List<ValidationMessage> validationMessages;

    ValidationMessagesGroup() {
        validationMessages = new ArrayList<ValidationMessage>();
    }

    void add(ValidationMessage message) {
        validationMessages.add(message);
    }

    int size() {
        return validationMessages.size();
    }

    ValidationMessage get(int index) {
        return validationMessages.get(index);
    }

}
