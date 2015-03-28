package br.ufpa.spider.pe.view.execution.logic;

import java.util.List;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.ComponentType;
import br.ufpa.spider.pe.view.execution.model.Diagram;
import br.ufpa.spider.pe.view.execution.model.DiagramComponent;
import br.ufpa.spider.pe.view.execution.model.DiagramRelationship;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.Relationship;
import br.ufpa.spider.pe.view.execution.model.RelationshipType;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;

class ComponentValidator {

    private ComponentValidator() {
    }

    private static boolean hasAAvailableName(Component component) {
        for (Component componentFromList : Modelling.getModelling().getComponents()) {
            if ((componentFromList.getId() != null)
                && (componentFromList.getParentId() != null)
                && (component.getId() != null)
                && (component.getParentId() != null)) {
                if ((componentFromList.getParentId().equals(component.getParentId()))
                    || (componentFromList.getId().equals(component.getParentId()))
                    || (component.getId().equals(componentFromList.getParentId()))) {
                    if ((componentFromList.getName().compareToIgnoreCase("") != 0)
                        && (componentFromList.getName().compareToIgnoreCase(component.getName()) == 0)
                        && !(componentFromList.getId().equals(component.getId()))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isAValidStereotype(ComponentType type, int stereotypeId) {
        List<StereotypesGroup> stereotypesGroups = Modelling.getModelling().
                getStereotypesGroups();
        for (StereotypesGroup stereotypesGroup : stereotypesGroups) {
            if (stereotypesGroup.getType().equalsToComponentOrRelationshipType(type)) {
                if (stereotypesGroup.getStereotypesIds().contains(stereotypeId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validadeActivityDiagramComponent(Component component, ValidationMessagesGroup messages) {
        if (component.getName() == null) {
            ComponentNameController.addName(component);
        }
        return true;
    }

    private static boolean validateBasicProperties(Component component, ValidationMessagesGroup messages) {
        if (component.getType() == null) {
            if (component.getId() == null) {
                messages.add(new ValidationMessage("O componente avaliado não apresenta identificador.",
                                                   ValidationMessageType.ERROR));
                messages.add(new ValidationMessage("O componente avaliado não apresenta tipo.",
                                                   ValidationMessageType.ERROR));
            } else {
                messages.add(new ValidationMessage("O componente com identificador\"" + component.getId() + "\" não apresenta tipo.",
                                                   ValidationMessageType.ERROR));
            }
            return false;
        } else {
            if (component.getId() == null) {
                messages.add(new ValidationMessage("O componente do tipo " + component.getType().toStringInPortuguese() + "\n"
                                                   + "não apresenta identificador.",
                                                   ValidationMessageType.ERROR));
                return false;
            }
        }
        return true;
    }

    private static boolean validateStereotypes(Component component, ValidationMessagesGroup messages) {
        for (Integer i : component.getStereotypesIds()) {
            if (!isAValidStereotype(component.getType(), i)) {
                String message;
                if (Modelling.getModelling().getStereotype(i) == null) {
                    message = "O componente \"" + component.getName() + "\" apresenta\n"
                              + "um estereótipo inexistente.";
                } else {
                    message = "O componente \"" + component.getName() + "\" não pode receber\n"
                              + "o esteriótipo \"" + Modelling.getModelling().getStereotype(i).getName() + "\"";
                }
                messages.add(new ValidationMessage(message, ValidationMessageType.ERROR));
                return false;
            }
        }
        return true;
    }

    static boolean validateCommit(Component component, ValidationMessagesGroup messages) {
        if (!validateBasicProperties(component, messages)) {
            return false;
        } else {
            if (component.getType() == ComponentType.STANDARD_PROCESS) {
                return validateStandardProcess(component, messages);
            } else if (component.getType() == ComponentType.DISCIPLINE) {
                return validateDiscipline(component, messages);
            } else if (component.getType() == ComponentType.TASK_DEFINITION) {
                return validateTaskDefinition(component, messages);
            } else if (component.getType() == ComponentType.PROCESS_PACKAGE) {
                return validateProcessPackage(component, messages);
            } else if (component.getType() == ComponentType.PROCESS) {
                return validateProcess(component, messages);
            } else if (component.getType() == ComponentType.PHASE) {
                return validatePhase(component, messages);
            } else if (component.getType() == ComponentType.ROLE_DEFINITION) {
                return validateRoleDefinition(component, messages);
            } else if (component.getType() == ComponentType.ROLE_USE) {
                return validateRoleUse(component, messages);
            } else if (component.getType() == ComponentType.GUIDANCE) {
                return validateGuidance(component, messages);
            } else if (component.getType() == ComponentType.TOOL_DEFINITION) {
                return validateToolDefinition(component, messages);
            } else if (component.getType() == ComponentType.WORK_PRODUCT_DEFINITION) {
                return validateWorkProductDefinition(component, messages);
            } else if (component.getType() == ComponentType.NOTE) {
                return validateNote(component, messages);
            } else if (component.getType() == ComponentType.MILESTONE) {
                return validateMilestone(component, messages);
            } else if (component.getType() == ComponentType.ACTIVITY
                           || component.getType() == ComponentType.ITERATION) {
                return validateWork(component, messages);
            } else if (component.getType() == ComponentType.WORK_PRODUCT_SET
                           || component.getType() == ComponentType.GUIDANCE_SET
                           || component.getType() == ComponentType.TOOL_SET
                           || component.getType() == ComponentType.ROLE_SET) {
                return validateSet(component, messages);

            } else if (component.getType() == ComponentType.INITIAL_STATE
                           || component.getType() == ComponentType.FINAL_STATE
                           || component.getType() == ComponentType.JOIN
                           || component.getType() == ComponentType.DECISION_AND_MERGE
                           || component.getType() == ComponentType.FORK) {
                return validadeActivityDiagramComponent(component, messages);
            } else if (component.getType() == ComponentType.WORK_PRODUCT_USE) {
                return validateWorkProductUse(component, messages);
            } else if (component.getType() == ComponentType.TOOL_USE) {
                return validateToolUse(component, messages);
            } else if (component.getType() == ComponentType.TASK_USE) {
                return validateTaskUse(component, messages);
            } else {
                return false;
            }
        }
    }

    private static boolean validateDiscipline(Component discipline, ValidationMessagesGroup messages) {
        if (!validateDisciplineProperties(discipline, messages)) {
            return false;
        } else if (!validateDisciplineDiagram(discipline, messages)) {
            return false;
        } else if (!validateStereotypes(discipline, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateDisciplineDiagram(Component discipline, ValidationMessagesGroup messages) {
        return true;
    }

    private static boolean validateDisciplineProperties(Component discipline, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (discipline.getName() == null) {
            ComponentNameController.addName(discipline);
            while (!hasAAvailableName(discipline)) {
                ComponentNameController.addName(discipline);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído à Disciplina recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(discipline)) {
            messages.add(new ValidationMessage("O nome \"" + discipline.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (discipline.getDiagram() == null) {
            discipline.setDiagram(new Diagram());
            messages.add(new ValidationMessage("Um Diagrama de Classes para descrever a Disciplina\n"
                                               + "\"" + discipline.getName() + "\" foi criado.",
                                               ValidationMessageType.AUTO));
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateGuidance(Component guidance, ValidationMessagesGroup messages) {
        if (!validateStereotypes(guidance, messages)) {
            return false;
        } else if (!validateGuidanceProperties(guidance, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateGuidanceProperties(Component guidance, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (guidance.getName() == null) {
            ComponentNameController.addName(guidance);
            while (!hasAAvailableName(guidance)) {
                ComponentNameController.addName(guidance);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao Procedimento recém criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(guidance)) {
            messages.add(new ValidationMessage("O nome \"" + guidance.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateMilestone(Component milestone, ValidationMessagesGroup messages) {
        if (!validateStereotypes(milestone, messages)) {
            return false;
        } else if (!validateMilestoneProperties(milestone, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateMilestoneProperties(Component milestone, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (milestone.getName() == null) {
            ComponentNameController.addName(milestone);
            while (!hasAAvailableName(milestone)) {
                ComponentNameController.addName(milestone);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao Marco recém criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(milestone)) {
            messages.add(new ValidationMessage("O nome \"" + milestone.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateNote(Component note, ValidationMessagesGroup messages) {
        if (!validateStereotypes(note, messages)) {
            return false;
        } else if (!validateNoteProperties(note, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateNoteProperties(Component note, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (note.getName() == null) {
            ComponentNameController.addName(note);
            while (!hasAAvailableName(note)) {
                ComponentNameController.addName(note);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído a Nota recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(note)) {
            messages.add(new ValidationMessage("O nome \"" + note.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validatePhase(Component phase, ValidationMessagesGroup messages) {
        if (!validateStereotypes(phase, messages)) {
            return false;
        } else if (!validatePhaseProperties(phase, messages)) {
            return false;
        } else if (!validatePhaseDiagram(phase, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validatePhaseDiagram(Component phase, ValidationMessagesGroup messages) {
        return true;
    }

    private static boolean validatePhaseProperties(Component phase, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (phase.getName() == null) {
            ComponentNameController.addName(phase);
            while (!hasAAvailableName(phase)) {
                ComponentNameController.addName(phase);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído à Fase recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(phase)) {
            messages.add(new ValidationMessage("O nome \"" + phase.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (phase.getDiagram() == null) {
            phase.setDiagram(new Diagram());

            messages.add(new ValidationMessage("Um Diagrama de Atividades para descrever\n"
                                               + "a Fase foi criado.",
                                               ValidationMessageType.AUTO));
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o tempo.">
        if (phase.getTime() == null) {
            phase.setTime(0.0f);
            messages.add(new ValidationMessage("O percentual de tempo do Processo Instanciado \"" + Modelling.getModelling().getComponent(phase.getParentId()).getName() + "\"\n"
                                               + "correspondente à Fase \"" + phase.getName() + "\" foi definido como 0%.",
                                               ValidationMessageType.AUTO));
        } else {
            float time = phase.getTime();
            for (Component component : Modelling.getModelling().getComponents()) {
                if (component.getParentId() != null && phase.getParentId() != null && component.getParentId().equals(phase.getParentId())
                    && component.getType() == ComponentType.PHASE
                    && component.getId() != phase.getId()) {
                    time += component.getTime();
                    if (time > 100) {
                        messages.add(new ValidationMessage("O tempo para conclusão do Processo Instanciado\n \""
                                                           + Modelling.getModelling().getComponent(phase.getParentId()).getName() + "\" não pode ultrapassar 100%.",
                                                           ValidationMessageType.ERROR));
                        return false;
                    }
                }
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o ciclo de vida.">
        if (phase.getDetails() == null || (phase.getDetails().compareToIgnoreCase("ITERATIVE") != 0 && phase.getDetails().compareToIgnoreCase("SEQUENTIAL") != 0)) {
            phase.setDetails("ITERATIVE");
            messages.add(new ValidationMessage("O ciclo de vida da Fase \"" + phase.getName() + "\" foi definido como Iterativo.",
                                               ValidationMessageType.AUTO));
        } else if (phase.getDetails().compareToIgnoreCase("ITERATIVE") == 0) {
            for (DiagramComponent diagramComponent : phase.getDiagram().getDiagramComponents()) {
                ComponentType type = Modelling.getModelling().getComponent(diagramComponent.getId()).getType();
                if (type == ComponentType.ACTIVITY || type == ComponentType.TASK_USE) {
                    messages.add(new ValidationMessage("O ciclo de vida Iterativo é incompativel com os\n"
                                                       + "componentes do tipo Atividade e Tarefa Instanciada.",
                                                       ValidationMessageType.ERROR));
                    return false;
                }
            }
        } else {
            for (DiagramComponent diagramComponent : phase.getDiagram().getDiagramComponents()) {
                ComponentType type = Modelling.getModelling().getComponent(diagramComponent.getId()).getType();
                if (type == ComponentType.ITERATION || type == ComponentType.MILESTONE) {
                    messages.add(new ValidationMessage("O ciclo de vida Seqüencial é incompativel com os\n"
                                                       + "componentes do tipo Iteração e Marco.",
                                                       ValidationMessageType.ERROR));
                    return false;
                }
            }
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateProcess(Component process, ValidationMessagesGroup messages) {
        if (!validateStereotypes(process, messages)) {
            return false;
        } else if (!validateProcessProperties(process, messages)) {
            return false;
        } else if (!validateProcessDiagram(process, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateProcessDiagram(Component process, ValidationMessagesGroup messages) {
        return true;
    }

    private static boolean validateProcessProperties(Component process, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (process.getName() == null) {
            ComponentNameController.addName(process);
            while (!hasAAvailableName(process)) {
                ComponentNameController.addName(process);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao Processo Instanciado récem criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(process)) {
            messages.add(new ValidationMessage("O nome \"" + process.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (process.getDiagram() == null) {
            process.setDiagram(new Diagram());
            messages.add(new ValidationMessage("Um Diagrama de Atividades para descrever o Processo Instanciado\n"
                                               + "\"" + process.getName() + "\" foi criado.",
                                               ValidationMessageType.AUTO));
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateProcessPackage(Component processPackage, ValidationMessagesGroup messages) {
        if (!validateStereotypes(processPackage, messages)) {
            return false;
        } else if (!validateProcessPackageProperties(processPackage, messages)) {
            return false;
        } else if (!validateProcessPackageDiagram(processPackage, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateProcessPackageDiagram(Component processPackage, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Verifica se o processo padrão já foi criado.">
        if (Modelling.getModelling().getStandardProcess() == null) {
            messages.add(new ValidationMessage("O Conjunto de Processos Instanciados deve\n"
                                               + "ser definido depois do Processo Padrão.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        Diagram diagram = processPackage.getDiagram();
        if (ModellingController.getAutoCreateRequiredElements()) {
            // <editor-fold defaultstate="collapsed" desc="Adiciona os elementos obrigatórios do diagrama quando necessário.">
            // <editor-fold defaultstate="collapsed" desc="Adiciona o Conjunto de Processos Instanciados se necessário.">
            if (diagram.getDiagramComponent(processPackage.getId()) == null) {
                DiagramComponent diagramComponent = new DiagramComponent();
                diagramComponent.setId(processPackage.getId());
                diagramComponent.setX(256.0);
                diagramComponent.setY(64.0);
                diagramComponent.setWidth(32.0);
                diagramComponent.setHeight(32.0);
                diagram.addDiagramComponent(diagramComponent);
                messages.add(new ValidationMessage("O Conjunto de Processos Instanciados foi adicinado ao Diagrama\n"
                                                   + "de Classes que descreve o Conjunto de Processos Instanciados.",
                                                   ValidationMessageType.AUTO));
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Adiciona o Processo Padrão se necessário.">
            if (diagram.getDiagramComponent(Modelling.getModelling().getStandardProcess().getId()) == null) {
                DiagramComponent diagramComponent = new DiagramComponent();
                diagramComponent.setId(Modelling.getModelling().getStandardProcess().getId());
                diagramComponent.setX(64.0);
                diagramComponent.setY(64.0);
                diagramComponent.setWidth(32.0);
                diagramComponent.setHeight(32.0);
                diagram.addDiagramComponent(diagramComponent);
                messages.add(new ValidationMessage("O Processo Padrão foi adicinado ao Diagrama de Classes\n"
                                                   + "que descreve o Conjunto de Processos Instanciados.",
                                                   ValidationMessageType.AUTO));
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Adiciona o relacionamento do tipo Dependência entre o Conjunto de Processos Instanciados e o Processo Padrão se necessário.">
            Integer relationshipId = null;
            for (Relationship relationship : Modelling.getModelling().getRelationships()) {
                if (relationship.getSourceId().equals(processPackage.getId()) && relationship.getDestinationId().equals(Modelling.getModelling().getStandardProcess().getId())
                    && relationship.getType() == RelationshipType.DEPENDENCY) {
                    relationshipId = relationship.getId();
                }
            }
            if (relationshipId == null) {
                Relationship relationship = new Relationship();
                relationship.setType(RelationshipType.DEPENDENCY);
                relationship.setSourceId(processPackage.getId());
                relationship.setDestinationId(Modelling.getModelling().getStandardProcess().getId());
                if (!ModellingController.commitRelationship(relationship)) {
                    messages.add(new ValidationMessage("Não foi possível adicionar o relacionamento do tipo Dependência\n"
                                                       + "entre o Conjunto de Processos Instanciados e o Processo Padrão.",
                                                       ValidationMessageType.ERROR));
                    return false;
                }
                relationshipId = relationship.getId();
            }
            if (diagram.getDiagramRelationship(relationshipId) == null) {
                DiagramRelationship diagramRelationship = new DiagramRelationship();
                diagramRelationship.setId(relationshipId);
                diagram.addDiagramRelationship(diagramRelationship);
                messages.add(new ValidationMessage(
                        "Um relacionamento do tipo Dependência entre o\n"
                        + "Conjunto de Processos Instanciados e o Processo Padrão\n"
                        + "foi adicionado ao Diagrama de Classes que descreve o\n"
                        + "Conjunto de Processos Instanciados.",
                        ValidationMessageType.AUTO));
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Adiciona os relacionamento do tipo Composição.">
            for (DiagramComponent diagramComponent : diagram.getDiagramComponents()) {
                if (processPackage.getId() != diagramComponent.getId() && Modelling.getModelling().getComponent(diagramComponent.getId()).getType() == ComponentType.PROCESS) {
                    relationshipId = null;
                    for (Relationship relationship : Modelling.getModelling().getRelationships()) {
                        if (relationship.getSourceId().equals(processPackage.getId()) && relationship.getDestinationId().equals(diagramComponent.getId())
                            && relationship.getType() == RelationshipType.COMPOSITION) {
                            relationshipId = relationship.getId();
                        }
                    }
                    if (relationshipId == null) {
                        Relationship relationship = new Relationship();
                        relationship.setType(RelationshipType.COMPOSITION);
                        relationship.setSourceId(processPackage.getId());
                        relationship.setDestinationId(diagramComponent.getId());
                        if (!ModellingController.commitRelationship(relationship)) {
                            messages.add(new ValidationMessage("Não foi possível adicionar o relacionamento do tipo\n"
                                                               + "Composição entre o Conjunto de Processos Instanciados\n"
                                                               + "e o Processo Instanciado \"" + Modelling.getModelling().getComponent(diagramComponent.getId()).getName() + "\".",
                                                               ValidationMessageType.ERROR));
                            return false;
                        }
                        relationshipId = relationship.getId();
                    }
                    if (diagram.getDiagramRelationship(relationshipId) == null) {
                        DiagramRelationship diagramRelationship = new DiagramRelationship();
                        diagramRelationship.setId(relationshipId);
                        diagram.addDiagramRelationship(diagramRelationship);
                        messages.add(new ValidationMessage("Um relacionamento do tipo Composição entre o\n"
                                                           + "Conjunto de Processos Instanciados e o Processo Instanciado\n"
                                                           + "\"" + Modelling.getModelling().getComponent(diagramComponent.getId()).getName() + "\" foi adicionado ao Diagrama de Classes\n"
                                                           + "que descreve o Conjunto de Processos Instanciados.",
                                                           ValidationMessageType.AUTO));
                    }
                }
            }
            // </editor-fold>
            // </editor-fold>
        }
        return true;
    }

    private static boolean validateProcessPackageProperties(Component processPackage, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (processPackage.getName() == null) {
            ComponentNameController.addName(processPackage);
            while (!hasAAvailableName(processPackage)) {
                ComponentNameController.addName(processPackage);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao Conjunto de Processos Instanciados recém criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(processPackage)) {
            messages.add(new ValidationMessage("O nome \"" + processPackage.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (processPackage.getDiagram() == null) {
            processPackage.setDiagram(new Diagram());

            messages.add(new ValidationMessage("Um Diagrama de Classes para descrever o\n"
                                               + "Conjunto de Processos Instanciados foi criado.",
                                               ValidationMessageType.AUTO));
        }
// </editor-fold>
        return true;
    }

    private static boolean validateRoleDefinition(Component roleDefinition, ValidationMessagesGroup messages) {
        if (!validateStereotypes(roleDefinition, messages)) {
            return false;
        } else if (!validateRoleDefinitionProperties(roleDefinition, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateRoleDefinitionProperties(Component roleDefinition, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (roleDefinition.getName() == null) {
            ComponentNameController.addName(roleDefinition);
            while (!hasAAvailableName(roleDefinition)) {
                ComponentNameController.addName(roleDefinition);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído a Definição de Papel recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(roleDefinition)) {
            messages.add(new ValidationMessage("O nome \"" + roleDefinition.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateRoleUse(Component roleUse, ValidationMessagesGroup messages) {
        if (!validateStereotypes(roleUse, messages)) {
            return false;
        } else if (!validateRoleUseProperties(roleUse, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateRoleUseProperties(Component roleUse, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (roleUse.getName() == null) {
            ComponentNameController.addName(roleUse);
            while (!hasAAvailableName(roleUse)) {
                ComponentNameController.addName(roleUse);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao Papel Instanciado recém criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(roleUse)) {
            messages.add(new ValidationMessage("O nome \"" + roleUse.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateSet(Component set, ValidationMessagesGroup messages) {
        if (set.getName() == null) {
            ComponentNameController.addName(set);
            while (!hasAAvailableName(set)) {
                ComponentNameController.addName(set);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao\n"
                                               + set.getType().toStringInPortuguese() + " recém criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(set)) {
            messages.add(new ValidationMessage("O nome \"" + set.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        return true;
    }

    private static boolean validateStandardProcess(Component standardProcess, ValidationMessagesGroup messages) {
        if (!validateStandardProcessProperties(standardProcess, messages)) {
            return false;
        } else if (!validateStandardProcessDiagram(standardProcess, messages)) {
            return false;
        } else if (!validateStereotypes(standardProcess, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateStandardProcessDiagram(Component standardProcess, ValidationMessagesGroup messages) {
        return true;
    }

    private static boolean validateStandardProcessProperties(Component standardProcess, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (standardProcess.getName() == null) {
            ComponentNameController.addName(standardProcess);
            while (!hasAAvailableName(standardProcess)) {
                ComponentNameController.addName(standardProcess);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao Processo Padrão recém criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(standardProcess)) {
            messages.add(new ValidationMessage("O nome \"" + standardProcess.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (standardProcess.getDiagram() == null) {
            standardProcess.setDiagram(new Diagram());
            messages.add(new ValidationMessage("Um Diagrama de Pacotes para descrever\n"
                                               + "o Processo Padrão foi criado.",
                                               ValidationMessageType.AUTO));
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateTaskDefinition(Component taskDefinition, ValidationMessagesGroup messages) {
        if (!validateTaskDefinitionProperties(taskDefinition, messages)) {
            return false;
        } else if (!validateTaskDefinitionDiagram(taskDefinition, messages)) {
            return false;
        } else if (!validateStereotypes(taskDefinition, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateTaskDefinitionDiagram(Component taskDefinition, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Adiciona a Definição de Tarefa ao diagrama caso necessário.">
        if (ModellingController.getAutoCreateRequiredElements()) {
            Diagram diagram = taskDefinition.getDiagram();
            boolean isAdded = false;
            for (DiagramComponent diagramComponent : diagram.getDiagramComponents()) {
                if (diagramComponent.getId().equals(taskDefinition.getId())) {
                    isAdded = true;
                }
            }
            if (!isAdded) {
                DiagramComponent diagramComponent = new DiagramComponent();
                diagramComponent.setId(taskDefinition.getId());
                diagramComponent.setX(64.0);
                diagramComponent.setY(64.0);
                diagramComponent.setWidth(32.0);
                diagramComponent.setHeight(32.0);
                diagram.addDiagramComponent(diagramComponent);
                messages.add(new ValidationMessage("A Definição de Tarefa\"" + taskDefinition.getName() + "\"foi\n"
                                                   + "adicionada no Diagrama de Classes que a descreve.",
                                                   ValidationMessageType.OBLIGATION));
            }
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateTaskDefinitionProperties(Component taskDefinition, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (taskDefinition.getName() == null) {
            ComponentNameController.addName(taskDefinition);
            while (!hasAAvailableName(taskDefinition)) {
                ComponentNameController.addName(taskDefinition);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído a Definição de Tarefa recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(taskDefinition)) {
            messages.add(new ValidationMessage("O nome \"" + taskDefinition.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (taskDefinition.getDiagram() == null) {
            taskDefinition.setDiagram(new Diagram());
            messages.add(new ValidationMessage("Um Diagrama de Classes para descrever a Definição de Tarefa foi criado.",
                                               ValidationMessageType.AUTO));
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateTaskUse(Component taskUse, ValidationMessagesGroup messages) {
        if (!validateTaskUseProperties(taskUse, messages)) {
            return false;
        } else if (!validateTaskUseDiagram(taskUse, messages)) {
            return false;
        } else if (!validateStereotypes(taskUse, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateTaskUseDiagram(Component taskUse, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Adiciona a Tarefa Instanciada ao diagrama caso necessário.">
        if (ModellingController.getAutoCreateRequiredElements()) {
            Diagram diagram = taskUse.getDiagram();
            boolean isAdded = false;
            for (DiagramComponent diagramComponent : diagram.getDiagramComponents()) {
                if (diagramComponent.getId().equals(taskUse.getId())) {
                    isAdded = true;
                }
            }
            if (!isAdded) {
                DiagramComponent diagramComponent = new DiagramComponent();
                diagramComponent.setId(taskUse.getId());
                diagramComponent.setX(64.0);
                diagramComponent.setY(64.0);
                diagramComponent.setWidth(32.0);
                diagramComponent.setHeight(32.0);
                diagram.addDiagramComponent(diagramComponent);
                messages.add(new ValidationMessage("A Definição de Tarefa\"" + taskUse.getName() + "\"foi\n"
                                                   + "adicionada no Diagrama de Classes que a descreve.",
                                                   ValidationMessageType.OBLIGATION));
            }
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateTaskUseProperties(Component taskUse, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (taskUse.getName() == null) {
            ComponentNameController.addName(taskUse);
            while (!hasAAvailableName(taskUse)) {
                ComponentNameController.addName(taskUse);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído a Tarefa Intanciada recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(taskUse)) {
            messages.add(new ValidationMessage("O nome \"" + taskUse.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (taskUse.getDiagram() == null) {
            taskUse.setDiagram(new Diagram());
            messages.add(new ValidationMessage("Um Diagrama de Classes para descrever a Tarefa Instanciada foi criado.",
                                               ValidationMessageType.AUTO));
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o tempo.">
        if (taskUse.getTime() == null) {
            taskUse.setTime(0.0f);
            messages.add(new ValidationMessage("O percentual de tempo da "
                                               + Modelling.getModelling().getComponent(taskUse.getParentId()).getType().toStringInPortuguese()
                                               + " \"" + Modelling.getModelling().getComponent(taskUse.getParentId()).getName() + "\"\n"
                                               + "correspondente à "
                                               + taskUse.getType().toStringInPortuguese()
                                               + " \"" + taskUse.getName() + "\" foi definido como 0%.",
                                               ValidationMessageType.AUTO));
        } else {
            float time = taskUse.getTime();
            for (Component component : Modelling.getModelling().getComponents()) {
                if (component.getParentId() != null && taskUse.getParentId() != null && component.getParentId().equals(taskUse.getParentId())
                    && component.getId() != taskUse.getId()
                    && (component.getType() == ComponentType.ACTIVITY
                        || component.getType() == ComponentType.TASK_USE)) {
                    time += component.getTime();
                    if (time > 100) {
                        messages.add(new ValidationMessage("O tempo para conclusão da "
                                                           + Modelling.getModelling().getComponent(taskUse.getParentId()).getType().toStringInPortuguese() + "\n \""
                                                           + Modelling.getModelling().getComponent(taskUse.getParentId()).getName() + "\" não pode ultrapassar 100%.",
                                                           ValidationMessageType.ERROR));
                        return false;
                    }
                }
            }
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateToolDefinition(Component toolDefinition, ValidationMessagesGroup messages) {
        if (!validateStereotypes(toolDefinition, messages)) {
            return false;
        } else if (!validateToolDefinitionProperties(toolDefinition, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateToolDefinitionProperties(Component toolDefinition, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (toolDefinition.getName() == null) {
            ComponentNameController.addName(toolDefinition);
            while (!hasAAvailableName(toolDefinition)) {
                ComponentNameController.addName(toolDefinition);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído a Definição de Ferramenta recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(toolDefinition)) {
            messages.add(new ValidationMessage("O nome \"" + toolDefinition.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateToolUse(Component toolUse, ValidationMessagesGroup messages) {
        if (!validateStereotypes(toolUse, messages)) {
            return false;
        } else if (!validateToolUseProperties(toolUse, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateToolUseProperties(Component toolUse, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (toolUse.getName() == null) {
            ComponentNameController.addName(toolUse);
            while (!hasAAvailableName(toolUse)) {
                ComponentNameController.addName(toolUse);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído a Ferramenta Instanciada recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(toolUse)) {
            messages.add(new ValidationMessage("O nome \"" + toolUse.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateWork(Component work, ValidationMessagesGroup messages) {
        if (!validateStereotypes(work, messages)) {
            return false;
        } else if (!validateWorkProperties(work, messages)) {
            return false;
        } else if (!validateWorkDiagram(work, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateWorkDiagram(Component work, ValidationMessagesGroup messages) {
        return true;
    }

    private static boolean validateWorkProperties(Component work, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (work.getName() == null) {
            ComponentNameController.addName(work);
            while (!hasAAvailableName(work)) {
                ComponentNameController.addName(work);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído à " + work.getType().toStringInPortuguese() + " recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(work)) {
            messages.add(new ValidationMessage("O nome \"" + work.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o diagrama.">
        if (work.getDiagram() == null) {
            work.setDiagram(new Diagram());

            messages.add(new ValidationMessage("Um Diagrama de Atividades para descrever a\n"
                                               + work.getType().toStringInPortuguese() + " foi criado.",
                                               ValidationMessageType.AUTO));
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Valida o tempo.">
        if (work.getTime() == null) {
            work.setTime(0.0f);
            messages.add(new ValidationMessage("O percentual de tempo da "
                                               + Modelling.getModelling().getComponent(work.getParentId()).getType().toStringInPortuguese()
                                               + " \"" + Modelling.getModelling().getComponent(work.getParentId()).getName() + "\"\n"
                                               + "correspondente à "
                                               + work.getType().toStringInPortuguese()
                                               + " \"" + work.getName() + "\" foi definido como 0%.",
                                               ValidationMessageType.AUTO));
        } else {
            float time = work.getTime();
            for (Component component : Modelling.getModelling().getComponents()) {
                if (component.getParentId() != null && component.getParentId().equals(work.getParentId())
                    && component.getId() != work.getId()
                    && (component.getType() == ComponentType.ITERATION
                        || component.getType() == ComponentType.ACTIVITY
                        || component.getType() == ComponentType.TASK_USE)) {
                    time += component.getTime();
                    if (time > 100) {
                        messages.add(new ValidationMessage("O tempo para conclusão da "
                                                           + Modelling.getModelling().getComponent(work.getParentId()).getType().toStringInPortuguese() + "\n \""
                                                           + Modelling.getModelling().getComponent(work.getParentId()).getName() + "\" não pode ultrapassar 100%.",
                                                           ValidationMessageType.ERROR));
                        return false;
                    }
                }
            }
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateWorkProductDefinition(Component workProductDefinition, ValidationMessagesGroup messages) {
        if (!validateStereotypes(workProductDefinition, messages)) {
            return false;
        } else if (!validateWorkProductDefinitionProperties(workProductDefinition, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateWorkProductDefinitionProperties(Component workProductDefinition, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (workProductDefinition.getName() == null) {
            ComponentNameController.addName(workProductDefinition);
            while (!hasAAvailableName(workProductDefinition)) {
                ComponentNameController.addName(workProductDefinition);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído a Definição de Produto de Trabalho recém criada.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(workProductDefinition)) {
            messages.add(new ValidationMessage("O nome \"" + workProductDefinition.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    private static boolean validateWorkProductUse(Component workProductUse, ValidationMessagesGroup messages) {
        if (!validateStereotypes(workProductUse, messages)) {
            return false;
        } else if (!validateWorkProductUseProperties(workProductUse, messages)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateWorkProductUseProperties(Component workProductUse, ValidationMessagesGroup messages) {
        // <editor-fold defaultstate="collapsed" desc="Valida o nome.">
        if (workProductUse.getName() == null) {
            ComponentNameController.addName(workProductUse);
            while (!hasAAvailableName(workProductUse)) {
                ComponentNameController.addName(workProductUse);
            }
            messages.add(new ValidationMessage("Um nome foi atribuído ao Produto de Trabalho Intanciado recém criado.",
                                               ValidationMessageType.AUTO));
        } else if (!hasAAvailableName(workProductUse)) {
            messages.add(new ValidationMessage("O nome \"" + workProductUse.getName() + "\" não pode ser utilizado no contexto atual.",
                                               ValidationMessageType.ERROR));
            return false;
        }
        // </editor-fold>
        return true;
    }

    static boolean validateRemove(Component component, ValidationMessagesGroup messages) {
        if (component.getType() == ComponentType.GUIDANCE_SET
            || component.getType() == ComponentType.INITIAL_STATE
            || component.getType() == ComponentType.PROCESS_PACKAGE
            || component.getType() == ComponentType.ROLE_SET
            || component.getType() == ComponentType.STANDARD_PROCESS
            || component.getType() == ComponentType.TOOL_SET
            || component.getType() == ComponentType.WORK_PRODUCT_SET) {
            messages.add(new ValidationMessage("Um componente do tipo "
                                               + component.getType().toStringInPortuguese()
                                               + " não pode ser diretamente removido da modelagem.",
                                               ValidationMessageType.CONSTRAINT));
            return false;
        }
        return true;
    }

}
