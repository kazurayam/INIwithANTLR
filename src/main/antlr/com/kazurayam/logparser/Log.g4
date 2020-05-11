/**
 * Our log line is formated as:
 *     <datetime> <level> <message>
 * E.g.,
 *     2018-May-05 14:20:18 INFO some error occurred
 */
grammar Log;

@header {
    package com.kazurayam.logparser;
}

log
    : entry+
    ;

entry
    : timestamp ' ' level ' ' message CRLF
    ;

timestamp
    : DATE ' ' TIME
    ;

level
    : 'ERROR' | 'INFO' | 'DEBUG'
    ;

message
    : (TEXT | ' ')+
    ;
DATE
    : TWODIGIT TWODIGIT '-' LETTER LETTER LETTER '-' TWODIGIT
    ;

TIME
    : TWODIGIT ':' TWODIGIT ':' TWODIGIT
    ;

TEXT
    : LETTER+
    ;

CRLF
    : '\r'? '\n' | '\r'
    ;

fragment
DIGIT
    : [0-9];

fragment
TWODIGIT
    : DIGIT DIGIT;

fragment
LETTER
    : [A-Za-z];
