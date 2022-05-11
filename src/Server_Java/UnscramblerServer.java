//package server;

import unscrambler.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class UnscramblerServer {
    public static void main(String[] args){
        try{
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            UnscramblerImpl unscramblerImpl = new UnscramblerImpl();

            org.omg.CORBA.Object ref =  rootpoa.servant_to_reference(unscramblerImpl);
            unscrambleridl cref = unscrambler.unscrambleridlHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "unscramble";
            NameComponent path[]= ncRef.to_name(name);
            ncRef.rebind(path, cref);

            System.out.println("\nUnscramblerServer ready and waiting...");
            System.out.println("...");

            orb.run();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
