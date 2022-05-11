# Python stubs generated by omniidl from unscrambler.idl
# DO NOT EDIT THIS FILE!

import omniORB, _omnipy
from omniORB import CORBA, PortableServer
_0_CORBA = CORBA


_omnipy.checkVersion(4,2, __file__, 1)

try:
    property
except NameError:
    def property(*args):
        return None


#
# Start of module "unscrambler"
#
__name__ = "unscrambler"
_0_unscrambler = omniORB.openModule("unscrambler", r"unscrambler.idl")
_0_unscrambler__POA = omniORB.openModule("unscrambler__POA", r"unscrambler.idl")


# interface unscrambleridl
_0_unscrambler._d_unscrambleridl = (omniORB.tcInternal.tv_objref, "IDL:unscrambler/unscrambleridl:1.0", "unscrambleridl")
omniORB.typeMapping["IDL:unscrambler/unscrambleridl:1.0"] = _0_unscrambler._d_unscrambleridl
_0_unscrambler.unscrambleridl = omniORB.newEmptyClass()
class unscrambleridl :
    _NP_RepositoryId = _0_unscrambler._d_unscrambleridl[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0_unscrambler.unscrambleridl = unscrambleridl
_0_unscrambler._tc_unscrambleridl = omniORB.tcInternal.createTypeCode(_0_unscrambler._d_unscrambleridl)
omniORB.registerType(unscrambleridl._NP_RepositoryId, _0_unscrambler._d_unscrambleridl, _0_unscrambler._tc_unscrambleridl)

# unscrambleridl operations and attributes
unscrambleridl._d_addToUsersArray = (((omniORB.tcInternal.tv_string,0), ), (omniORB.tcInternal.tv_boolean, ), None)
unscrambleridl._d_answerIsCorrect = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0)), (omniORB.tcInternal.tv_boolean, ), None)
unscrambleridl._d_ScrambledWord = (((omniORB.tcInternal.tv_string,0), ), ((omniORB.tcInternal.tv_string,0), ), None)
unscrambleridl._d_reScramble = (((omniORB.tcInternal.tv_string,0), ), ((omniORB.tcInternal.tv_string,0), ), None)
unscrambleridl._d_returnAttemptsLeft = (((omniORB.tcInternal.tv_string,0), ), (omniORB.tcInternal.tv_short, ), None)
unscrambleridl._d_exitUser = (((omniORB.tcInternal.tv_string,0), ), (), None)

# unscrambleridl object reference
class _objref_unscrambleridl (CORBA.Object):
    _NP_RepositoryId = unscrambleridl._NP_RepositoryId

    def __init__(self, obj):
        CORBA.Object.__init__(self, obj)

    def addToUsersArray(self, *args):
        return self._obj.invoke("addToUsersArray", _0_unscrambler.unscrambleridl._d_addToUsersArray, args)

    def answerIsCorrect(self, *args):
        return self._obj.invoke("answerIsCorrect", _0_unscrambler.unscrambleridl._d_answerIsCorrect, args)

    def ScrambledWord(self, *args):
        return self._obj.invoke("ScrambledWord", _0_unscrambler.unscrambleridl._d_ScrambledWord, args)

    def reScramble(self, *args):
        return self._obj.invoke("reScramble", _0_unscrambler.unscrambleridl._d_reScramble, args)

    def returnAttemptsLeft(self, *args):
        return self._obj.invoke("returnAttemptsLeft", _0_unscrambler.unscrambleridl._d_returnAttemptsLeft, args)

    def exitUser(self, *args):
        return self._obj.invoke("exitUser", _0_unscrambler.unscrambleridl._d_exitUser, args)

omniORB.registerObjref(unscrambleridl._NP_RepositoryId, _objref_unscrambleridl)
_0_unscrambler._objref_unscrambleridl = _objref_unscrambleridl
del unscrambleridl, _objref_unscrambleridl

# unscrambleridl skeleton
__name__ = "unscrambler__POA"
class unscrambleridl (PortableServer.Servant):
    _NP_RepositoryId = _0_unscrambler.unscrambleridl._NP_RepositoryId


    _omni_op_d = {"addToUsersArray": _0_unscrambler.unscrambleridl._d_addToUsersArray, "answerIsCorrect": _0_unscrambler.unscrambleridl._d_answerIsCorrect, "ScrambledWord": _0_unscrambler.unscrambleridl._d_ScrambledWord, "reScramble": _0_unscrambler.unscrambleridl._d_reScramble, "returnAttemptsLeft": _0_unscrambler.unscrambleridl._d_returnAttemptsLeft, "exitUser": _0_unscrambler.unscrambleridl._d_exitUser}

unscrambleridl._omni_skeleton = unscrambleridl
_0_unscrambler__POA.unscrambleridl = unscrambleridl
omniORB.registerSkeleton(unscrambleridl._NP_RepositoryId, unscrambleridl)
del unscrambleridl
__name__ = "unscrambler"

#
# End of module "unscrambler"
#
__name__ = "unscrambler_idl"

_exported_modules = ( "unscrambler", )

# The end.
