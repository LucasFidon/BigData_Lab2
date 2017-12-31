Project: Ranking of words from two texts (defoe-robinson-103 et callwild) with respect to their TF-IDF value.
author: Lucas Fidon (lucas.fidon@student.ecp.fr)

Following the Hints of the homeword I proceeded in 3 steps + 1 extra step to sort the results wrt TF-IDF values

Round 1: Compute WordCount for each word of each document (wordcount in one document)
You can run:
java cs.Lab2.WordCount.WordCountDriver.java ../Data/defoe-robinson-103.txt ../Data/callwild output_round1
Results can be found in output_round1/part-r-00000

Round 2: Compute wordcount and wordsperdoc for each word of each document
You can run:
java cs.Lab2.WordCountperDoc.WordCountperDocDriver.java output_round1/part-r-00000 output_round2
Results can be found in output_round2/part-r-00000

Round 3: Compute TF-IDF for each word of each document
You can run:
java cs.Lab2.TFIDF.TFIDFDriver.java output_round2/part-r-00000 output_round3
Results can be found in output_round3/part-r-00000

Sort: sort the words wrt to their TF-IDF score (still using MapReduce)
You can run:
java cs.Lab2.SortperTFIDF.SortDriver.java output_round3/part-r-00000 rankingTFIDF
Results can be found in rankingTFIDF/part-r-00000

In particular, the top20 words and their TF-IDF values were:

buck@callwild	0.006827235
dogs@callwild	0.002442956
thornton@callwild	0.0017668009
myself@defoe-robinson-103.txt	0.0016665459
spitz@callwild	0.0013086796
sled@callwild	0.0013086796
francois@callwild	0.0011342764
bucks@callwild	0.0010251999
friday@defoe-robinson-103.txt	0.0010216236
trail@callwild	8.943081E-4
john@callwild	8.724928E-4
perrault@callwild	8.070469E-4
hal@callwild	6.543398E-4
team@callwild	6.543398E-4
thoughts@defoe-robinson-103.txt	6.3347816E-4
ice@callwild	6.107092E-4
traces@callwild	6.107092E-4
solleks@callwild	5.888939E-4
around@callwild	5.670786E-4
dave@callwild	5.23448E-4


