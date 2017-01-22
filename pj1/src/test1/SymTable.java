package test1;
import java.util.*;

/**
 * Created by fuhao on 1/21/17.
 */
public class SymTable {
    List< HashMap<String, Sym> > scopes;
    public SymTable(){
        //SymTable()    This is the constructor; it should initialize the SymTable's List field to contain a single, empty HashMap.
        scopes = new LinkedList<HashMap<String, Sym> >();
        HashMap<String, Sym> first = new HashMap<String, Sym>();
        scopes.add(0, first);
    }

    public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException, NullPointerException{
        // If this SymTable's list is empty, throw an EmptySymTableException.
        // If either name or sym (or both) is null, throw a NullPointerException.
        // If the first HashMap in the list already contains the given name as a key, throw a DuplicateSymException. Otherwise, add the given name and sym to the first HashMap in the list.
        if(scopes.size() == 0){
            throw new EmptySymTableException();
        }

        if(name == null || sym == null){
            throw new NullPointerException();
        }

        if(scopes.get(0).containsKey(name)){
            throw new DuplicateSymException();
        }
        else{
            scopes.get(0).put(name, sym);
        }

    }


    public void addScope(){
        //Add a new, empty HashMap to the front of the list.
        HashMap<String, Sym> tmp = new HashMap<String, Sym>();
        scopes.add(0, tmp);
    }

    public Sym lookupLocal(String name) throws EmptySymTableException{
        //  If this SymTable's list is empty, throw an EmptySymTableException.
        // Otherwise, if the first HashMap in the list contains name as a key, return the associated Sym; otherwise, return null.
        if(scopes.isEmpty()) throw new EmptySymTableException();
        else {
            return scopes.get(0).get(name);
        }
    }

    public Sym lookupGlobal(String name) throws EmptySymTableException{
        //  If this SymTable's list is empty, throw an EmptySymTableException.
        // If any HashMap in the list contains name as a key, return the first associated Sym (i.e., the one from the HashMap that is closest to the front of the list); otherwise, return null.
        if(scopes.isEmpty()) throw new EmptySymTableException();
        else{
            int l = scopes.size();
            for(int i = 0; i < l; i++){
                if(scopes.get(i).containsKey(name)) return scopes.get(i).get(name);
            }
            return null;
        }
    }

    public void removeScope() throws EmptySymTableException{
        //  If this SymTable's list is empty, throw an EmptySymTableException;
        //  otherwise, remove the HashMap from the front of the list.
        // To clarify, throw an exception only if before attempting to remove, the list is empty (i.e. there are no HashMaps to remove).
        if(scopes.isEmpty()) throw new EmptySymTableException();
        else{
            scopes.remove(0);
        }
    }

    public void print(){
        //  This method is for debugging. First, print “\nSym Table\n”. Then, for each HashMap M in the list, print M.toString() followed by a newline. Finally, print one more newline. All output should go to System.out.
        System.out.print("\nSym Table\n");
        int l = scopes.size();
        for(int i = 0; i < l; i++){
            System.out.println(scopes.get(i).toString());
        }
        System.out.println();
    }
}
