package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "#"(.)* ) {/*Ignore*/}

/* Comillas */
( "\"" ) {return new Symbol(sym.Comillas, yychar, yyline, yytext());}


/* Bloque declaracion de variables */
( variables: ) {return new Symbol(sym.Variables, yychar, yyline, yytext());}

/* Declaracion de variable */
( variable ) {return new Symbol(sym.Variable, yychar, yyline, yytext());}

/* Tipos de datos */
( entero ) {return new Symbol(sym.Tentero, yychar, yyline, yytext());}
( decimal ) {return new Symbol(sym.Tdecimal, yychar, yyline, yytext());}
( simbolo ) {return new Symbol(sym.Tsimbolo, yychar, yyline, yytext());}
( texto ) {return new Symbol(sym.Ttexto, yychar, yyline, yytext());}
( logico ) {return new Symbol(sym.TLogico, yychar, yyline, yytext());}
( fechahora ) {return new Symbol(sym.Tfechahora, yychar, yyline, yytext());}
( fecha ) {return new Symbol(sym.Tfecha, yychar, yyline, yytext());}
( hora ) {return new Symbol(sym.Thora, yychar, yyline, yytext());}
( rfc ) {return new Symbol(sym.Trfc, yychar, yyline, yytext());}
( curp ) {return new Symbol(sym.Tcurp, yychar, yyline, yytext());}

/* Bloque codigo principal */
( codigo-principal: ) {return new Symbol(sym.CodigoPrincipal, yychar, yyline, yytext());}

/* Bloque funciones */
( funciones: ) {return new Symbol(sym.Funciones, yychar, yyline, yytext());}

/* Bloque procedimientos */
( procedimientos: ) {return new Symbol(sym.Procedimientos, yychar, yyline, yytext());}

/* Declaracion funcion */
( funcion ) {return new Symbol(sym.Funcion, yychar, yyline, yytext());}

/* devolver */
( devolver ) {return new Symbol(sym.Devolver, yychar, yyline, yytext());}

/* Declaracion procedimiento*/
( procedimiento ) {return new Symbol(sym.Procedimiento, yychar, yyline, yytext());}

/* inicio codigo */
( inicio. ) {return new Symbol(sym.Inicio, yychar, yyline, yytext());}

/* fin codigo */
( fin. ) {return new Symbol(sym.Fin, yychar, yyline, yytext());}

/* Si */
( si ) {return new Symbol(sym.Si, yychar, yyline, yytext());}

/* entonces */
( entonces ) {return new Symbol(sym.Entonces, yychar, yyline, yytext());}

/* Sino */
( sino ) {return new Symbol(sym.Sino, yychar, yyline, yytext());}

/* Para */
( para ) {return new Symbol(sym.Para, yychar, yyline, yytext());}

/* Mientras */
( mientras ) {return new Symbol(sym.Mientras, yychar, yyline, yytext());}

/* Romper */
( romper ) {return new Symbol(sym.Romper, yychar, yyline, yytext());}

/* Imprimir */
( imprimir ) {return new Symbol(sym.Imprimir, yychar, yyline, yytext());}

/* Hasta */
( hasta ) {return new Symbol(sym.Hasta, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}

/* Hacer */
( hacer ) {return new Symbol(sym.Hacer, yychar, yyline, yytext());}

/* Operador Suma */
( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}

/* Operador Resta */
( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}

/* Operador Multiplicacion */
( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}

/* Operador Division */
( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}

/* Operadores logicos */
("&&") { return new Symbol(sym.Op_logico_and, yychar, yyline, yytext()); }
("||") { return new Symbol(sym.Op_logico_or, yychar, yyline, yytext()); }
("!") { return new Symbol(sym.Op_logico_not, yychar, yyline, yytext()); }
("&") { return new Symbol(sym.Op_bitwise_and, yychar, yyline, yytext()); }
("|") { return new Symbol(sym.Op_bitwise_or, yychar, yyline, yytext()); }

/*Operadores Relacionales */
( ">" ) {return new Symbol(sym.MayorQue, yychar, yyline, yytext());}
( "<" ) {return new Symbol(sym.MenorQue, yychar, yyline, yytext());}
( "==" ) {return new Symbol(sym.Igualdad, yychar, yyline, yytext());}
( "!=" ) {return new Symbol(sym.Diferente, yychar, yyline, yytext());}
( ">=" ) {return new Symbol(sym.MayorIgual, yychar, yyline, yytext());}
( "<=" ) {return new Symbol(sym.MenorIgual, yychar, yyline, yytext());}

/* Operadores Atribucion */
("+=") { return new Symbol(sym.Op_atribucion_suma, yychar, yyline, yytext()); }
("-=") { return new Symbol(sym.Op_atribucion_resta, yychar, yyline, yytext()); }
("*=") { return new Symbol(sym.Op_atribucion_multiplicacion, yychar, yyline, yytext()); }
("/=") { return new Symbol(sym.Op_atribucion_division, yychar, yyline, yytext()); }
("%=") { return new Symbol(sym.Op_atribucion_modulo, yychar, yyline, yytext()); }

/* Parentesis de apertura */
( "(" ) {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {return new Symbol(sym.Llave_a, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {return new Symbol(sym.Llave_c, yychar, yyline, yytext());}

/* Corchete de apertura */
( "[" ) {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}

/* Corchete de cierre */
( "]" ) {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}

/* Cadena */
( "\""(.)*"\"" ) {return new Symbol(sym.Cadena, yychar, yyline, yytext());}

/* Identificador */
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
