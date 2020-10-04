# Programming-Language-Parser-CS350
This program acts as a parser created for a homework assignment for my Organization of Programming Languages class. This will find whether an input is valid using the rules of a pre-determined context-free-grammar.

Context-Free-Grammar:
set -> { list }
list -> head tail | ε
head -> number | set
tail -> , head tail | ε

*Number is assumed to be already scanned, and represented in this program as an input token n*