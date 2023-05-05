package sbu.cs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindMultiples
{
    public long getSum(int n) {
        long sum = 0;
        Set<Long> ans=new HashSet<>();
        Thread[] threads=new Thread[3];
        for (int i=0;i<3;i++){
            threads[i]=new Thread(new findAnswer(n,ans,i*2+3));
            threads[i].start();
        }
        try {
            for (int i=0;i<3;i++){
                threads[i].join();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for (long i:ans){
            sum+=i;
        }
        return sum;
    }
    private class findAnswer implements Runnable{
        private int n;
        private Set<Long>ans=new HashSet<>();
        private int x;
        public findAnswer(int n, Set<Long> ans, int x) {
            this.n = n;
            this.ans = ans;
            this.x = x;
        }
        @Override
        public void run() {
            for(long i=1;i<=n;i++){
                if(i%x==0){
                    ans.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        FindMultiples A=new FindMultiples();
        System.out.println(A.getSum(n));
    }
}
