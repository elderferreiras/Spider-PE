package br.ufpa.spider.pe.view.pm.model;

public enum ExtendibleElementType {

    AGGREGATION,
    COMPOSITION,
    DEPENDENCY,
    GENERALIZATION,
    ASSOCIATION,
    TRANSITION,
    ACTIVITY,
    DISCIPLINE,
    GUIDANCE,
    ITERATION,
    MILESTONE,
    PHASE,
    PROCESS,
    PROCESS_PACKAGE,
    ROLE_DEFINITION,
    ROLE_USE,
    STANDARD_PROCESS,
    TASK_DEFINITION,
    TASK_USE,
    TOOL_DEFINITION,
    TOOL_USE,
    WORK_PRODUCT_DEFINITION,
    WORK_PRODUCT_USE;

    @Override
    public String toString() {
        if (equals(AGGREGATION)) {
            return "Agregação";
        } else if (equals(ASSOCIATION)) {
            return "Associação";
        } else if (equals(COMPOSITION)) {
            return "Composição";
        } else if (equals(DEPENDENCY)) {
            return "Dependência";
        } else if (equals(GENERALIZATION)) {
            return "Generalização";
        } else if (equals(TRANSITION)) {
            return "Transição";
        } else if (equals(ACTIVITY)) {
            return "Atividade";
        } else if (equals(DISCIPLINE)) {
            return "Disciplina";
        } else if (equals(GUIDANCE)) {
            return "Procedimento";
        } else if (equals(ITERATION)) {
            return "Iteração";
        } else if (equals(MILESTONE)) {
            return "Marco";
        } else if (equals(PHASE)) {
            return "Fase";
        } else if (equals(PROCESS)) {
            return "Processo";
        } else if (equals(PROCESS_PACKAGE)) {
            return "Conjunto de Processos Instanciados";
        } else if (equals(ROLE_DEFINITION)) {
            return "Definição de Papel";
        } else if (equals(ROLE_USE)) {
            return "Papel Instanciado";
        } else if (equals(STANDARD_PROCESS)) {
            return "Processo padrão";
        } else if (equals(TASK_DEFINITION)) {
            return "Definição de Tarefa";
        } else if (equals(TASK_USE)) {
            return "Tarefa Instanciada";
        } else if (equals(TOOL_DEFINITION)) {
            return "Definição de Ferramenta";
        } else if (equals(TOOL_USE)) {
            return "Ferramenta Instanciada";
        } else if (equals(WORK_PRODUCT_DEFINITION)) {
            return "Definição de Produto de Trabalho";
        } else if (equals(WORK_PRODUCT_USE)) {
            return "Produto de Trabalho Instanciado";
        } else {
            return null;
        }
    }

    public boolean equalsToComponentOrRelationshipType(Object object) {
        if(object.getClass() == ComponentType.class){
            ComponentType componentType = (ComponentType)object;
            return componentType.toStringInPortuguese().equalsIgnoreCase(this.toString());
        } else if(object.getClass() == RelationshipType.class){
            RelationshipType relationshipType = (RelationshipType) object;
            return relationshipType.toStringInPortuguese().equalsIgnoreCase(this.toString());
        } else {
            return false;
        }
    }

}
