package test1;

/**
 * Created by fuhao on 1/21/17.
 */
public class P1 {
    public static void main(String [] args) {
        SymTable table = new SymTable();
        Sym t = new Sym("int");
        String k = "global_a";


        try{
            table.addDecl(k, t);
            table.addDecl(k, t);
            //should catch DuplicateSymException
        }
        catch(NullPointerException n){
            System.out.println("rcaught an NullPointerException Error");
        }
        catch (EmptySymTableException emp){
            System.out.println("caught an EmptySymTableException Error");
        }
        catch (DuplicateSymException dup){
            System.out.println("caught an DuplicateSymException Error");
        }



        try{
            k = null;
            table.addDecl(k, t);
            //should catch NullPointerException
        }
        catch(NullPointerException n){
            System.out.println("caught an NullPointerException Error");
        }
        catch (EmptySymTableException emp){
            System.out.println("caught an EmptySymTableException Error");
        }
        catch (DuplicateSymException dup){
            System.out.println("caught an DuplicateSymException Error");
        }



        try{
            table.removeScope();
            k = "global_a";
            table.addDecl(k, t);
            //should catch EmptySymTableException
        }
        catch(NullPointerException n){
            System.out.println("caught an NullPointerException Error");
        }
        catch (EmptySymTableException emp){
            System.out.println("caught an EmptySymTableException Error");
        }
        catch (DuplicateSymException dup){
            System.out.println("caught an DuplicateSymException Error");
        }

        try{
            table.lookupLocal(k);
            //should catch EmptySymTableException
        }
        catch(NullPointerException n){
            System.out.println("caught an NullPointerException Error");
        }
        catch (EmptySymTableException emp){
            System.out.println("caught an EmptySymTableException Error");
        }


        try{
            table.addScope();
            k = "global_a";
            t = new Sym("int");
            table.addDecl(k, t);

            t = new Sym("double");
            table.addScope();
            table.addDecl(k, t);
            //should not catch any exception, but found two var with same name with differnt type in different scope
        }
        catch(NullPointerException n){
            System.out.println("caught an NullPointerException Error");
        }
        catch (EmptySymTableException emp){
            System.out.println("caught an EmptySymTableException Error");
        }
        catch (DuplicateSymException dup){
            System.out.println("caught an DuplicateSymException Error");
        }



        try{
            k = "local_a";
            t = new Sym("int");
            Sym tmp = new Sym("int");
            table.addDecl(k, t);

            if(table.lookupLocal("local_a").getType() != tmp.getType()){
                System.out.println("wrong answer when add local var");
            }


            tmp = new Sym("double");
            if(table.lookupGlobal("global_a").getType() != tmp.getType()){
                System.out.println("wrong answer when local and global var has the same name");
            }
            table.print();

        }
        catch(NullPointerException n){
            System.out.println("caught an NullPointerException Error");
        }
        catch (EmptySymTableException emp){
            System.out.println("caught an EmptySymTableException Error");
        }
        catch (DuplicateSymException dup){
            System.out.println("caught an DuplicateSymException Error");
        }

    }
}
