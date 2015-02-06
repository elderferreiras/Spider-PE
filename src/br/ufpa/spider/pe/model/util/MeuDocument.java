package br.ufpa.spider.pe.model.util;
    import javax.swing.text.AttributeSet;  
    import javax.swing.text.BadLocationException;  
    import javax.swing.text.PlainDocument;  
      
    public class MeuDocument extends PlainDocument{     
      
        @Override  
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {  
            try {  
                Integer.parseInt(str);  
                String texto = getText(0, getLength());  
                if(texto.length() < 100) {  
                    remove(0, getLength());  
                    StringBuffer strBuf = new StringBuffer(texto.replaceAll(",", "") + str);  
                    if(strBuf.length() < 3) {  
                        strBuf.insert(0, ",");  
                    } else {  
                        strBuf.insert(strBuf.length()-2, ",");  
                    }  
                    super.insertString(0, strBuf.toString(), a);  
                }  
            } catch(Exception e) {}  
        }   
    }   