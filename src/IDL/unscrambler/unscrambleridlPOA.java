package unscrambler;


/**
* unscrambler/unscrambleridlPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from unscrambler.idl
* Tuesday, May 25, 2021 1:09:16 PM SGT
*/

public abstract class unscrambleridlPOA extends org.omg.PortableServer.Servant
 implements unscrambler.unscrambleridlOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addToUsersArray", new java.lang.Integer (0));
    _methods.put ("answerIsCorrect", new java.lang.Integer (1));
    _methods.put ("ScrambledWord", new java.lang.Integer (2));
    _methods.put ("reScramble", new java.lang.Integer (3));
    _methods.put ("returnAttemptsLeft", new java.lang.Integer (4));
    _methods.put ("exitUser", new java.lang.Integer (5));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // unscrambler/unscrambleridl/addToUsersArray
       {
         String username = in.read_string ();
         boolean $result = false;
         $result = this.addToUsersArray (username);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // unscrambler/unscrambleridl/answerIsCorrect
       {
         String username = in.read_string ();
         String answer = in.read_string ();
         boolean $result = false;
         $result = this.answerIsCorrect (username, answer);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // unscrambler/unscrambleridl/ScrambledWord
       {
         String username = in.read_string ();
         String $result = null;
         $result = this.ScrambledWord (username);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // unscrambler/unscrambleridl/reScramble
       {
         String username = in.read_string ();
         String $result = null;
         $result = this.reScramble (username);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // unscrambler/unscrambleridl/returnAttemptsLeft
       {
         String username = in.read_string ();
         short $result = (short)0;
         $result = this.returnAttemptsLeft (username);
         out = $rh.createReply();
         out.write_short ($result);
         break;
       }

       case 5:  // unscrambler/unscrambleridl/exitUser
       {
         String username = in.read_string ();
         this.exitUser (username);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:unscrambler/unscrambleridl:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public unscrambleridl _this() 
  {
    return unscrambleridlHelper.narrow(
    super._this_object());
  }

  public unscrambleridl _this(org.omg.CORBA.ORB orb) 
  {
    return unscrambleridlHelper.narrow(
    super._this_object(orb));
  }


} // class unscrambleridlPOA