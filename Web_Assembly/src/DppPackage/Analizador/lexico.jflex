/*CODIGO DE USUARIO*/
package DppPackage.Analizador;
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

    private String quitarDeTexto(String quita, String cadena)
    {
      cadena = cadena.replace(quita, "");
      return cadena;
    }
%}

%public
%class DPPLex
%cupsym Simbolos
%cup
%char
%column
%full
%line
%unicode

// REGLAS
entero = [0-9]+
decimal = [0-9]+\.[0-9]+
identificador = ([a-zA-Z]|_)([a-zA-Z]|[0-9]|_)*
caracter = \'.\'

// CADENAS
AnyCharacterButDoubleQuoteOrBackSlash   = ([^\\\"\n])
StringLiteral = ([\"]({AnyCharacterButDoubleQuoteOrBackSlash})*[\"])

//CARACTERES
AnyCharacterButApostropheOrBackSlash   = ([^\\'])
CharLiteral = ([\']({AnyCharacterButApostropheOrBackSlash})[\'])

inicioComentarioMult = "/*"
finComentarioMult ="*/"
inicioComentarioLinea = "//"

// -----------> ESTADOS
%state MULTICOMMENT
%state LINECOMMENT

%%
// REGLAS LEXICAS
//--------------------> Simbolos O CARACTERES DEL LENGUAJE
<YYINITIAL> ";"                                           {return new Symbol(DppPackage.Analizador.Simbolos.ptComa, yycolumn, yyline, yytext());}
<YYINITIAL> "="                                           {return new Symbol(DppPackage.Analizador.Simbolos.asigna, yycolumn, yyline, yytext());}
<YYINITIAL> "("                                           {return new Symbol(DppPackage.Analizador.Simbolos.oParent, yycolumn, yyline, yytext());}
<YYINITIAL> ")"                                           {return new Symbol(DppPackage.Analizador.Simbolos.cParent, yycolumn, yyline, yytext());}
<YYINITIAL> ","                                           {return new Symbol(DppPackage.Analizador.Simbolos.coma, yycolumn, yyline, yytext());}
<YYINITIAL> "{"                                           {return new Symbol(DppPackage.Analizador.Simbolos.oKey, yycolumn, yyline, yytext());}
<YYINITIAL> "}"                                           {return new Symbol(DppPackage.Analizador.Simbolos.cKey, yycolumn, yyline, yytext());}
<YYINITIAL> "."                                           {return new Symbol(DppPackage.Analizador.Simbolos.punto, yycolumn, yyline, yytext());}
// OPERADORES
<YYINITIAL> "+"                                           {return new Symbol(DppPackage.Analizador.Simbolos.mas, yycolumn, yyline, yytext());}
<YYINITIAL> "-"                                           {return new Symbol(DppPackage.Analizador.Simbolos.menos, yycolumn, yyline, yytext());}
<YYINITIAL> "*"                                           {return new Symbol(DppPackage.Analizador.Simbolos.mult, yycolumn, yyline, yytext());}
<YYINITIAL> "/"                                           {return new Symbol(DppPackage.Analizador.Simbolos.div, yycolumn, yyline, yytext());}
<YYINITIAL> "^"                                           {return new Symbol(DppPackage.Analizador.Simbolos.pot, yycolumn, yyline, yytext());}
<YYINITIAL> "++"                                          {return new Symbol(DppPackage.Analizador.Simbolos.inc, yycolumn, yyline, yytext());}
<YYINITIAL> "--"                                          {return new Symbol(DppPackage.Analizador.Simbolos.dec, yycolumn, yyline, yytext());}
<YYINITIAL> "=="                                          {return new Symbol(DppPackage.Analizador.Simbolos.igual, yycolumn, yyline, yytext());}
<YYINITIAL> "<>"                                          {return new Symbol(DppPackage.Analizador.Simbolos.diff, yycolumn, yyline, yytext());}
<YYINITIAL> "<"                                           {return new Symbol(DppPackage.Analizador.Simbolos.menor, yycolumn, yyline, yytext());}
<YYINITIAL> ">"                                           {return new Symbol(DppPackage.Analizador.Simbolos.mayor, yycolumn, yyline, yytext());}
<YYINITIAL> "<="                                          {return new Symbol(DppPackage.Analizador.Simbolos.menorigual, yycolumn, yyline, yytext());}
<YYINITIAL> ">="                                          {return new Symbol(DppPackage.Analizador.Simbolos.mayorigual, yycolumn, yyline, yytext());}
<YYINITIAL> "&&"                                          {return new Symbol(DppPackage.Analizador.Simbolos.and, yycolumn, yyline, yytext());}
<YYINITIAL> "||"                                          {return new Symbol(DppPackage.Analizador.Simbolos.or, yycolumn, yyline, yytext());}
<YYINITIAL> "!"                                           {return new Symbol(DppPackage.Analizador.Simbolos.not, yycolumn, yyline, yytext());}
// PALABRAS RESERVADAS
<YYINITIAL> "entero"                                      {return new Symbol(DppPackage.Analizador.Simbolos.entero, yycolumn, yyline, yytext());}
<YYINITIAL> "decimal"                                     {return new Symbol(DppPackage.Analizador.Simbolos.decimal, yycolumn, yyline, yytext());}
<YYINITIAL> "cadena"                                      {return new Symbol(DppPackage.Analizador.Simbolos.cadena, yycolumn, yyline, yytext());}
<YYINITIAL> "caracter"                                    {return new Symbol(DppPackage.Analizador.Simbolos.caracter, yycolumn, yyline, yytext());}
<YYINITIAL> "booleano"                                    {return new Symbol(DppPackage.Analizador.Simbolos.booleano, yycolumn, yyline, yytext());}
<YYINITIAL> "verdadero"                                   {return new Symbol(DppPackage.Analizador.Simbolos.verdadero, yycolumn, yyline, yytext());}
<YYINITIAL> "falso"                                       {return new Symbol(DppPackage.Analizador.Simbolos.falso, yycolumn, yyline, yytext());}
<YYINITIAL> "importar"                                    {return new Symbol(DppPackage.Analizador.Simbolos.importar, yycolumn, yyline, yytext());}
<YYINITIAL> "vacio"                                       {return new Symbol(DppPackage.Analizador.Simbolos.vacio, yycolumn, yyline, yytext());}
<YYINITIAL> "principal"                                   {return new Symbol(DppPackage.Analizador.Simbolos.principal, yycolumn, yyline, yytext());}
<YYINITIAL> "retornar"                                    {return new Symbol(DppPackage.Analizador.Simbolos.retornar, yycolumn, yyline, yytext());}
<YYINITIAL> "si"                                          {return new Symbol(DppPackage.Analizador.Simbolos.si, yycolumn, yyline, yytext());}
<YYINITIAL> "sino"                                        {return new Symbol(DppPackage.Analizador.Simbolos.sino, yycolumn, yyline, yytext());}
<YYINITIAL> "mientras"                                    {return new Symbol(DppPackage.Analizador.Simbolos.mientras, yycolumn, yyline, yytext());}
<YYINITIAL> "para"                                        {return new Symbol(DppPackage.Analizador.Simbolos.para, yycolumn, yyline, yytext());}
<YYINITIAL> "detener"                                     {return new Symbol(DppPackage.Analizador.Simbolos.detener, yycolumn, yyline, yytext());}
<YYINITIAL> "continuar"                                   {return new Symbol(DppPackage.Analizador.Simbolos.continuar, yycolumn, yyline, yytext());}
<YYINITIAL> "imprimir"                                    {return new Symbol(DppPackage.Analizador.Simbolos.imprimir, yycolumn, yyline, yytext());}
<YYINITIAL> "estructura"                                  {return new Symbol(DppPackage.Analizador.Simbolos.estructura, yycolumn, yyline, yytext());}
<YYINITIAL> "punto"                                       {return new Symbol(DppPackage.Analizador.Simbolos.point, yycolumn, yyline, yytext());}
<YYINITIAL> "cuadrado"                                    {return new Symbol(DppPackage.Analizador.Simbolos.cuadrado, yycolumn, yyline, yytext());}
<YYINITIAL> "ovalo"                                       {return new Symbol(DppPackage.Analizador.Simbolos.ovalo, yycolumn, yyline, yytext());}
<YYINITIAL> "linea"                                       {return new Symbol(DppPackage.Analizador.Simbolos.linea, yycolumn, yyline, yytext());}
<YYINITIAL> "nulo"                                        {return new Symbol(DppPackage.Analizador.Simbolos.nulo, yycolumn, yyline, yytext());}
 
// EXPRESIONES REGULARES
<YYINITIAL> {entero}                                      {return new Symbol(DppPackage.Analizador.Simbolos.numEntero, yycolumn, yyline, yytext());}
<YYINITIAL> {decimal}                                     {return new Symbol(DppPackage.Analizador.Simbolos.numDecimal, yycolumn, yyline, yytext());}
//<YYINITIAL> {caracter}                                    {return new Symbol(DppPackage.Analizador.Simbolos.dataChar, yycolumn, yyline, quitarDeTexto("\'",yytext()));}
<YYINITIAL> {identificador}                               {return new Symbol(DppPackage.Analizador.Simbolos.identificador, yycolumn, yyline, quitarDeTexto("\'",yytext()));}
<YYINITIAL> {CharLiteral}                                 {return new Symbol(DppPackage.Analizador.Simbolos.dataChar, yycolumn, yyline, quitarDeTexto("\"",yytext()));}
<YYINITIAL> {StringLiteral}                               {return new Symbol(DppPackage.Analizador.Simbolos.dataString, yycolumn, yyline, yytext());}
<YYINITIAL> {inicioComentarioMult}                        {yybegin(MULTICOMMENT);}
<YYINITIAL> {inicioComentarioLinea}                       {yybegin(LINECOMMENT);}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
<MULTICOMMENT>
{
    {finComentarioMult}                                   {yybegin(YYINITIAL);}
    .                                                     {/*NO HAGO NADA*/}
}
<LINECOMMENT>
{
    \n                                                    {yybegin(YYINITIAL);}
    .                                                     {/*NO HAGO NADA*/}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
[ \t\r\f\n]                                               {/*NO HAGO NADA*/}
<YYINITIAL> .                                             {
                                                                TError error = new TError("Caracter: "+yytext(), "Error Lexico, Caracter no reconocido", "Lexico", yyline, yycolumn, false, this.nombreArchivo);
                                                                Estatico.agregarError(error);
                                                          }
