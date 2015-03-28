/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpa.spider.pe.view.execution.logic;

import java.util.List;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.ComponentType;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.Relationship;
import br.ufpa.spider.pe.view.execution.model.RelationshipType;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;

/**
 *
 * @author Specktro
 */
class RelationshipValidator {

    private static boolean validateBasicProperties(Relationship relationship, ValidationMessagesGroup messages) {
        if (relationship.getType() == null) {
            if (relationship.getId() == null) {
                messages.add(new ValidationMessage("O relacionamento avaliado não apresenta identificador.",
                                                   ValidationMessageType.ERROR));
                messages.add(new ValidationMessage("O relacionamento avaliado não apresenta tipo.",
                                                   ValidationMessageType.ERROR));
            } else {
                messages.add(new ValidationMessage("O relacionamento com identificador\"" + relationship.getId() + "\" não apresenta tipo.",
                                                   ValidationMessageType.ERROR));
            }
            return false;
        } else {
            if (relationship.getId() == null) {
                messages.add(new ValidationMessage("O relacionamento do tipo " + relationship.getType().toStringInPortuguese() + "\n"
                                                   + "não apresenta identificador.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        }
        Component source = Modelling.getModelling().getComponent(relationship.getSourceId());
        if (source == null && relationship.getType() != RelationshipType.DEPENDENCY) {
            messages.add(new ValidationMessage("O relacionamento do tipo " + relationship.getType().toStringInPortuguese() + " não apresenta\n"
                                               + "uma origem válida.\n",
                                               ValidationMessageType.ERROR));
            return false;
        }

        if (source != null && source.getType() == ComponentType.NOTE && relationship.getType() != RelationshipType.ANCHOR) {
            messages.add(new ValidationMessage("O relacionamento do tipo " + relationship.getType().toStringInPortuguese() + " não pode ser\n"
                                               + "utilizado com um componente do tipo Nota.\n",
                                               ValidationMessageType.ERROR));
            return false;
        }
        Component destination = Modelling.getModelling().getComponent(relationship.getDestinationId());
        if (destination == null && relationship.getType() != RelationshipType.DEPENDENCY) {
            messages.add(new ValidationMessage("O relacionamento do tipo " + relationship.getType().toStringInPortuguese() + " não apresenta\n"
                                               + "um destino válido.\n",
                                               ValidationMessageType.ERROR));
            return false;
        }
        if (destination != null && destination.getType() == ComponentType.NOTE && relationship.getType() != RelationshipType.ANCHOR) {
            messages.add(new ValidationMessage("O relacionamento do tipo " + relationship.getType().toStringInPortuguese() + " não pode ser\n"
                                               + "utilizado com um componente do tipo Nota.\n",
                                               ValidationMessageType.ERROR));
            return false;
        }
        if (source != null && destination != null && source.getId().equals(destination.getId())) {
            messages.add(new ValidationMessage("Auto relacionamentos não são permitidos.\n",
                                               ValidationMessageType.ERROR));
            return false;
        }
        for (Relationship relationshipFromList : Modelling.getModelling().getRelationships()) {
            if ((relationshipFromList.getSourceId().equals(relationship.getSourceId()))
                && (relationshipFromList.getDestinationId().equals(relationship.getDestinationId()))
                && relationshipFromList.getType().equals(relationship.getType())
                && !relationshipFromList.getId().equals(relationship.getId())) {
                messages.add(new ValidationMessage("Relacionamentos duplicados não são permitidos.", ValidationMessageType.ERROR));
                return false;
            }
        }
        if (!validateStereotypes(relationship, messages)) {
            return false;
        }
        if (relationship.getLabel() == null) {
            relationship.setLabel("");
        }
        return true;
    }

    private static boolean isAValidStereotype(RelationshipType type, int stereotypeId) {
        List<StereotypesGroup> stereotypesGroups = Modelling.getModelling().getStereotypesGroups();
        for (StereotypesGroup stereotypesGroup : stereotypesGroups) {
            if (stereotypesGroup.getType().equalsToComponentOrRelationshipType(type)) {
                if (stereotypesGroup.getStereotypesIds().contains(stereotypeId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validateStereotypes(Relationship relationship, ValidationMessagesGroup messages) {
        for (Integer i : relationship.getStereotypesIds()) {
            if (!isAValidStereotype(relationship.getType(), i)) {
                String message;
                if (Modelling.getModelling().getStereotype(i) == null) {
                    message = "O relacionamento apresenta um estereótipo inexistente.";
                } else {
                    message = "Um relacionamento do tipo \"" + relationship.getType().toStringInPortuguese() + "\" não pode receber\n"
                              + "o esteriótipo \"" + Modelling.getModelling().getStereotype(i).getName() + "\"";
                }
                messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
                return false;
            }
        }
        return true;
    }

    static boolean validateCommit(Relationship relationship, ValidationMessagesGroup messages) {
        if (!validateBasicProperties(relationship, messages)) {
            return false;
        } else {
            if ((relationship.getType() == RelationshipType.AGGREGATION)
                || (relationship.getType() == RelationshipType.GENERALIZATION)) {
                return validateClassRelationship(relationship, messages);
            } else if (relationship.getType() == RelationshipType.ANCHOR) {
                return validateAnchor(relationship, messages);
            } else if (relationship.getType() == RelationshipType.ASSOCIATION) {
                return validateAssociation(relationship, messages);
            } else if (relationship.getType() == RelationshipType.COMPOSITION) {
                return validateComposition(relationship, messages);
            } else if (relationship.getType() == RelationshipType.DEPENDENCY) {
                //return validateDependency(relationship, messages);
            } else if (relationship.getType() == RelationshipType.TRANSITION) {
                return validateTransition(relationship, messages);
            }
        }
        return true;
    }

    private static boolean validateClassRelationship(Relationship relationship, ValidationMessagesGroup messages) {
        Component source = Modelling.getModelling().getComponent(relationship.getSourceId());
        Component destination = Modelling.getModelling().getComponent(relationship.getDestinationId());
        if (source.getType() != destination.getType()) {
            messages.add(new ValidationMessage("O relacionamento do tipo " + relationship.getType().toStringInPortuguese() + " não pode ser\n"
                                               + "utilizado com componentes de tipos diferentes.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        return true;
    }

    private static boolean validateAnchor(Relationship relationship, ValidationMessagesGroup messages) {
        Component source = Modelling.getModelling().getComponent(relationship.getSourceId());
        Component destination = Modelling.getModelling().getComponent(relationship.getDestinationId());
        for (Relationship relationshipFromList : Modelling.getModelling().getRelationships()) {
            if (relationship.getSourceId().equals(relationshipFromList.getDestinationId())
                && relationship.getDestinationId().equals(relationshipFromList.getSourceId())) {
                messages.add(new ValidationMessage("Relacionamentos duplicados não são permitidos.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        }
        if (source.getType() != ComponentType.NOTE && destination.getType() != ComponentType.NOTE) {
            messages.add(new ValidationMessage("A Âncora de Nota deve relacionar pelo\n"
                                               + "menos um componente do tipo Nota.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        return true;
    }

    private static boolean validateAssociation(Relationship relationship, ValidationMessagesGroup messages) {
        Component source = Modelling.getModelling().getComponent(relationship.getSourceId());
        Component destination = Modelling.getModelling().getComponent(relationship.getDestinationId());
        if (source.getType() == ComponentType.TASK_DEFINITION) {
            if (destination.getType() == ComponentType.WORK_PRODUCT_DEFINITION) {
                return true;
            } else {
                messages.add(new ValidationMessage("Quando um relacionamento do tipo Associação tem como origem\n"
                                                   + "um componente do tipo Definição de Tarefa o destino deve\n"
                                                   + "ser um componente do tipo Definição de Produto de Trabalho.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        } else if (source.getType() == ComponentType.TASK_USE) {
            if (destination.getType() == ComponentType.WORK_PRODUCT_USE) {
                return true;
            } else {
                messages.add(new ValidationMessage("Quando um relacionamento do tipo Associação tem como origem\n"
                                                   + "um componente do tipo Tarefa Instanciada o destino deve\n"
                                                   + "ser um componente do tipo Produto de Trabalho Instanciado.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        } else if ((source.getType() == ComponentType.WORK_PRODUCT_DEFINITION)
                       || (source.getType() == ComponentType.TOOL_DEFINITION)
                       || (source.getType() == ComponentType.ROLE_DEFINITION)) {
            if (destination.getType() == ComponentType.TASK_DEFINITION) {
                return true;
            } else {
                messages.add(new ValidationMessage("Quando um relacionamento do tipo Associação tem como origem\n"
                                                   + "um componente do tipo Definição de Ferramenta, Definição de\n"
                                                   + "Papel ou Definição de Produto de Trabalho o destino deve ser\n"
                                                   + "um componente do tipo Definição de Produto de Trabalho.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        } else if ((source.getType() == ComponentType.TOOL_USE)
                       || (source.getType() == ComponentType.WORK_PRODUCT_USE)
                       || (source.getType() == ComponentType.ROLE_USE)) {
            if (destination.getType() == ComponentType.TASK_USE) {
                return true;
            } else {
                messages.add(new ValidationMessage("Quando um relacionamento do tipo Associação tem como origem um\n"
                                                   + "componente do tipo Ferramenta Instanciada, Papel Instanciado\n"
                                                   + "ou Produto de Trabalho Instanciado o destino deve ser um\n"
                                                   + "componente do tipo Definição de Produto de Trabalho.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        } else if (source.getType() == ComponentType.GUIDANCE) {
            if (destination.getType() == ComponentType.TASK_USE
                || destination.getType() == ComponentType.TASK_DEFINITION) {
                return true;
            } else {
                messages.add(new ValidationMessage("Quando um relacionamento do tipo Associação tem como origem um\n"
                                                   + "componente do tipo Procedimento o destino deve ser um\n"
                                                   + "componente do tipo Definição de Tarefa ou Tarefa Instanciada.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean validateComposition(Relationship relationship, ValidationMessagesGroup messages) {
        Component source = Modelling.getModelling().getComponent(relationship.getSourceId());
        Component destination = Modelling.getModelling().getComponent(relationship.getDestinationId());
        if ((source.getType() != ComponentType.PROCESS_PACKAGE)
            && (destination.getType() != ComponentType.PROCESS)
            && source.getType() != destination.getType()) {
            messages.add(new ValidationMessage("O relacionamento do tipo Composição não pode ser\n"
                                               + "utilizado com componentes de tipos diferentes\n"
                                               + "no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        return true;
    }

    private static boolean validateDependency(Relationship relationship, ValidationMessagesGroup messages) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static boolean validateTransition(Relationship relationship, ValidationMessagesGroup messages) {
        Component source = Modelling.getModelling().getComponent(relationship.getSourceId());
        Component destination = Modelling.getModelling().getComponent(relationship.getDestinationId());
        if (source.getType() == ComponentType.FINAL_STATE) {
            messages.add(new ValidationMessage("O componente Estado Final apenas pode ser destino\n"
                                               + "de relacionamentos do tipo Transição.",
                                               ValidationMessageType.ERROR));
            return false;
        } else if (source.getType() == ComponentType.JOIN) {
            for (Relationship relationshipFromList : Modelling.getModelling().getRelationships()) {
                if (relationshipFromList.getId() != relationship.getId()) {
                    if (relationshipFromList.getSourceId().equals(relationship.getSourceId())) {
                        messages.add(new ValidationMessage("O componente Junção somente pode ser origem de\n"
                                                           + "um único relacionamento do tipo Transição",
                                                           ValidationMessageType.ERROR));
                        return false;
                    }
                }
            }
        } else if (destination.getType() == ComponentType.FORK) {
            for (Relationship relationshipFromList : Modelling.getModelling().getRelationships()) {
                if (relationshipFromList.getId() != relationship.getId()) {
                    if (relationshipFromList.getDestinationId().equals(relationship.getDestinationId())) {
                        messages.add(new ValidationMessage("O componente Bifurcação somente pode ser destino de\n"
                                                           + "um único relacionamento do tipo Transição",
                                                           ValidationMessageType.ERROR));
                        return false;
                    }
                }
            }
        } else if (destination.getType() == ComponentType.INITIAL_STATE) {
            messages.add(new ValidationMessage("O componente Estado Inicial apenas pode ser origem\n"
                                               + "de relacionamentos do tipo Transição.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        return true;
    }

    static boolean validateRemove(Relationship relationship, ValidationMessagesGroup messages) {
        Component source = Modelling.getModelling().getComponent(relationship.getSourceId());
        if (source != null && source.getType() == ComponentType.PROCESS_PACKAGE) {
            messages.add(new ValidationMessage("O relacionamento não pode ser removido no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        return true;
    }

}
