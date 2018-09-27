/*                 CODIGO DE USUARIO                       */
package DracoScriptPackage.Analizador;
import java_cup.runtime.*;
import java.util.ArrayList;
import ErrorManager.TError;
/*                 OPCIONES Y DECLARACIONES                */
%%
%{
    ///
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


//---------------------------EXPRESIONES REGULARES
<YYINITIAL> {entero}                              {return new Symbol(Simbolos.entero, yycolumn, yyline, yytext());}
<YYINITIAL> {decimal}                             {return new Symbol(Simbolos.decimal, yycolumn, yyline, yytext());}
<YYINITIAL> {caracter}                            {return new Symbol(Simbolos.caracter, yycolumn, yyline, yytext());}
<YYINITIAL> {identificador}                       {return new Symbol(Simbolos.identificador, yycolumn, yyline, yytext());}
<YYINITIAL> {CharLiteral}                         {return new Symbol(Simbolos.caracter, yycolumn, yyline, yytext());}
<YYINITIAL> {StringLiteral}                       {return new Symbol(Simbolos.cadena, yycolumn, yyline, yytext());}
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
                                                      System.err.println("Error lexico: "+yytext());
                                                  }
