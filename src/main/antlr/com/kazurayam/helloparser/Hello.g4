grammar Hello;

@header {
    package com.kazurayam.iniparser;
}

r  : 'hello' ID ;
ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip;