package GUI.FileTree;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Clase que se utiliza para poder colocar un orden al Arbol de Archivos de 
 * la interfaz grafica
 * @author Ricardo
 */
public class FileSystemModel implements TreeModel {

    private File root;
    private Vector listeners = new Vector();
    
    /**
     * Metodo constructor del Modelo del JTree
     * 
     * @param rootDirectory Parametro que permite usar un archivo como la raiz 
     */
    
    public FileSystemModel(File rootDirectory) {
        root = rootDirectory;
    }

    /**
     * 
     * @return Retorna la raiz del modelo
     */
    @Override
    public Object getRoot() {
        return root;
    }

    /**
     * Metodo que obtiene el hijo de algun nodo del arbol
     * @param parent Padre del nodo
     * @param index indice del hijo
     * @return retorna Un TreeFile
     */
    @Override
    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        String[] children = directory.list();
 /*       for (int j = 0; j< children.length; j++ ){
            System.out.println(children[j]);
        }       */
        
        return new FileSystemModel.TreeFile(directory, children[index]);
    }

    /**
     * Metodo que devuelve el numero de hijos de un Nodo Padre
     * @param parent Nodo padre 
     * @return devuelve el numero de hijos de un Nodo Padre 
     */
    @Override
    public int getChildCount(Object parent) {
        File file = (File) parent;
        if (file.isDirectory()) {
            String[] fileList = file.list();
          
            if (fileList != null) {
                return file.list().length;
            }
        }
        return 0;
    }
    /**
     * Metodo para poder ver si un nodo es hoja o no
     * @param node Nodo que se quiere saber si es hoja o no
     * @return un Booelano, True o False 
     */
    @Override
    public boolean isLeaf(Object node) {
        File file = (File) node;
        return file.isFile();
    }

    /**
     * Metodo que busca el indice de algun Hijo
     * @param parent NOdo padre
     * @param child NOdo hijo
     * @return retorna el indice del del Hijo
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File file = (File) child;
        String[] children = directory.list();
        for (int i = 0; i < children.length; i++) {
            if (file.getName().equals(children[i])) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public void valueForPathChanged(TreePath path, Object value) {
        File oldFile = (File) path.getLastPathComponent();
        String fileParentPath = oldFile.getParent();
        String newFileName = (String) value;
        File targetFile = new File(fileParentPath, newFileName);
        oldFile.renameTo(targetFile);
        File parent = new File(fileParentPath);
        int[] changedChildrenIndices = {getIndexOfChild(parent, targetFile)};
        Object[] changedChildren = {targetFile};
        fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);

    }

    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
        Iterator iterator = listeners.iterator();
        TreeModelListener listener = null;
        while (iterator.hasNext()) {
            listener = (TreeModelListener) iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(listener);
    }

    private class TreeFile extends File {

        public TreeFile(File parent, String child) {
            super(parent, child);
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}