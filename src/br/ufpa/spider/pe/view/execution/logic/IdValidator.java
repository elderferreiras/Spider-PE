package br.ufpa.spider.pe.view.execution.logic;

import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.HumanResource;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.Relationship;
import br.ufpa.spider.pe.view.execution.model.Stereotype;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;

class IdValidator {

    private static int nextAvailableId;

    private IdValidator() {
    }

    static void initialize() {
        nextAvailableId = 1;
    }

    private static void setNextAvailableID(int nextAvailableId) {
        IdValidator.nextAvailableId = nextAvailableId;
    }

    private static int getNextAvailableId() {
        return nextAvailableId++;
    }

    private static int getLastUsedId() {
        return nextAvailableId - 1;
    }

    static boolean validade(Object modellingElement, ValidationMessagesGroup messages) {
        if (modellingElement.getClass() == Component.class) {
            Component component = (Component) modellingElement;
            if (component.getId() == null) {
                component.setId(getNextAvailableId());
                return true;
            } else if (isIdAvailable(component.getId())) {
                if (getLastUsedId() < component.getId()) {
                    setNextAvailableID(component.getId() + 1);
                }
                return true;
            } else {
                String message = "O identificador \""
                                 + component.getId()
                                 + "\" já está associado a outro elemento da modelagem.";
                messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
                return false;
            }
        } else if (modellingElement.getClass() == HumanResource.class) {
            HumanResource humanResource = (HumanResource) modellingElement;
            if (humanResource.getId() == null) {
                humanResource.setId(getNextAvailableId());
                return true;
            } else if (isIdAvailable(humanResource.getId())) {
                if (getLastUsedId() < humanResource.getId()) {
                    setNextAvailableID(humanResource.getId() + 1);
                }
                return true;
            } else {
                String message = "O identificador \""
                                 + humanResource.getId()
                                 + "\" já está associado a outro elemento da modelagem.";
                messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
                return false;
            }
        } else if (modellingElement.getClass() == Stereotype.class) {
            Stereotype stereotype = (Stereotype) modellingElement;
            if (stereotype.getId() == null) {
                stereotype.setId(getNextAvailableId());
                return true;
            } else if (isIdAvailable(stereotype.getId())) {
                if (getLastUsedId() < stereotype.getId()) {
                    setNextAvailableID(stereotype.getId() + 1);
                }
                return true;
            } else {
                String message = "O identificador \""
                                 + stereotype.getId()
                                 + "\" já está associado a outro elemento da modelagem.";
                messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
                return false;
            }
        } else if (modellingElement.getClass() == StereotypesGroup.class) {
            StereotypesGroup stereotypesGroup = (StereotypesGroup) modellingElement;
            if (stereotypesGroup.getId() == null) {
                stereotypesGroup.setId(getNextAvailableId());
                return true;
            } else if (isIdAvailable(stereotypesGroup.getId())) {
                if (getLastUsedId() < stereotypesGroup.getId()) {
                    setNextAvailableID(stereotypesGroup.getId() + 1);
                }
                return true;
            } else {
                String message = "O identificador \""
                                 + stereotypesGroup.getId()
                                 + "\" já está associado a outro elemento da modelagem.";
                messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
                return false;
            }
        } else if (modellingElement.getClass() == Relationship.class) {
            Relationship relationship = (Relationship) modellingElement;
            if (relationship.getId() == null) {
                relationship.setId(getNextAvailableId());
                return true;
            } else if (isIdAvailable(relationship.getId())) {
                if (getLastUsedId() < relationship.getId()) {
                    setNextAvailableID(relationship.getId() + 1);
                }
                return true;
            } else {
                String message = "O identificador \""
                                 + relationship.getId()
                                 + "\" já está associado a outro elemento da modelagem.";
                messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
                return false;
            }
        } else {
            String message = "O objeto \""
                             + modellingElement.toString()
                             + "\" não pode receber um identificador.";
            messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
            return false;
        }
    }

    static boolean isIdAvailable(int id) {
        for (Component component : Modelling.getModelling().getComponents()) {
            if (component.getId().equals(new Integer(id))) {
                return false;
            }
        }
        for (HumanResource humanResource : Modelling.getModelling().
                getHumanResources()) {
            if (humanResource.getId().equals(new Integer(id))) {
                return false;
            }
        }
        for (Relationship relationship : Modelling.getModelling().
                getRelationships()) {
            if (relationship.getId().equals(new Integer(id))) {
                return false;
            }
        }
        for (Stereotype stereotype : Modelling.getModelling().getStereotypes()) {
            if (stereotype.getId().equals(new Integer(id))) {
                return false;
            }
        }
        for (StereotypesGroup stereotypesGroup : Modelling.getModelling().
                getStereotypesGroups()) {
            if (stereotypesGroup.getId().equals(new Integer(id))) {
                return false;
            }
        }
        return true;
    }

}
