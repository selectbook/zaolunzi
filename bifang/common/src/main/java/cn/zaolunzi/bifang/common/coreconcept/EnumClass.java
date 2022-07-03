package cn.zaolunzi.bifang.common.coreconcept;

/**
 * @Author: SelectBook
 * @Date: 2022/7/3 23:31
 */

enum Shrubbery {GROUND, CRAWLING, HAVING}

public class EnumClass {
    public static void main(String args[]) {
        for (Shrubbery s: Shrubbery.values()) {
            System.out.println(s + " ordinal: " + s.ordinal());
            System.out.print(s.compareTo(Shrubbery.CRAWLING) + " ");
            System.out.print(s.equals(Shrubbery.CRAWLING) + " ");
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("********************");
        }
    
        for(String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrub);
        }
    }
}
