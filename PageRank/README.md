PageRank project of Lab2 on Hadoop
author: Lucas Fidon (lucas.fidon@student.ecp.fr)

The algorith is iterative:

Initialization: build the graph from the data
you can run:
java cs.Lab2.InitGraph. InitGraphDriver.java ../Data/soc-Epinions1.txt initGraph

Iteration: perform the iterative PageRank algorithm following ressources provided in the homework.
You can run:
java cs.Lab2.PageRank.PageRankDriver.java ./InitGraph/part-r-00000 output_pageRank
the number of iterations is fixed at 200 (but you can change it by setting the variabl max_iter to a different value if you like)

Get top10 ranking according to the PageRank:
You can run:
java cs.Lab2.PageRank.Run_ranking.java
the result is save in a text file whose name is of the form "top10ranking_output.txt"
The ranking I found was:

Top 10 ranking according to the pageRank for the file /Data/soc-Epinions1.txt
(damping factor used: 0,85, number of iterations: 199)
Rank 1: nodeId=18, PageRank=347.6029
Rank 2: nodeId=737, PageRank=243.10344
Rank 3: nodeId=118, PageRank=163.19756
Rank 4: nodeId=1719, PageRank=161.73041
Rank 5: nodeId=136, PageRank=155.348
Rank 6: nodeId=143, PageRank=150.72754
Rank 7: nodeId=790, PageRank=150.51283
Rank 8: nodeId=40, PageRank=140.00058
Rank 9: nodeId=725, PageRank=124.71484
Rank 10: nodeId=1619, PageRank=121.212975
