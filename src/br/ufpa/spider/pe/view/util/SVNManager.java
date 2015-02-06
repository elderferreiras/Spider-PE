package br.ufpa.spider.pe.view.util;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNManager {
	public static SVNCommitInfo addDir( ISVNEditor editor , String dirPath , String filePath , byte[] data ) throws SVNException {
		/*
		 * Classe respons�vel por criar o diretorio no reposit�rio e ap�s criado o diret�rio, tamb�m cria
		 * o arquivo a partir dos bytes que s�o passados por par�metro (byte[] data).  Ela � utilizada no
		 * m�todo saveToRepository.
		 */
        editor.openRoot( -1 );
        editor.addFile( filePath , null , -1 );
        editor.applyTextDelta( filePath , null );
        SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator( );
        String checksum = deltaGenerator.sendDelta( filePath , new ByteArrayInputStream( data ) , editor , true );
        editor.closeFile(filePath, checksum);
        editor.closeDir();
        return editor.closeEdit();
    }
	
	public static void setupLibrary() {
		/*
		 * Classe respons�vel por inicializar as bibliotecas respons�veis pela conex�o com o reposit�rio,
		 * ela � usada no m�todo openFile(...).
		 */
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
    }
	
	public static void saveToRepository(String nome, String login, String password, String url, String path){
		/*
		 * Classe respons�vel por salvar um arquivo no reposit�rio.
		 * Os par�metros s�o:
		 * nome: String - o nome e a extens�o que o arquivo ter� no reposit�rio, por exemplo: "arquivo.pdf"
		 * name: String - o login para acessar o reposit�rio.
		 * password: String - a senha referente ao login para acessar o reposit�rio.
		 * url: String - o caminho onde ser� colocado o arquivo que ser� criado, por exemplo: 
		 * http://www.spidernet.icen.ufpa.br/svn/trunk/spider/Subprojetos/execucaoProcesso/Publicacoes/
		 * path: String -  o caminho local para o arquivo, por exemplo: a classe ArquivoPDF cria um pdf localmente
		 * e no seu m�todo ITextHelloWorld(...) � definido onde ser� salvo temporariamente o arquivo, atrav�s da vari�vel path,
		 * o m�todo saveToRepository(...) recebe o endere�o local para esse arquivo para poder extrair os bytes do dito cujo
		 * e ent�o subir com esses bytes para o reposit�rio.		 * 
		 */
		DAVRepositoryFactory.setup();
        byte[] contents=null;
        try{
        FileInputStream fis = new FileInputStream(path);  
        contents = new byte[fis.available()];  
        fis.read(contents);  
        fis.close();
        }
        catch(Exception e){
        	System.out.println(e);
        }
        
        SVNRepository repository = null;
        try {        	
            repository = SVNRepositoryFactory.create( SVNURL.parseURIDecoded( url ) );
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( login , password );
            repository.setAuthenticationManager( authManager );
            
            String logMessage = "log message";
            ISVNEditor editor = repository.getCommitEditor( logMessage , null /*locks*/ , true /*keepLocks*/ , null /*mediator*/ );	            
           
            try {
                SVNCommitInfo commitInfo = addDir( editor , null ,nome , contents );
                JOptionPane.showMessageDialog(null, "O arquivo foi adicionado em: " + commitInfo );
            } catch ( SVNException svne ) {
                editor.abortEdit( );
                throw svne;
            }
            
        }
        catch(Exception e){
        	JOptionPane.showMessageDialog(null, e);
        }
	}
	
	public static void saveToRepositoryPolitica(String nome, String login, String password, String url, File file){
		/*
		 * Classe respons�vel por salvar um arquivo no reposit�rio.
		 * Os par�metros s�o:
		 * nome: String - o nome e a extens�o que o arquivo ter� no reposit�rio, por exemplo: "arquivo.pdf"
		 * name: String - o login para acessar o reposit�rio.
		 * password: String - a senha referente ao login para acessar o reposit�rio.
		 * url: String - o caminho onde ser� colocado o arquivo que ser� criado, por exemplo: 
		 * http://www.spidernet.icen.ufpa.br/svn/trunk/spider/Subprojetos/execucaoProcesso/Publicacoes/
		 * path: String -  o caminho local para o arquivo, por exemplo: a classe ArquivoPDF cria um pdf localmente
		 * e no seu m�todo ITextHelloWorld(...) � definido onde ser� salvo temporariamente o arquivo, atrav�s da vari�vel path,
		 * o m�todo saveToRepository(...) recebe o endere�o local para esse arquivo para poder extrair os bytes do dito cujo
		 * e ent�o subir com esses bytes para o reposit�rio.		 * 
		 */
		DAVRepositoryFactory.setup();
        byte[] contents=null;
        try{
        FileInputStream fis = new FileInputStream(file);  
        contents = new byte[fis.available()];  
        fis.read(contents);  
        fis.close();
        }
        catch(Exception e){
        	System.out.println(e);
        }
        
        SVNRepository repository = null;
        try {        	
            repository = SVNRepositoryFactory.create( SVNURL.parseURIDecoded( url ) );
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( login , password );
            repository.setAuthenticationManager( authManager );
            
            String logMessage = "log message";
            ISVNEditor editor = repository.getCommitEditor( logMessage , null /*locks*/ , true /*keepLocks*/ , null /*mediator*/ );	            
           
            try {
                SVNCommitInfo commitInfo = addDir( editor , null ,nome , contents );
                JOptionPane.showMessageDialog(null, "O arquivo foi adicionado em: " + commitInfo );
            } catch ( SVNException svne ) {
                editor.abortEdit( );
                throw svne;
            }
            
        }
        catch(Exception e){
        	JOptionPane.showMessageDialog(null, e);
        }
	}
	
	public static void openFile(String url, String path, String name, String password, String nomeTemp) throws IOException{
		/*
		 * url: String - o caminho onde est� o arquivo que se deseja visualizar, por exemplo: 
		 * http://www.spidernet.icen.ufpa.br/svn/trunk/spider/Subprojetos/execucaoProcesso/Publicacoes/
		 * path: String - o nome e a extens�o que o arquivo possui no reposit�rio, por exemplo: "arquivo.pdf"
		 * name: String - o login para acessar o reposit�rio.
		 * password: String - a senha referente ao login para acessar o reposit�rio.
		 * nomeTemp: String - um nome tempor�rio qualquer para o arquivo criado no computador.
		 */
        SVNManager.setupLibrary();
        SVNRepository repository = null;
        
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
        } catch (SVNException svne) {            
            System.err
                    .println("Erro enquanto estava sendo criado um Reposit�rio SVN para alocar o arquivo."
                            + url + "': " + svne.getMessage());
        }
        
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
        repository.setAuthenticationManager(authManager);
        SVNProperties fileProperties = new SVNProperties();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            SVNNodeKind nodeKind = repository.checkPath(path, -1);
            
            if (nodeKind == SVNNodeKind.NONE) {
                System.err.println("N�o existe entrada para: '" + url + "'.");
            } else if (nodeKind == SVNNodeKind.DIR) {
                System.err.println("A entrada para" + url
                        + "� um diret�rio, enquanto que uma arquivo � esperado.");
            }
            repository.getFile(path, -1, fileProperties, baos);

        } catch (SVNException svne) {
            System.err.println("Erro enquanto estava buscando as propriedades e o conte�do do arquivo." + svne.getMessage());
        }
        
        byte[] b  =  baos.toByteArray();
       
        File fileDestiny =  File.createTempFile(nomeTemp, ".pdf");
        
        try {
        	FileOutputStream o = new FileOutputStream(fileDestiny);       
			o.write(b); 
	        o.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
        
	    try {
			Desktop.getDesktop().open(fileDestiny);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}  
	}
	
	
}
