package unscrambler;

/**
* unscrambler/unscrambleridlHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from unscrambler.idl
* Tuesday, May 25, 2021 1:09:16 PM SGT
*/

public final class unscrambleridlHolder implements org.omg.CORBA.portable.Streamable
{
  public unscrambler.unscrambleridl value = null;

  public unscrambleridlHolder ()
  {
  }

  public unscrambleridlHolder (unscrambler.unscrambleridl initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = unscrambler.unscrambleridlHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    unscrambler.unscrambleridlHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return unscrambler.unscrambleridlHelper.type ();
  }

}