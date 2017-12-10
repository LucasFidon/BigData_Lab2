Project Trees of Paris
author: Lucas Fidon (lucas.fidon@student.ecp.fr)

Part 1: Count the number of tree per type
Note that I assumed that what was meant by "type" was the field "genre".
Basically this is just a WordCount for the different type of trees.
You can run:
java cs.Lab2.CountPerType.CountDriver.java ../Data/arbres.csv type_count
Resukts can be found in the file type_count/part-r-00000

Part 2: Compute the height of the highest tree per type of trees
Once one have notive that the max operator is commutative and distributive using MapReduce to compute it is easy
You can run:
java cs.Lab2.HighestTreePerType.MaxDriver.java ../Data/arbres.csv highestTreePerType_output
Results can be found in highestTreePerType_output/part-r-00000

Part 3: Compute the borough of the oldest tree
To do that we sorted the couples (borough, age) wrt to age in descending order
You can run:
java cs.Lab2.SortBoroughPerAge.SortDriver.java ../Data/arbres.csv BoroughSortedPerHeight_output
Results can be found in the file BoroughSortedPerHeight_output/part-r-00000
The oldest tree we found was in the 5th borough of Paris. 
