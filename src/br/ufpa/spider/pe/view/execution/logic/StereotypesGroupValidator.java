package br.ufpa.spider.pe.view.execution.logic;

import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;

class StereotypesGroupValidator {

    private StereotypesGroupValidator() {
    }

    static boolean validateCommit(StereotypesGroup stereotypesGroup, ValidationMessagesGroup messages) {
        if (stereotypesGroup.getId() == null) {
            messages.add(new ValidationMessage("O grupo de estereótipos avaliado não apresenta identificador.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        if (stereotypesGroup.getStereotypesIds().size() == 0) {
            messages.add(new ValidationMessage("O grupo de estereótipos não apresenta estereótipos.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        if (stereotypesGroup.getType() == null) {
            messages.add(new ValidationMessage("O grupo de estereótipos não apresenta um tipo de elemento.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        for (Integer id : stereotypesGroup.getStereotypesIds()) {
            if (Modelling.getModelling().getStereotype(id) == null) {
                messages.add(new ValidationMessage("O grupo de estereótipos referencia um estereótipo inexistente.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        }
        for (StereotypesGroup stereotypesGroupFromList : Modelling.getModelling().getStereotypesGroups()) {
            if (stereotypesGroupFromList.getType() == stereotypesGroup.getType() && !stereotypesGroup.getId().equals(stereotypesGroupFromList.getId())) {
                for (Integer idFromList : stereotypesGroupFromList.getStereotypesIds()) {
                    for (Integer id : stereotypesGroup.getStereotypesIds()) {
                        if (id.equals(idFromList)) {
                            messages.add(new ValidationMessage("Dois grupos de estereótipos relacionados com um mesmo tipo\n"
                                                               + "de elemento devem apresentar estereótipos diferentes.",
                                                               ValidationMessageType.ERROR));
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
