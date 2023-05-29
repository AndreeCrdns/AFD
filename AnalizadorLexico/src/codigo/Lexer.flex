package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "#"(.)* ) {/*Ignore*/}

/* Salto de linea */
( "\n" ) {return Linea;}

/* Comillas */
( "\"" ) {lexeme=yytext(); return Comillas;}

/* Bloque declaracion de variables */
( "variables:" ) {lexeme=yytext(); return Variables;}

/* Declaracion de variable */
( "variable" ) {lexeme=yytext(); return Variable;}

/* Tipos de datos */
( entero ) {lexeme = yytext(); return Tentero;}
( decimal ) {lexeme = yytext(); return Tdecimal;}
( simbolo ) {lexeme = yytext(); return Tsimbolo;}
( texto ) {lexeme = yytext(); return Ttexto;}
( logico ) {lexeme = yytext(); return TLogico;}
( fechahora ) {lexeme = yytext(); return Tfechahora;}
( fecha ) {lexeme = yytext(); return Tfecha;}
( hora ) {lexeme = yytext(); return Thora;}
( rfc ) {lexeme = yytext(); return Trfc;}
( curp ) {lexeme = yytext(); return Tcurp;}

/* Bloque codigo principal */
( codigo-principal: ) {lexeme=yytext(); return CodigoPrincipal;}

/* Bloque funciones */
( funciones: ) {lexeme=yytext(); return Funciones;}

/* Bloque procedimientos */
( procedimientos: ) {lexeme=yytext(); return Procedimientos;}

/* Declaracion de funcion */
( funcion ) {lexeme=yytext(); return Funcion;}

/* devolver */
( devolver ) {lexeme=yytext(); return Devolver;}

/* Declaracion de procedimiento */
( procedimiento ) {lexeme=yytext(); return Procedimiento;}

/* inicio codigo */
( inicio. ) {lexeme=yytext(); return Inicio;}

/* fin codigo */
( fin. ) {lexeme=yytext(); return Fin;}

/* Si */
( si ) {lexeme=yytext(); return Si;}

/* Entonces */
( entonces ) {lexeme=yytext(); return Entonces;}

/* Sino */
( sino ) {lexeme=yytext(); return Sino;}

/* Para */
( para ) {lexeme=yytext(); return Para;}

/* Mientras */
( mientras ) {lexeme=yytext(); return Mientras;}

/* Romper */
( romper ) {lexeme=yytext(); return Romper;}

/* Imprimir */
( imprimir ) {lexeme=yytext(); return Imprimir;}

/* Operador Igual */
( "=" ) {lexeme=yytext(); return Igual;}

/* Hacer*/
( hacer ) {lexeme=yytext(); return Hacer;}

/* Operador Suma */
( "+" ) {lexeme=yytext(); return Suma;}

/* Operador Resta */
( "-" ) {lexeme=yytext(); return Resta;}

/* Hasta */
( hasta ) {lexeme=yytext(); return Hasta;}

/* Operador Multiplicacion */
( "*" ) {lexeme=yytext(); return Multiplicacion;}

/* Operador Division */
( "/" ) {lexeme=yytext(); return Division;}

/* Operadores logicos */
("&&") { lexeme = yytext(); return Op_logico_and; }
("||") { lexeme = yytext(); return Op_logico_or; }
("!")  { lexeme = yytext(); return Op_logico_not; }
("&")  { lexeme = yytext(); return Op_bitwise_and; }
("|")  { lexeme = yytext(); return Op_bitwise_or; }

/*Operadores Relacionales */
( ">" ) { lexeme = yytext(); return MayorQue; }
( "<" ) { lexeme = yytext(); return MenorQue; }
( "==" ) { lexeme = yytext(); return Igualdad; }
( "!=" ) { lexeme = yytext(); return Diferente; }
( ">=" ) { lexeme = yytext(); return MayorIgual; }
( "<=" ) { lexeme = yytext(); return MenorIgual; }

/* Operadores Atribucion */
("+=") { lexeme = yytext(); return Op_atribucion_suma; }
("-=") { lexeme = yytext(); return Op_atribucion_resta; }
("*=")  { lexeme = yytext(); return Op_atribucion_multiplicacion; }
("/=") { lexeme = yytext(); return Op_atribucion_division; }
("%=") { lexeme = yytext(); return Op_atribucion_modulo; }

/* Parentesis de apertura */
( "(" ) {lexeme=yytext(); return Parentesis_a;}

/* Parentesis de cierre */
( ")" ) {lexeme=yytext(); return Parentesis_c;}

/* Llave de apertura */
( "{" ) {lexeme=yytext(); return Llave_a;}

/* Llave de cierre */
( "}" ) {lexeme=yytext(); return Llave_c;}

/* Corchete de apertura */
( "[" ) {lexeme = yytext(); return Corchete_a;}

/* Corchete de cierre */
( "]" ) {lexeme = yytext(); return Corchete_c;}

/* Punto y coma */
( ";" ) {lexeme=yytext(); return P_coma;}

/* Cadena */
( "\""(.)*"\"" ) {lexeme=yytext(); return Cadena;}

/* Identificador */
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}

/* Numero */
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}

/* Error de analisis */
 . {return ERROR;}
