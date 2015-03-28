/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpa.spider.pe.view.execution.logic;

import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.ComponentType;

/**
 *
 * @author Specktro
 */
class ComponentNameController {

    private static int standardProcessCounter;

    private static int disciplineCounter;

    private static int taskDefinitionCounter;

    private static int roleDefitionCounter;

    private static int workProductDefinitionCounter;

    private static int toolDefinitionCounter;

    private static int guidanceCounter;

    private static int processPackageCounter;

    private static int processCounter;

    private static int phaseCounter;

    private static int iterationCounter;

    private static int milestoneCounter;

    private static int activityCounter;

    private static int taskUseCounter;

    private static int roleUseCounter;

    private static int workProductUseCounter;

    private static int toolUseCounter;

    private static int toolSetCounter;

    private static int roleSetCounter;

    private static int guidanceSetCounter;

    private static int workProductSetCounter;

    private static int noteCounter;

    private ComponentNameController() {

    }

    static void initialize() {
        noteCounter = 0;
        standardProcessCounter = 0;
        disciplineCounter = 0;
        taskDefinitionCounter = 0;
        toolDefinitionCounter = 0;
        guidanceCounter = 0;
        roleDefitionCounter = 0;
        workProductDefinitionCounter = 0;
        processPackageCounter = 0;
        processCounter = 0;
        phaseCounter = 0;
        iterationCounter = 0;
        milestoneCounter = 0;
        activityCounter = 0;
        taskUseCounter = 0;
        roleUseCounter = 0;
        workProductUseCounter = 0;
        toolUseCounter = 0;
        toolSetCounter = 0;
        roleSetCounter = 0;
        workProductSetCounter = 0;
        guidanceSetCounter = 0;
    }

    static void addName(Component component) {
        if (component.getType().equals(ComponentType.DISCIPLINE)) {
            component.setName(getNextDisciplineName());
        } else if (component.getType().equals(ComponentType.TASK_DEFINITION)) {
            component.setName(getNextTaskDefinitionName());
        } else if (component.getType().equals(ComponentType.TOOL_DEFINITION)) {
            component.setName(getNextToolDefinitionName());
        } else if (component.getType().equals(ComponentType.WORK_PRODUCT_DEFINITION)) {
            component.setName(getNextWorkProductDefinitionName());
        } else if (component.getType().equals(ComponentType.GUIDANCE)) {
            component.setName(getNextGuidanceName());
        } else if (component.getType().equals(ComponentType.ROLE_DEFINITION)) {
            component.setName(getNextRoleDefinitionName());
        } else if (component.getType().equals(ComponentType.STANDARD_PROCESS)) {
            component.setName(getNextStandardProcessName());
        } else if (component.getType().equals(ComponentType.PROCESS_PACKAGE)) {
            component.setName(getNextProcessPackageName());
        } else if (component.getType().equals(ComponentType.PROCESS)) {
            component.setName(getNextProcessName());
        } else if (component.getType().equals(ComponentType.PHASE)) {
            component.setName(getNextPhaseName());
        } else if (component.getType().equals(ComponentType.ITERATION)) {
            component.setName(getNextIterationName());
        } else if (component.getType().equals(ComponentType.MILESTONE)) {
            component.setName(getNextMilestoneName());
        } else if (component.getType().equals(ComponentType.ACTIVITY)) {
            component.setName(getNextActivityName());
        } else if (component.getType().equals(ComponentType.TASK_USE)) {
            component.setName(getNextTaskUseName());
        } else if (component.getType().equals(ComponentType.ROLE_USE)) {
            component.setName(getNextRoleUseName());
        } else if (component.getType().equals(ComponentType.WORK_PRODUCT_USE)) {
            component.setName(getNextWorkProductUseName());
        } else if (component.getType().equals(ComponentType.TOOL_USE)) {
            component.setName(getNextToolUseName());
        } else if (component.getType().equals(ComponentType.TOOL_SET)) {
            component.setName(getNextToolSetName());
        } else if (component.getType().equals(ComponentType.ROLE_SET)) {
            component.setName(getNextRoleSetName());
        } else if (component.getType().equals(ComponentType.GUIDANCE_SET)) {
            component.setName(getNextGuidanceSetName());
        } else if (component.getType().equals(ComponentType.WORK_PRODUCT_SET)) {
            component.setName(getNextWorkProductSetName());
        } else if (component.getType().equals(ComponentType.NOTE)) {
            component.setName(getNextNoteName());
        } else {
            component.setName("");
        }
    }

    private static String getNextNoteName() {
        ++noteCounter;
        if (noteCounter > 9) {
            return ComponentType.NOTE.toStringInPortuguese() + " " + noteCounter;
        } else {
            return ComponentType.NOTE.toStringInPortuguese() + " 0" + noteCounter;
        }
    }

    private static String getNextWorkProductSetName() {
        ++workProductSetCounter;
        if (workProductSetCounter > 9) {
            return ComponentType.WORK_PRODUCT_SET.toStringInPortuguese() + " " + workProductSetCounter;
        } else {
            return ComponentType.WORK_PRODUCT_SET.toStringInPortuguese() + " 0" + workProductSetCounter;
        }
    }

    private static String getNextGuidanceSetName() {
        ++guidanceSetCounter;
        if (guidanceSetCounter > 9) {
            return ComponentType.GUIDANCE_SET.toStringInPortuguese() + " " + guidanceSetCounter;
        } else {
            return ComponentType.GUIDANCE_SET.toStringInPortuguese() + " 0" + guidanceSetCounter;
        }
    }

    private static String getNextRoleSetName() {
        ++roleSetCounter;
        if (roleSetCounter > 9) {
            return ComponentType.ROLE_SET.toStringInPortuguese() + " " + roleSetCounter;
        } else {
            return ComponentType.ROLE_SET.toStringInPortuguese() + " 0" + roleSetCounter;
        }
    }

    private static String getNextToolSetName() {
        ++toolSetCounter;
        if (toolSetCounter > 9) {
            return ComponentType.TOOL_SET.toStringInPortuguese() + " " + toolSetCounter;
        } else {
            return ComponentType.TOOL_SET.toStringInPortuguese() + " 0" + toolSetCounter;
        }
    }

    private static String getNextProcessPackageName() {
        String name = ComponentType.PROCESS_PACKAGE.toStringInPortuguese();
        if (processPackageCounter > 9) {
            name += " " + processPackageCounter;
        } else if (processPackageCounter > 0) {
            name += " 0" + processPackageCounter;
        }
        ++processPackageCounter;
        return name;
    }

    private static String getNextPhaseName() {
        ++phaseCounter;
        if (phaseCounter > 9) {
            return ComponentType.PHASE.toStringInPortuguese() + " " + phaseCounter;
        } else {
            return ComponentType.PHASE.toStringInPortuguese() + " 0" + phaseCounter;
        }
    }

    private static String getNextIterationName() {
        ++iterationCounter;
        if (iterationCounter > 9) {
            return ComponentType.ITERATION.toStringInPortuguese() + " " + iterationCounter;
        } else {
            return ComponentType.ITERATION.toStringInPortuguese() + " 0" + iterationCounter;
        }
    }

    private static String getNextMilestoneName() {
        ++milestoneCounter;
        if (milestoneCounter > 9) {
            return ComponentType.MILESTONE.toStringInPortuguese() + " " + milestoneCounter;
        } else {
            return ComponentType.MILESTONE.toStringInPortuguese() + " 0" + milestoneCounter;
        }
    }

    private static String getNextActivityName() {
        ++activityCounter;
        if (activityCounter > 9) {
            return ComponentType.ACTIVITY.toStringInPortuguese() + " " + activityCounter;
        } else {
            return ComponentType.ACTIVITY.toStringInPortuguese() + " 0" + activityCounter;
        }
    }

    private static String getNextTaskUseName() {
        ++taskUseCounter;
        if (taskUseCounter > 9) {
            return ComponentType.TASK_USE.toStringInPortuguese() + " " + taskUseCounter;
        } else {
            return ComponentType.TASK_USE.toStringInPortuguese() + " 0" + taskUseCounter;
        }
    }

    private static String getNextRoleUseName() {
        ++roleUseCounter;
        if (roleUseCounter > 9) {
            return ComponentType.ROLE_USE.toStringInPortuguese() + " " + roleUseCounter;
        } else {
            return ComponentType.ROLE_USE.toStringInPortuguese() + " 0" + roleUseCounter;
        }
    }

    private static String getNextWorkProductUseName() {
        ++workProductUseCounter;
        if (workProductUseCounter > 9) {
            return ComponentType.WORK_PRODUCT_USE.toStringInPortuguese() + " " + workProductUseCounter;
        } else {
            return ComponentType.WORK_PRODUCT_USE.toStringInPortuguese() + " 0" + workProductUseCounter;
        }
    }

    private static String getNextToolUseName() {
        ++toolUseCounter;
        if (toolUseCounter > 9) {
            return ComponentType.TOOL_USE.toStringInPortuguese() + " " + toolUseCounter;
        } else {
            return ComponentType.TOOL_USE.toStringInPortuguese() + " 0" + toolUseCounter;
        }
    }

    private static String getNextStandardProcessName() {
        String name = ComponentType.STANDARD_PROCESS.toStringInPortuguese();
        if (standardProcessCounter > 9) {
            name += " " + standardProcessCounter;
        } else if (standardProcessCounter > 0) {
            name += " 0" + standardProcessCounter;
        }
        ++standardProcessCounter;
        return name;
    }

    private static String getNextDisciplineName() {
        ++disciplineCounter;
        if (disciplineCounter > 9) {
            return ComponentType.DISCIPLINE.toStringInPortuguese() + " " + disciplineCounter;
        } else {
            return ComponentType.DISCIPLINE.toStringInPortuguese() + " 0" + disciplineCounter;
        }
    }

    private static String getNextProcessName() {
        ++processCounter;
        if (processCounter > 9) {
            return ComponentType.PROCESS.toStringInPortuguese() + " " + processCounter;
        } else {
            return ComponentType.PROCESS.toStringInPortuguese() + " 0" + processCounter;
        }
    }

    private static String getNextTaskDefinitionName() {
        ++taskDefinitionCounter;
        if (taskDefinitionCounter > 9) {
            return ComponentType.TASK_DEFINITION.toStringInPortuguese() + " " + taskDefinitionCounter;
        } else {
            return ComponentType.TASK_DEFINITION.toStringInPortuguese() + " 0" + taskDefinitionCounter;
        }
    }

    private static String getNextToolDefinitionName() {
        ++toolDefinitionCounter;
        if (toolDefinitionCounter > 9) {
            return ComponentType.TOOL_DEFINITION.toStringInPortuguese() + " " + toolDefinitionCounter;
        } else {
            return ComponentType.TOOL_DEFINITION.toStringInPortuguese() + " 0" + toolDefinitionCounter;
        }
    }

    private static String getNextRoleDefinitionName() {
        ++roleDefitionCounter;
        if (roleDefitionCounter > 9) {
            return ComponentType.ROLE_DEFINITION.toStringInPortuguese() + " " + roleDefitionCounter;
        } else {
            return ComponentType.ROLE_DEFINITION.toStringInPortuguese() + " 0" + roleDefitionCounter;
        }
    }

    private static String getNextWorkProductDefinitionName() {
        ++workProductDefinitionCounter;
        if (workProductDefinitionCounter > 9) {
            return ComponentType.WORK_PRODUCT_DEFINITION.toStringInPortuguese() + " " + workProductDefinitionCounter;
        } else {
            return ComponentType.WORK_PRODUCT_DEFINITION.toStringInPortuguese() + " 0" + workProductDefinitionCounter;
        }
    }

    private static String getNextGuidanceName() {
        ++guidanceCounter;
        if (guidanceCounter > 9) {
            return ComponentType.GUIDANCE.toStringInPortuguese() + " " + guidanceCounter;
        } else {
            return ComponentType.GUIDANCE.toStringInPortuguese() + " 0" + guidanceCounter;
        }
    }

}
