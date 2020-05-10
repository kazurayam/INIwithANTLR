grammar INI;

@header {
    package com.kazurayam.iniparser;
}

file: (ini)+
    ;

ini : section (option)*
    ;

section : '[' STRING ']'
        ;

option : STRING '=' STRING
       ;

COMMENT : ';'  ~[\r\n]* -> skip ;
STRING  : [a-zA-Z0-9]+ ;
WS      : [ \t\n\r]+ -> skip ;
