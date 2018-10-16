/*CODIGO DE USUARIO*/
package DasmPackage.Analizador;
import java_cup.runtime.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
/*OPCIONES Y DECLARACION*/
%%

%{
    String nombreArchivo;
    public void setNombreArchivo(String nombreArchivo)
    {
      this.nombreArchivo = nombreArchivo;
    }
%}

%public
%class DasmLex
%cupsym Simbolos
%cup
%char
%column
%full
%line
%unicode

//REGLAS
entero = [0-9]+
decimal = [0-9]+\.[0-9]+
identificador = \$([a-zA-Z]|_)([a-zA-Z]|[0-9]|_)*

inicioComentarioMult = "/*"
finComentarioMult ="*/"
inicioComentarioLinea = "//"

// -----------> ESTADOS
%state MULTICOMMENT
%state LINECOMMENT

%%
// REGLAS LEXICAS
<YYINITIAL> "ADD"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.add, yycolumn, yyline, yytext());}
<YYINITIAL> "DIFF"                                          {return new Symbol(DasmPackage.Analizador.Simbolos.diff, yycolumn, yyline, yytext());}
<YYINITIAL> "MULT"                                          {return new Symbol(DasmPackage.Analizador.Simbolos.mult, yycolumn, yyline, yytext());}
<YYINITIAL> "DIV"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.div, yycolumn, yyline, yytext());}
<YYINITIAL> "MOD"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.mod, yycolumn, yyline, yytext());}
<YYINITIAL> "LT"                                            {return new Symbol(DasmPackage.Analizador.Simbolos.lt, yycolumn, yyline, yytext());}
<YYINITIAL> "GT"                                            {return new Symbol(DasmPackage.Analizador.Simbolos.gt, yycolumn, yyline, yytext());}
<YYINITIAL> "LTE"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.lte, yycolumn,yyline, yytext());}
<YYINITIAL> "GTE"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.gte, yycolumn, yyline, yytext());}
<YYINITIAL> "EQZ"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.eqz, yycolumn, yyline, yytext());}
<YYINITIAL> "AND"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.and, yycolumn, yyline, yytext());}
<YYINITIAL> "OR"                                            {return new Symbol(DasmPackage.Analizador.Simbolos.or, yycolumn, yyline, yytext());}
<YYINITIAL> "NOT"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.not, yycolumn, yyline, yytext());}
<YYINITIAL> "BR"                                            {return new Symbol(DasmPackage.Analizador.Simbolos.br, yycolumn, yyline, yytext());}
<YYINITIAL> "BR_IF"                                         {return new Symbol(DasmPackage.Analizador.Simbolos.brIf, yycolumn, yyline, yytext());}
<YYINITIAL> "get_local"                                     {return new Symbol(DasmPackage.Analizador.Simbolos.glocal, yycolumn, yyline, yytext());}
<YYINITIAL> "set_local"                                     {return new Symbol(DasmPackage.Analizador.Simbolos.slocal, yycolumn, yyline, yytext());}
<YYINITIAL> "get_global"                                    {return new Symbol(DasmPackage.Analizador.Simbolos.gglobal, yycolumn, yyline,yytext());}
<YYINITIAL> "set_global"                                    {return new Symbol(DasmPackage.Analizador.Simbolos.sglobal, yycolumn, yyline, yytext());}
<YYINITIAL> "tee_local"                                     {return new Symbol(DasmPackage.Analizador.Simbolos.tlocal, yycolumn, yyline, yytext());}
<YYINITIAL> "tee_global"                                    {return new Symbol(DasmPackage.Analizador.Simbolos.tglobal, yycolumn, yyline, yytext());}
<YYINITIAL> "Function"                                      {return new Symbol(DasmPackage.Analizador.Simbolos.function, yycolumn, yyline, yytext());}
<YYINITIAL> "End"                                           {return new Symbol(DasmPackage.Analizador.Simbolos.end, yycolumn, yyline, yytext());}
<YYINITIAL> "$calc"                                         {return new Symbol(DasmPackage.Analizador.Simbolos.calc, yycolumn, yyline, yytext());}
<YYINITIAL> "$Point"                                        {return new Symbol(DasmPackage.Analizador.Simbolos.point, yycolumn, yyline, yytext());}
<YYINITIAL> "$Quadrate"                                     {return new Symbol(DasmPackage.Analizador.Simbolos.quadrate, yycolumn, yyline, yytext());}
<YYINITIAL> "$Oval"                                         {return new Symbol(DasmPackage.Analizador.Simbolos.oval, yycolumn, yyline, yytext());}
<YYINITIAL> "$String"                                       {return new Symbol(DasmPackage.Analizador.Simbolos.str, yycolumn, yyline, yytext());}
<YYINITIAL> "$Line"                                         {return new Symbol(DasmPackage.Analizador.Simbolos.line, yycolumn, yyline, yytext());}
<YYINITIAL> "Call"                                          {return new Symbol(DasmPackage.Analizador.Simbolos.llama, yycolumn, yyline, yytext());}
// EXPRESIONES REGULARES
<YYINITIAL> {entero}                                        {return new Symbol(DasmPackage.Analizador.Simbolos.entero, yycolumn, yyline, yytext());}
<YYINITIAL> {decimal}                                       {return new Symbol(DasmPackage.Analizador.Simbolos.decimal, yycolumn, yyline, yytext());}
<YYINITIAL> {identificador}                                 {return new Symbol(DasmPackage.Analizador.Simbolos.identificador, yycolumn, yyline, yytext());}
<YYINITIAL> {inicioComentarioLinea}                         {yybegin(LINECOMMENT);}
<YYINITIAL> {inicioComentarioMult}                          {yybegin(MULTICOMMENT);}
///////////////////////////////////////////////////////////////////////////////////
<MULTICOMMENT>
{
    {finComentarioMult}                                     {yybegin(YYINITIAL);}
    .                                                       {/*NO HAY NADA*/}
}
<LINECOMMENT>
{
    \n                                                      {yybegin(YYINITIAL);}
    .                                                       {/*NO HAY NADA*/}
}
///////////////////////////////////////////////////////////////////////////////////
[ \t\r\f\n]                                                 {/*NO HAGO NADA*/}
<YYINITIAL> .                                               {
                                                                TError error = new TError("Caracter: "+yytext(), "Error Lexico, Caracter no reconocido", "Lexico", yyline, yycolumn, false, this.nombreArchivo);
                                                                Estatico.agregarError(error);
                                                            }