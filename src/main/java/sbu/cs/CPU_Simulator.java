package sbu.cs;

import java.util.*;
public class CPU_Simulator
{
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
            this.processingTime=processingTime;
            this.ID=ID;
        }
        @Override
        public void run() {
            try{
                Thread.sleep(processingTime);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();
        tasks=sort(tasks);
        for(Task i:tasks){
            Thread thread=new Thread(i);
            thread.start();
            try{
                thread.join();
                executedTasks.add(i.ID);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return executedTasks;
    }

    public ArrayList sort(ArrayList<Task> tasks){
        for(int i=0;i<tasks.size();i++){
            for(int j=i+1;j<tasks.size();j++){
                if(tasks.get(i).processingTime>tasks.get(j).processingTime){
                    Collections.swap(tasks,i,j);
                }
            }
        }
        return tasks;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task>tasks = new ArrayList<>();
        while(true){
            try{
                Task newTask = new Task(in.next(),in.nextInt());
                tasks.add(newTask);
            }
            catch (Exception e){
                break;
            }
        }
        CPU_Simulator cpu=new CPU_Simulator();
        cpu.startSimulation(tasks);
    }
}
