package br.ufpa.spider.pe.view.pm.logic;

import br.ufpa.spider.pe.view.pm.model.HumanResource;
import br.ufpa.spider.pe.view.pm.model.Modelling;

class HumanResourceValidator {

    private HumanResourceValidator(){}

    static boolean validateCommit(HumanResource humanResource, ValidationMessagesGroup messages) {
        boolean result = true;
        if(humanResource.getId() == null) {
            messages.add(new ValidationMessage("O recurso humano avaliado não apresenta identificador.",
                                                   ValidationMessageType.ERROR));
            result = false;
        }
        if(humanResource.getName() == null || humanResource.getName().equals("")) {
            messages.add(new ValidationMessage("O recurso humano avaliado não apresenta um nome.",
                                                   ValidationMessageType.ERROR));
            result = false;
        }
        for(HumanResource humanResourceFromList : Modelling.getModelling().getHumanResources()) {
            if(humanResource.getId() != null
                    && humanResourceFromList.getId()!= null
                    && !humanResource.getId().equals(humanResourceFromList.getId())
                    && humanResource.getName().compareToIgnoreCase(humanResourceFromList.getName())==0){
                messages.add(new ValidationMessage("O recurso humano apresenta um nome já utilizado.",
                                                   ValidationMessageType.ERROR));
            result = false;
            }
        }
        if(humanResource.getCapabilities() == null || humanResource.getCapabilities().equals("")) {
            messages.add(new ValidationMessage("O recurso humano avaliado não apresenta capacidades.",
                                                   ValidationMessageType.ERROR));
            result = false;
        }
        return result;
    }

}
