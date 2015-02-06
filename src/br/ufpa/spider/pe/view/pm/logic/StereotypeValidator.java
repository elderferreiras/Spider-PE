package br.ufpa.spider.pe.view.pm.logic;

import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.model.Stereotype;

class StereotypeValidator {

    private StereotypeValidator() {
    }

    static boolean validateCommit(Stereotype stereotype, ValidationMessagesGroup messages) {
        boolean result = true;
        if(stereotype.getId() == null) {
            messages.add(new ValidationMessage("O estereótipo avaliado não apresenta identificador.",
                                                   ValidationMessageType.ERROR));
            result = false;
        }
        if(stereotype.getName() == null || stereotype.getName().equals("")) {
            messages.add(new ValidationMessage("O estereótipo não pode ser nulo ou em branco.",
                                                   ValidationMessageType.ERROR));
            result = false;
        }
        for(Stereotype stereotypeFromList : Modelling.getModelling().getStereotypes()) {
            if(stereotype.getId() != null
                    && stereotypeFromList.getId()!= null
                    && !stereotype.getId().equals(stereotypeFromList.getId())
                    && stereotype.getName().compareToIgnoreCase(stereotypeFromList.getName())==0){
                messages.add(new ValidationMessage("Estereótipo já presente na modelagem.",
                                                   ValidationMessageType.ERROR));
            result = false;
            }
        }
        return result;
    }

}
