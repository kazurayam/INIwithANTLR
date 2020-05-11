/**
 * https://github.com/afucher/yaip/blob/master/parser/IniFileGrammar.g4
 */

grammar INI;

@header {
    package com.kazurayam.iniparser;
}

ini : (LINE_COMMENT | section)* EOF;

section : section_header key_values;

section_header : LBRACK section_header_title RBRACK;

section_header_title : text;

key_values : key_value*;

key_value : key EQUALS value?;

key : text;

value : text;





text :TEXT;
TEXT: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '/' | '\\' | ':' | '*' | '.' | ',' | '@' | ' ' | '(' | ')')+;
        //( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '/'| '\\' | ':')* ;

//TEXT : ( ~('='|'\n') )*;

EQUALS	: '=';

LBRACK	: '['  ;

RBRACK	: ']'  ;

LINE_COMMENT : ';' ~('\n'|'\r')*  ->  channel(HIDDEN);

WS  :   (('\r')? '\n' |  ' ' | '\t')+  -> skip;