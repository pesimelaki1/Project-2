package project2;
public class ArrayStackTest{
   public static void main(String[] args){
      String postfixExpression = "ab*ca-/de*+";
      testEvaluatePostfix(postfixExpression);

   }
   
   private static void testEvaluatePostfix(String expression) {
      System.out.println("Postfix expression: " + expression + " where a = 2, b = 3, c = 4, d = 5, e = 6");// 
      expression = "23*42-/56*+";
      System.out.println(expression + " = " + evaluatePostfix(expression));
   }

   public static int evaluatePostfix(String postfix)
   {
      if(postfix == null)
      {
         System.out.println("Error: Postfix expression is null.");
         return -1;
      }

      // Evaluates a postfix expression.
      //valueStack = a new empty stack
      StackInterface<Integer> valueStack = new ResizableArrayStack<Integer>();
      char nextCharacter;
      int index = 0;
      char operator = ' ';

      while (index < postfix.length())
      {
         //nextCharacter = next nonblank character of postfix
         nextCharacter = postfix.charAt(index);
  
         if (nextCharacter == '%' || nextCharacter == '/' || nextCharacter == '*' || nextCharacter == '+' || nextCharacter == '-'){
            operator = nextCharacter;
         }
         if (Character.isSpaceChar(nextCharacter))
         {
            index++;
            continue;
         }
         if(Character.isDigit(nextCharacter)){
            valueStack.push(Character.getNumericValue(nextCharacter));
            continue;
         }
         switch (nextCharacter){
            case '+' : case '-' : case '*' : case '/' : case '^' :
               int operandTwo = valueStack.pop();
               int operandOne = valueStack.pop();
               int result = 0;
               switch(operator) {
                  case '+' : 
                     result = operandOne + operandTwo;
                     break;
                  case '-' : 
                     result = operandOne - operandTwo;
                     break;
                  case '*' : 
                     result = operandOne * operandTwo;
                     break;
                  case '/' : 
                     try {
                        System.out.println(operandOne / operandTwo); // throw Exception
                     }
                     catch (ArithmeticException e) {
                        System.out.println("Error: attempting to divide by zero");
                     }
                     break;
                  case '^' : 
                     result = (int)Math.pow(operandOne,operandTwo);
                     break;
               }
               valueStack.push(result);
               break;
            default: 
               break; // Ignore unexpected characters
         }
         index++;
      }
      return valueStack.peek();
   }
}
