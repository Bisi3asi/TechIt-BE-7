package com.ll;

import java.io.*;
import java.util.ArrayList;

// todo refactor
class Calc {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int run(String cmd) throws IOException {
        StringBuilder sb = new StringBuilder(cmd);

        int cnt = 0, max = 0, index = 0;
        for (int i = 0; i < sb.length() - 1; i++) {
            if (max < cnt) {
                max = cnt;
                index = i;
            }
            if (sb.charAt(i) == ('('))
                cnt++;
            if (sb.charAt(i) == (')'))
                cnt--;
        }

        String prior;
        int startIndex = 0, endIndex = 0;
        if (index == 0) prior = sb.toString();
        else {
            startIndex = index;
            endIndex = sb.indexOf(")", index + 1);
            prior = sb.substring(startIndex, endIndex);
        }
        String[] arr = prior.split("\\s+"); r

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].matches("\\*")) {
                arr[i + 1] = Integer.toString(Integer.parseInt(arr[i - 1]) * Integer.parseInt(arr[i + 1]));
                arr[i - 1] = "";
                arr[i] = "";
            }
            if (arr[i].matches("\\/")) {
                arr[i + 1] = Integer.toString(Integer.parseInt(arr[i - 1]) / Integer.parseInt(arr[i + 1]));
                arr[i - 1] = "";
                arr[i] = "";
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].matches("[*]")) {
                arr[i + 1] = Integer.toString(Integer.parseInt(arr[i - 1]) * Integer.parseInt(arr[i + 1]));
                arr[i - 1] = "";
                arr[i] = "";
            }
            if (arr[i].matches("/")) {
                arr[i + 1] = Integer.toString(Integer.parseInt(arr[i - 1]) * Integer.parseInt(arr[i + 1]));
                arr[i - 1] = "";
                arr[i] = "";
            }
        }

        ArrayList<Integer> digList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].matches("[-+]?\\d+")) { // 음수 혹은 양수일 때
                digList.add(Integer.parseInt(arr[i]));
            }
        }

        int target = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].matches("\\+")) {
                target++;
            }
            if (arr[i].matches("-")) {
                digList.set(target, -digList.get(target));
                target++;
            }
        }

        int midCalc = 0;
        for (int i : digList) {
            midCalc += i;
        }

        if (index == 0) return midCalc;
        else {
            sb.delete(startIndex - 1, endIndex + 1);
            return run(sb.insert(startIndex - 1, midCalc).toString());
        }
    }

    public static void main(String[] args) throws IOException {
        Calc calc = new Calc();
        try {
            calc.bw.write("계산할 값을 입력해주세요 : ");
            calc.bw.flush();
            String cmd = calc.br.readLine();
            calc.bw.write(Integer.toString(run(cmd)));
        } catch (IOException e) {
            System.out.println(e);
        }
        calc.br.close();
        calc.bw.flush();
        calc.bw.close();
    }
}