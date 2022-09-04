package com.company;

import java.util.*;

public class Main {
    public static List<String> romanianNums = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String[] input = text.split(" ");
        String operator = input[1];
        int[] nums = parse(text.split(" "));
        boolean isRomanian = Character.isAlphabetic(text.charAt(0));
        if (isRomanian) {
            System.out.println(toRomanian(calculate(nums[0], nums[1], operator)));
        } else {
            System.out.println(calculate(nums[0], nums[1], operator));
        }

    }

    public static int[] parse(String[] nums) throws Exception {
        if (nums.length != 3) {
            throw new Exception("Неверный формат входящей строки");
        }
        try {
            return parseArabian(nums);
        }
        catch (Exception e) {
            return parseRomanian(nums);
        }
    }

    public static int calculate(int first, int second, String operator) throws Exception {
        switch (operator) {
            case  ("+"):
                return (first + second);
            case  ("-"):
                return (first - second);
            case  ("*"):
                return (first*second);
            case  ("/"):
                return (first/second);
        }
        throw new Exception("Неверный формат входящей строки");
    }

    public static int[] parseArabian(String[] nums) throws Exception {
        int first = Integer.parseInt(nums[0]);
        int second = Integer.parseInt(nums[2]);
        if (Math.abs(first) > 10 || Math.abs(second) > 10) {
            throw new RuntimeException("Неверный формат входящей строки");
        }
        return new int[]{first, second};
    }

    public static int[] parseRomanian(String[] nums) throws Exception {
        return new int[]{fromRomanian(nums[0]), fromRomanian(nums[2])};
    }

    public static String toRomanian(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRomanian(number-l);
    }

    public static int fromRomanian(String num) throws Exception {
        int index = romanianNums.indexOf(num);
        if (index == -1) {
            throw new Exception("Неверный формат входящей строки");
        }
        return (index + 1);
    }

}
