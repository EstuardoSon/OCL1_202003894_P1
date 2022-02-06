package ocl1_proyecto1;
import java_cup.runtime.Symbol;
import java.util.*; 

%%

%{
    public List tokens = new ArrayList();
    String cadena = "";
%}

%cup
%class AnalizadorLexico
%public
%line
%char
%column
%full
%state LEX, COUNI, COMULTI
%debug

//Simbolos
LLAVE_APERTURA = "{"
LLAVE_CIERRE = "}"
DOS_PTS = ":"
PT_COMA = ";"
ASIGNACION = "->" 
SEPARADOR = (\%)+

//Palabras Reservadas
CONJ = "CONJ"

//Expresiones
IDENTIFICADOR = (_)*[a-zA-ZnÑ]+[_a-zA-Z0-9ñÑ]*
CONJUNTO = ([a-z]\~[a-z])|([^a-zA-Z0-9\~]\~[^a-zA-Z0-9\~])|([A-Z]\~[A-Z])|([0-9]\~[0-9])|([^\~](,[^\~])+)
EXPRESION = ([\.\|\*\+\?]|\{(_)*[a-zA-ZnÑ]+[_a-zA-Z0-9ñÑ]*\}|\"(.|(\\\")|(\\n)|(\\\'))\")+
ESPACIO = [\ \r\t\f\t]
SALTO = [\ \n]

%%

<YYINITIAL> {CONJ} { return new Symbol(sym.CONJ, yyline, yycolumn, yytext());}

<YYINITIAL> {LLAVE_APERTURA} { return new Symbol(sym.LLAVE_APERTURA, yyline, yycolumn, yytext());}
<YYINITIAL> {LLAVE_CIERRE} { return new Symbol(sym.LLAVE_CIERRE, yyline, yycolumn, yytext());}
<YYINITIAL> {DOS_PTS} { return new Symbol(sym.DOS_PTS, yyline, yycolumn, yytext());}
<YYINITIAL> {PT_COMA} { return new Symbol(sym.PT_COMA, yyline, yycolumn, yytext());}
<YYINITIAL> {ASIGNACION} { return new Symbol(sym.ASIGNACION, yyline, yycolumn, yytext());}
<YYINITIAL> {SEPARADOR} { return new Symbol(sym.SEPARADOR, yyline, yycolumn, yytext());}

<YYINITIAL> {IDENTIFICADOR} { return new Symbol(sym.IDENTIFICADOR, yyline, yycolumn, yytext());}
<YYINITIAL> {CONJUNTO} { return new Symbol(sym.CONJUNTO, yyline, yycolumn, yytext());}
<YYINITIAL> {EXPRESION} { return new Symbol(sym.EXPRESION, yyline, yycolumn, yytext());}
<YYINITIAL> \" { yybegin(LEX); cadena += "\"";}
<YYINITIAL> \/\/ { yybegin(COUNI);}
<YYINITIAL> \<\! { yybegin(COMULTI);}
<YYINITIAL> {ESPACIO} { }
<YYINITIAL> {SALTO} { System.out.println("salto"); }
<YYINITIAL> . {}

<LEX>{
    \\\" { cadena+=yytext();}
    \\n { cadena+=yytext();}
    \\\' { cadena+=yytext();}
    \" { String tmp=cadena+"\""; cadena=""; yybegin(YYINITIAL); return new Symbol(sym.FRASE, yychar, yyline, tmp);}
    [\n] { String tmp=cadena; cadena=""; yybegin (YYINITIAL);}
    [^\"] { cadena+=yytext(); }
}

<COUNI>{
    . {}
    [\n] { yybegin(YYINITIAL); }
}

<COMULTI>{
    . {}
    \!\> { yybegin(YYINITIAL); }
}