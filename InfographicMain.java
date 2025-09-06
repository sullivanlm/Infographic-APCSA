
/**
 * Write a description of class InfographicMain here.
 * This project is supposed to create an inforgraphic that displays the relationship 
 * between top colleges and the family income of students that go there. I display data 
 * points that represent the top college from every state. The x-axis represent the 
 * median family income per state and the color intensity represents median family
 * income. Blue dots represent publically funded institutions while red dots represent 
 * privatly funded institutions
 * @author Aalaa mahmoud
 * @version v.1
 */
import csteutils.*;
import csteutils.myro.*; 
import java.util.Scanner; 
import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;  
import java.lang.Math;  

public class InfographicMain
{
    public static int countLines(File file) throws IOException
    {
        Scanner lineCounter = new Scanner(file); 
        int count = 0; 
        //skips the first line which holds the description
        lineCounter.nextLine(); 
        while(lineCounter.hasNextLine())
        {
            count ++; 
            lineCounter.nextLine(); 
        }
        return count; 
    }
 
    public static void createXYAxis(MyroCanvas canvas )
    {
        int y = 410; // the starting y-coordinate on the canvas
        int yVal = 50000; // the starting y coordinate label
        MyroLine yAxis = new MyroLine(canvas, 5, 10, 5, 410); 
        for(int i = 0; i <11; i++)
        {
            MyroLine x = new MyroLine(canvas, 5, y, 1000, y); 
            MyroText yLabel = new MyroText(canvas, 5, y-10, Integer.toString(yVal)); 
            yLabel.setFontSize(10); 
            yVal += 5000;//increments the label as lines go up 
            y-=40; // adjusting the y coordinate on the canvas to go up each time
            x.visible();
            yLabel.visible(); 
        }
        yAxis.visible(); 
    }
    
    public static void addCollegeLabel(MyroCanvas canvas, int xCoor, int yCoor, ArrayList<String> collegeID, int i)
    {
            String cID = collegeID.get(i);
            MyroText cIDtext = new MyroText(canvas, xCoor+9, (int)yCoor+7, cID);
            cIDtext.setFontSize(10); 
            cIDtext.visible();
    }

    public static ArrayList<ArrayList<String>> makeDataSets(Scanner sc, int count)
    {
        //Creates a list of lists for that contain the seperated data in the csv
        //All lists need to be of the type string so that I can add them to a the larger list
        //Neccessary conversions to integers for income values will be done in the main function 
        sc.nextLine(); 
        ArrayList<String> collegeName = new ArrayList<String>(); 
        ArrayList<String> collegeID = new ArrayList<String>(); 
        ArrayList<String> stateID = new ArrayList<String>(); 
        ArrayList<String> fundingModel = new ArrayList<String>(); 
        ArrayList<String> medianFamilyIncome = new ArrayList<String>(); 
        ArrayList<String> medianStateIncome = new ArrayList<String>();
        int line = 0; 
        while (sc.hasNextLine()&&line<count)
        {
            for(int i = 0; i<count; i++)
            {
                collegeName.add(sc.next()); 
                collegeID.add(sc.next());
                stateID.add(sc.next());
                fundingModel.add(sc.next());
                medianFamilyIncome.add(sc.next());
                medianStateIncome.add(sc.next());
                sc.nextLine(); 
                line++; 
            }
        }
        ArrayList<ArrayList<String>> dataSets = new ArrayList<ArrayList<String>>();
        dataSets.add(collegeName); 
        dataSets.add(collegeID); 
        dataSets.add(stateID);
        dataSets.add(fundingModel);
        dataSets.add(medianFamilyIncome);
        dataSets.add(medianStateIncome);
        return dataSets; 
    }

    public static void main(String []args)throws IOException
    {
        File collegeData = new File("IncomeData.csv") ; 
        Scanner dataReader = new Scanner(collegeData);
        Scanner sc = new Scanner(System.in); //to interact with users on the console
        dataReader.useDelimiter(",");
        int lineCount = countLines(collegeData); 
        
        MyroCanvas canvas = new MyroCanvas("canvas", 1000, 440); 
        MyroCanvas key = new MyroCanvas("key", 500, 200);
        MyroCanvas specifics = new MyroCanvas("specs", 600, 300); 
        
        //labels for the key
        MyroCircle publicColorKey = new MyroCircle(key, 20, 20 , 10); 
        publicColorKey.makeFilled(); 
        publicColorKey.setFillColor(new Color(90, 90, 240));
        publicColorKey.visible(); 
        
        MyroCircle privateColorKey = new MyroCircle(key, 20, 60, 10); 
        privateColorKey.makeFilled(); 
        privateColorKey.setFillColor(new Color (240, 90 ,90 ));
        privateColorKey.visible();
        
        MyroText publicTextKey = new MyroText(key, 40, 20 , "Indicates that the college is publically funded "); 
        publicTextKey.setFontSize(10); 
        publicTextKey.visible(); 

        MyroText privateTextKey = new MyroText(key, 40, 60, "Indicates that the college is privately funded");
        privateTextKey.setFontSize(10);
        privateTextKey.visible();
 
        MyroText MFIkeyText = new MyroText(key, 10, 100 , "Color darkens as Median Family Income from each school increases");
        MFIkeyText.setFontSize(10); 
        MFIkeyText.visible(); 
        
        MyroText yAxisKey = new MyroText(key, 10, 130, "Y-Axis: Madian state income from $50,000/year to $100,000/year");
        yAxisKey.setFontSize(10); 
        yAxisKey.visible(); 
        
        MyroText xLabel = new MyroText(canvas, 400, 420, "<---States Alphabetically--->");
        xLabel.setFontSize(15);
        xLabel.makeFilled(); 
        xLabel.setFillColor(new Color(0,0,0)); 
        xLabel.visible();
        
        createXYAxis(canvas); 
        
        ArrayList<ArrayList<String>> data = makeDataSets(dataReader, lineCount);
        ArrayList<String> collegeName = data.get(0);  
        ArrayList<String> collegeID = data.get(1);  
        ArrayList<String> stateID = data.get(2);
        ArrayList<String> FM = data.get(3); // Funding Model 
        ArrayList<String> stringMSI = data.get(4); // Average State Income
        ArrayList<Integer> intMSI = new ArrayList<Integer>();
        ArrayList<String> stringMFI = data.get(5); // Median Family Income
        ArrayList<Integer> intMFI = new ArrayList<Integer>(); 
        ArrayList<MyroCircle> dataPoints = new ArrayList<MyroCircle>();

        
        for(int i = 0; i<lineCount;i++)
        {
            intMSI.add(Integer.parseInt(stringMSI.get(i)));
            intMFI.add(Integer.parseInt(stringMFI.get(i))); 
        }
        
        for(int i = 0; i<intMFI.size(); i++)
        {
            String currentFM = FM.get(i); 
            int currentMFI = intMFI.get(i);  
            MyroCircle circ = new MyroCircle(canvas, 0, 0, 8);
            circ.makeFilled();
            int R = 0; 
            int G = 0; 
            int B  = 0;
            if(currentFM.equalsIgnoreCase("private"))
            { 
                R = 120; //creates a red starting color
            }
            else if(currentFM.equalsIgnoreCase("public"))
            {
                B = 120; //creates a blue starting color
            }
            //this if statement is applicable to either public or private because of 
            //the increased starting values or R and B
            if (currentMFI<90000)
            {  
                circ.setFillColor(new Color(R+135, G+135, B+135)); 
            }
            else if (currentMFI>90000 && currentMFI<140000)
            {
                circ.setFillColor(new Color(R+90, G+90, B+90)); 
            }
            else if (currentMFI>140000 && currentMFI<200000)
            {
                circ.setFillColor(new Color (R+45, G+45, B+45));
            }
            else if (currentMFI>200000)
            {
                circ.setFillColor(new Color (R, G, B));
            }
            
            dataPoints.add(circ);
        }
        
        //min and max values for median state income to scale graph correcly 
        int maxMSI = 100000; 
        int minMSI = 50000;
        int xCoor = 30; //starting x coordinate for the first point
        
        System.out.println("Would you like to display data with labels(yes/no):"); 
        String input= sc.nextLine(); 
 
        for(int i =0; i<dataPoints.size(); i++)
        {
            int currentASI = intMSI.get(i);
            String ID = stateID.get(i); 
            
            // Scales the correct y value based on the max ASI (top of the graph) to 
            // the min (bottom of the graph)
            double yCoor = (double)maxMSI-currentASI; 
            yCoor = (yCoor/minMSI*400); 
            
            dataPoints.get(i).visible(); 
            dataPoints.get(i).setCenter(xCoor, (int)(yCoor)+12); 
            if(input.equalsIgnoreCase("yes"))
            {
                addCollegeLabel( canvas, xCoor, (int)yCoor, collegeID, i);  
            }
            xCoor+=18; 
        }
        
        // When a user want the data displayed for a specific college, they can see
        // the specs by entering the state ID
        
        MyroText stateText = new MyroText(specifics, 10, 100,"");
        MyroText nameText = new MyroText(specifics, 10, 125,"");
        MyroText modelText = new MyroText(specifics, 10, 150,"");
        MyroText MSIText = new MyroText(specifics, 10, 175, "");
        MyroText MFIText = new MyroText(specifics, 10, 200, "");
        MyroText IDR = new MyroText(specifics, 8, 225, "");
        while(true){
            System.out.println("Enter State ID to see college information: " ); 
            input = sc.nextLine();
            int CCI = stateID.indexOf(input.toUpperCase());// current college index 
            MyroCircle point = new MyroCircle(specifics, 50, 50, 40);
            point.makeFilled(); 
            point.setFillColor(dataPoints.get(CCI).getFillColor());
            point.visible(); 
            
            String state = stateID.get(CCI);
            stateText.setText("State: " + state);
            stateText.setFontSize(20); 
            stateText.visible(); 
            
            String name = collegeName.get(CCI); 
            nameText.setText("College Name: " + name);
            nameText.setFontSize(20); 
            nameText.visible(); 
            
            String model = FM.get(CCI);
            modelText.setText("Funding Model: " + model); 
            modelText.setFontSize(20); 
            modelText.visible(); 
            
            int specMSI = intMSI.get(CCI);
            MSIText.setText("Average State Income: " + "$" + specMSI); 
            MSIText.setFontSize(20); 
            MSIText.visible(); 
            
            int specMFI = intMFI.get(CCI); 
            MFIText.setText("Median Family Income from school: " + "$" +  specMFI);
            MFIText.setFontSize(20); 
            MFIText.visible(); 
            
            // the IDR is the ratio between the MFI and MSI, signifiying the dicrpancy
            // between the two values 
            double incomeDiscrepancyRatio = (double)specMFI/specMSI; 
            IDR.setText("Income Discrepancy Ratio (MFI:ASI): " + Math.round(incomeDiscrepancyRatio) + ":" + "1" );
            IDR.setFontSize(20); 
            IDR.visible(); 
             
        }
    }
} 
