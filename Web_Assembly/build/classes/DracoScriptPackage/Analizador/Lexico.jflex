/*                 CODIGO DE USUARIO                       */
package DracoScriptPackage.Analizador;
import java_cup.runtime.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
/*                 OPCIONES Y DECLARACIONES                */
%%
%{
    String nombreArchivo;
    ///CODIVFO DE USUARIO
    public void setNombreArchivo(String nombreArchivo)
    {
      this.nombreArchivo = nombreArchivo;
    }

    private String quitarDeTexto(String quita, String cadena)
    {
      cadena = cadena.replace(quita, "");
      return cadena;
    }
%}

%public
%class DSLex
%cupsym Simbolos
%cup
%char
%column
%full
%line
%ignorecase
%unicode

entero = [0-9]+
decimal = [0-9]+\.[0-9]+
identificador = [a-zA-Z]([a-zA-Z]|[0-9]|_)*
caracter = \'.\'

// CADENAS
AnyCharacterButDoubleQuoteOrBackSlash   = ([^\\\"\n])
StringLiteral = ([\"]({AnyCharacterButDoubleQuoteOrBackSlash})*[\"])

//CARACTERES
AnyCharacterButApostropheOrBackSlash   = ([^\\'])
CharLiteral = ([\']({AnyCharacterButApostropheOrBackSlash})[\'])

inicioComentarioMult = "$*"
finComentarioMult ="*$"
inicioComentarioLinea = "$$"

// -----------> ESTADOS
%state MULTICOMMENT
%state LINECOMMENT

%%
/*                 REGLAS LEXICAS                          */
//---------------------------SIMBOLOS O CARACTERES DEL LENGUAJE
<YYINITIAL> ";"                                   {return new Symbol(Simbolos.ptComa, yycolumn, yyline, yytext());}
<YYINITIAL> ":=:"                                 {return new Symbol(Simbolos.asigna, yycolumn, yyline, yytext());}
<YYINITIAL> "("                                   {return new Symbol(Simbolos.oParent, yycolumn, yyline, yytext());}
<YYINITIAL> ")"                                   {return new Symbol(Simbolos.cParent, yycolumn, yyline, yytext());}
<YYINITIAL> ","                                   {return new Symbol(Simbolos.coma, yycolumn, yyline, yytext());}

// OPERADORES
<YYINITIAL> "+"                                   {return new Symbol(Simbolos.mas, yycolumn, yyline, yytext());}
<YYINITIAL> "-"                                   {return new Symbol(Simbolos.menos, yycolumn, yyline, yytext());}
<YYINITIAL> "*"                                   {return new Symbol(Simbolos.mult, yycolumn, yyline, yytext());}
<YYINITIAL> "%"                                   {return new Symbol(Simbolos.mod, yycolumn, yyline, yytext());}
<YYINITIAL> "/"                                   {return new Symbol(Simbolos.div, yycolumn, yyline, yytext());}
<YYINITIAL> "^"                                   {return new Symbol(Simbolos.pot, yycolumn, yyline, yytext());}
<YYINITIAL> "++"                                  {return new Symbol(Simbolos.inc, yycolumn, yyline, yytext());}
<YYINITIAL> "--"                                  {return new Symbol(Simbolos.dec, yycolumn, yyline, yytext());}
<YYINITIAL> "=="                                  {return new Symbol(Simbolos.igual, yycolumn, yyline, yytext());}
<YYINITIAL> "!="                                  {return new Symbol(Simbolos.dif, yycolumn, yyline, yytext());}
<YYINITIAL> "<"                                   {return new Symbol(Simbolos.menor, yycolumn, yyline, yytext());}
<YYINITIAL> ">"                                   {return new Symbol(Simbolos.mayor, yycolumn, yyline, yytext());}
<YYINITIAL> "<="                                  {return new Symbol(Simbolos.menorigual, yycolumn, yyline, yytext());}
<YYINITIAL> ">="                                  {return new Symbol(Simbolos.mayorigual, yycolumn, yyline, yytext());}
<YYINITIAL> "&&"                                  {return new Symbol(Simbolos.and, yycolumn, yyline, yytext());}
<YYINITIAL> "||"                                  {return new Symbol(Simbolos.or, yycolumn, yyline, yytext());}
<YYINITIAL> "!"                                   {return new Symbol(Simbolos.not, yycolumn, yyline, yytext());}

//------------------------------- PALABRAS RESERVADAS ---------------------------------------------------------
<YYINITIAL> "var"                                 {return new Symbol(Simbolos.var, yycolumn, yyline, yytext());}
<YYINITIAL> "true"                                {return new Symbol(Simbolos.verdad, yycolumn, yyline, yytext());}
<YYINITIAL> "false"                               {return new Symbol(Simbolos.falso, yycolumn, yyline, yytext());}
<YYINITIAL> "print"                               {return new Symbol(Simbolos.print, yycolumn, yyline, yytext());}

//---------------------------EXPRESIONES REGULARES
<YYINITIAL> {entero}                              {return new Symbol(Simbolos.entero, yycolumn, yyline, yytext());}
<YYINITIAL> {decimal}                             {return new Symbol(Simbolos.decimal, yycolumn, yyline, yytext());}
<YYINITIAL> {caracter}                            {return new Symbol(Simbolos.caracter, yycolumn, yyline, yytext());}
<YYINITIAL> {identificador}                       {return new Symbol(Simbolos.identificador, yycolumn, yyline, yytext());}
<YYINITIAL> {CharLiteral}                         {return new Symbol(Simbolos.caracter, yycolumn, yyline, quitarDeTexto("\'",yytext()));}
<YYINITIAL> {StringLiteral}                       {return new Symbol(Simbolos.cadena, yycolumn, yyline, quitarDeTexto("\"",yytext()));}
<YYINITIAL> {inicioComentarioMult}                {yybegin(MULTICOMMENT);}
<YYINITIAL> {inicioComentarioLinea}               {yybegin(LINECOMMENT);}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
<MULTICOMMENT>
{
  {finComentarioMult}                             {yybegin(YYINITIAL);}
  .                                               {/*NO HACE NADA*/}
}
<LINECOMMENT>
{
  \n                                              {yybegin(YYINITIAL);}
  .                                               {/*NO HACE NADA*/}
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
[ \t\r\f\n]                                       {/*NO HAGO NADA*/}
<YYINITIAL> .
                                                  {
                                                      /*NO HAY NADA*/
                                                      TError error = new TError("Caracter: "+yytext(), "Erro Lexico, Caracter no reconocido", "Lexico", yyline, yycolumn, false, this.nombreArchivo);
                                                      Estatico.agregarError(error);
                                                  }
