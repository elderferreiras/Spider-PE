package br.ufpa.spider.pe.model.util;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Risco;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Stakeholder;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.view.util.Icone;

public class MyTableModel {
	public static DefaultTableModel modelProdutoTrabalho(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 String.class, String.class, String.class, ImageIcon.class, ImageIcon.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelDefinicaoMedicao(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 String.class, String.class, ImageIcon.class, ImageIcon.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
            	
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelLog(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Tarefa.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
            	
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelManagers(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Processo.class, GerenteProcesso.class, String.class,ImageIcon.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {            	
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelRecursoHardware(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Hardware.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	
	public static DefaultTableModel modelCapacidade(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 String.class, String.class, String.class, ArrayList.class, ArrayList.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelTreinamento(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelRevisaoProblemas(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }

	public static DefaultTableModel modelGerenciaResponsabilidade(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Tarefa.class, Papel.class, Humano.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	public static DefaultTableModel modelPlanoComunicacao(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Object.class, String.class,  String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	public static DefaultTableModel modelStakeHolder(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Stakeholder.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	public static DefaultTableModel modelGerenciaRecursos(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Tarefa.class, String.class, Object.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelCronograma(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false,false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	public static DefaultTableModel modelCadastroRisco(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {
        	
            Class[] types = new Class[]{
            		 Risco.class, String.class, String.class ,String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	public static DefaultTableModel modelGerenciaRisco(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {
        	
            Class[] types = new Class[]{
            		Risco.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelEsforco(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		Tarefa.class, Integer.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelTipos(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Tipo.class
            };
            boolean[] canEdit = new boolean[]{
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelRecursoHumano(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Humano.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
	
	public static DefaultTableModel modelRecursoSoftware(Vector columns, Vector data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {

            Class[] types = new Class[]{
            		 Software.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        return model;
    }
}
