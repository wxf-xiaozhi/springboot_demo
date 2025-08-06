package com.example.demo.antlr.visitor;


import com.example.demo.antlr.CalcBaseVisitor;
import com.example.demo.antlr.CalcLexer;
import com.example.demo.antlr.CalcParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Calculator {
    public static void main(String[] args) {
        String input = "1 + 2 * 3";
        CharStream stream = CharStreams.fromString(input);
        CalcLexer lexer = new CalcLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);
        ParseTree tree = parser.expr(); // 解析表达式

        // 使用Visitor计算表达式结果
        double result = new EvalVisitor().visit(tree);
        System.out.println(input + " = " + result); // 输出: 1 + 2 * 3 = 7.0
    }

    // 自定义Visitor实现计算逻辑
    public static class EvalVisitor extends CalcBaseVisitor<Double> {
        @Override
        public Double visitInt(CalcParser.IntContext ctx) {
            return Double.parseDouble(ctx.INT().getText()); // 返回整数
        }

        @Override
        public Double visitAddSub(CalcParser.AddSubContext ctx) {
            double left = visit(ctx.expr(0));  // 递归计算左子树
            double right = visit(ctx.expr(1)); // 递归计算右子树
            // 根据操作符类型进行计算
            String operator = ctx.getChild(1).getText(); // 获取操作符文本
            if ("+".equals(operator)) {
                return left + right;
            } else { // "-"
                return left - right;
            }

        }

        @Override
        public Double visitMulDiv(CalcParser.MulDivContext ctx) {
            double left = visit(ctx.expr(0));
            double right = visit(ctx.expr(1));
            // 根据操作符类型进行计算
            String operator = ctx.getChild(1).getText(); // 获取操作符文本
            if ("*".equals(operator)) {
                return left * right;
            } else { // "/"
                return left / right;
            }
        }

        @Override
        public Double visitParens(CalcParser.ParensContext ctx) {
            return visit(ctx.expr()); // 直接返回括号内表达式的值
        }
    }
}
