import java.io.*;
public class Calendar
{
    public static void main()throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Calendar call=new Calendar();
        int c=0;
        while(c!=3)
        {
            System.out.print("\f");
            System.out.println("\t\tMENU");
            System.out.println("1) Print calendar of year");
            System.out.println("2) Print calendar of month");
            System.out.println("3) Exit");
            System.out.print("Enter choice: ");
            c=Integer.parseInt(br.readLine());
            if(c==1)
            {
                System.out.print("Enter year(after 1800): ");
                int y=Integer.parseInt(br.readLine());
                while(y<=1800)
                {
                    System.out.print("\f");
                    System.out.println("\t\tMENU");
                    System.out.println("1) Print calendar of year");
                    System.out.println("2) Print calendar of month");
                    System.out.println("3) Exit");
                    System.out.println("Enter choice: 1");
                    System.out.print("Enter year: ");
                    y=Integer.parseInt(br.readLine());
                }
                System.out.print("\f");
                call.calendarForYear(y);
                System.out.println();
                System.out.println("Press enter to return to menu...");
                char t=(char)br.read();
            }
            else if(c==2)
            {
                System.out.print("Enter month: ");
                int m=Integer.parseInt(br.readLine());
                while(m<1 || m>12)
                {
                    System.out.print("\f");
                    System.out.println("\t\tMENU");
                    System.out.println("1) Print calendar of year");
                    System.out.println("2) Print calendar of month");
                    System.out.println("3) Exit");
                    System.out.println("Enter choice: 2");
                    System.out.print("Enter month: ");
                    m=Integer.parseInt(br.readLine());
                }
                System.out.print("Enter year(after 1800): ");
                int y=Integer.parseInt(br.readLine());
                while(y<=1800)
                {
                    System.out.print("\f");
                    System.out.println("\t\tMENU");
                    System.out.println("1) Print calendar of year");
                    System.out.println("2) Print calendar of month");
                    System.out.println("3) Exit");
                    System.out.println("Enter choice: 2");
                    System.out.println("Enter month: "+m);
                    System.out.print("Enter year: ");
                    y=Integer.parseInt(br.readLine());
                }
                System.out.print("\f");
                call.calendarForMonth(m,y);
                System.out.println();
                System.out.println("Press enter to return to menu...");
                char t=(char)br.read();
            }
            else if(c==3)
            {
                System.out.print("\f");
                System.out.print("Thank You!");
            }
        }
    }

    public void calendarForYear(int y2)
    {
        for(int i=1; i<=12; i++) calendarForMonth(i,y2);
    }

    public void calendarForMonth(int m2, int y2)
    {
        int d2=1;
        int d1=5,m1=1,y1=1800;
        int diff=diffInDays(d1,m1,y1,d2,m2,y2);
        String date[][]=new String[6][7];//the array that stores the dates

        int start=diff%7;//to store the initial number of black spaces in the calendar
        for(int i=0; i<start; i++)date[0][i]="";

        int days=daysInMonth(m2,y2);//to store the number of days in the month
        int count=1;
        for(int i=0; i<6; i++)
        {
            if(i==0)
            {
                for(int j=start; j<7; j++)
                {
                    date[i][j]=""+count+"";
                    count++;
                }
            }
            else
            {
                for(int j=0; j<7; j++)
                {
                    if(count>days)break;
                    else
                    {
                        date[i][j]=""+count+"";
                        count++;
                    }
                }
            }
        }
        if((start>=4 && days==31) || (start>=5 && days==30) || (start>=6 && days==29))//(start>=7 && days==28) is not required
        {
            for(int i=(days+start)%7; i<7; i++)date[5][i]="";
        }
        else
        {
            for(int i=(days+start)%7; i<7; i++)date[4][i]="";
            for(int i=0; i<7; i++)date[5][i]="";
        }

        if(m2==1)System.out.print("JANUARY");
        else if(m2==2)System.out.print("FEBRUARY");
        else if(m2==3)System.out.print("MARCH");
        else if(m2==4)System.out.print("APRIL");
        else if(m2==5)System.out.print("MAY");
        else if(m2==6)System.out.print("JUNE");
        else if(m2==7)System.out.print("JULY");
        else if(m2==8)System.out.print("AUGUST");
        else if(m2==9)System.out.print("SEPTEMBER");
        else if(m2==10)System.out.print("OCTOBER");
        else if(m2==11)System.out.print("NOVEMBER");
        else if(m2==12)System.out.print("DECEMBER");
        System.out.println(" "+y2);
        System.out.println();
        System.out.println("Sun\tMon\tTues\tWed\tThur\tFri\tSat");
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<7; j++)
            {
                System.out.print(date[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public int diffInDays(int d1, int m1, int y1, int d2, int m2, int y2)
    {
        int days=0;
        if(d1==d2 && m1==m2 && y1==y2)days=0;
        else if(m1==m2 && y1==y2)days=d2-d1;
        else if(y1==y2)
        {
            if(m1==1 || m1==3 || m1==5 || m1==7 || m1==8 || m1==10 || m1==12) days=31-d1+d2;
            else if(m1==4 || m1==6 || m1==9 || m1==11)days=30-d1+d2;
            else if((y1%4==0 && y1%100!=0) || y1%400==0)days=29-d1+d2;
            else days=28-d1+d2;
            for(int i=m1+1; i<=m2-1; i++)days=days+daysInMonth(i,y1);
        }
        else
        {
            if(m1==1 || m1==3 || m1==5 || m1==7 || m1==8 || m1==10 || m1==12) days=31-d1+d2;
            else if(m1==4 || m1==6 || m1==9 || m1==11)days=30-d1+d2;
            else if((y1%4==0 && y1%100!=0) || y1%400==0)days=29-d1+d2;
            else days=28-d1+d2;
            for(int i=m1+1; i<=12; i++)days=days+daysInMonth(i,y1);
            for(int i=1; i<=m2-1; i++)days=days+daysInMonth(i,y2);
            for(int i=y1+1; i<=y2-1; i++)days=days+daysInYear(i);
        }
        return days;
    }

    public int daysInMonth(int m, int y)
    {
        if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12) return 31;
        else if(m==4 || m==6 || m==9 || m==11) return 30;
        else if((y%4==0 && y%100!=0) || y%400==0)return 29;
        else return 28;
    }

    public int daysInYear(int y)
    {
        if((y%4==0 && y%100!=0) || y%400==0)return 366;
        else return 365;
    }
}