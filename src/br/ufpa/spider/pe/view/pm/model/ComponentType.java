package br.ufpa.spider.pe.view.pm.model;

/**
 * Enumeração que indica todos os possíveis tipos de componentes que podem ser
 * selecionados durante a modelagem na ferramenta.
 */
public enum ComponentType {

    ACTIVITY,
    DECISION_AND_MERGE,
    DISCIPLINE,
    FINAL_STATE,
    FORK,
    GUIDANCE,
    GUIDANCE_SET,
    INITIAL_STATE,
    ITERATION,
    JOIN,
    MILESTONE,
    NOTE,
    PHASE,
    PROCESS,
    PROCESS_PACKAGE,
    ROLE_DEFINITION,
    ROLE_SET,
    ROLE_USE,
    STANDARD_PROCESS,
    TASK_DEFINITION,
    TASK_USE,
    TOOL_DEFINITION,
    TOOL_SET,
    TOOL_USE,
    WORK_PRODUCT_DEFINITION,
    WORK_PRODUCT_SET,
    WORK_PRODUCT_USE;

    @Override
    public String toString() {
        if (equals(ACTIVITY)) {
            return "Activity";
        } else if (equals(DECISION_AND_MERGE)) {
            return "DecisionAndMerge";
        } else if (equals(DISCIPLINE)) {
            return "Discipline";
        } else if (equals(FINAL_STATE)) {
            return "FinalState";
        } else if (equals(FORK)) {
            return "Fork";
        } else if (equals(GUIDANCE)) {
            return "Guidance";
        } else if (equals(GUIDANCE_SET)) {
            return "GuidanceSet";
        } else if (equals(INITIAL_STATE)) {
            return "InitialState";
        } else if (equals(ITERATION)) {
            return "Iteration";
        } else if (equals(JOIN)) {
            return "Join";
        } else if (equals(MILESTONE)) {
            return "Milestone";
        } else if (equals(NOTE)) {
            return "Note";
        } else if (equals(PHASE)) {
            return "Phase";
        } else if (equals(PROCESS)) {
            return "Process";
        } else if (equals(PROCESS_PACKAGE)) {
            return "ProcessPackage";
        } else if (equals(ROLE_DEFINITION)) {
            return "RoleDefinition";
        } else if (equals(ROLE_SET)) {
            return "RoleSet";
        } else if (equals(ROLE_USE)) {
            return "RoleUse";
        } else if (equals(STANDARD_PROCESS)) {
            return "StantardProcess";
        } else if (equals(TASK_DEFINITION)) {
            return "TaskDefinition";
        } else if (equals(TASK_USE)) {
            return "TaskUse";
        } else if (equals(TOOL_DEFINITION)) {
            return "ToolDefinition";
        } else if (equals(TOOL_SET)) {
            return "ToolSet";
        } else if (equals(TOOL_USE)) {
            return "ToolUse";
        } else if (equals(WORK_PRODUCT_DEFINITION)) {
            return "WorkProductDefinition";
        } else if (equals(WORK_PRODUCT_SET)) {
            return "WorkProductSet";
        } else if (equals(WORK_PRODUCT_USE)) {
            return "WorkProductUse";
        } else {
            return null;
        }
    }

     public String toStringInPortuguese() {
        if (equals(ACTIVITY)) {
            return "Atividade";
        } else if (equals(DECISION_AND_MERGE)) {
            return "Decisão e União";
        } else if (equals(DISCIPLINE)) {
            return "Disciplina";
        } else if (equals(FINAL_STATE)) {
            return "Estado Final";
        } else if (equals(FORK)) {
            return "Bifurcação";
        } else if (equals(GUIDANCE)) {
            return "Procedimento";
        } else if (equals(GUIDANCE_SET)) {
            return "Conjunto de Procedimentos";
        } else if (equals(INITIAL_STATE)) {
            return "Estado Inicial";
        } else if (equals(ITERATION)) {
            return "Iteração";
        } else if (equals(JOIN)) {
            return "Junção";
        } else if (equals(MILESTONE)) {
            return "Marco";
        } else if (equals(NOTE)) {
            return "Nota";
        } else if (equals(PHASE)) {
            return "Fase";
        } else if (equals(PROCESS)) {
            return "Processo Instanciado";
        } else if (equals(PROCESS_PACKAGE)) {
            return "Conjunto de Processos Instanciados";
        } else if (equals(ROLE_DEFINITION)) {
            return "Definição de Papel";
        } else if (equals(ROLE_SET)) {
            return "Conjunto de Papéis";
        } else if (equals(ROLE_USE)) {
            return "Papel Instanciado";
        } else if (equals(STANDARD_PROCESS)) {
            return "Processo Padrão";
        } else if (equals(TASK_DEFINITION)) {
            return "Definição de Tarefa";
        } else if (equals(TASK_USE)) {
            return "Tarefa Instanciada";
        } else if (equals(TOOL_DEFINITION)) {
            return "Definição de Ferramenta";
        } else if (equals(TOOL_SET)) {
            return "Conjunto de Ferramentas";
        } else if (equals(TOOL_USE)) {
            return "Ferramenta Instanciada";
        } else if (equals(WORK_PRODUCT_DEFINITION)) {
            return "Definição de Produto de Trabalho";
        } else if (equals(WORK_PRODUCT_SET)) {
            return "Conjunto de Produtos de Trabalho";
        } else if (equals(WORK_PRODUCT_USE)) {
            return "Produto de Trabalho Instanciado";
        } else {
            return null;
        }
    }

    public static ComponentType parseComponentType(String string) {
        if (string.compareToIgnoreCase("ACTIVITY") == 0) {
            return ACTIVITY;
        } else if ((string.compareToIgnoreCase("DECISION_AND_MERGE") == 0)
                       || (string.compareToIgnoreCase("DECISIONANDMERGE") == 0)) {
            return DECISION_AND_MERGE;
        } else if (string.compareToIgnoreCase("DISCIPLINE") == 0) {
            return DISCIPLINE;
        } else if ((string.compareToIgnoreCase("FINAL_STATE") == 0)
                       || (string.compareToIgnoreCase("FINALSTATE") == 0)) {
            return FINAL_STATE;
        } else if (string.compareToIgnoreCase("FORK") == 0) {
            return FORK;
        } else if (string.compareToIgnoreCase("GUIDANCE") == 0) {
            return GUIDANCE;
        } else if ((string.compareToIgnoreCase("GUIDANCE_SET") == 0)
                       || (string.compareToIgnoreCase("GUIDANCESET") == 0)) {
            return GUIDANCE_SET;
        } else if ((string.compareToIgnoreCase("INITIAL_STATE") == 0)
                       || (string.compareToIgnoreCase("INITIALSTATE") == 0)) {
            return INITIAL_STATE;
        } else if (string.compareToIgnoreCase("ITERATION") == 0) {
            return ITERATION;
        } else if (string.compareToIgnoreCase("JOIN") == 0) {
            return JOIN;
        } else if (string.compareToIgnoreCase("MILESTONE") == 0) {
            return MILESTONE;
        } else if (string.compareToIgnoreCase("NOTE") == 0) {
            return NOTE;
        } else if (string.compareToIgnoreCase("PHASE") == 0) {
            return PHASE;
        } else if (string.compareToIgnoreCase("PROCESS") == 0) {
            return PROCESS;
        } else if ((string.compareToIgnoreCase("PROCESS_PACKAGE") == 0)
                       || (string.compareToIgnoreCase("PROCESSPACKAGE") == 0)) {
            return PROCESS_PACKAGE;
        } else if ((string.compareToIgnoreCase("ROLE_DEFINITION") == 0)
                       || (string.compareToIgnoreCase("ROLEDEFINITION") == 0)) {
            return ROLE_DEFINITION;
        } else if ((string.compareToIgnoreCase("ROLE_SET") == 0)
                       || (string.compareToIgnoreCase("ROLESET") == 0)) {
            return ROLE_SET;
        } else if ((string.compareToIgnoreCase("ROLE_USE") == 0)
                       || (string.compareToIgnoreCase("ROLEUSE") == 0)) {
            return ROLE_USE;
        } else if ((string.compareToIgnoreCase("STANDARD_PROCESS") == 0)
                       || (string.compareToIgnoreCase("STANDARDPROCESS") == 0)) {
            return STANDARD_PROCESS;
        } else if ((string.compareToIgnoreCase("TASK_DEFINITION") == 0)
                       || (string.compareToIgnoreCase("TASKDEFINITION") == 0)) {
            return TASK_DEFINITION;
        } else if ((string.compareToIgnoreCase("TASK_USE") == 0)
                       || (string.compareToIgnoreCase("TASKUSE") == 0)) {
            return TASK_USE;
        } else if ((string.compareToIgnoreCase("TOOL_DEFINITION") == 0)
                       || (string.compareToIgnoreCase("TOOLDEFINITION") == 0)) {
            return TOOL_DEFINITION;
        } else if ((string.compareToIgnoreCase("TOOL_SET") == 0)
                       || (string.compareToIgnoreCase("TOOLSET") == 0)) {
            return TOOL_SET;
        } else if ((string.compareToIgnoreCase("TOOL_USE") == 0)
                       || (string.compareToIgnoreCase("TOOLUSE") == 0)) {
            return TOOL_USE;
        } else if ((string.compareToIgnoreCase("WORK_PRODUCT_DEFINITION")
                        == 0)
                       || (string.compareToIgnoreCase("WORKPRODUCTDEFINITION")
                           == 0)) {
            return WORK_PRODUCT_DEFINITION;
        } else if ((string.compareToIgnoreCase("WORK_PRODUCT_SET") == 0)
                       || (string.compareToIgnoreCase("WORKPRODUCTSET") == 0)) {
            return WORK_PRODUCT_SET;
        } else if ((string.compareToIgnoreCase("WORK_PRODUCT_USE") == 0)
                       || (string.compareToIgnoreCase("WORKPRODUCTUSE") == 0)) {
            return WORK_PRODUCT_USE;
        } else {
            return null;
        }
    }

}
