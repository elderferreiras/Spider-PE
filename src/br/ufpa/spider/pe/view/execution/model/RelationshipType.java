package br.ufpa.spider.pe.view.execution.model;

/**
 * Os tipos de relacionamentos presentes na modelagem.
 */
public enum RelationshipType {

    AGGREGATION,
    COMPOSITION,
    DEPENDENCY,
    GENERALIZATION,
    ASSOCIATION,
    ANCHOR,
    TRANSITION;

    @Override
    public String toString() {
        if (equals(AGGREGATION)) {
            return "Aggregation";
        } else if (equals(ANCHOR)) {
            return "Anchor";
        } else if (equals(ASSOCIATION)) {
            return "Association";
        } else if (equals(COMPOSITION)) {
            return "Composition";
        } else if (equals(DEPENDENCY)) {
            return "Dependency";
        } else if (equals(GENERALIZATION)) {
            return "Generalization";
        } else if (equals(TRANSITION)) {
            return "Transition";
        } else {
            return null;
        }
    }

    public String toStringInPortuguese() {
        if (equals(AGGREGATION)) {
            return "Agrega��o";
        } else if (equals(ANCHOR)) {
            return "�ncora de Nota";
        } else if (equals(ASSOCIATION)) {
            return "Associa��o";
        } else if (equals(COMPOSITION)) {
            return "Composi��o";
        } else if (equals(DEPENDENCY)) {
            return "Depend�ncia";
        } else if (equals(GENERALIZATION)) {
            return "Generaliza��o";
        } else if (equals(TRANSITION)) {
            return "Transi��o";
        } else {
            return null;
        }
    }

    public static RelationshipType parseRelationshipType(String s) {
        if (s.compareToIgnoreCase("AGGREGATION") == 0) {
            return AGGREGATION;
        } else if (s.compareToIgnoreCase("AGGREGATION") == 0) {
            return ANCHOR;
        } else if (s.compareToIgnoreCase("ASSOCIATION") == 0) {
            return ASSOCIATION;
        } else if (s.compareToIgnoreCase("COMPOSITION") == 0) {
            return COMPOSITION;
        } else if (s.compareToIgnoreCase("DEPENDENCY") == 0) {
            return DEPENDENCY;
        } else if (s.compareToIgnoreCase("GENERALIZATION") == 0) {
            return GENERALIZATION;
        } else if (s.compareToIgnoreCase("TRANSITION") == 0) {
            return TRANSITION;
        } else {
            return null;
        }
    }
}
