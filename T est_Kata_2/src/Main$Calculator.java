import arabian.Converter;

import java.util.Scanner;

public class Main$Calculator
{
    public static void main(String[] args)
    {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        int Len = exp.length();//переменная запоминающая длину нашей строки
        int Plus =exp.lastIndexOf('+');// переменная запоминающая индекс последнего вхождения знака "+"
        int Minus =(exp.lastIndexOf('-'));// переменная запоминающая индекс последнего вхождения знака "-"
        int Multiply =(exp.lastIndexOf('*'));// переменная запоминающая индекс последнего вхождения знака "*"
        int Divide =(exp.lastIndexOf('/'));// переменная запоминающая индекс последнего вхождения знака "/"


        if((Plus>=Len-2 || Minus >= Len-2 || Multiply >= Len-2 || Divide >= Len-2) && 3<Len-1 )//путем математических вычислений придумал такой костыль,
            // который не пропускает выражение, содержащие более 1 операнда,
            // надеюсь, что в Kata меня научат более профессиональному рещению таких задач)
        {
            System.out.println("Некорректное выражение");
            return;
        }
        //Определяем арифметическое действие:
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++)
        {
            if(exp.contains(actions[i]))
            {
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1)
        {
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по найденному арифметическому знаку


        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1]))
        {
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
        if(isRoman)
            {
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }
            else
            {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                if(1>a||a>10||b<1||b>10)
                {
                    System.out.print("Введены некорректные данные ");
                    return;
                }
            }

            //выполняем с числами арифметическое действие
            int result = switch (actions[actionIndex])
            {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            if(isRoman)
            {
                //если числа были римские, возвращаем результат в римском числе
                System.out.println(converter.intToRoman(result));
            }
            else
            {
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        }
        else
        {
            System.out.println("Числа должны быть в одном формате");
        }
    }
}