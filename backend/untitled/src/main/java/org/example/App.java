package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Solutions solutions = new Solutions();

        System.out.println(solutions.isEgoistic("25"));
        System.out.println(solutions.isEgoistic("4544535353"));
        System.out.println(solutions.isEgoistic("4443434"));
        System.out.println(solutions.isEgoistic("323232323225"));
    }
}
