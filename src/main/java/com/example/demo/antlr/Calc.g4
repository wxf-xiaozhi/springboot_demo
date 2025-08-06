grammar Calc;

// 语法规则（生成AST）
expr   : expr ('*'|'/') expr  # MulDiv
       | expr ('+'|'-') expr  # AddSub
       | INT                  # Int
       | '(' expr ')'         # Parens
       ;

// 词法规则
INT    : [0-9]+;
WS     : [ \t\r\n]+ -> skip; // 忽略空格和换行