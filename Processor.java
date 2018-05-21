import java.util.*;
import java.io.*;


public class Processor 
{
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{


		ArrayList<Double> euclidArray = new ArrayList<Double>();
		ArrayList<Double> quadrants = new ArrayList<Double>();

		//initialize variables
		//variables for difference between 2 data points
		double xDifference = 0.0;
		double yDifference = 0.0;
		double timeDifference = 0.0;
		double xLargest = 0.0;
		double yLargest = 0.0;
		
		//variables for distance
		double cityBlock = 0.0;
		double euclidean = 0.0;
		double cbTotal = 0.0;
		double euTotal = 0.0;

		//variables for speed
		double xSpeed = 0.0;
		double xSpeedTotal = 0.0;
		double ySpeed = 0.0;
		double ySpeedTotal = 0.0;
		double cbSpeed = 0.0;
		double cbSpeedTotal = 0.0;
		double euSpeed = 0.0;
		double euSpeedTotal = 0.0;

		//variables for speed averages
		double xSpeedAvg = 0.0;
		double ySpeedAvg = 0.0;
		double cbSpeedAvg = 0.0;
		double euSpeedAvg = 0.0;

		//largest distances
		double cbLargest = 0.0;
		double euLargest = 0.0;
		
		//variables dealing with standard deviation and eye activity
		double standardDev = 0.0;
		double percentFixed = 0.0;
		double percentActive = 0.0; 
		double percentVeryActive = 0.0; 
		
		//variables dealing with what quadrants are loooked at
		double percentA = 0.0;
		double percentB = 0.0;
		double percentC = 0.0;
		double percentD = 0.0;
		double quadSwitches = 0.0;
		
		//find the skill level of the subject (beginner, intermediate, or advanced)
		String skill = "";
		int question;
		int qnum = 0;
		
		Scanner start = new Scanner(System.in);
		
		System.out.println("What type of ad is it \n1. Cars \n2. Characters \n3. Drugs \n4. Food?");
		int type = start.nextInt();
		String advert = "";
		
		switch(type)
		{
		case 1:
			advert = "Cars";
			break;
		case 2:
			advert = "Characters";
			break;
		case 3:
			advert = "Pharmaceutical";
			break;
		case 4: 
			advert = "Food";
			break;
		default:
			System.out.println("Invalid entry... closing program");
			System.exit(0);
			break;
			
		}

//		System.out.println("What is the question number (1-15)?");
//		question = start.nextInt();
//
//		switch(question)
//		{
//		case 1:
//			qnum = 1;
//			break;
//		case 2:
//			qnum = 2;
//			break;
//		case 3:
//			qnum = 3;
//			break;
//		case 4:
//			qnum = 4;
//			break;
//		case 5:
//			qnum = 5;
//			break;
//		case 6:
//			qnum = 6;
//			break;
//		case 7:
//			qnum = 7;
//			break;
//		case 8:
//			qnum = 8;
//			break;
//		case 9:
//			qnum = 9;
//			break;
//		case 10:
//			qnum = 10;
//			break;
//		case 11:
//			qnum = 11;
//			break;
//		case 12:
//			qnum = 12;
//			break;
//		case 13:
//			qnum = 13;
//			break;
//		case 14:
//			qnum = 14;
//			break;
//		case 15:
//			qnum = 15;
//			break;
//		default:
//			System.out.println("Invalid entry... closing program");
//			System.exit(0);
//			break;
//		}
//
//		System.out.println("Is the person: \n1. Beginner \n2. Intermediate \n3. Advanced");
//		int level = start.nextInt();
//
//		switch(level)
//		{
//		case 1:
//			skill = "Beginner";
//			break;
//		case 2:
//			skill = "Intermediate";
//			break;
//		case 3:
//			skill = "Advanced";
//			break;
//		default:
//			System.out.println("Invalid entry... closing program");
//			System.exit(0);
//			break;
//		}
		ArrayList<String[]> filter = new ArrayList<String[]>();
		

		Scanner scan = new Scanner(new File    ("food14.txt"));
		Scanner scan2 = new Scanner(new File   ("food14.txt"));
		Scanner quadScan = new Scanner(new File("food14.txt"));

		//skipping the line of text that contains the column names
		scan.nextLine();
		scan2.nextLine();
		scan2.nextLine();
		int i = 0;
		while(scan2.hasNext())
		{
			//split each line in the file into individual values;
			String line = scan.nextLine();
			String[] array1 = line.split(",");

			String line2 = scan2.nextLine();
			String[] array2 = line2.split(",");

			//collecting gaze coordinates
			double xPos1 = Double.parseDouble(array1[3]);
			double xPos2 = Double.parseDouble(array2[3]);
			double yPos1 = Double.parseDouble(array1[4]);
			double yPos2 = Double.parseDouble(array2[4]);
			double time1 = Double.parseDouble(array1[0]);
			double time2 = Double.parseDouble(array2[0]);

			//calculate distance and speed
			xDifference = Math.abs(xPos1 - xPos2);
			yDifference = Math.abs(yPos1 - yPos2);
			timeDifference = Math.abs(time2 - time1);

			cityBlock = xDifference + yDifference;
			euclidean = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
			euclidArray.add(euclidean);
			cbTotal += cityBlock;
			euTotal += euclidean;

			xSpeed = xDifference/timeDifference;
			xSpeedTotal += xSpeed;
			ySpeed = yDifference/timeDifference;
			ySpeedTotal += ySpeed;
			cbSpeed = cityBlock/timeDifference;
			cbSpeedTotal +=cbSpeed;
			euSpeed = euclidean/timeDifference;
			euSpeedTotal += euSpeed;


			if(xLargest < xDifference)
			{
				xLargest = xDifference;
			}

			if(yLargest < yDifference)
			{
				yLargest = yDifference;
			}

			if(cbLargest < cityBlock)
			{
				cbLargest = cityBlock;
			}

			if(euLargest < euclidean)
			{
				euLargest = euclidean;
			}
			i++;
		}
		//calculate averages
		xSpeedAvg = xSpeedTotal / i;
		ySpeedAvg = ySpeedTotal / i;
		cbSpeedAvg = cbSpeedTotal / i;
		euSpeedAvg = euSpeedTotal / i;
		
		//get data on eye activity
		standardDev = standardDeviation(euclidArray);
		percentFixed = fixation(euclidArray);
		percentActive = active(euclidArray);
		percentVeryActive = veryActive(euclidArray);
		quadrants = getQuadrants(quadScan);

		//get quadrant data
		percentA = quadrants.get(0);
		percentB = quadrants.get(1);
		percentC = quadrants.get(2);
		percentD = quadrants.get(3);
		quadSwitches = quadrants.get(4);

		System.out.println("The largest x distance traveled is: " + xLargest);
		System.out.println("The largest y distance traveled is: " + yLargest);

		System.out.println("The largest cityblock distance traveled is: " + cbLargest);
		System.out.println("The largest euclidian distance traveled is: " + euLargest);

		System.out.println("The average cityblock distance traveled is: " + cbTotal/i);
		System.out.println("The average euclidian distance traveled is: " + euTotal/i);
		System.out.println("The average speed is: " + (euSpeedTotal/i) + " units per second");

		System.out.println("The standard deviation is: " + standardDev);
		System.out.println("The percentage of the time fixed is: " + percentFixed);
		System.out.println("The percentage of the time active is: " + percentActive);
		System.out.println("The percentage of the time very active is: " + percentVeryActive);

		System.out.println("The percentage of time spent in Quadrant A " + percentA);
		System.out.println("The percentage of time spent in Quadrant B " + percentB);
		System.out.println("The percentage of time spent in Quadrant C " + percentC);
		System.out.println("The percentage of time spent in Quadrant D " + percentD);
		System.out.println("The number of times quadrants were switched " + quadSwitches);
		
		//output data to .csv file
		output(advert, qnum, skill,xSpeedAvg, ySpeedAvg, cbSpeedAvg, euSpeedAvg, standardDev, percentFixed, percentActive, percentVeryActive, percentA, percentB, percentC,
				percentD, quadSwitches);

	}
	
	/**
	 * This method outputs the processed data to a .csv file
	 * @param skill
	 * @param xSpeedAvg
	 * @param ySpeedAvg
	 * @param cbSpeedAvg
	 * @param euSpeedAvg
	 * @param standardDev
	 * @param percentFixed
	 * @param percentActive
	 * @param percentVeryActive
	 * @param percentA
	 * @param percentB
	 * @param percentC
	 * @param percentD
	 * @param quadSwitches
	 * @throws IOException
	 */
	public static void output(String advert, int qnum, String skill,double xSpeedAvg, double ySpeedAvg, double cbSpeedAvg, double euSpeedAvg, double standardDev, double percentFixed, 
			double percentActive, double percentVeryActive, double percentA, double percentB, double percentC, double percentD, double quadSwitches) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter("Advert_Data.csv", true));
		StringBuilder sb = new StringBuilder();
//		sb.append("Question Num");
//		sb.append(',');
//		sb.append("Skill");
//		sb.append(',');
//		sb.append("Advert Type");
//		sb.append(',');
//		sb.append("Average X Speed");
//		sb.append(',');
//		sb.append("Average Y Speed");
//		sb.append(',');
//		sb.append("Average City Block Speed");
//		sb.append(',');
//		sb.append("Average Euclidian Speed");
//		sb.append(',');
//		sb.append("Standard Deviation");
//		sb.append(',');
//		sb.append("Percent Fixed");
//		sb.append(',');
//		sb.append("Percent Active");
//		sb.append(',');
//		sb.append("Percent Very Active");
//		sb.append(',');
//		sb.append("Percent spent in Quad A");
//		sb.append(',');
//		sb.append("Percent spent in Quad B");
//		sb.append(',');
//		sb.append("Percent spent in Quad C");
//		sb.append(',');
//		sb.append("Percent spent in Quad D");
//		sb.append(',');
//		sb.append("# of Quadrant Switches");
//		sb.append('\n');

//		sb.append(qnum);
//		sb.append(',');
//		sb.append(skill);
//		sb.append(',');
		sb.append(advert);
		sb.append(',');
		sb.append(xSpeedAvg);
		sb.append(',');
		sb.append(ySpeedAvg);
		sb.append(',');
		sb.append(cbSpeedAvg);
		sb.append(',');
		sb.append(euSpeedAvg);
		sb.append(',');
		sb.append(standardDev);
		sb.append(',');
		sb.append(percentFixed);
		sb.append(',');
		sb.append(percentActive);
		sb.append(',');
		sb.append(percentVeryActive);
		sb.append(',');
		sb.append(percentA);
		sb.append(',');
		sb.append(percentB);
		sb.append(',');
		sb.append(percentC);
		sb.append(',');
		sb.append(percentD);
		sb.append(',');
		sb.append(quadSwitches);
		sb.append('\n');

		out.write(sb.toString());
		out.close();
		System.out.println("done!");

	}
	
	/**
	 * This method finds percentages of what quadrants the subject looked at
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Double> getQuadrants(Scanner scan) throws FileNotFoundException
	{
		
		ArrayList<Double> xList = new ArrayList<Double>();
		ArrayList<Double> yList = new ArrayList<Double>();

		ArrayList<Double> returnList = new ArrayList<Double>();

		double aCount = 0.0;
		double bCount = 0.0;
		double cCount = 0.0;
		double dCount = 0.0;

		double percentA = 0.0;
		double percentB = 0.0;
		double percentC = 0.0;
		double percentD = 0.0;

		int currentPos = 0;
		int prevPos = 0;
		double quadSwitches = 0.0;

		//skipping the first line that contains the column names
		scan.nextLine();
		//create lists of every x and y position
		while(scan.hasNextLine())
		{
			String line = scan.nextLine();
			String[] array1 = line.split(",");
			double xPos = Double.parseDouble(array1[3]);
			double yPos = Double.parseDouble(array1[4]);
			xList.add(xPos);
			yList.add(yPos);
		}


		ArrayList<Double> xSorted = (ArrayList<Double>) xList.clone();
		ArrayList<Double> ySorted = (ArrayList<Double>) yList.clone();
		Collections.sort(xSorted);
		Collections.sort(ySorted);

		double domain = xSorted.get(xSorted.size()-1);
		double range = ySorted.get(ySorted.size()-1);

		for(int i = 0; i < xList.size(); i++)
		{
			if(xList.get(i) < domain/2 && yList.get(i) >= range/2)
			{
				currentPos = 1;
				aCount++;

				if(currentPos != prevPos)
				{
					quadSwitches++;
				}
				prevPos = 1;

			}
			else if(xList.get(i) >= domain/2 && yList.get(i) >= range/2)
			{
				currentPos = 2;
				bCount++;
				if(currentPos != prevPos)
				{
					quadSwitches++;
				}
				prevPos = 2;
			}
			else if(xList.get(i) < domain/2 && yList.get(i) < range/2)
			{
				currentPos = 3;
				cCount++;
				if(currentPos != prevPos)
				{
					quadSwitches++;
				}
				prevPos = 3;
			}
			else if(xList.get(i) >= domain/2 && yList.get(i) < range/2)
			{
				currentPos = 4;
				dCount++;
				if(currentPos != prevPos)
				{
					quadSwitches++;
				}
				prevPos = 4;
			}

		}

		percentA = (aCount/xList.size()) * 100;
		percentB = (bCount/xList.size()) * 100;
		percentC = (cCount/xList.size()) * 100;
		percentD = (dCount/xList.size()) * 100;

		returnList.add(percentA);
		returnList.add(percentB);
		returnList.add(percentC);
		returnList.add(percentD);
		returnList.add(quadSwitches);

		return returnList;
	}

	/**
	 * This nethod finds the standard deviation of the input data
	 * @param euclidArray
	 * @return
	 */
	public static double standardDeviation(ArrayList<Double> euclidArray)
	{
		double sum =0.0, standardDev = 0.0;
		for(double num: euclidArray)
		{
			sum += num;
		}

		double mean  = sum/euclidArray.size();

		for(double num: euclidArray) 
		{
			standardDev += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDev/euclidArray.size());

	}

	/**
	 *this method takes the standard deviation and finds if eye fixation
	 * @param euclidArray
	 * @return
	 */
	public static double fixation(ArrayList<Double> euclidArray)
	{
		double standardDev = standardDeviation(euclidArray);
		double fixCount = 0;

		for(double num: euclidArray)
		{
			if(num <= standardDev/2)
			{
				fixCount++;
			}
		}

		return (fixCount / euclidArray.size()) * 100;

	}

	/**
	 * This method takes the standard deviation and finds eye activity 
	 * @param euclidArray
	 * @return
	 */
	public static double active(ArrayList<Double> euclidArray)
	{
		double standardDev = standardDeviation(euclidArray);
		double activeCount = 0;

		for(double num: euclidArray)
		{
			if(num >= standardDev*2)
			{
				activeCount++;
			}
		}

		return (activeCount / euclidArray.size()) * 100;

	}

	/**
	 * This method takes the standard deviation and finds eye activity (this one looks for high activity)
	 * @param euclidArray
	 * @return
	 */
	public static double veryActive(ArrayList<Double> euclidArray)
	{
		double standardDev = standardDeviation(euclidArray);
		double activeCount = 0;

		for(double num: euclidArray)
		{
			if(num >= standardDev*4)
			{
				activeCount++;
			}
		}

		return (activeCount / euclidArray.size()) * 100;

	}

}
