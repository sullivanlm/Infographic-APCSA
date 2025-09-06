------------------------------------------------------------------------

PROJECT TITLE: Infographic Final Project
PURPOSE OF PROJECT: Display imported data using Myro and Scanner to create a readable infographic
VERSION or DATE: v.1
HOW TO START THIS: 
AUTHORS: Aalaa Mahmoud
USER INSTRUCTIONS: 
  -- Clone repository 
  -- In order to execute, open VScode and add CSTEUTILS jar library to the Java Projects teb on the side


  
Analysis: 
I’m analyzing income data form the top college from each state. I collected the data myself using the UpShot database from the New York Times for Median Family Income, a college data set from github https://github.com/lux-org/lux-datasets/blob/master/data/college.csv?scrlybrkr=f6cc8ef3 
income data: 
  https://en.wikipedia.org/wiki/List_of_U.S._states_and_territories_by_income?scrlybrkr=f6cc8ef3
  https://www.nytimes.com/interactive/projects/college-mobility/princeton-university

 
This data is of interest to me because it gives context to post secondary education accessibility, which I find very interesting and very important. 
The data is stored in the csv file: IncomeData

Cleaning steps: 
I started by downloading the csv file from github. I deleted everything except for the name, state, and funding model for the top college of each state, deleting the rest of the schools except for these ones. Then using wikipedia and the UpShot, I inserted the data for each college into the excel sheet
I used multiple array lists to separate the data so I could go back through the data for multiple elements of the infographic without creating a new scanner to read through it again. 

The relevant fields are the name, the state id, the funding model (public or private), the median family income from the school, and the median state income
The limited nature of the data and having to create the data set myself forced me to leave out data that might have been interesting to look at such as geography or legacy factors that could have had interesting correlations to the data available. 

<img width="627" height="291" alt="Screenshot 2025-09-06 at 3 54 42 PM" src="https://github.com/user-attachments/assets/aa792bab-c7c3-49b5-8f18-698e1f79965f" />
<img width="629" height="291" alt="Screenshot 2025-09-06 at 3 54 49 PM" src="https://github.com/user-attachments/assets/1ac81690-7752-40ed-bb2a-3d97002f9004" />

<img width="625" height="343" alt="Screenshot 2025-09-06 at 3 54 55 PM" src="https://github.com/user-attachments/assets/5bc94fc3-cbad-43dc-b9ab-f51af0ef176f" />
<img width="625" height="286" alt="Screenshot 2025-09-06 at 3 55 04 PM" src="https://github.com/user-attachments/assets/9e52fa1e-ba29-499e-ab80-050f9eed517c" />


Insights: 
This data is important because it shows that the majority of students that go to these top colleges have the money to do so. In comparison to the median state income, the discrepancy is significant. Almost every single college on the list has a median family income greater than $100,000. In comparison, not one state has a median income that breaks $100,000. Seeing as more than 60% of American households don’t make greater than $100,000/year, the data has serious implications for who is going to these colleges, and, maybe more importantly, who the college is accepting. There are obviously outliers to this trend but for the most part, as ranking increasing income does as well. 
It is also important to see the difference between publicly funded institutions and privately funded institutions and the trends in their income data. When a college is privately funded, we can see an uptick in median family income in comparison to publicly state funded ones. 

