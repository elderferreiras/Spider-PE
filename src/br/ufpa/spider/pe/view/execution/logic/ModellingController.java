package br.ufpa.spider.pe.view.execution.logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tmatesoft.sqljet.core.internal.lang.SqlParser.join_constraint_return;

import br.ufpa.spider.pe.controller.JoinForkController;
import br.ufpa.spider.pe.controller.TransicaoController;
import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.JoinFork;
import br.ufpa.spider.pe.model.Log;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Transicao;
import br.ufpa.spider.pe.model.dao.JoinForkDAO;
import br.ufpa.spider.pe.model.dao.LogDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.dao.TransicaoDAO;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.execution.gui.BasicPropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.ComponentWithFilePropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.DisciplinePropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.Graph;
import br.ufpa.spider.pe.view.execution.gui.GraphComponent;
import br.ufpa.spider.pe.view.execution.gui.MilestonePropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.NameAndDescriptionPropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.PhasePropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.RelationshipPropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.RoleDefinitionPropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.RoleUsePropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.TaskDefinitionPropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.TaskUseFeedBackPanel;
import br.ufpa.spider.pe.view.execution.gui.TaskUsePropertiesPanel;
import br.ufpa.spider.pe.view.execution.gui.Spider_PE_Execution;
import br.ufpa.spider.pe.view.execution.gui.WorkPropertiesPanel;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.ComponentType;
import br.ufpa.spider.pe.view.execution.model.Diagram;
import br.ufpa.spider.pe.view.execution.model.DiagramComponent;
import br.ufpa.spider.pe.view.execution.model.DiagramComponentStereotypesGroup;
import br.ufpa.spider.pe.view.execution.model.DiagramRelationship;
import br.ufpa.spider.pe.view.execution.model.DiagramRelationshipStereotypesGroup;
import br.ufpa.spider.pe.view.execution.model.HumanResource;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.Relationship;
import br.ufpa.spider.pe.view.execution.model.RelationshipType;
import br.ufpa.spider.pe.view.execution.model.Stereotype;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.Mensagem;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphSelectionModel;

public class ModellingController {

    // --------------------------------------------------------------------------
    // Componentes de interface gráfica que são controlados por essa classe.
    // --------------------------------------------------------------------------
    private static JPanel diagramPanel;
    private static JScrollPane propertiesScrollPane = new JScrollPane();
    private static JScrollPane feedBackScrollPane = new JScrollPane();
    private static JTabbedPane propertiesTabbedPane;

    // --------------------------------------------------------------------------
    // Os painéis utilizados para exibir as informações dos componentes.
    // --------------------------------------------------------------------------
    private static BasicPropertiesPanel basicPropertiesPanel;
    private static ComponentWithFilePropertiesPanel componentWithFilePropertiesPanel;
    private static DisciplinePropertiesPanel disciplinePropertiesPanel;
    private static MilestonePropertiesPanel milestonePropertiesPanel;
    private static NameAndDescriptionPropertiesPanel nameAndDescriptionPropertiesPanel;
    private static RelationshipPropertiesPanel relationshipPropertiesPanel;
    private static RoleDefinitionPropertiesPanel roleDefinitionPropertiesPanel;
    private static RoleUsePropertiesPanel roleUsePropertiesPanel;
    private static TaskDefinitionPropertiesPanel taskDefinitionPropertiesPanel;
    private static WorkPropertiesPanel workPropertiesPanel;
    public static TaskUsePropertiesPanel taskUsePropertiesPanel;
    static TaskUseFeedBackPanel feedBackPanel;
    private static PhasePropertiesPanel phasePropertiesPanel;
    /**
     * O id do componente cujo diagrama está sendo exibido.
     */
    public static Integer componentShowingDiagramId;
    /**
     * O id do elemento cujas propriedades estão sendo exibidas.
     */
    private static Integer elementShowingPropertiesId;
    /**
     * Todos os grafos usados para representar os diagramas da modelagem
     * mapeados pelo id do componente ao qual o diagrama se refere.
     */
    private static Map<Integer, GraphComponent> graphs;
    /**
     * O grafo atualmente em uso para representar um diagrama.
     */
    public static GraphComponent currentGraph;
    /**
     * Variável que indica quando o controlodar de modelagem está pronto para
     * criar um novo componente e adicioná-lo à modelagem.
     */
    private static boolean readyToAddAComponent;
    /**
     * Váriável que indica o tipo do componente que será criado e adicionado
     * à modelagem.
     */
    private static ComponentType componentTypeToAdd;
    /**
     * Variável que indica quando o controlador de modelagem está pronto para
     * criar e adicionar um novo relacionamento à modelagem.
     */
    private static boolean readyToAddARelationship;
    /**
     * Indica qual o tipo do relacionamento que será criado e adicionado à
     * modelagem.
     */
    private static RelationshipType relationshipTypeToAdd;
    /**
     * Indica qual o componente é a origem do relacionamento que será criado e
     * adicionado à modelagem.
     */
    private static mxCell relationshipSource;
    private static mxCell relationshipDestination;

    public static boolean commitStereotype(Stereotype stereotype) {
        boolean hasValidationProblems = false;
        ValidationMessagesGroup messages = new ValidationMessagesGroup();
        if (Modelling.getModelling().getStereotype(stereotype.getId()) == null) {
            if (!IdValidator.validade(stereotype, messages)) {
                hasValidationProblems = true;
            }
            if (!StereotypeValidator.validateCommit(stereotype, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                addStereotype(stereotype);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        } else {
            if (!StereotypeValidator.validateCommit(stereotype, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                updateStereotype(stereotype);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        }
        if (!hasValidationProblems) {

        }
        return !hasValidationProblems;
    }

    public static boolean commitStereotypesGroup(
            StereotypesGroup stereotypesGroup) {
        boolean hasValidationProblems = false;
        ValidationMessagesGroup messages = new ValidationMessagesGroup();
        if (stereotypesGroup.getId() == null) {
            if (!IdValidator.validade(stereotypesGroup, messages)) {
                hasValidationProblems = true;
            }

            if (!StereotypesGroupValidator.validateCommit(stereotypesGroup,
                    messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                addStereotypesGroup(stereotypesGroup);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }

            if (!hasValidationProblems) {

            }
            return !hasValidationProblems;
        } else if (!autoCreateRequiredElements) {
            if (!StereotypesGroupValidator.validateCommit(stereotypesGroup,
                    messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                addStereotypesGroup(stereotypesGroup);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }

            if (!hasValidationProblems) {

            }
            return !hasValidationProblems;
        } else {
            return false;
        }
    }

    private static void addHumanResource(HumanResource humanResource) {
        Modelling.getModelling().addHumanResource(humanResource);
        setElementToShowProperties(elementShowingPropertiesId);
        setComponentToShowDiagram(componentShowingDiagramId);
    }

    private static void updateHumanResource(HumanResource humanResource) {
        HumanResource oldHumanResource = Modelling.getModelling()
                .getHumanResource(humanResource.getId());
        for (Component component : Modelling.getModelling().getComponents()) {
            if (component.getType() == ComponentType.ROLE_USE
                    && component.getDetails() != null
                    && component.getDetails().compareToIgnoreCase(
                            oldHumanResource.getName()) == 0) {
                component.setDetails(humanResource.getName());
            }
        }
        Modelling.getModelling().addHumanResource(humanResource);
        setElementToShowProperties(elementShowingPropertiesId);
        setComponentToShowDiagram(componentShowingDiagramId);
    }

    private static void addStereotype(Stereotype stereotype) {
        Modelling.getModelling().addStereotype(stereotype);
    }

    private static void updateStereotype(Stereotype stereotype) {
        Modelling.getModelling().addStereotype(stereotype);
        setElementToShowProperties(elementShowingPropertiesId);
        setComponentToShowDiagram(componentShowingDiagramId);
    }

    private static void addStereotypesGroup(StereotypesGroup stereotypesGroup) {
        Modelling.getModelling().addStereotypesGroup(stereotypesGroup);
    }

    public static boolean removeStereotypesGroup(
            StereotypesGroup stereotypesGroup) {
        JPanel mainPanel = Spider_PE_Home.execution.getMainPanel();
        int selectedOption = JOptionPane.showConfirmDialog(mainPanel,
                "Tem certeza que deseja remover o estereótipo da modelagem?",
                "Confirmar exclusão de estereótipo",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (selectedOption == JOptionPane.YES_OPTION) {
            for (Component component : Modelling.getModelling().getComponents()) {
                if (stereotypesGroup.getType()
                        .equalsToComponentOrRelationshipType(
                                component.getType())) {
                    for (Integer stereotypeId : stereotypesGroup
                            .getStereotypesIds()) {
                        boolean stopIteration = false;
                        int indexToRemove;
                        for (indexToRemove = 0; indexToRemove < component
                                .getStereotypesIds().size(); ++indexToRemove) {
                            if (component.getStereotypesIds()
                                    .get(indexToRemove).equals(stereotypeId)) {
                                component.getStereotypesIds().remove(
                                        indexToRemove);
                                stopIteration = true;
                                break;
                            }
                        }
                        if (stopIteration) {
                            break;
                        }
                    }
                }
            }
            for (Relationship relationship : Modelling.getModelling()
                    .getRelationships()) {
                if (stereotypesGroup.getType()
                        .equalsToComponentOrRelationshipType(
                                relationship.getType())) {
                    for (Integer stereotypeId : stereotypesGroup
                            .getStereotypesIds()) {
                        boolean stopIteration = false;
                        int indexToRemove;
                        for (indexToRemove = 0; indexToRemove < relationship
                                .getStereotypesIds().size(); ++indexToRemove) {
                            if (relationship.getStereotypesIds()
                                    .get(indexToRemove).equals(stereotypeId)) {
                                relationship.getStereotypesIds().remove(
                                        indexToRemove);
                                stopIteration = true;
                                break;
                            }
                        }
                        if (stopIteration) {
                            break;
                        }
                    }
                }
            }
            Modelling.getModelling().removeStereotypesGroup(stereotypesGroup);
            for (Component component : Modelling.getModelling().getComponents()) {
                if (component.getDiagram() != null) {
                    GraphComponent graphComponent = graphs.get(component
                            .getId());
                    addDiagramElements(graphComponent.getGraph(),
                            component.getDiagram());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Impede a classe de ser instanciada.
     */
    private ModellingController() {
    }

    private static void addComponentToGUI(Component component) {
        addComponentToTree(component);
        createDiagram(component);

    }

    private static void addComponentToTree(Component component) {
        TreeController.add(component);
    }

    private static void createDiagram(Component component) {
        Diagram diagram = component.getDiagram();
        if (diagram == null) {
            return;
        }
        Graph graph = new Graph();
        GraphComponent graphComponent = new GraphComponent(graph);
        graphs.put(component.getId(), graphComponent);
        configureGraphProperties(graphComponent);
        configureGraphEvents(graphComponent);
        addDiagramElements(graph, diagram);
    }

    private static boolean removeRelationship(Relationship relationship) {
        for (Component component : Modelling.getModelling().getComponents()) {
            if (component.getDiagram() != null) {
                component.getDiagram().removeDiagramRelationship(
                        relationship.getId());
                GraphComponent graphComponent = graphs.get(component.getId());
                addDiagramElements(graphComponent.getGraph(),
                        component.getDiagram());
            }
        }
        Modelling.getModelling().removeRelationship(relationship);
        return true;
    }

    public static boolean removeHumanResource(HumanResource humanResource) {
        JPanel mainPanel = Spider_PE_Home.execution.getMainPanel();
        int selectedOption = JOptionPane
                .showConfirmDialog(
                        mainPanel,
                        "Ao remover um recurso humano, todos os papéis instanciados\n"
                                + "associados à esse recurso humano perderão essa associação.\n"
                                + "Tem certeza que deseja remover o recurso humano da modelagem?",
                        "Confirmar exclusão de recurso humano",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
        if (selectedOption == JOptionPane.YES_OPTION) {
            for (Component component : Modelling.getModelling().getComponents()) {
                if (component.getType() == ComponentType.ROLE_USE
                        && component.getDetails().compareToIgnoreCase(
                                humanResource.getName()) == 0) {
                    component.setDetails(null);
                }
            }
            Modelling.getModelling().removeHumanResource(humanResource);
            setElementToShowProperties(elementShowingPropertiesId);
            setComponentToShowDiagram(componentShowingDiagramId);

            return true;
        } else {
            return false;
        }
    }

    public static boolean commitHumanResource(HumanResource humanResource) {
        boolean hasValidationProblems = false;
        ValidationMessagesGroup messages = new ValidationMessagesGroup();
        if (Modelling.getModelling().getHumanResource(humanResource.getId()) == null) {
            if (!IdValidator.validade(humanResource, messages)) {
                hasValidationProblems = true;
            }
            if (!HumanResourceValidator.validateCommit(humanResource, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                addHumanResource(humanResource);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        } else {
            if (!HumanResourceValidator.validateCommit(humanResource, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                updateHumanResource(humanResource);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        }
        if (!hasValidationProblems) {

        }
        return !hasValidationProblems;
    }

    private static String generateDiagramComponentStyle(int componentId) {
        ComponentType type = Modelling.getModelling().getComponent(componentId)
                .getType();
        Component component = Modelling.getModelling()
                .getComponent(componentId);
        if (type == ComponentType.FORK || type == ComponentType.JOIN) {
            return mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_RECTANGLE
                    + ";" + mxConstants.STYLE_FILLCOLOR + "=black;"
                    + mxConstants.STYLE_VERTICAL_LABEL_POSITION + "="
                    + mxConstants.ALIGN_CENTER + ";"
                    + mxConstants.STYLE_FONTCOLOR + "=white;"
                    + mxConstants.STYLE_FONTSTYLE + "=1;";
        }
        String componentImageFilePath = null;
        if (type == ComponentType.ACTIVITY) {
            if (FindByProcess.findAtividade(component) != null) {
                Atividade atv = FindByProcess.findAtividade(component);
                if (atv.getEstado() != null) {
                    if (atv.getEstado().equals("Em Execu\u00E7\u00E3o"))
                        componentImageFilePath = ComponentImageFile.ACTIVITY_PLAY;
                    else if (atv.getEstado().equals("Pausada"))
                        componentImageFilePath = ComponentImageFile.ACTIVITY_PAUSE;
                    else if (atv.getEstado().equals("Finalizada"))
                        componentImageFilePath = ComponentImageFile.ACTIVITY_CHECK;
                    else
                        componentImageFilePath = ComponentImageFile.ACTIVITY;
                } else
                    componentImageFilePath = ComponentImageFile.ACTIVITY;
            } else
                componentImageFilePath = ComponentImageFile.ACTIVITY;           
        } else if (type == ComponentType.DECISION_AND_MERGE) {
            componentImageFilePath = ComponentImageFile.DECISION_AND_MERGE;
        } else if (type == ComponentType.DISCIPLINE) {
            componentImageFilePath = ComponentImageFile.DISCIPLINE;
        } else if (type == ComponentType.FINAL_STATE) {
            componentImageFilePath = ComponentImageFile.FINAL_STATE;
        } else if (type == ComponentType.GUIDANCE) {
            componentImageFilePath = ComponentImageFile.GUIDANCE;
        } else if (type == ComponentType.GUIDANCE_SET) {
            componentImageFilePath = ComponentImageFile.GUIDANCE_SET;
        } else if (type == ComponentType.INITIAL_STATE) {
            componentImageFilePath = ComponentImageFile.INITIAL_STATE;
        } else if (type == ComponentType.ITERATION) {           
            if (FindByProcess.findIteracao(component) != null) {
                Iteracao it = FindByProcess.findIteracao(component);
                if (it.getEstado() != null) {
                    if (it.getEstado().equals("Em Execu\u00E7\u00E3o"))
                        componentImageFilePath = ComponentImageFile.ITERATION_PLAY;
                    else if (it.getEstado().equals("Pausada"))
                        componentImageFilePath = ComponentImageFile.ITERATION_PAUSE;
                    else if (it.getEstado().equals("Finalizada"))
                        componentImageFilePath = ComponentImageFile.ITERATION_CHECK;
                    else
                        componentImageFilePath = ComponentImageFile.ITERATION;
                } else
                    componentImageFilePath = ComponentImageFile.ITERATION;
            } else
                componentImageFilePath = ComponentImageFile.ITERATION;       
        } else if (type == ComponentType.MILESTONE) {            
            if (FindByProcess.findMarco(component) != null) {
                Marco marco = FindByProcess.findMarco(component);
                if (marco.getEstado() != null) {
                    if (marco.getEstado().equals("Em Execu\u00E7\u00E3o"))
                        componentImageFilePath = ComponentImageFile.MILESTONE_PLAY;
                    else if (marco.getEstado().equals("Pausada"))
                        componentImageFilePath = ComponentImageFile.MILESTONE_PAUSE;
                    else if (marco.getEstado().equals("Finalizada"))
                        componentImageFilePath = ComponentImageFile.MILESTONE_CHECK;
                    else
                        componentImageFilePath = ComponentImageFile.MILESTONE;
                } else
                    componentImageFilePath = ComponentImageFile.MILESTONE;
            } else
                componentImageFilePath = ComponentImageFile.MILESTONE;   
        } else if (type == ComponentType.NOTE) {
            componentImageFilePath = ComponentImageFile.NOTE;
        } else if (type == ComponentType.PHASE) {
            if (FindByProcess.findFase(component) != null) {
                Fase fase = FindByProcess.findFase(component);
                if (fase.getEstado() != null) {
                    if (fase.getEstado().equals("Em Execu\u00E7\u00E3o"))
                        componentImageFilePath = ComponentImageFile.PHASE_PLAY;
                    else if (fase.getEstado().equals("Pausada"))
                        componentImageFilePath = ComponentImageFile.PHASE_PAUSE;
                    else if (fase.getEstado().equals("Finalizada"))
                        componentImageFilePath = ComponentImageFile.PHASE_CHECK;
                    else
                        componentImageFilePath = ComponentImageFile.PHASE;
                } else
                    componentImageFilePath = ComponentImageFile.PHASE;
            } else
                componentImageFilePath = ComponentImageFile.PHASE;       
        } else if (type == ComponentType.PROCESS) {
            if (FindByProcess.findProcesso(component) != null) {
                Processo process = FindByProcess.findProcesso(component);
                if (process.getEstado() != null) {
                    if (process.getEstado().equals("Em Execu\u00E7\u00E3o"))
                        componentImageFilePath = ComponentImageFile.PROCESS_PLAY;
                    else if (process.getEstado().equals("Pausada"))
                        componentImageFilePath = ComponentImageFile.PROCESS_PAUSE;
                    else if (process.getEstado().equals("Finalizada"))
                        componentImageFilePath = ComponentImageFile.PROCESS_CHECK;
                    else
                        componentImageFilePath = ComponentImageFile.PROCESS;
                } else
                    componentImageFilePath = ComponentImageFile.PROCESS;
            } else
                componentImageFilePath = ComponentImageFile.PROCESS;       
        } else if (type == ComponentType.PROCESS_PACKAGE) {
            componentImageFilePath = ComponentImageFile.PROCESS_PACKAGE;
        } else if (type == ComponentType.ROLE_DEFINITION) {
            componentImageFilePath = ComponentImageFile.ROLE_DEFINITION;
        } else if (type == ComponentType.ROLE_SET) {
            componentImageFilePath = ComponentImageFile.ROLE_SET;
        } else if (type == ComponentType.ROLE_USE) {
            componentImageFilePath = ComponentImageFile.ROLE_USE;
        } else if (type == ComponentType.STANDARD_PROCESS) {
            componentImageFilePath = ComponentImageFile.STANDARD_PROCESS;
        } else if (type == ComponentType.TASK_DEFINITION) {
            componentImageFilePath = ComponentImageFile.TASK_DEFINITION;
        } else if (type == ComponentType.TASK_USE) {
            if (FindByProcess.findTarefa(component) != null) {
                Tarefa tarefa = FindByProcess.findTarefa(component);
                if (tarefa.getEstado() != null) {
                    if (tarefa.getEstado().equals("Em Execu\u00E7\u00E3o"))
                        componentImageFilePath = ComponentImageFile.TASK_USE_PLAY;
                    else if (tarefa.getEstado().equals("Pausada"))
                        componentImageFilePath = ComponentImageFile.TASK_USE_PAUSED;
                    else if (tarefa.getEstado().equals("Finalizada"))
                        componentImageFilePath = ComponentImageFile.TASK_USE_FINISHED;
                    else
                        componentImageFilePath = ComponentImageFile.TASK_USE;
                } else
                    componentImageFilePath = ComponentImageFile.TASK_USE;
            } else
                componentImageFilePath = ComponentImageFile.TASK_USE;

        } else if (type == ComponentType.TOOL_DEFINITION) {
            componentImageFilePath = ComponentImageFile.TOOL_DEFINITION;
        } else if (type == ComponentType.TOOL_SET) {
            componentImageFilePath = ComponentImageFile.TOOL_SET;
        } else if (type == ComponentType.TOOL_USE) {
            componentImageFilePath = ComponentImageFile.TOOL_USE;
        } else if (type == ComponentType.WORK_PRODUCT_DEFINITION) {
            componentImageFilePath = ComponentImageFile.WORK_PRODUCT_DEFINITION;
        } else if (type == ComponentType.WORK_PRODUCT_SET) {
            componentImageFilePath = ComponentImageFile.WORK_PRODUCT_SET;
        } else if (type == ComponentType.WORK_PRODUCT_USE) {
            componentImageFilePath = ComponentImageFile.WORK_PRODUCT_USE;
        } else {
            componentImageFilePath = "";
        }
        return mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_IMAGE + ";"
                + mxConstants.STYLE_IMAGE + "=" + componentImageFilePath + ";"
                + mxConstants.STYLE_VERTICAL_LABEL_POSITION + "="
                + mxConstants.ALIGN_BOTTOM + ";" + mxConstants.STYLE_FONTCOLOR
                + "=black;";
    }

    private static String generateDiagramRelationshipStyle(
            DiagramRelationship diagramRelationship) {
        RelationshipType type = Modelling.getModelling()
                .getRelationship(diagramRelationship.getId()).getType();
        if (type == RelationshipType.AGGREGATION) {
            return mxConstants.STYLE_STARTSIZE + "=10;"
                    + mxConstants.STYLE_STARTARROW + "=aggregation;"
                    + mxConstants.STYLE_ENDARROW + "=" + mxConstants.NONE + ";"
                    + mxConstants.STYLE_STROKECOLOR + "=black;"
                    + mxConstants.STYLE_FONTCOLOR + "=black;";
        } else if (type == RelationshipType.ANCHOR) {
            return mxConstants.STYLE_DASHED + "=" + true + ";"
                    + mxConstants.STYLE_ENDARROW + "=" + mxConstants.NONE + ";"
                    + mxConstants.STYLE_STROKECOLOR + "=black;"
                    + mxConstants.STYLE_FONTCOLOR + "=black;";
        } else if (type == RelationshipType.ASSOCIATION
                || type == RelationshipType.TRANSITION) {
            return mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_OPEN
                    + ";" + mxConstants.STYLE_STROKECOLOR + "=black;"
                    + mxConstants.STYLE_FONTCOLOR + "=black;";
        } else if (type == RelationshipType.COMPOSITION) {
            return mxConstants.STYLE_STARTSIZE + "=10;"
                    + mxConstants.STYLE_STARTARROW + "="
                    + mxConstants.ARROW_DIAMOND + ";"
                    + mxConstants.STYLE_ENDARROW + "=" + mxConstants.NONE + ";"
                    + mxConstants.STYLE_STROKECOLOR + "=black;"
                    + mxConstants.STYLE_FONTCOLOR + "=black;";
        } else if (type == RelationshipType.DEPENDENCY) {
            return mxConstants.STYLE_DASHED + "=" + true + ";"
                    + mxConstants.STYLE_ENDARROW + "=" + mxConstants.ARROW_OPEN
                    + ";" + mxConstants.STYLE_STROKECOLOR + "=black;"
                    + mxConstants.STYLE_FONTCOLOR + "=black;";

        } else if (type == RelationshipType.GENERALIZATION) {
            return mxConstants.STYLE_ENDSIZE + "=10;"
                    + mxConstants.STYLE_ENDARROW + "=generalization;"
                    + mxConstants.STYLE_STARTARROW + "=" + mxConstants.NONE
                    + ";" + mxConstants.STYLE_STROKECOLOR + "=black;"
                    + mxConstants.STYLE_FONTCOLOR + "=black;";
        } else {
            return null;
        }
    }

    private static String generateComponentStereotypeGroupStyle() {
        return mxConstants.STYLE_VERTICAL_LABEL_POSITION + "="
                + mxConstants.ALIGN_CENTER + ";" + mxConstants.STYLE_FONTCOLOR
                + "=black;" + mxConstants.STYLE_STROKECOLOR + "=black;";
    }

    private static String generateRelationshipStereotypeGroupStyle() {
        return mxConstants.STYLE_VERTICAL_LABEL_POSITION + "="
                + mxConstants.ALIGN_CENTER + ";" + mxConstants.STYLE_FONTCOLOR
                + "=black;" + mxConstants.STYLE_STROKECOLOR + "=black;" + mxConstants.STYLE_FONTSIZE +"=14px;";
    }

    public static void addDiagramElements(mxGraph graph, Diagram diagram) {
        Object graphParent = graph.getDefaultParent();
        Object[] cells = graph.getChildCells(graphParent);
        graph.removeCells(cells);
        graph.getModel().beginUpdate();
        try {
            for (DiagramComponent diagramComponent : diagram
                    .getDiagramComponents()) {
                graph.insertVertex(graphParent, null, diagramComponent,
                        diagramComponent.getX(), diagramComponent.getY(),
                        diagramComponent.getWidth(),
                        diagramComponent.getHeight(),
                        generateDiagramComponentStyle(diagramComponent.getId()));
            }
            Object source = null;
            Object destination = null;
            Object[] vertices = graph.getChildVertices(graphParent);
            for (DiagramRelationship diagramRelationship : diagram
                    .getDiagramRelationships()) {
                for (int i = 0; i < vertices.length; ++i) {
                    if (((DiagramComponent) ((mxCell) vertices[i]).getValue())
                            .getId()
                            .equals(Modelling
                                    .getModelling()
                                    .getRelationship(
                                            diagramRelationship.getId())
                                    .getSourceId())) {
                        source = vertices[i];
                    } else if (((DiagramComponent) ((mxCell) vertices[i])
                            .getValue()).getId().equals(
                            Modelling
                                    .getModelling()
                                    .getRelationship(
                                            diagramRelationship.getId())
                                    .getDestinationId())) {
                        destination = vertices[i];
                    }
                }
                graph.insertEdge(graphParent, null, diagramRelationship,
                        source, destination,
                        generateDiagramRelationshipStyle(diagramRelationship));
            }
            for (DiagramComponentStereotypesGroup componentStereotypesGroup : diagram
                    .getDiagramComponentStereotypesGroups()) {
                graph.insertVertex(graphParent, null,
                        componentStereotypesGroup,
                        componentStereotypesGroup.getX(),
                        componentStereotypesGroup.getY(), 0, 0,
                        generateComponentStereotypeGroupStyle());
            }
            for (DiagramRelationshipStereotypesGroup relationshipStereotypesGroup : diagram
                    .getDiagramRelationshipStereotypesGroups()) {
                graph.insertVertex(graphParent, null,
                        relationshipStereotypesGroup,
                        relationshipStereotypesGroup.getX(),
                        relationshipStereotypesGroup.getY(), 0, 0,
                        generateRelationshipStereotypeGroupStyle());
            }
        } finally {
            graph.getModel().endUpdate();
        }
    }

    private static void configureGraphProperties(GraphComponent graphComponent) {
        graphComponent.getGraph().setCellsCloneable(false);
        graphComponent.getGraph().setCellsDisconnectable(false);
        graphComponent.getGraph().setCellsEditable(false);
        graphComponent.getGraph().setCellsResizable(true);
        graphComponent.getViewport().setBackground(Color.WHITE);
        graphComponent.setToolTips(true);
        graphComponent.setConnectable(false);
    }

    static Tarefa tarefa;
    static Relationship transicao;
    static Component barra;
    static String label;
    static String fork;
    static Tarefa tarefaController = null;  
    static Marco marcoController = null;
    static String labelRelationShip, labelJoin;
    static ArrayList<Tarefa> TarefasController = new ArrayList<Tarefa>();

    private static void configureGraphEvents(final GraphComponent graphComponent) {       
        /*POPUP PARA FORK E JUNCAO*/
        final JPopupMenu popupForkJoin = new JPopupMenu(); 
        JMenuItem menuItemForkJuncao = new JMenuItem("AND");
        popupForkJoin.add(menuItemForkJuncao); 
        menuItemForkJuncao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.AND))); 
        menuItemForkJuncao.addActionListener(new ActionListener() {               
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBarra(ExecutionController.AND);
            }
        });
       
        menuItemForkJuncao = new JMenuItem("OR");
        popupForkJoin.add(menuItemForkJuncao); 
        menuItemForkJuncao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.OR))); 
        menuItemForkJuncao.addActionListener(new ActionListener() {               
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBarra(ExecutionController.OR);
            }
        });
       
        menuItemForkJuncao = new JMenuItem("XOR");
        popupForkJoin.add(menuItemForkJuncao); 
        menuItemForkJuncao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.XOR))); 
        menuItemForkJuncao.addActionListener(new ActionListener() {               
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBarra(ExecutionController.XOR);
            }
        });
       
       
       
        /*POP UP MENU PRA TRANSICAO*/
        final JPopupMenu popupTransicao = new JPopupMenu(); 
        JMenuItem menuItemTransicao = new JMenuItem(ExecutionController.startToStart);
        popupTransicao.add(menuItemTransicao); 
        //menuItemTransicao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.PLAY))); 
        menuItemTransicao.addActionListener(new ActionListener() {               
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTransicao(ExecutionController.startToStart);
            }
        });
       
        menuItemTransicao = new JMenuItem(ExecutionController.startToFinish);
        popupTransicao.add(menuItemTransicao); 
        //menuItemTransicao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.PLAY))); 
        menuItemTransicao.addActionListener(new ActionListener() {               
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTransicao(ExecutionController.startToFinish);
            }
        });
       
        menuItemTransicao = new JMenuItem(ExecutionController.finishToStart);
        popupTransicao.add(menuItemTransicao); 
        //menuItemTransicao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.PLAY))); 
        menuItemTransicao.addActionListener(new ActionListener() {               
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTransicao(ExecutionController.finishToStart);
            }
        });
       
        menuItemTransicao = new JMenuItem(ExecutionController.finishToFinish);
        popupTransicao.add(menuItemTransicao); 
        //menuItemTransicao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.PLAY))); 
        menuItemTransicao.addActionListener(new ActionListener() {               
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTransicao(ExecutionController.finishToFinish);
            }
        });
       
         final JPopupMenu popup = new JPopupMenu(); 
            JMenuItem menuItem = new JMenuItem(ExecutionController.EXECUCAO);
            menuItem.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.PLAY))); 
            menuItem.addActionListener(new ActionListener() {                   
                @Override
                public void actionPerformed(ActionEvent e) {
                	if(tarefaController!=null){                		
	                    if(taskController(label, tarefaController, ExecutionController.EXECUCAO, tarefa, fork)){
	                    	 saveTarefaMenuPop(tarefa, ExecutionController.EXECUCAO);
	                    	 taskUsePropertiesPanel.setComponent(TarefaDAO.findById(tarefa.getId()));	                 		
	             			 feedBackPanel.setComponent(TarefaDAO.findById(tarefa.getId()));
	                     }  
                	} else if(tarefa!=null && tarefaController==null) {
                		saveTarefaMenuPop(tarefa, ExecutionController.EXECUCAO); 
                		 taskUsePropertiesPanel.setComponent(TarefaDAO.findById(tarefa.getId()));	                 		
             			 feedBackPanel.setComponent(TarefaDAO.findById(tarefa.getId()));
                		}              		
                	else if(marcoController!=null){
                		saveMarcoMenuPop(marcoController, ExecutionController.EXECUCAO);
                		milestonePropertiesPanel.setComponent(MarcoDAO.findById(marcoController.getId()));
                	}
                	
                    marcoController=null; label = null; tarefaController = null; TarefasController.clear(); fork = null; tarefa = null; transicao = null;
                    
                }
            });
            popup.add(menuItem); 
            menuItem = new JMenuItem(ExecutionController.FINALIZADA);            
            menuItem.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.FINISHED)));
            menuItem.addActionListener(new ActionListener() {               
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	if(tarefaController!=null){
		                    if(taskController(label, tarefaController, ExecutionController.FINALIZADA, null, null)){
		                    	taskExtensaoComponentsFinalizar(tarefa);
		                    	saveTarefaMenuPop(tarefa, ExecutionController.FINALIZADA);
		                    	taskUsePropertiesPanel.setComponent(TarefaDAO.findById(tarefa.getId()));	                 		
		             			feedBackPanel.setComponent(TarefaDAO.findById(tarefa.getId()));                      
		                        }
		             	}else if(tarefaController==null && tarefa!=null){             		
		             		 taskExtensaoComponentsFinalizar(tarefa);
		             		 saveTarefaMenuPop(tarefa, ExecutionController.FINALIZADA);
		             		 taskUsePropertiesPanel.setComponent(TarefaDAO.findById(tarefa.getId()));	                 		
	             			 feedBackPanel.setComponent(TarefaDAO.findById(tarefa.getId()));
		                }else if(marcoController!=null){
		                	saveMarcoMenuPop(marcoController, ExecutionController.FINALIZADA);
		                	milestonePropertiesPanel.setComponent(MarcoDAO.findById(marcoController.getId()));
		                }
	                    marcoController=null; label = null; tarefaController = null; TarefasController.clear(); fork = null; tarefa = null; transicao = null;
                
            }});
            popup.add(menuItem); 
            menuItem = new JMenuItem(ExecutionController.PAUSADA);
            menuItem.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.PAUSE)));
            menuItem.addActionListener(new ActionListener() {               
                @Override
                public void actionPerformed(ActionEvent e) {
                	if(tarefaController!=null && (fork!=null || label!=null)){
	                    if(taskController(label, tarefaController, ExecutionController.PAUSADA, null, null))
	                    	saveTarefaMenuPop(tarefa, ExecutionController.PAUSADA);
		                    taskUsePropertiesPanel.setComponent(TarefaDAO.findById(tarefa.getId()));	                 		
	            			feedBackPanel.setComponent(TarefaDAO.findById(tarefa.getId()));
                	}
                	else if(tarefaController==null && tarefa!=null){
                			saveTarefaMenuPop(tarefa, ExecutionController.PAUSADA);
                			 taskUsePropertiesPanel.setComponent(TarefaDAO.findById(tarefa.getId()));	                 		
	             			 feedBackPanel.setComponent(TarefaDAO.findById(tarefa.getId()));
                	}
                	else if(marcoController!=null){                		
                		saveMarcoMenuPop(marcoController, ExecutionController.PAUSADA);
                		milestonePropertiesPanel.setComponent(MarcoDAO.findById(marcoController.getId()));
                	}
                    marcoController=null; label = null; tarefaController = null; TarefasController.clear(); fork = null; tarefa = null; transicao = null;
                }
            });
            popup.add(menuItem); 
           
           
           
        graphComponent.getGraph().addListener(mxEvent.CELLS_MOVED, new mxIEventListener() {

            @Override
            public void invoke(Object object, mxEventObject eventObject) {
                cellsMoved(object, eventObject);
            }
        });
       
        graphComponent.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new mxIEventListener() {

            @Override
            public void invoke(Object object, mxEventObject eventObject) {
                graphChanged(object, eventObject);
            }
        });
       
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(Spider_PE_Home.getInstance().getLogado() == 3){
                    if(isTask(graphComponent, mouseEvent.getX(), mouseEvent.getY())  || isMarco(graphComponent, mouseEvent.getX(), mouseEvent.getY()))
                        maybeShowPopup(mouseEvent);
                } else if(Spider_PE_Home.getInstance().getLogado() == 2) {
                    mxCell cell =  (mxCell) graphComponent.getCellAt(mouseEvent.getX(), mouseEvent.getY());               
                    if(ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell) instanceof DiagramRelationship){
                        DiagramRelationship relationShip = (DiagramRelationship) ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell); 
                        if(cell!=null){
                            transicao = Modelling.getModelling().getRelationship(relationShip.getId());
                            if(transicao.getType().equals(RelationshipType.TRANSITION)){maybeShowPopup2(mouseEvent); }                           
                        }
                    } else if(ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell) instanceof DiagramComponent){
                        DiagramComponent dc = (DiagramComponent) ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell); 
                        if(cell!=null){
                            barra = Modelling.getModelling().getComponent(dc.getId());
                            if(barra.getType().equals(ComponentType.FORK) || barra.getType().equals(ComponentType.JOIN))
                                {maybeShowPopup3(mouseEvent); }                           
                        }
                    }
                }
                super.mouseReleased(mouseEvent);
                graphMouseReleased(mouseEvent);
            }
           
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if(Spider_PE_Home.getInstance().getLogado() == 3){
                    if(isTask(graphComponent, mouseEvent.getX(), mouseEvent.getY()) || isMarco(graphComponent, mouseEvent.getX(), mouseEvent.getY()))
                        maybeShowPopup(mouseEvent);
                } else if(Spider_PE_Home.getInstance().getLogado() == 2) {
                    mxCell cell =  (mxCell) graphComponent.getCellAt(mouseEvent.getX(), mouseEvent.getY());               
                    if(ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell) instanceof DiagramRelationship){
                        DiagramRelationship relationShip = (DiagramRelationship) ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell); 
                        if(cell!=null){
                            transicao = Modelling.getModelling().getRelationship(relationShip.getId());
                            if(transicao.getType().equals(RelationshipType.TRANSITION)){maybeShowPopup2(mouseEvent); }                           
                        }
                    } else if(ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell) instanceof DiagramComponent){
                        DiagramComponent dc = (DiagramComponent) ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell); 
                        if(cell!=null){
                            barra = Modelling.getModelling().getComponent(dc.getId());
                            if(barra.getType().equals(ComponentType.FORK) || barra.getType().equals(ComponentType.JOIN))
                                {maybeShowPopup3(mouseEvent); }                           
                        }
                    }
                }
                super.mousePressed(mouseEvent);
                graphMouseReleased(mouseEvent);
            }
            private void maybeShowPopup3(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    mxCell cell =  (mxCell) graphComponent.getCellAt(e.getX(), e.getY());
                    if(ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell) instanceof DiagramComponent){
                        DiagramComponent diagramComponent = (DiagramComponent) ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell);
                        if(Modelling.getModelling().getComponent(diagramComponent.getId()).getType().equals(ComponentType.FORK)){
                             popupForkJoin.show(e.getComponent(), 
                                     e.getX(), e.getY()); 
                        }
                    }
                      
                  }
            }
           
            private void maybeShowPopup2(MouseEvent e) {
                mxCell cell =  (mxCell) graphComponent.getCellAt(e.getX(), e.getY());               
                if(ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell) instanceof DiagramRelationship){
                    DiagramRelationship relationShip = (DiagramRelationship) ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell); 
                    if(cell!=null){
                        transicao = Modelling.getModelling().getRelationship(relationShip.getId());
                        if(transicao.getType().equals(RelationshipType.TRANSITION)){                           
                            /*VERIFICA SE OS VERTICES VIZINHOS S�O TAREFAS*/
                            if(Modelling.getModelling().getComponent(transicao.getDestinationId()).getType().equals(ComponentType.TASK_USE)
                               && Modelling.getModelling().getComponent(transicao.getSourceId()).getType().equals(ComponentType.TASK_USE)){
                                 if (e.isPopupTrigger()) { 
                                     popupTransicao.show(e.getComponent(), 
                                                e.getX(), e.getY()); 
                               }
                            }
                                                       
                            if(Modelling.getModelling().getComponent(transicao.getSourceId()).getType().equals(ComponentType.TASK_USE)
                                        && Modelling.getModelling().getComponent(transicao.getDestinationId()).getType().equals(ComponentType.DECISION_AND_MERGE)){
                                          if (e.isPopupTrigger()) { 
                                              popupTransicao.show(e.getComponent(), 
                                                         e.getX(), e.getY()); 
                                        }
                                     }
                           
                            /*VERIFICA SE OS V�RTICES VIZINHOS S�O FORK/JOIN E ALGUMA COISA*/
                            if(Modelling.getModelling().getComponent(transicao.getDestinationId()).getType().equals(ComponentType.FORK)
                                        && Modelling.getModelling().getComponent(transicao.getSourceId()).getType().equals(ComponentType.TASK_USE)){
                                          if (e.isPopupTrigger()) { 
                                              popupTransicao.show(e.getComponent(), 
                                                         e.getX(), e.getY()); 
                                        }
                                     }       
                           
                            if(Modelling.getModelling().getComponent(transicao.getSourceId()).getType().equals(ComponentType.JOIN)
                                        && Modelling.getModelling().getComponent(transicao.getDestinationId()).getType().equals(ComponentType.TASK_USE)){
                                          if (e.isPopupTrigger()) { 
                                              popupTransicao.show(e.getComponent(), 
                                                         e.getX(), e.getY()); 
                                        }
                                     }       
                           
                           
                        }
                         
                        
                    }
                }
                           
              
            }
            private void maybeShowPopup(MouseEvent e) {                
                mxCell cell =  (mxCell) graphComponent.getCellAt(e.getX(), e.getY());
                if(cell!=null){
                    Component component = Modelling.getModelling().getComponent(((DiagramComponent) ModellingController.getCurrentGraph().getGraph().getModel().getValue(cell)).getId());
                    tarefa = FindByProcess.findTarefa(component);             
                    
                    if(tarefa!=null){
                    	ArrayList<DiagramRelationship> relantionships = null;
                        if(tarefa.getAtividade()!=null){
                       	 relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
                        }
                        else if (tarefa.getIteracao()!=null){
                       	 relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                        }
                        else if (tarefa.getFase()!=null){
                       	 relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                       	 }
                        for(DiagramRelationship diagramaComponent: relantionships){
                            Relationship transicao = Modelling.getModelling().getRelationship(diagramaComponent.getId());
                            Modelling.getModelling().getRelationship(diagramaComponent.getId());
                            Transicao trans = TransicaoDAO.findById(transicao.getId(), transicao.getSourceId(), transicao.getDestinationId(), Spider_PE_Home.getInstance().execution.getProcesso().getId());
                            if(trans!=null)    {
                                if(transicao.getDestinationId() == tarefa.getIdComponent()){
                                    label = trans.getTipo();
                                    tarefaController = FindByProcess.findTarefa(Modelling.getModelling().getComponent(transicao.getSourceId()));
                                }
                            }                        
                        }                      
           
                    //TASK_USE -> FORK
                        ArrayList<DiagramRelationship> relantionshipsaux = null;
                        for(DiagramRelationship diagramaComponent: relantionships)
                        {
                       	 Relationship relationship = Modelling.getModelling().getRelationship(diagramaComponent.getId());
                                if(relationship.getDestinationId().equals(tarefa.getIdComponent())){
                                    Component componentSource = Modelling.getModelling().getComponent(relationship.getSourceId());
                                    if(componentSource.getType().equals(ComponentType.FORK)){
                                   	 if(tarefa.getAtividade()!=null){
                                   		 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
                                        }
                                        else if (tarefa.getIteracao()!=null){
                                       	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                                        }
                                        else if (tarefa.getFase()!=null){
                                       	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                                       	 }
                                        for(DiagramRelationship diagramaComponent1: relantionshipsaux)
                                        {
                                            Relationship relationship1 = Modelling.getModelling().getRelationship(diagramaComponent1.getId());
                                            if(relationship1.getDestinationId().equals(componentSource.getId())){
                                            	 if(tarefa.getAtividade()!=null)
                                            		 fork =  Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                                                 else if (tarefa.getIteracao()!=null)
                                                	 fork =  Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                                                 else if (tarefa.getFase() !=null)
                                                	 fork =  Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                                                Transicao trans = TransicaoDAO.findById(relationship1.getId(), relationship1.getSourceId(), relationship1.getDestinationId(), Spider_PE_Home.getInstance().execution.getProcesso().getId());
                                                label = trans.getTipo();
                                                tarefaController = FindByProcess.findTarefa(Modelling.getModelling().getComponent(relationship1.getSourceId()));
                                                break;
                                            }
                                        }
                                    }  else if (componentSource.getType().equals(ComponentType.DECISION_AND_MERGE)){
                                   	 if(tarefa.getAtividade()!=null){
                                   		 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
                                        }
                                        else if (tarefa.getIteracao()!=null){
                                       	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                                        }
                                        else if (tarefa.getFase()!=null){
                                       	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                                       	 }
                                    for(DiagramRelationship diagramaComponent1: relantionshipsaux)
                                    {
                                        Relationship relationship1 = Modelling.getModelling().getRelationship(diagramaComponent1.getId());
                                        if(relationship1.getDestinationId().equals(componentSource.getId())){
                                            Transicao trans = TransicaoDAO.findById(relationship1.getId(), relationship1.getSourceId(), relationship1.getDestinationId(), Spider_PE_Home.getInstance().execution.getProcesso().getId());
                                            label = trans.getTipo();
                                            tarefaController = FindByProcess.findTarefa(Modelling.getModelling().getComponent(relationship1.getSourceId()));
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                   
                        //JOIN -> TASK_USE
                        ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
                        for(DiagramRelationship diagramaComponent: relantionships)
                        {
                            Relationship relationship = Modelling.getModelling().getRelationship(diagramaComponent.getId());
                            if(relationship.getDestinationId().equals(tarefa.getIdComponent())){
                                Component componentSource = Modelling.getModelling().getComponent(relationship.getSourceId());
                                if(componentSource.getType().equals(ComponentType.JOIN)){
                                    Transicao trans = TransicaoDAO.findById(relationship.getId(), relationship.getSourceId(), relationship.getDestinationId(), Spider_PE_Home.getInstance().execution.getProcesso().getId());
                                    label = trans.getTipo();
                                    if(tarefa.getAtividade()!=null){
                               		 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
                                    }
                                    else if (tarefa.getIteracao()!=null){
                                   	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                                    }
                                    else if (tarefa.getFase()!=null){
                                   	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                                   	 }
                                    for(DiagramRelationship diagramaComponent1: relantionshipsaux)
                                    {
                                        Relationship relationship1 = Modelling.getModelling().getRelationship(diagramaComponent1.getId());
                                        if(relationship1.getDestinationId().equals(componentSource.getId())){
                                            tarefas.add(FindByProcess.findTarefa(Modelling.getModelling().getComponent(relationship1.getSourceId())));
                                        }
                                    }
                                }
                            }
                        }   
                    
                                     
                     
                        if(!tarefas.isEmpty())
                            tarefaController = ExecutionController.getEstadoTarefaJoinTaskUse(tarefas);
                    
                    Humano humano = (Humano) Spider_PE_Home.getInstance().getInstanciaLogado();
                    if(tarefa!=null){
                        for (Papel p : tarefa.getPapeis()) {                           
                            if(p.getHumanosAlocados().contains(humano)){                           
                                if (e.isPopupTrigger()) {
                                    popup.show(e.getComponent(), 
                                            e.getX(), e.getY()); 
                                } 
                                break;
                            }                   
                        }                   
                    } 
                  } else {
                	  Humano humano = (Humano) Spider_PE_Home.getInstance().getInstanciaLogado();
                	  marcoController = FindByProcess.findMarco(component); 
                	 
                	  if (marcoController!=null){ 
                      	for (Papel p : marcoController.getPapeis()) {  
                      		if(p.getHumanosAlocados().contains(humano)){
                      			if (e.isPopupTrigger()) {
                                    popup.show(e.getComponent(), 
                                            e.getX(), e.getY()); 
                                } 
                                break; 
                      		}    
                          }   
                	  }
                }
              }
          } 
       });
    }


    protected static void saveMarcoMenuPop(Marco marco,
			String estado) {
    	marco.setEstado(estado);
//        Log log = new Log();
//        log.setEstado(estado);
//        log.setDate(new Date());
//        log.setOrdem( TarefaDAO.getOrdem(tarefa)+ 1);
//        log.setTarefa(tarefa);
//        LogDAO.createLog(log);
//    	marcoController.getLog().add(log);
        MarcoDAO.updateMarco(marco);
       
        Component component = Modelling.getModelling().getComponent(
                marcoController.getFase().getIdComponent());
        DiagramComponent diagram = component.getDiagram().getDiagramComponent(marcoController.getIdComponent());
        long x = (long) Math.round(diagram.getX());
        long y = (long) Math.round(diagram.getY());
        int x1 = (int) x;
        int y2 = (int) y;
        mxCell cell = (mxCell) ModellingController.getCurrentGraph().getCellAt(
                x1, y2);

        if (cell != null) {
            if (marco.getEstado().equals("Em Execu\u00E7\u00E3o"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.MILESTONE_PLAY);
            else if (marco.getEstado().equals("Pausada"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.MILESTONE_PAUSE);
            else if (marco.getEstado().equals("Finalizada"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.MILESTONE_CHECK);
            else
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.MILESTONE);

        }
       
       
       
        addDiagramElements(ModellingController.currentGraph.getGraph(),
                Modelling.getModelling()
                        .getComponent(componentShowingDiagramId).getDiagram());
       
//        if(marco.getFase()!=null)
//        	addDiagramElements(ModellingController.getGraphComponent(marco.getFase().getIdComponent()).getGraph(),
//                Modelling.getModelling().getComponent(marco.getFase().getIdComponent()).getDiagram());
//        
//        ModellingController.getCurrentGraph().getGraph().refresh();       
//        ModellingController.getCurrentGraph().getGraph().repaint();
//        ModellingController.getCurrentGraph().revalidate();
//        ModellingController.getCurrentGraph().repaint();
//        ModellingController.getCurrentGraph().refresh();
       
       // ExecutionController.update(marco);
       
        TreeController.update();
        ModellingController.update();
		
	}

	protected static boolean isMarco(GraphComponent graphComponent, int x, int y) {
    	mxCell cell = (mxCell) graphComponent.getCellAt(x, y);
        if (cell != null) {
            if (ModellingController.getCurrentGraph().getGraph().getModel()
                    .getValue(cell) instanceof DiagramComponent) {
                Component component = Modelling.getModelling().getComponent(
                        ((DiagramComponent) ModellingController
                                .getCurrentGraph().getGraph().getModel()
                                .getValue(cell)).getId());
                if (component.getType() == ComponentType.MILESTONE)
                    return true;
            }
        }
        return false;
	}

public static void taskExtensaoComponentsFinalizar(Tarefa tarefa) {
		ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
		String label = "";
		String fork = "";
		
			ArrayList<DiagramRelationship> relationships = new ArrayList<DiagramRelationship>();
			if(tarefa.getAtividade()!=null)
				relationships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
			else if(tarefa.getIteracao()!=null)
				relationships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
			else if(tarefa.getFase()!=null)
				relationships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
	        for(DiagramRelationship diagramaComponent: relationships)
	        {
	           
	            Relationship relationship = Modelling.getModelling().getRelationship(diagramaComponent.getId());
	            if(relationship.getSourceId().equals(tarefa.getIdComponent())){                           
	                Component componentSource = Modelling.getModelling().getComponent(relationship.getDestinationId());
	                if(componentSource.getType().equals(ComponentType.FORK)){                               
	                	 if(tarefa.getAtividade()!=null)
                    		 fork =  Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                         else if (tarefa.getIteracao()!=null)
                        	 fork =  Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                         else if (tarefa.getFase() !=null)
                        	 fork =  Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                         Transicao trans = TransicaoDAO.findById(relationship.getId(), relationship.getSourceId(), relationship.getDestinationId(), Spider_PE_Home.getInstance().execution.getProcesso().getId());
	                            label = trans.getTipo();
	                            for(DiagramRelationship dc: relationships)
	                            {
	                            	
	                                if(Modelling.getModelling().getRelationship(dc.getId()).getSourceId().equals(componentSource.getId())
	                                        && Modelling.getModelling().getComponent(Modelling.getModelling().getRelationship(dc.getId()).getDestinationId()).getType().equals(ComponentType.TASK_USE)){
	                                    tarefas.add(FindByProcess.findTarefa(Modelling.getModelling().getComponent(Modelling.getModelling().getRelationship(dc.getId()).getDestinationId())));
	                                }
	                            }
	                }
	            }
	        }
	   if(label!=null){
	       if(label.contains(ExecutionController.finishToStart)){ 
	        	if(fork!=null){
		            if(fork.contains(ExecutionController.AND)){
		                for(Tarefa t: tarefas){
		                    Log log = new Log();
		                    t.setEstado(ExecutionController.EXECUCAO);
		                    log.setDate(new Date());
		                    log.setEstado(ExecutionController.EXECUCAO);
		                    log.setOrdem( TarefaDAO.getOrdem(t)+ 1);
		                    log.setTarefa(t);
		                    LogDAO.createLog(log);
		                    t.getLog().add(log);
		                    TarefaDAO.updateTarefa(t);
		                }
		            }
	            }
	       }
	   }
        ModellingController.addDiagramElements(ModellingController.getCurrentGraph().getGraph(), Modelling.getModelling().getComponent(ModellingController.getComponentShowingDiagramId()).getDiagram());
        ModellingController.update();
    }
   
protected static boolean taskExtensaoComponentsExecutar(Tarefa tarefa, String label, String fork, String estadoAnteior, String estadoRequisitado) {
	    ArrayList<DiagramRelationship> relantionships = null;
		ArrayList<DiagramRelationship> relantionshipsaux = null;
		ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
		if(tarefa.getAtividade()!=null){
			relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
        }
        else if (tarefa.getIteracao()!=null){
        	relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
        }
        else if (tarefa.getFase()!=null){
        	relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
       	 }
        for(DiagramRelationship diagramaComponent: relantionships)
        {
           
            Relationship relationship = Modelling.getModelling().getRelationship(diagramaComponent.getId());
            if(relationship.getDestinationId().equals(tarefa.getIdComponent())){                           
                Component componentSource = Modelling.getModelling().getComponent(relationship.getSourceId());
                if(componentSource.getType().equals(ComponentType.FORK)){  
                	if(tarefa.getAtividade()!=null){
            			relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
            			 fork =  Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                    }
                    else if (tarefa.getIteracao()!=null){
                    	relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                   	    fork =  Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                    }
                    else if (tarefa.getFase()!=null){
                    	relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                   		fork =  Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                    }
                    for(DiagramRelationship dc: relantionshipsaux)
                            {                               
                                if(Modelling.getModelling().getRelationship(dc.getId()).getSourceId().equals(componentSource.getId())
                                        && Modelling.getModelling().getComponent(Modelling.getModelling().getRelationship(dc.getId()).getDestinationId()).getType().equals(ComponentType.TASK_USE)){
                                    tarefas.add(FindByProcess.findTarefa(Modelling.getModelling().getComponent(Modelling.getModelling().getRelationship(dc.getId()).getDestinationId())));
                                   
                                }
                            }
                }
            }
        }
        if(label.contains(ExecutionController.finishToStart)){
            if(fork.contains(ExecutionController.OR)){     
            	tarefas.remove(tarefa);
                	if(estadosTarefas(tarefas)){
	                       JOptionPane.showMessageDialog(null, "Somente uma tarefa deve ser executada para satisfazer o fluxo de execução definido pela Barra de Bifurcação OR");
	                        return false;
                     }  else {      
                    	 	if(estadoAnteior.contains(ExecutionController.FINALIZADA) && (estadoRequisitado.contains(ExecutionController.PAUSADA) || estadoRequisitado.contains(ExecutionController.EXECUCAO) || estadoRequisitado.contains(ExecutionController.FINALIZADA))){
                    	 			return true;
                    	 	}else {
        		                    JOptionPane.showMessageDialog(null, Mensagem.MSG4);
        		                    return false;
        		            }
                     }
             }else if(fork.contains(ExecutionController.XOR)){
                return true;
            } 
        }   
        return false;
    }
	
	 private static boolean estadosTarefas(ArrayList<Tarefa> tarefas) {
		 for (Tarefa t : tarefas) {
			 if(t.getEstado() !=null){
				 if(t.getEstado().contains(ExecutionController.EXECUCAO) || t.getEstado().contains(ExecutionController.FINALIZADA) || t.getEstado().contains(ExecutionController.PAUSADA)){
				  return true;
				 }
			}
		}
		 return false;
	}

	 public static boolean taskController(String label, Tarefa tarefaController,
	            String estado, Tarefa tarefaSelecionada, String fork) {
		 if(!(tarefaController.getEstado() == null)){
			 if (label.contains(ExecutionController.startToStart)) {
		            if (estado.contains(ExecutionController.EXECUCAO) || estado.contains(ExecutionController.PAUSADA) || estado.contains(ExecutionController.FINALIZADA)) {
		                if (tarefaController.getEstado().contains(ExecutionController.EXECUCAO)
		                        || tarefaController.getEstado().contains(
		                                ExecutionController.PAUSADA)
		                        || tarefaController.getEstado().contains(
		                                ExecutionController.FINALIZADA)) {
		                    return true;
		                } else {
		                    JOptionPane.showMessageDialog(null, Mensagem.MSG1);
		                    return false;
		                }
		            }
	
		        } else if (label.contains(ExecutionController.startToFinish)) {
		            if (estado.contains(ExecutionController.FINALIZADA) || estado.contains(ExecutionController.EXECUCAO) || estado.contains(ExecutionController.PAUSADA)) {
		                if (tarefaController.getEstado().contains(ExecutionController.EXECUCAO)
		                        || tarefaController.getEstado().contains(
		                                ExecutionController.PAUSADA)
		                                || tarefaController.getEstado().contains(ExecutionController.FINALIZADA)) {
		                    return true;
		                } else {
		                    JOptionPane.showMessageDialog(null, Mensagem.MSG2);
		                    return false;
		                }
		            }
	
		        } else if (label.contains(ExecutionController.finishToFinish)) {	            
		                if (tarefaController.getEstado().contains(ExecutionController.FINALIZADA)) {
		                	if(estado.contains(ExecutionController.FINALIZADA) || estado.contains(ExecutionController.PAUSADA) || estado.contains(ExecutionController.EXECUCAO))
		                		return true;
		                	else {
			                    JOptionPane.showMessageDialog(null, Mensagem.MSG3);
			                    return false;
			                }
		                }else if(tarefaController.getEstado().contains(ExecutionController.PAUSADA) || tarefaController.getEstado().contains(ExecutionController.EXECUCAO)){
		                	if(estado.contains(ExecutionController.PAUSADA) || estado.contains(ExecutionController.EXECUCAO))
		                		return true;
		                	else {
			                    JOptionPane.showMessageDialog(null, Mensagem.MSG3);
			                    return false;
			                }
		                }else {
		                    JOptionPane.showMessageDialog(null, Mensagem.MSG3);
		                    return false;
		                }
		        } else if (label.contains(ExecutionController.finishToStart) && fork == null) {	        	
		        		if (estado.contains(ExecutionController.FINALIZADA) || estado.contains(ExecutionController.PAUSADA) || estado.contains(ExecutionController.EXECUCAO)) {
		        			if (tarefaController.getEstado().contains(ExecutionController.FINALIZADA)) {
			                    return true;
			                } else {
			                    JOptionPane.showMessageDialog(null, Mensagem.MSG4);
			                    return false;
			                }
		        	      }        		
		        		
		        		
		        }else if (label.contains(ExecutionController.finishToStart) && fork.contains(ExecutionController.AND)) {	        	
	        		if (estado.contains(ExecutionController.FINALIZADA) || estado.contains(ExecutionController.PAUSADA) || estado.contains(ExecutionController.EXECUCAO)) {
	        			if (tarefaController.getEstado().contains(ExecutionController.FINALIZADA)) {
		                    return true;
		                } else {
		                    JOptionPane.showMessageDialog(null, Mensagem.MSG4);
		                    return false;
		                }
	        	      }        		
	        } else if (label.contains(ExecutionController.finishToStart) && (fork.contains(ExecutionController.OR) || fork.contains(ExecutionController.XOR))){
			        	if(taskExtensaoComponentsExecutar(tarefaSelecionada, label, fork, tarefaController.getEstado(), estado))
			        		return true;
		        }
		 } else JOptionPane.showMessageDialog(null, Mensagem.MSG5);
	     return false;
	    }


    protected static void saveBarra(String string) {
        JoinFork joinfork = JoinForkDAO.findById(barra.getId(),
                Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso()
                        .getId());
        if (joinfork == null)
            joinfork = new JoinFork();
        joinfork.setIdComponent(barra.getId());
        joinfork.setProcessId(Spider_PE_Home.getInstance().Spider_PE_Management
                .getProcesso().getId());
        joinfork.setTipo(string);
        JoinForkController.savejoinFork(joinfork);
        addDiagramElements(ModellingController.currentGraph.getGraph(),
                Modelling.getModelling()
                        .getComponent(componentShowingDiagramId).getDiagram());
        ModellingController.getCurrentGraph().getGraph().refresh();
        ModellingController.getCurrentGraph().getGraph().repaint();
        ModellingController.getCurrentGraph().revalidate();
        ModellingController.getCurrentGraph().repaint();
        ModellingController.getCurrentGraph().refresh();
    }

    protected static void saveTransicao(String tipoTransicao) {
        Transicao trans = TransicaoDAO.findById(transicao.getId(), transicao
                .getSourceId(), transicao.getDestinationId(),
                Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso()
                        .getId());
        if (trans == null)
            trans = new Transicao();
        trans.setIdComponent(transicao.getId());
        trans.setDestinationId(transicao.getDestinationId());
        trans.setSourceId(transicao.getSourceId());
        if(Modelling.getModelling().getComponent(transicao.getDestinationId()).getType().equals(ComponentType.DECISION_AND_MERGE))
            trans.setTipo(transicao.getLabel() + "\n\n" +tipoTransicao);
        else
            trans.setTipo("\n"+tipoTransicao);
        trans.setProcessId(Spider_PE_Home.getInstance().Spider_PE_Management
                .getProcesso().getId());
        try {
            TransicaoController.savetransicao(trans);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addDiagramElements(ModellingController.currentGraph.getGraph(),
                Modelling.getModelling()
                        .getComponent(componentShowingDiagramId).getDiagram());
        ModellingController.getCurrentGraph().getGraph().refresh();
        ModellingController.getCurrentGraph().getGraph().repaint();
        ModellingController.getCurrentGraph().revalidate();
        ModellingController.getCurrentGraph().repaint();
        ModellingController.getCurrentGraph().refresh();
        transicao = null;
        tarefa = null;
        
    }

    protected static void saveTarefaMenuPop(Tarefa tarefa, String estado) {
        tarefa.setEstado(estado);
        Log log = new Log();
        log.setEstado(estado);
        log.setDate(new Date());
        log.setOrdem( TarefaDAO.getOrdem(tarefa)+ 1);
        log.setTarefa(tarefa);
        tarefa.getLog().add(log);
        LogDAO.createLog(log);
        TarefaDAO.updateTarefa(tarefa);
       
        Component component = Modelling.getModelling().getComponent(
                tarefa.getIdComponent());
        DiagramComponent diagram = component.getDiagram().getDiagramComponent(
                tarefa.getIdComponent());
        long x = (long) Math.round(diagram.getX());
        long y = (long) Math.round(diagram.getY());
        int x1 = (int) x;
        int y2 = (int) y;
        mxCell cell = (mxCell) ModellingController.getCurrentGraph().getCellAt(
                x1, y2);

        if (cell != null) {
            if (tarefa.getEstado().equals("Em Execu\u00E7\u00E3o"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE_PLAY);
            else if (tarefa.getEstado().equals("Pausada"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE_PAUSED);
            else if (tarefa.getEstado().equals("Finalizada"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE_FINISHED);
            else
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE);

        }
       
       
       
        addDiagramElements(ModellingController.currentGraph.getGraph(),
                Modelling.getModelling()
                        .getComponent(componentShowingDiagramId).getDiagram());
       
        if(tarefa.getIteracao()!=null)
        addDiagramElements(ModellingController.getGraphComponent(tarefa.getIteracao().getIdComponent()).getGraph(),
                Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram());
        else if(tarefa.getAtividade()!=null)
            addDiagramElements(ModellingController.getGraphComponent(tarefa.getAtividade().getIdComponent()).getGraph(),
                    Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram());
       
        ModellingController.getCurrentGraph().getGraph().refresh();       
        ModellingController.getCurrentGraph().getGraph().repaint();
        ModellingController.getCurrentGraph().revalidate();
        ModellingController.getCurrentGraph().repaint();
        ModellingController.getCurrentGraph().refresh();
       
        ExecutionController.update(tarefa);
       
        TreeController.update();
        ModellingController.update();

    }

    protected static boolean isTask(GraphComponent graphComponent, int x, int y) {
        mxCell cell = (mxCell) graphComponent.getCellAt(x, y);
        if (cell != null) {
            if (ModellingController.getCurrentGraph().getGraph().getModel()
                    .getValue(cell) instanceof DiagramComponent) {
                Component component = Modelling.getModelling().getComponent(
                        ((DiagramComponent) ModellingController
                                .getCurrentGraph().getGraph().getModel()
                                .getValue(cell)).getId());
                if (component.getType() == ComponentType.TASK_USE)
                    return true;
            }
        }
        return false;
    }

    private static void graphChanged(Object object, mxEventObject eventObject) {
        mxCell cell = (mxCell) ((mxGraphSelectionModel) object).getCell();
        if (cell != null) {
            if (cell.getValue().getClass() == DiagramComponent.class) {
                if (isReadyToAddARelationship()) {
                    if (relationshipSource != null) {
                        relationshipDestination = cell;
                        if (createRelationship()) {
                            createDiagramRelationship();
                        }
                    } else {
                        relationshipSource = cell;
                    }
                } else {
                    setElementToShowProperties(((DiagramComponent) (cell
                            .getValue())).getId());
                }
            } else if (cell.getValue().getClass() == DiagramRelationship.class) {
                setElementToShowProperties(((DiagramRelationship) (cell
                        .getValue())).getId());
                setReadyToAddARelationship(false);
                setReadyToAddAComponent(false);

            } else if (cell.getValue().getClass() == DiagramComponentStereotypesGroup.class) {
                setReadyToAddARelationship(false);
                setReadyToAddAComponent(false);

            } else if (cell.getValue().getClass() == DiagramRelationshipStereotypesGroup.class) {
                setReadyToAddARelationship(false);
                setReadyToAddAComponent(false);

            } else if (cell.getValue().getClass() == String.class) {
                if (dropComponent()) {
                    createDiagramComponent(cell.getGeometry().getX(), cell
                            .getGeometry().getY());
                } else {
                    commitComponent(Modelling.getModelling().getComponent(
                            componentShowingDiagramId));
                }
            }
        }
    }

    private static void cellsMoved(Object object, mxEventObject eventObject) {
        Graph graph = (Graph) object;
        Object[] cells = graph.getSelectionCells();
        for (Object objectFromCellArray : cells) {
            mxCell cell = (mxCell) objectFromCellArray;
            if (cell.getValue().getClass() == DiagramComponent.class) {
                DiagramComponent diagramComponent = ((DiagramComponent) cell
                        .getValue());
                DiagramComponentStereotypesGroup diagramComponentStereotypesGroup = Modelling
                        .getModelling()
                        .getComponent(componentShowingDiagramId)
                        .getDiagram()
                        .getDiagramComponentStereotypesGroup(
                                diagramComponent.getId());
                if (diagramComponentStereotypesGroup != null) {
                    diagramComponentStereotypesGroup
                            .setX(diagramComponentStereotypesGroup.getX()
                                    + cell.getGeometry().getX()
                                    - diagramComponent.getX());
                    diagramComponentStereotypesGroup
                            .setY(diagramComponentStereotypesGroup.getY()
                                    + cell.getGeometry().getY()
                                    - diagramComponent.getY());
                }
                for (DiagramRelationship diagramRelationship : Modelling
                        .getModelling().getComponent(componentShowingDiagramId)
                        .getDiagram().getDiagramRelationships()) {
                    Relationship relationship = Modelling.getModelling()
                            .getRelationship(diagramRelationship.getId());
                    if (relationship.getSourceId().equals(
                            diagramComponent.getId())
                            || relationship.getDestinationId().equals(
                                    diagramComponent.getId())) {
                        DiagramRelationshipStereotypesGroup diagramRelationshipStereotypesGroup = Modelling
                                .getModelling()
                                .getComponent(componentShowingDiagramId)
                                .getDiagram()
                                .getDiagramRelationshipStereotypesGroup(
                                        relationship.getId());
                        if (diagramRelationshipStereotypesGroup != null) {
                            DiagramComponent source = Modelling
                                    .getModelling()
                                    .getComponent(componentShowingDiagramId)
                                    .getDiagram()
                                    .getDiagramComponent(
                                            relationship.getSourceId());
                            DiagramComponent destination = Modelling
                                    .getModelling()
                                    .getComponent(componentShowingDiagramId)
                                    .getDiagram()
                                    .getDiagramComponent(
                                            relationship.getDestinationId());
                            double x = destination.getX() - source.getX();
                            double y = destination.getY() - source.getY();
                            if (y > x) {
                                y = y / 2 + source.getY();
                                x = x / 2 + 20 + source.getX();
                            } else {
                                x = x / 2 + source.getX();
                                y = y / 2 + 20 + source.getY();
                            }
                            double newX;
                            double newY;
                            if (destination.getId().equals(
                                    diagramComponent.getId())) {
                                newX = cell.getGeometry().getX()
                                        - source.getX();
                                newY = cell.getGeometry().getY()
                                        - source.getY();
                                if (y > x) {
                                    newY = newY / 2 + source.getY();
                                    newX = newX / 2 + 20 + source.getX();
                                } else {
                                    newX = newX / 2 + source.getX();
                                    newY = newY / 2 + 20 + source.getY();
                                }
                            } else {
                                newX = destination.getX()
                                        - cell.getGeometry().getX();
                                newY = destination.getY()
                                        - cell.getGeometry().getY();
                                if (y > x) {
                                    newY = newY / 2 + cell.getGeometry().getY();
                                    newX = newX / 2 + 20
                                            + cell.getGeometry().getX();
                                } else {
                                    newX = newX / 2 + cell.getGeometry().getX();
                                    newY = newY / 2 + 20
                                            + cell.getGeometry().getY();
                                }
                            }
                            diagramRelationshipStereotypesGroup
                                    .setX(diagramRelationshipStereotypesGroup
                                            .getX() + newX - x);
                            diagramRelationshipStereotypesGroup
                                    .setY(diagramRelationshipStereotypesGroup
                                            .getY() + newY - y);
                        }
                    }
                }
                diagramComponent.setX(cell.getGeometry().getX());
                diagramComponent.setY(cell.getGeometry().getY());
                diagramComponent.setHeight(cell.getGeometry().getHeight());
                diagramComponent.setWidth(cell.getGeometry().getWidth());

            } else if (cell.getValue().getClass() == DiagramComponentStereotypesGroup.class) {
                DiagramComponentStereotypesGroup diagramComponentStereotypesGroup = ((DiagramComponentStereotypesGroup) cell
                        .getValue());
                diagramComponentStereotypesGroup
                        .setX(cell.getGeometry().getX());
                diagramComponentStereotypesGroup
                        .setY(cell.getGeometry().getY());
            } else if (cell.getValue().getClass() == DiagramRelationshipStereotypesGroup.class) {
                DiagramRelationshipStereotypesGroup diagramRelationshipStereotypesGroup = ((DiagramRelationshipStereotypesGroup) cell
                        .getValue());
                diagramRelationshipStereotypesGroup.setX(cell.getGeometry()
                        .getX());
                diagramRelationshipStereotypesGroup.setY(cell.getGeometry()
                        .getY());
            }
        }
        addDiagramElements(graph,
                Modelling.getModelling()
                        .getComponent(componentShowingDiagramId).getDiagram());

    }

    public static mxCell getCellToDrop(int componentToDropId) {
        ModellingController.componentToDropId = componentToDropId;
        mxCell cell = new mxCell(Modelling.getModelling()
                .getComponent(componentToDropId).getName(), new mxGeometry(0,
                0, 32, 32), generateDiagramComponentStyle(componentToDropId));
        cell.setVertex(true);
        return cell;
    }

    private static int componentToDropId;

    private static Component copyComponent(Component component,
            Integer parentId, Map<Integer, Integer> oldIdNewIdMap) {
        Component newComponent = Modelling.getModelling().getComponentToUpdate(
                component.getId());
        newComponent.setId(null);
        newComponent.setParentId(parentId);
        if (component.getType() == ComponentType.TASK_DEFINITION) {
            newComponent.setType(ComponentType.TASK_USE);
        }
        if (!commitComponent(newComponent)) {
            return null;
        }
        oldIdNewIdMap.put(component.getId(), newComponent.getId());
        if (component.getType() != ComponentType.TASK_USE
                && component.getType() != ComponentType.TASK_DEFINITION) {
            for (Component componentFromList : Modelling.getModelling()
                    .getComponents()) {
                if (componentFromList.getParentId() != null
                        && componentFromList.getParentId().equals(
                                component.getId())) {
                    copyComponent(componentFromList, newComponent.getId(),
                            oldIdNewIdMap);
                }
            }
            if (newComponent.getDiagram() != null) {
                for (DiagramComponent diagramComponent : newComponent
                        .getDiagram().getDiagramComponents()) {
                    diagramComponent.setId(oldIdNewIdMap.get(diagramComponent
                            .getId()));
                }
                for (DiagramRelationship diagramRelationship : newComponent
                        .getDiagram().getDiagramRelationships()) {
                    Relationship relationship = Modelling.getModelling()
                            .getRelationshipToUpdate(
                                    diagramRelationship.getId());
                    relationship.setId(null);
                    relationship.setSourceId(oldIdNewIdMap.get(relationship
                            .getSourceId()));
                    relationship.setDestinationId(oldIdNewIdMap
                            .get(relationship.getDestinationId()));
                    if (commitRelationship(relationship)) {
                        diagramRelationship.setId(relationship.getId());
                    } else {
                        newComponent.getDiagram().removeDiagramRelationship(
                                diagramRelationship.getId());
                    }
                }
            }
        } else if (component.getType() == ComponentType.TASK_USE) {
            DiagramComponent newDiagramComponent = newComponent.getDiagram()
                    .getDiagramComponent(newComponent.getId());
            DiagramComponent oldDiagramComponent = newComponent.getDiagram()
                    .getDiagramComponent(component.getId());
            newDiagramComponent.setHeight(oldDiagramComponent.getHeight());
            newDiagramComponent.setWidth(oldDiagramComponent.getWidth());
            newDiagramComponent.setX(oldDiagramComponent.getX());
            newDiagramComponent.setY(oldDiagramComponent.getY());
            newComponent.getDiagram().removeDiagramComponent(component.getId());
            List<Integer> notAdded = new ArrayList<Integer>();
            for (DiagramComponent diagramComponent : newComponent.getDiagram()
                    .getDiagramComponents()) {
                Component auxComponent = Modelling.getModelling().getComponent(
                        diagramComponent.getId());
                if (auxComponent.getType() == ComponentType.ROLE_USE
                        || auxComponent.getType() == ComponentType.TOOL_USE
                        || auxComponent.getType() == ComponentType.WORK_PRODUCT_USE) {
                    Component newAuxComponent = Modelling.getModelling()
                            .getComponentToUpdate(auxComponent.getId());
                    newAuxComponent.setId(null);
                    setParentId(newAuxComponent);
                    if (auxComponent.getParentId().equals(
                            newAuxComponent.getParentId())) {
                        oldIdNewIdMap.put(auxComponent.getId(),
                                auxComponent.getId());
                    } else {
                        if (commitComponent(newAuxComponent)) {
                            oldIdNewIdMap.put(auxComponent.getId(),
                                    newAuxComponent.getId());
                        } else {
                            notAdded.add(auxComponent.getId());
                        }
                    }
                } else if (auxComponent.getType() == ComponentType.GUIDANCE) {
                    oldIdNewIdMap.put(auxComponent.getId(),
                            auxComponent.getId());
                }
            }
            for (Integer integer : notAdded) {
                newComponent.getDiagram().removeDiagramComponent(integer);
            }
            for (DiagramComponent diagramComponent : newComponent.getDiagram()
                    .getDiagramComponents()) {
                if (!diagramComponent.getId().equals(newComponent.getId())) {
                    diagramComponent.setId(oldIdNewIdMap.get(diagramComponent
                            .getId()));
                }
            }
            for (DiagramRelationship diagramRelationship : newComponent
                    .getDiagram().getDiagramRelationships()) {
                Relationship oldRelationship = Modelling.getModelling()
                        .getRelationship(diagramRelationship.getId());
                if (notAdded.contains(oldRelationship.getSourceId())
                        || notAdded.contains(oldRelationship.getSourceId())) {
                    continue;
                }
                Relationship newRelationship = Modelling.getModelling()
                        .getRelationshipToUpdate(diagramRelationship.getId());
                newRelationship.setId(null);
                newRelationship.setSourceId(oldIdNewIdMap.get(newRelationship
                        .getSourceId()));
                newRelationship.setDestinationId(oldIdNewIdMap
                        .get(newRelationship.getDestinationId()));
                if (oldRelationship.getSourceId() != newRelationship
                        .getSourceId()
                        || oldRelationship.getDestinationId() != newRelationship
                                .getDestinationId()) {
                    if (commitRelationship(newRelationship)) {
                        diagramRelationship.setId(newRelationship.getId());
                    } else {
                        newComponent.getDiagram().removeDiagramRelationship(
                                diagramRelationship.getId());
                    }
                }
            }
        } else {
            DiagramComponent newDiagramComponent = newComponent.getDiagram()
                    .getDiagramComponent(newComponent.getId());
            DiagramComponent oldDiagramComponent = newComponent.getDiagram()
                    .getDiagramComponent(component.getId());
            newDiagramComponent.setHeight(oldDiagramComponent.getHeight());
            newDiagramComponent.setWidth(oldDiagramComponent.getWidth());
            newDiagramComponent.setX(oldDiagramComponent.getX());
            newDiagramComponent.setY(oldDiagramComponent.getY());
            newComponent.getDiagram().removeDiagramComponent(component.getId());
            List<Integer> notAdded = new ArrayList<Integer>();
            for (DiagramComponent diagramComponent : newComponent.getDiagram()
                    .getDiagramComponents()) {
                Component auxComponent = Modelling.getModelling().getComponent(
                        diagramComponent.getId());
                if (auxComponent.getType() == ComponentType.ROLE_DEFINITION
                        || auxComponent.getType() == ComponentType.TOOL_DEFINITION
                        || auxComponent.getType() == ComponentType.WORK_PRODUCT_DEFINITION) {
                    Component newAuxComponent = Modelling.getModelling()
                            .getComponentToUpdate(auxComponent.getId());
                    newAuxComponent.setId(null);
                    if (auxComponent.getType() == ComponentType.ROLE_DEFINITION) {
                        newAuxComponent.setType(ComponentType.ROLE_USE);
                    } else if (auxComponent.getType() == ComponentType.TOOL_DEFINITION) {
                        newAuxComponent.setType(ComponentType.TOOL_USE);
                    } else {
                        newAuxComponent.setType(ComponentType.WORK_PRODUCT_USE);
                    }
                    setParentId(newAuxComponent);
                    if (commitComponent(newAuxComponent)) {
                        oldIdNewIdMap.put(auxComponent.getId(),
                                newAuxComponent.getId());
                    } else {
                        notAdded.add(auxComponent.getId());
                    }
                } else if (auxComponent.getType() == ComponentType.GUIDANCE) {
                    oldIdNewIdMap.put(auxComponent.getId(),
                            auxComponent.getId());
                }
            }
            for (Integer integer : notAdded) {
                newComponent.getDiagram().removeDiagramComponent(integer);
            }
            for (DiagramComponent diagramComponent : newComponent.getDiagram()
                    .getDiagramComponents()) {
                if (!diagramComponent.getId().equals(newComponent.getId())) {
                    diagramComponent.setId(oldIdNewIdMap.get(diagramComponent
                            .getId()));
                }
            }
            for (DiagramRelationship diagramRelationship : newComponent
                    .getDiagram().getDiagramRelationships()) {
                Relationship oldRelationship = Modelling.getModelling()
                        .getRelationship(diagramRelationship.getId());
                if (notAdded.contains(oldRelationship.getSourceId())
                        || notAdded.contains(oldRelationship.getSourceId())) {
                    continue;
                }
                Relationship newRelationship = Modelling.getModelling()
                        .getRelationshipToUpdate(diagramRelationship.getId());
                newRelationship.setId(null);
                newRelationship.setSourceId(oldIdNewIdMap.get(newRelationship
                        .getSourceId()));
                newRelationship.setDestinationId(oldIdNewIdMap
                        .get(newRelationship.getDestinationId()));
                if (oldRelationship.getSourceId() != newRelationship
                        .getSourceId()
                        || oldRelationship.getDestinationId() != newRelationship
                                .getDestinationId()) {
                    if (commitRelationship(newRelationship)) {
                        diagramRelationship.setId(newRelationship.getId());
                    } else {
                        newComponent.getDiagram().removeDiagramRelationship(
                                diagramRelationship.getId());
                    }
                }
            }
        }
        commitComponent(newComponent);
        return newComponent;
    }

    private static boolean dropComponent() {
        Component componentToDrop = Modelling.getModelling().getComponent(
                componentToDropId);
        ComponentType componentShowingDiagramType = Modelling.getModelling()
                .getComponent(componentShowingDiagramId).getType();

        if (componentToDrop.getType() == ComponentType.WORK_PRODUCT_DEFINITION) {
            if (componentShowingDiagramType == ComponentType.TASK_USE) {
                Component component = new Component();
                component.setName(componentToDrop.getName());
                component.setType(ComponentType.WORK_PRODUCT_USE);
                setParentId(component);
                component.setAdditionalInformations(componentToDrop
                        .getAdditionalInformations());
                component.setDescription(componentToDrop.getDescription());
                component.setObjectives(componentToDrop.getObjectives());
                if (commitComponent(component)) {
                    setElementToShowProperties(component.getId());
                    return true;
                }
            } else if (componentShowingDiagramType == ComponentType.TASK_DEFINITION) {
                setElementToShowProperties(componentToDropId);
                return true;
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.TASK_DEFINITION) {
            if (componentShowingDiagramType == ComponentType.ACTIVITY
                    || componentShowingDiagramType == ComponentType.ITERATION
                    || (componentShowingDiagramType == ComponentType.PHASE && Modelling
                            .getModelling()
                            .getComponent(componentShowingDiagramId)
                            .getDetails().compareToIgnoreCase("SEQUENTIAL") == 0)) {
                Component newComponent = copyComponent(componentToDrop,
                        componentShowingDiagramId,
                        new HashMap<Integer, Integer>());
                if (newComponent != null) {
                    setElementToShowProperties(newComponent.getId());
                    return true;
                }
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.TOOL_DEFINITION) {
            if (componentShowingDiagramType == ComponentType.TASK_USE) {
                Component component = new Component();
                component.setName(componentToDrop.getName());
                component.setType(ComponentType.TOOL_USE);
                setParentId(component);
                component.setAdditionalInformations(componentToDrop
                        .getAdditionalInformations());
                component.setDescription(componentToDrop.getDescription());
                component.setObjectives(componentToDrop.getObjectives());
                if (commitComponent(component)) {
                    setElementToShowProperties(component.getId());
                    return true;
                }
            } else if (componentShowingDiagramType == ComponentType.TASK_DEFINITION) {
                setElementToShowProperties(componentToDropId);
                return true;
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.ROLE_DEFINITION) {
            if (componentShowingDiagramType == ComponentType.TASK_USE) {
                Component component = new Component();
                component.setName(componentToDrop.getName());
                component.setType(ComponentType.ROLE_USE);
                setParentId(component);
                component.setAdditionalInformations(componentToDrop
                        .getAdditionalInformations());
                component.setDescription(componentToDrop.getDescription());
                component.setObjectives(componentToDrop.getObjectives());
                if (commitComponent(component)) {
                    setElementToShowProperties(component.getId());
                    return true;
                }
            } else if (componentShowingDiagramType == ComponentType.TASK_DEFINITION) {
                setElementToShowProperties(componentToDropId);
                return true;
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.GUIDANCE) {
            if (componentShowingDiagramType == ComponentType.TASK_USE
                    || componentShowingDiagramType == ComponentType.TASK_DEFINITION) {
                setElementToShowProperties(componentToDropId);
                return true;
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.PHASE) {
            if (componentShowingDiagramType == ComponentType.PROCESS
                    && componentShowingDiagramId != componentToDrop
                            .getParentId()) {
                Component newComponent = copyComponent(componentToDrop,
                        componentShowingDiagramId,
                        new HashMap<Integer, Integer>());
                if (newComponent != null) {
                    setElementToShowProperties(newComponent.getId());
                    return true;
                }
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.ITERATION) {
            if (componentShowingDiagramType == ComponentType.PHASE
                    && Modelling.getModelling()
                            .getComponent(componentShowingDiagramId)
                            .getDetails().compareToIgnoreCase("ITERATIVE") == 0) {
                Component newComponent = copyComponent(componentToDrop,
                        componentShowingDiagramId,
                        new HashMap<Integer, Integer>());
                if (newComponent != null) {
                    setElementToShowProperties(newComponent.getId());
                    return true;
                }
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.MILESTONE) {
            if (componentShowingDiagramType == ComponentType.PHASE
                    && Modelling.getModelling()
                            .getComponent(componentShowingDiagramId)
                            .getDetails().compareToIgnoreCase("ITERATIVE") == 0) {
                Component newComponent = copyComponent(componentToDrop,
                        componentShowingDiagramId,
                        new HashMap<Integer, Integer>());
                if (newComponent != null) {
                    setElementToShowProperties(newComponent.getId());
                    return true;
                }
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.ACTIVITY) {
            if (componentShowingDiagramType == ComponentType.ACTIVITY
                    || componentShowingDiagramType == ComponentType.ITERATION
                    || (componentShowingDiagramType == ComponentType.PHASE && Modelling
                            .getModelling()
                            .getComponent(componentShowingDiagramId)
                            .getDetails().compareToIgnoreCase("SEQUENTIAL") == 0)) {
                Component newComponent = copyComponent(componentToDrop,
                        componentShowingDiagramId,
                        new HashMap<Integer, Integer>());
                if (newComponent != null) {
                    setElementToShowProperties(newComponent.getId());
                    return true;
                }
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.TASK_USE) {
            if (componentShowingDiagramType == ComponentType.ACTIVITY
                    || componentShowingDiagramType == ComponentType.ITERATION
                    || (componentShowingDiagramType == ComponentType.PHASE && Modelling
                            .getModelling()
                            .getComponent(componentShowingDiagramId)
                            .getDetails().compareToIgnoreCase("SEQUENTIAL") == 0)) {
                Component newComponent = copyComponent(componentToDrop,
                        componentShowingDiagramId,
                        new HashMap<Integer, Integer>());
                if (newComponent != null) {
                    setElementToShowProperties(newComponent.getId());
                    return true;
                }
            }
            return false;
        } else if (componentToDrop.getType() == ComponentType.ROLE_USE
                || componentToDrop.getType() == ComponentType.TOOL_USE
                || componentToDrop.getType() == ComponentType.WORK_PRODUCT_USE) {
            if (componentShowingDiagramType == ComponentType.TASK_USE) {
                int taskUseProcessId = componentShowingDiagramId;
                while (Modelling.getModelling().getComponent(taskUseProcessId)
                        .getType() != ComponentType.PROCESS) {
                    taskUseProcessId = Modelling.getModelling()
                            .getComponent(taskUseProcessId).getParentId();
                }
                int componentToDropProcessId = Modelling.getModelling()
                        .getComponent(componentToDrop.getParentId())
                        .getParentId();
                if (componentToDropProcessId == taskUseProcessId) {
                    setElementToShowProperties(componentToDropId);
                    return true;
                } else {
                    Component component = Modelling.getModelling()
                            .getComponentToUpdate(componentToDropId);
                    component.setId(null);
                    setParentId(component);
                    if (commitComponent(component)) {
                        setElementToShowProperties(component.getId());
                        return true;
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private static void graphMouseReleased(MouseEvent mouseEvent) {
        if (isReadyToAddAComponent()) {
            if (createComponent()) {
                createDiagramComponent(mouseEvent.getX(), mouseEvent.getY());
            }
        }
        if (isReadyToAddARelationship()
                && currentGraph.getGraph().getSelectionCell() == null) {

            setReadyToAddARelationship(false);
        }
        TreeController.processPackageTree.setSelectionPath(null);
    }

    private static boolean createRelationship() {
        Relationship relationship = new Relationship();
        relationship.setType(relationshipTypeToAdd);
        relationship.setSourceId(((DiagramComponent) (relationshipSource
                .getValue())).getId());
        relationship
                .setDestinationId(((DiagramComponent) (relationshipDestination
                        .getValue())).getId());

        if (commitRelationship(relationship)) {
            setElementToShowProperties(relationship.getId());

            return true;

        } else {

            setReadyToAddARelationship(false);

            return false;

        }
    }

    private static boolean createDiagramRelationship() {
        DiagramRelationship diagramRelationship = new DiagramRelationship();
        diagramRelationship.setId(elementShowingPropertiesId);
        Component component = Modelling.getModelling().getComponent(
                componentShowingDiagramId);
        component.getDiagram().addDiagramRelationship(diagramRelationship);

        return commitComponent(component);

    }

    private static boolean createDiagramComponent(double x, double y) {
        DiagramComponent diagramComponent = new DiagramComponent();
        diagramComponent.setX(x);
        diagramComponent.setY(y);
        diagramComponent.setId(elementShowingPropertiesId);
        ComponentType diagramComponentType = Modelling.getModelling()
                .getComponent(elementShowingPropertiesId).getType();

        if (diagramComponentType == ComponentType.FORK
                || diagramComponentType == ComponentType.JOIN) {
            diagramComponent.setHeight(100.0);
            diagramComponent.setWidth(10.0);

        } else {
            diagramComponent.setHeight(32.0);
            diagramComponent.setWidth(32.0);

        }
        Component component = Modelling.getModelling().getComponent(
                componentShowingDiagramId);
        Diagram diagram = component.getDiagram();
        diagram.addDiagramComponent(diagramComponent);

        for (Relationship relationship : Modelling.getModelling()
                .getRelationships()) {
            if (relationship.getSourceId().equals(diagramComponent.getId())
                    || relationship.getDestinationId().equals(
                            diagramComponent.getId())) {
                if (diagram.getDiagramComponent(relationship.getSourceId()) != null
                        && diagram.getDiagramComponent(relationship
                                .getDestinationId()) != null) {
                    DiagramRelationship diagramRelationship = new DiagramRelationship();
                    diagramRelationship.setId(relationship.getId());
                    diagram.addDiagramRelationship(diagramRelationship);

                }
            }
        }
        return commitComponent(component);

    }

    private static void setParentId(Component component) {
        if (component.getType() == ComponentType.GUIDANCE) {
            for (Component componentFromList : Modelling.getModelling()
                    .getComponents()) {
                if (componentFromList.getParentId() != null
                        && componentFromList.getParentId().equals(
                                Modelling.getModelling().getStandardProcess()
                                        .getId())
                        && componentFromList.getType() == ComponentType.GUIDANCE_SET) {
                    component.setParentId(componentFromList.getId());

                    break;

                }
            }
        } else if (component.getType() == ComponentType.ROLE_DEFINITION) {
            for (Component componentFromList : Modelling.getModelling()
                    .getComponents()) {
                if (componentFromList.getParentId() != null
                        && componentFromList.getParentId().equals(
                                Modelling.getModelling().getStandardProcess()
                                        .getId())
                        && componentFromList.getType() == ComponentType.ROLE_SET) {
                    component.setParentId(componentFromList.getId());

                    break;

                }
            }
        } else if (component.getType() == ComponentType.ROLE_USE) {
            Component component1 = Modelling.getModelling().getComponent(
                    componentShowingDiagramId);

            while (component1.getType() != ComponentType.PROCESS) {
                component1 = Modelling.getModelling().getComponent(
                        component1.getParentId());

            }
            for (Component component2 : Modelling.getModelling()
                    .getComponents()) {
                if (component2.getParentId() != null
                        && component2.getParentId().equals(component1.getId())
                        && component2.getType() == ComponentType.ROLE_SET) {
                    component.setParentId(component2.getId());

                    break;

                }
            }
        } else if (component.getType() == ComponentType.TOOL_DEFINITION) {
            for (Component componentFromList : Modelling.getModelling()
                    .getComponents()) {
                if (componentFromList.getParentId() != null
                        && componentFromList.getParentId().equals(
                                Modelling.getModelling().getStandardProcess()
                                        .getId())
                        && componentFromList.getType() == ComponentType.TOOL_SET) {
                    component.setParentId(componentFromList.getId());

                    break;

                }
            }
        } else if (component.getType() == ComponentType.TOOL_USE) {
            Component component1 = Modelling.getModelling().getComponent(
                    componentShowingDiagramId);

            while (component1.getType() != ComponentType.PROCESS) {
                component1 = Modelling.getModelling().getComponent(
                        component1.getParentId());

            }
            for (Component component2 : Modelling.getModelling()
                    .getComponents()) {
                if (component2.getParentId() != null
                        && component2.getParentId().equals(component1.getId())
                        && component2.getType() == ComponentType.TOOL_SET) {
                    component.setParentId(component2.getId());

                    break;

                }
            }
        } else if (component.getType() == ComponentType.WORK_PRODUCT_DEFINITION) {
            for (Component componentFromList : Modelling.getModelling()
                    .getComponents()) {
                if (componentFromList.getParentId() != null
                        && componentFromList.getParentId().equals(
                                Modelling.getModelling().getStandardProcess()
                                        .getId())
                        && componentFromList.getType() == ComponentType.WORK_PRODUCT_SET) {
                    component.setParentId(componentFromList.getId());

                    break;

                }
            }
        } else if (component.getType() == ComponentType.WORK_PRODUCT_USE) {
            Component component1 = Modelling.getModelling().getComponent(
                    componentShowingDiagramId);

            while (component1.getType() != ComponentType.PROCESS) {
                component1 = Modelling.getModelling().getComponent(
                        component1.getParentId());

            }
            for (Component component2 : Modelling.getModelling()
                    .getComponents()) {
                if (component2.getParentId() != null
                        && component2.getParentId().equals(component1.getId())
                        && component2.getType() == ComponentType.WORK_PRODUCT_SET) {
                    component.setParentId(component2.getId());

                    break;

                }
            }
        } else {
            component.setParentId(componentShowingDiagramId);

        }
    }

    private static boolean createComponent() {

        setReadyToAddAComponent(false);
        Component component = new Component();
        component.setType(componentTypeToAdd);
        setParentId(component);

        if (commitComponent(component)) {
            setElementToShowProperties(component.getId());

            if (autoCreateRequiredElements) {
                if (component.getType() == ComponentType.ACTIVITY
                        || component.getType() == ComponentType.ITERATION
                        || component.getType() == ComponentType.PHASE
                        || component.getType() == ComponentType.PROCESS) {
                    Component child = new Component();
                    child.setType(ComponentType.INITIAL_STATE);
                    child.setParentId(component.getId());

                    if (ModellingController.commitComponent(child)) {
                        DiagramComponent diagramComponent = new DiagramComponent();
                        diagramComponent.setX(64.0);
                        diagramComponent.setY(64.0);
                        diagramComponent.setId(child.getId());
                        diagramComponent.setHeight(32.0);
                        diagramComponent.setWidth(32.0);
                        component.getDiagram().addDiagramComponent(
                                diagramComponent);
                        commitComponent(component);

                    }
                    if (component.getType() == ComponentType.PROCESS) {
                        child = new Component();
                        child.setType(ComponentType.ROLE_SET);
                        child.setParentId(component.getId());
                        ModellingController.commitComponent(child);
                        child = new Component();
                        child.setType(ComponentType.WORK_PRODUCT_SET);
                        child.setParentId(component.getId());
                        ModellingController.commitComponent(child);
                        child = new Component();
                        child.setType(ComponentType.TOOL_SET);
                        child.setParentId(component.getId());
                        ModellingController.commitComponent(child);
                        child = new Component();

                    }
                }
            }
            if (propertiesScrollPane.getViewport().getView() != null) {
                propertiesScrollPane.getViewport().getView()
                        .requestFocusInWindow();

            }
            return true;

        } else {
            return false;

        }
    }

    private static void addComponentToModelling(Component component) {
        Modelling.getModelling().addComponent(component);

    }

    /**
     * Adiciona ao painel do diagrama a barra de ferramentas selecionada e o
     * diagrama do componente selecionado.
     */
    private static void setDiagramPanelComponents() {

        diagramPanel.add(graphs.get(componentShowingDiagramId),
                java.awt.BorderLayout.CENTER);
        currentGraph = graphs.get(componentShowingDiagramId);

    }

    /**
     * Configura a painel do diagrama para mostrar a barra de ferramentas e o
     * diagrama referentes ao componente selelecionado.
     */
    private static void configureDiagramPanel() {
        diagramPanel.removeAll();
        if (componentShowingDiagramId != null) {
            Component component = Modelling.getModelling().getComponent(
                    componentShowingDiagramId);

            if (component.getDiagram() != null) {
                if (component.getType() == ComponentType.STANDARD_PROCESS) {

                } else if (component.getType() == ComponentType.DISCIPLINE) {

                } else if (component.getType() == ComponentType.TASK_DEFINITION) {

                } else if (component.getType() == ComponentType.PROCESS_PACKAGE) {
                } else if (component.getType() == ComponentType.PROCESS) {

                } else if (component.getType() == ComponentType.PHASE
                        && component.getDetails().compareToIgnoreCase(
                                "ITERATIVE") == 0) {

                } else if ((component.getType() == ComponentType.PHASE && component
                        .getDetails().compareToIgnoreCase("SEQUENTIAL") == 0)
                        || component.getType() == ComponentType.ACTIVITY
                        || component.getType() == ComponentType.ITERATION) {

                } else if (component.getType() == ComponentType.TASK_USE) {

                }
                setDiagramPanelComponents();

            }
        }
        diagramPanel.updateUI();

    }

    public static GraphComponent getCurrentGraph() {
        return currentGraph;
    }

    /**
     * Configura o painel com as propriedades do componente determinado pelo
     * atributo <code>componentShowingPropertiesId</code>.
     */
    private static void configurePropertiesPanel() {
        if (elementShowingPropertiesId != null) {
            propertiesTabbedPane.removeAll();
            Relationship relationship = Modelling.getModelling()
                    .getRelationship(elementShowingPropertiesId);

            if (relationship != null) {
                if (relationship.getType() != RelationshipType.ANCHOR) {
                    relationshipPropertiesPanel.setRelationship(relationship);
                    propertiesScrollPane
                            .setViewportView(relationshipPropertiesPanel);

                    return;
                } else {
                    propertiesScrollPane.setViewportView(null);
                    return;
                }
            }

            Component component = Modelling.getModelling().getComponent(
                    elementShowingPropertiesId);

            if (component == null) {
                propertiesScrollPane.setViewportView(null);
                return;
            }

            ComponentType type = component.getType();

            if (type == ComponentType.PROCESS_PACKAGE
                    || type == ComponentType.TOOL_DEFINITION
                    || type == ComponentType.PROCESS
                    || type == ComponentType.ACTIVITY
                    || type == ComponentType.ITERATION) {
                basicPropertiesPanel.setComponent(component);
                propertiesScrollPane.setViewportView(basicPropertiesPanel);

                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                return;
            } else if (type == ComponentType.ACTIVITY) {
                Atividade atividadetoShow = FindByProcess
                        .findAtividade(component);
                workPropertiesPanel.setComponent(atividadetoShow);
                propertiesScrollPane.setViewportView(workPropertiesPanel);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
                return;
            } else if (type == ComponentType.GUIDANCE
                    || type == ComponentType.WORK_PRODUCT_USE
                    || type == ComponentType.WORK_PRODUCT_DEFINITION) {
                componentWithFilePropertiesPanel.setComponent(component);
                propertiesScrollPane
                        .setViewportView(componentWithFilePropertiesPanel);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
                return;
            } else if (type == ComponentType.DISCIPLINE) {
                disciplinePropertiesPanel.setComponent(component);
                propertiesScrollPane.setViewportView(disciplinePropertiesPanel);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
                return;
            } else if (type == ComponentType.MILESTONE) {            	
                milestonePropertiesPanel.setComponent(FindByProcess.findMarco(component));
                propertiesScrollPane.setViewportView(milestonePropertiesPanel);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
                return;
            } else if (type == ComponentType.GUIDANCE_SET
                    || type == ComponentType.NOTE
                    || type == ComponentType.ROLE_SET
                    || type == ComponentType.TOOL_SET
                    || type == ComponentType.WORK_PRODUCT_SET) {
                propertiesScrollPane.setViewportView(null);
                nameAndDescriptionPropertiesPanel.setComponent(component);
                propertiesScrollPane
                        .setViewportView(nameAndDescriptionPropertiesPanel);

                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                return;
            } else if (type == ComponentType.ROLE_DEFINITION) {
                roleDefinitionPropertiesPanel.setComponent(component);
                propertiesScrollPane
                        .setViewportView(roleDefinitionPropertiesPanel);

                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                return;

            } else if (type == ComponentType.ROLE_USE) {
                roleUsePropertiesPanel.setComponent(component);
                propertiesScrollPane.setViewportView(roleUsePropertiesPanel);

                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                return;
            } else if (type == ComponentType.TASK_USE) {
            	final Tarefa tarefaToShow = FindByProcess.findTarefa(component);  
            	
            	ChangeListener changeListener = new ChangeListener() {  
            		public void stateChanged(ChangeEvent changeEvent) {  
            		propertiesTabbedPane = (javax.swing.JTabbedPane) changeEvent.getSource();  
            		int index = propertiesTabbedPane.getSelectedIndex();  
            		if(index == 0)
            			taskUsePropertiesPanel.setComponent(TarefaDAO.findById(tarefaToShow.getId()));
            		if(index == 1)
            			feedBackPanel.setComponent(TarefaDAO.findById(tarefaToShow.getId()));
            		    }  
            		
            		  };              	
                             
                feedBackPanel = new TaskUseFeedBackPanel(tarefaToShow);
                taskUsePropertiesPanel.setComponent(tarefaToShow);
                propertiesScrollPane.setViewportView(taskUsePropertiesPanel);
                feedBackScrollPane.setViewportView(feedBackPanel);
               
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);
            	propertiesTabbedPane.addChangeListener(changeListener);
                       
               
                if (Spider_PE_Home.getInstance().getLogado() == 3) {
                    if (Spider_PE_Home.getInstance().getInstanciaLogado() instanceof Humano) {
                        Humano humano = (Humano) Spider_PE_Home.getInstance().getInstanciaLogado();
                        for (Papel p : tarefaToShow.getPapeis()) {
                            if (p.getHumanosAlocados().contains(humano)) {
                                propertiesTabbedPane.addTab("Feedback", null,
                                        feedBackScrollPane, null);
                                break;
                            }
                        }
                    }
                }
                // Salvar informa��es do tabbedPane: ver isso depois
                return;
            } else if (type == ComponentType.PHASE) {
                phasePropertiesPanel.setComponent(component);
                propertiesScrollPane.setViewportView(phasePropertiesPanel);
                propertiesTabbedPane.addTab("Informa\u00E7\u00F5es", null,
                        propertiesScrollPane, null);

                return;
            }
        }
        propertiesScrollPane.setViewportView(null);

    }

    /**
     * Realiza as inicializações necessárias para o correto funcionamento da
     * classe.
     */
    public static void initialize() {
        ComponentNameController.initialize();
        IdValidator.initialize();
        TreeController.initialize();

        // ----------------------------------------------------------------------
        // Inicializa os painéis que exibem as propriedades dos componentes.
        // ----------------------------------------------------------------------
        relationshipPropertiesPanel = new RelationshipPropertiesPanel();
        disciplinePropertiesPanel = new DisciplinePropertiesPanel();
        roleDefinitionPropertiesPanel = new RoleDefinitionPropertiesPanel();
        basicPropertiesPanel = new BasicPropertiesPanel();
        taskDefinitionPropertiesPanel = new TaskDefinitionPropertiesPanel();
        componentWithFilePropertiesPanel = new ComponentWithFilePropertiesPanel();
        milestonePropertiesPanel = new MilestonePropertiesPanel();
        nameAndDescriptionPropertiesPanel = new NameAndDescriptionPropertiesPanel();
        roleUsePropertiesPanel = new RoleUsePropertiesPanel();
        workPropertiesPanel = new WorkPropertiesPanel();
        taskUsePropertiesPanel = new TaskUsePropertiesPanel();

        phasePropertiesPanel = new PhasePropertiesPanel();

        // ----------------------------------------------------------------------
        // Inicializa o mapeamento dos componentes usados para exibir os grafos.
        // ----------------------------------------------------------------------
        graphs = new HashMap<Integer, GraphComponent>();

    }

    private static void addComponent(Component component) {
        addComponentToModelling(component);
        addComponentToGUI(component);

    }

    /**
     * Verifica as informações contidas no componente. Se essas informações
     * apresentarem falhas que não podem ser corrigidas uma execeção será
     * lançada. Caso contrário, o componente será adicionado ou modificado na
     * modelagem atual.
     *
     * @param stereotypesGroup
     *            o componente que poderá ser modificado ou adicionado
     */
    public static boolean commitComponent(Component component) {
        boolean hasValidationProblems = false;
        ValidationMessagesGroup messages = new ValidationMessagesGroup();
        if (Modelling.getModelling().getComponent(component.getId()) == null) {
            if (!IdValidator.validade(component, messages)) {
                hasValidationProblems = true;
            }
            if (!ComponentValidator.validateCommit(component, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                addComponent(component);
            }
            if (component.getType() == ComponentType.STANDARD_PROCESS) {
                if (autoCreateRequiredElements) {
                    Component child = new Component();
                    child.setType(ComponentType.ROLE_SET);
                    child.setParentId(component.getId());
                    ModellingController.commitComponent(child);
                    child = new Component();
                    child.setType(ComponentType.WORK_PRODUCT_SET);
                    child.setParentId(component.getId());
                    ModellingController.commitComponent(child);
                    child = new Component();
                    child.setType(ComponentType.TOOL_SET);
                    child.setParentId(component.getId());
                    ModellingController.commitComponent(child);
                    child = new Component();
                    child.setType(ComponentType.GUIDANCE_SET);
                    child.setParentId(component.getId());
                    ModellingController.commitComponent(child);
                }
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        } else {
            if (!ComponentValidator.validateCommit(component, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                updateComponent(component);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        }
        if (!hasValidationProblems) {
            if (component.getStereotypesIds().size() > 0) {
                for (Component componentFromList : Modelling.getModelling()
                        .getComponents()) {
                    if (componentFromList.getDiagram() != null) {
                        if (componentFromList.getDiagram()
                                .getDiagramComponentStereotypesGroup(
                                        component.getId()) == null
                                && componentFromList.getDiagram()
                                        .getDiagramComponent(component.getId()) != null) {
                            DiagramComponent diagramComponent = componentFromList
                                    .getDiagram().getDiagramComponent(
                                            component.getId());
                            DiagramComponentStereotypesGroup diagramComponentStereotypesGroup = new DiagramComponentStereotypesGroup();
                            diagramComponentStereotypesGroup.setId(component
                                    .getId());
                            diagramComponentStereotypesGroup
                                    .setX(diagramComponent.getX() + 16.0);
                            diagramComponentStereotypesGroup
                                    .setY(diagramComponent.getY() - 10.0);
                            componentFromList.getDiagram()
                                    .addDiagramComponentStereotypesGroup(
                                            diagramComponentStereotypesGroup);
                            addDiagramElements(
                                    graphs.get(componentFromList.getId())
                                            .getGraph(),
                                    componentFromList.getDiagram());
                        }
                    }
                }
            } else {
                for (Component componentFromList : Modelling.getModelling()
                        .getComponents()) {
                    if (componentFromList.getDiagram() != null) {
                        if (componentFromList.getDiagram()
                                .getDiagramComponentStereotypesGroup(
                                        component.getId()) != null) {
                            componentFromList.getDiagram()
                                    .removeComponentStereotypesGroup(
                                            component.getId());
                            addDiagramElements(
                                    graphs.get(componentFromList.getId())
                                            .getGraph(),
                                    componentFromList.getDiagram());
                        }
                    }
                }
            }
            setElementToShowProperties(elementShowingPropertiesId);
            setComponentToShowDiagram(componentShowingDiagramId);

            TreeController.update();
        }
        return !hasValidationProblems;
    }

    private static void addRelationship(Relationship relationship) {
        Modelling.getModelling().addRelationship(relationship);

    }

    private static void updateRelationship(Relationship relationship) {
        Modelling.getModelling().addRelationship(relationship);
        diagramPanel.updateUI();
    }

    private static boolean autoCreateRequiredElements = true;

    public static void setAutoCreateRequiredElements(boolean value) {
        autoCreateRequiredElements = value;
    }

    public static boolean getAutoCreateRequiredElements() {
        return autoCreateRequiredElements;
    }

    public static boolean commitRelationship(Relationship relationship) {
        boolean hasValidationProblems = false;
        ValidationMessagesGroup messages = new ValidationMessagesGroup();
        if (Modelling.getModelling().getRelationship(relationship.getId()) == null) {
            if (!IdValidator.validade(relationship, messages)) {
                hasValidationProblems = true;
            }
            if (!RelationshipValidator.validateCommit(relationship, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                addRelationship(relationship);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        } else {
            if (!RelationshipValidator.validateCommit(relationship, messages)) {
                hasValidationProblems = true;
            }
            if (!hasValidationProblems) {
                updateRelationship(relationship);
            }
            for (int i = 0; i < messages.size(); ++i) {
                if (messages.get(i).getType() == ValidationMessageType.ERROR
                        || messages.get(i).getType() == ValidationMessageType.CONSTRAINT
                        || messages.get(i).getType() == ValidationMessageType.CORRECTION) {
                    messages.get(i).show();
                }
            }
        }
        if (!hasValidationProblems) {
            if (relationship.getStereotypesIds().size() > 0) {
                for (Component componentFromList : Modelling.getModelling()
                        .getComponents()) {
                    if (componentFromList.getDiagram() != null) {
                        if (componentFromList.getDiagram()
                                .getDiagramRelationshipStereotypesGroup(
                                        relationship.getId()) == null
                                && componentFromList.getDiagram()
                                        .getDiagramRelationship(
                                                relationship.getId()) != null) {
                            DiagramRelationshipStereotypesGroup diagramRelationshipStereotypesGroup = new DiagramRelationshipStereotypesGroup();
                            diagramRelationshipStereotypesGroup
                                    .setId(relationship.getId());
                            DiagramComponent source = componentFromList
                                    .getDiagram().getDiagramComponent(
                                            relationship.getSourceId());
                            DiagramComponent destination = componentFromList
                                    .getDiagram().getDiagramComponent(
                                            relationship.getDestinationId());
                            double x = destination.getX() - source.getX();
                            double y = destination.getY() - source.getY();
                            if (y > x) {
                                y = y / 2 + source.getY();
                                x = x / 2 + 20 + source.getX();
                            } else {
                                x = x / 2 + source.getX();
                                y = y / 2 + 20 + source.getY();
                            }
                            diagramRelationshipStereotypesGroup.setX(x);
                            diagramRelationshipStereotypesGroup.setY(y);
                            componentFromList
                                    .getDiagram()
                                    .addDiagramRelationshipStereotypesGroup(
                                            diagramRelationshipStereotypesGroup);
                            addDiagramElements(
                                    graphs.get(componentFromList.getId())
                                            .getGraph(),
                                    componentFromList.getDiagram());
                        }
                    }
                }
            } else {
                for (Component componentFromList : Modelling.getModelling()
                        .getComponents()) {
                    if (componentFromList.getDiagram() != null) {
                        if (componentFromList.getDiagram()
                                .getDiagramRelationshipStereotypesGroup(
                                        relationship.getId()) != null) {
                            componentFromList.getDiagram()
                                    .removeRelationshipStereotypesGroup(
                                            relationship.getId());
                            addDiagramElements(
                                    graphs.get(componentFromList.getId())
                                            .getGraph(),
                                    componentFromList.getDiagram());
                        }
                    }
                }
            }
            setElementToShowProperties(elementShowingPropertiesId);
            setComponentToShowDiagram(componentShowingDiagramId);

        }
        return !hasValidationProblems;
    }

    private static void updateComponent(Component component) {
        if (component.getType() == ComponentType.WORK_PRODUCT_USE) {
            String lastName = Modelling.getModelling()
                    .getComponent(component.getId()).getName();
            for (Component componentFromList : Modelling.getModelling()
                    .getComponents()) {
                if (componentFromList.getType() == ComponentType.MILESTONE
                        && Modelling
                                .getModelling()
                                .getComponent(componentFromList.getParentId())
                                .getParentId()
                                .equals(Modelling.getModelling()
                                        .getComponent(component.getParentId())
                                        .getParentId())
                        && componentFromList.getDetails() != null
                        && componentFromList.getDetails().equalsIgnoreCase(
                                lastName)) {
                    componentFromList.setDetails(component.getName());
                }
            }
        }
        Modelling.getModelling().addComponent(component);
        TreeController.get(component.getId()).setUserObject(component);
        Diagram diagram = component.getDiagram();
        if (diagram == null) {
            return;
        }
        GraphComponent graphComponent = graphs.get(component.getId());
        addDiagramElements(graphComponent.getGraph(), diagram);
        diagramPanel.updateUI();
        setElementToShowProperties(elementShowingPropertiesId);
    }

    public static void setDiagramPanel(JPanel diagramPanel) {
        ModellingController.diagramPanel = diagramPanel;

    }

    public static void setProcessPackageTree(JTree processPackageTree) {
        TreeController.setProcessPackageTree(processPackageTree);
    }

    public static void setPropertiesScrollPane(JScrollPane propertiesScrollPane) {
        ModellingController.propertiesScrollPane = propertiesScrollPane;
    }

    public static void setFeedBackScrollPane(JScrollPane propertiesScrollPane) {
        ModellingController.feedBackScrollPane = propertiesScrollPane;
    }

    /**
     * Indica se controlador está pronto para criar e adicionar um novo
     * componente à modelagem.
     */
    public static boolean isReadyToAddAComponent() {
        return readyToAddAComponent;

    }

    /**
     * Indica se o controlador de modelagem deverá estar pronto ou não para
     * criar e adicionar um novo componente à modelagem.
     */
    public static void setReadyToAddAComponent(boolean ready) {
        ModellingController.readyToAddAComponent = ready;

        if (readyToAddAComponent) {
            setReadyToAddARelationship(false);

        }
    }

    /**
     * Indica qual o tipo do componente que deve ser criado e adicionado à
     * modelagem.
     *
     * @param componentType
     *            o tipo do componente
     */
    public static void setComponentTypeToAdd(ComponentType componentType) {
        componentTypeToAdd = componentType;

    }

    /**
     * Indica se o controlador de modelagem está ou não pronto para criar e
     * adicionar um novo relacionamento à modelagem.
     */
    public static boolean isReadyToAddARelationship() {
        return readyToAddARelationship;

    }

    /**
     * Método usado para indicar se será adicionado ou não um relaciomento à
     * modelagem.
     */
    public static void setReadyToAddARelationship(boolean ready) {
        readyToAddARelationship = ready;
        if (readyToAddARelationship) {
            setReadyToAddAComponent(false);
            currentGraph.getGraph().setSelectionCell(null);
        } else {
            relationshipSource = null;
            relationshipDestination = null;
        }
    }

    /**
     * Indica qual o tipo do relacionamento que deve ser criado e adicionado à
     * modelagem.
     *
     * @param relationshipType
     *            o tipo do relacionamento
     */
    public static void setRelationshipTypeToAdd(
            RelationshipType relationshipType) {
        ModellingController.relationshipTypeToAdd = relationshipType;

    }

    /**
     * Determina qual o componente que terá o diagrama e as propriedades
     * exibidas na interface da ferramenta.
     *
     * @param componentId
     *            o id do componente que terá o diagrama e as propriedades
     *            exibidas
     */
    public static void setSelectedComponent(Integer componentId) {
        if (componentId == null) {
            JTabbedPane tabbedPane = (JTabbedPane) diagramPanel.getParent();
            tabbedPane.setTabComponentAt(0, null);
            setComponentToShowDiagram(null);
            setElementToShowProperties(null);
            return;
        }

        ComponentType componentType = Modelling.getModelling()
                .getComponent(componentId).getType();

        if ((componentType != ComponentType.ACTIVITY)
                && (componentType != ComponentType.DISCIPLINE)
                && (componentType != ComponentType.ITERATION)
                && (componentType != ComponentType.PHASE)
                && (componentType != ComponentType.PROCESS)
                && (componentType != ComponentType.PROCESS_PACKAGE)
                && (componentType != ComponentType.STANDARD_PROCESS)
                && (componentType != ComponentType.TASK_DEFINITION)
                && (componentType != ComponentType.TASK_USE)) {

            setElementToShowProperties(componentId);
            return;
        }
        setReadyToAddAComponent(false);
        setReadyToAddARelationship(false);
        elementShowingPropertiesId = componentId;
        componentShowingDiagramId = componentId;
        configureDiagramPanel();
        configurePropertiesPanel();

        JLabel label = new JLabel(Modelling.getModelling()
                .getComponent(componentId).getName());

        if (componentType.equals(ComponentType.ACTIVITY)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.ACTIVITY)));
        } else if (componentType.equals(ComponentType.DISCIPLINE)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.DISCIPLINE)));
        } else if (componentType.equals(ComponentType.ITERATION)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.ITERATION)));
        } else if (componentType.equals(ComponentType.PHASE)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.PHASE)));
        } else if (componentType.equals(ComponentType.PROCESS)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.PROCESS)));
        } else if (componentType.equals(ComponentType.PROCESS_PACKAGE)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.PROCESS_PACKAGE)));
        } else if (componentType.equals(ComponentType.STANDARD_PROCESS)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.STANDARD_PROCESS)));
        } else if (componentType.equals(ComponentType.TASK_DEFINITION)) {
            label.setIcon(new ImageIcon(ImageIcon.class
                    .getResource(ComponentImageFile.TASK_DEFINITION)));
        } else if (componentType.equals(ComponentType.TASK_USE)) {
           
            Tarefa tarefa = FindByProcess.findTarefa(Modelling.getModelling()
                    .getComponent(componentId));
            if (tarefa.getEstado().equals("Em Execu\u00E7\u00E3o"))
                label.setIcon(new ImageIcon(ImageIcon.class
                        .getResource(ComponentImageFile.TASK_USE_PLAY)));
            else if (tarefa.getEstado().equals("Pausada"))
                label.setIcon(new ImageIcon(ImageIcon.class
                        .getResource(ComponentImageFile.TASK_USE_PAUSED)));
            else if (tarefa.getEstado().equals("Finalizada"))
                label.setIcon(new ImageIcon(ImageIcon.class
                        .getResource(ComponentImageFile.TASK_USE_FINISHED)));
            else
                label.setIcon(new ImageIcon(ImageIcon.class
                        .getResource(ComponentImageFile.TASK_USE)));
        }
        label.setHorizontalAlignment(SwingConstants.LEFT);
        JTabbedPane tabbedPane = (JTabbedPane) diagramPanel.getParent();
        tabbedPane.setTabComponentAt(0, label);

        Object cell = ModellingController.getCurrentGraph().getCellAt(0, 0);
        Object[] obj = { cell };
        ModellingController.getCurrentGraph().getGraph().moveCells(obj, 1, 1);
    }

    /**
     * Retorna o id do elemento cuja as propriedades estão sendo exibidas na
     * interface gráfica da ferramenta.
     *
     * @return o id do componente com as propriedades sendo exibidas
     */
    public static Integer getElementShowingPropertiesId() {
        return elementShowingPropertiesId;
    }

    /**
     * Determina o elemento cuja as propriedades serão exibidas.
     *
     * @param componentId
     *            o id do componente que terá as propriedades exibidas
     */
    public static void setElementToShowProperties(Integer componentId) {
        elementShowingPropertiesId = componentId;
        configurePropertiesPanel();
    }

    /**
     * Indica qual o componente cujo diagrama está sendo exibido.
     *
     * @return o id do componente cujo diagrama está sendo exibido
     */
    public static Integer getComponentShowingDiagramId() {
        return componentShowingDiagramId;
    }

    /**
     * Determina qual o componente terá o seu diagrama exibido na ferramenta.
     *
     * @param componentId
     *            o id do componente que terá o diagrama exibido na ferramenta.
     */
    private static void setComponentToShowDiagram(Integer componentId) {
        componentShowingDiagramId = componentId;
        configureDiagramPanel();
    }

    public static GraphComponent getDiagram(Integer id) {
        return graphs.get(id);
    }

    public static void setPropertiesTabbedPane(JTabbedPane propertiesTabbedPane2) {
        ModellingController.propertiesTabbedPane = propertiesTabbedPane2;
    }

    public static void update() {
        TreeController.processPackageTree.repaint();
        TreeController.processPackageTree.validate();
    }

    public static GraphComponent getGraphComponent(int id) {
        return graphs.get(id);

    }

    public static TaskUsePropertiesPanel getTaskUsePropertiesPanel() {
        return taskUsePropertiesPanel;
    }
}