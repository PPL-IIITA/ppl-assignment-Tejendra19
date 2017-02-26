package q1;
import java.util.*;
import java.io.*;

public class main {
    public static void main(String ... args) throws Exception {
        int a;
        ArrayList<Girl> alg=new ArrayList<Girl>();
        for(a=0;a<30;a++) {
            StringBuilder sb = new StringBuilder();
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            Random rand = new Random();
            int intelligence_level=rand.nextInt(10) + 1;
            int maintenance_cost=rand.nextInt(4000) + 1000;
            int attractiveness=rand.nextInt(10) + 1;
            int type=rand.nextInt(3) + 1;
            Girl g=new Girl(output,intelligence_level,maintenance_cost,attractiveness,type);
            alg.add(g);
        }
        PrintWriter pw = new PrintWriter(new File("girl.csv"));
        StringBuilder sb1 = new StringBuilder();
        for(Girl x:alg) {
            sb1.append(x.name+","+x.intelligence_level+","+x.maintenance_cost+","+x.attractiveness+","+x.type+"\n");     
        }
        pw.write(sb1.toString());
        pw.close();
        
        ArrayList<Boy> alb=new ArrayList<Boy>();
        for(a=0;a<100;a++) {
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb2 = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb2.append(c);
            }
            String output = sb2.toString();
            Random rand = new Random();
            int intelligence_level=rand.nextInt(10) + 1;
            int budget=rand.nextInt(5000) + 1000;
            int attractiveness=rand.nextInt(10) + 1;
            Boy b=new Boy(output,intelligence_level,budget,attractiveness);
            alb.add(b);
        }
        PrintWriter pw2 = new PrintWriter(new File("boy.csv"));
        StringBuilder sb2 = new StringBuilder();
        for(Boy y:alb) {
            sb2.append(y.name+","+y.intelligence_level+","+y.budget+","+y.attractiveness+"\n");     
        }
        pw2.write(sb2.toString());
        pw2.close();
        
        int index=-1,max=0;
        ArrayList<Boy> blist=new ArrayList<Boy>(); 
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader("boy.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] str = line.split(cvsSplitBy);
                String n=str[0];
                int intelligence_level=Integer.parseInt(str[1]);
                int budget=Integer.parseInt(str[2]);
                int attractiveness=Integer.parseInt(str[3]);
                Boy b=new Boy(n,intelligence_level,budget,attractiveness);
                blist.add(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ArrayList<Girl> glist=new ArrayList<Girl>();
        String lin = "";
        String cvsSplit = ",";
        try (BufferedReader br1 = new BufferedReader(new FileReader("girl.csv"))) {
            while ((lin = br1.readLine()) != null) {
                String[] str = lin.split(cvsSplit);
                String n=str[0];
                int intelligence_level=Integer.parseInt(str[1]);
                int maintenance_cost=Integer.parseInt(str[2]);
                int attractiveness=Integer.parseInt(str[3]);
                int type=Integer.parseInt(str[4]);
                Girl g=new Girl(n,intelligence_level,maintenance_cost,attractiveness,type);
                glist.add(g);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        for(Girl x:glist) {
            if(x.type==1) {
                max=0;
                for(Boy y:blist) {
                    if(y.attractiveness>=max && x.maintenance_cost<=y.budget && y.available==0) {
                        max=y.attractiveness;
                        index=blist.indexOf(y);
                    }   
                }
                
                System.out.println(x.name+" got committed to "+blist.get(index).name);
                blist.get(index).available=1;
            }
            else if(x.type==2) {
                max=0;
                for(Boy y:blist) {
                    if(y.budget>=max && x.maintenance_cost<=y.budget && y.available==0) {
                        max=y.budget;
                        index=blist.indexOf(y);
                    }
                }
                
  
                System.out.println(x.name+" got committed to "+blist.get(index).name);
                blist.get(index).available=1;
            }
            else if(x.type==3) {
                 max=0;
                for(Boy y:blist) {
                    if(y.intelligence_level>=max && x.maintenance_cost<=y.budget && y.available==0) {
                        max=y.intelligence_level;
                        index=blist.indexOf(y);
                    }   
                }
                System.out.println(x.name+" got committed to "+blist.get(index).name);
                blist.get(index).available=1;
            }
        }  
    }
}
