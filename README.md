# Lexical-Parser
Lexical analysis is the first stage a compiler undergoes in order for a compiler to convert source code into a format that can be executed by a machine. A lexical analyser will convert any source code into its tokenized form. 

In this project...

* The token for integers is INT_CONST
* Identifiers (procedures names, variable names) are alphanumeric; however, they must begin with
an alphabetical character. The token for identifiers is IDENT

Some lexumes (individual tokens) are dependent on what special symbols enginerrs want to support in their compiler. For this project in particular, the special tokens are as follows. 

| Symbol | Token |
| ------------- | ------------- |
| if | IF |
| for | FOR |
| while | WHILE |
| procedure | PROC |
| returb | RETURN |
| int | INT |
| else | ELSE |
| do | DO |
| break | BREAK |
| end | END |
| = | ASSIG |
| + | ADD_OP |
| - | SUB_OP |
| * | MUL_OP |
| / | DIV/OP |
| % | MOD_OP |
| > | GT |
| < | LT |
| >= | GE |
| <= | LE |
| ) | INC |
| ( | LP |
| } | RP |
| { | LB |
|  | RB |
| \| | OR |
| & | AND |
| == | EE |
| ! | NEG |
| , | COMMA |
| ; | SEMI |

> [!CAUTION]
> I do not support people plagiarizing my code. I do not take responsibility for the unlawful actions of others.