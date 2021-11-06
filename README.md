# HITS-algoritm
Hyperlink-Induced Topic Search (HITS; also known as hubs and authorities) is a link analysis algorithm that rates Web pages, developed by Jon Kleinberg. 
Kleinberg (1998) introduced a model that assigns two ranks to each web-
page/document. One rank signies how important the document is as a hub of information, and the other how important
it is as an authoritative source of information. The resulting ranking algorithm is sometimes referred to as HITS i.e. a
Hypertext Induced Topic Search. The ideas behind the model can be summarized as follows:

### HITS modeling: Hubs and Authorities.

(1) A page is an authoritative page if it is referenced by many hub pages that are relevant to the query,

(2) a page is a hub page for a query if it points to many authoritative pages for that query, and

(3) good authoritative and hub pages reinforce one another.

### Algorithm HubAuthority-Rank

![Screenshot (3)](https://user-images.githubusercontent.com/24352869/140625786-951a63af-e2e6-42be-9283-dadd962e759c.png)


### Sample input and output:
```
Kleinberg's HITS (Hub and Authority) Algorithm
0 : 0 1 1
1 : 0 0 1 (Adjacency list)
2 : 0 0 0
(a) vectors are initialized to = 1.000000
Base : 0 :A/H[ 0]=1.00000/1.00000 A/H[ 1]=1.00000/1.00000 A/H[ 2]=1.00000/1.00000
Iterat : 1 :A/H[ 0]=0.00000/0.83205 A/H[ 1]=0.44721/0.55470 A/H[ 2]=0.89443/0.00000
Iterat : 2 :A/H[ 0]=0.00000/0.84800 A/H[ 1]=0.51450/0.53000 A/H[ 2]=0.85749/0.00000
Iterat : 3 :A/H[ 0]=0.00000/0.85027 A/H[ 1]=0.52410/0.52635 A/H[ 2]=0.85166/0.00000
Iterat : 4 :A/H[ 0]=0.00000/0.85059 A/H[ 1]=0.52549/0.52582 A/H[ 2]=0.85080/0.00000
Iterat : 5 :A/H[ 0]=0.00000/0.85064 A/H[ 1]=0.52570/0.52574 A/H[ 2]=0.85067/0.00000
Iterat : 6 :A/H[ 0]=0.00000/0.85065 A/H[ 1]=0.52573/0.52573 A/H[ 2]=0.85065/0.00000
Iterat : 7 :A/H[ 0]=0.00000/0.85065 A/H[ 1]=0.52573/0.52573 A/H[ 2]=0.85065/0.00000
(b) vectors are initialized to = 0.333333
Base : 0 :A/H[ 0]=0.33333/0.33333 A/H[ 1]=0.33333/0.33333 A/H[ 2]=0.33333/0.33333
Iterat : 1 :A/H[ 0]=0.00000/0.83205 A/H[ 1]=0.44721/0.55470 A/H[ 2]=0.89443/0.00000
Iterat : 2 :A/H[ 0]=0.00000/0.84800 A/H[ 1]=0.51450/0.53000 A/H[ 2]=0.85749/0.00000
Iterat : 3 :A/H[ 0]=0.00000/0.85027 A/H[ 1]=0.52410/0.52635 A/H[ 2]=0.85166/0.00000
Iterat : 4 :A/H[ 0]=0.00000/0.85059 A/H[ 1]=0.52549/0.52582 A/H[ 2]=0.85080/0.00000
Iterat : 5 :A/H[ 0]=0.00000/0.85064 A/H[ 1]=0.52570/0.52574 A/H[ 2]=0.85067/0.00000
Iterat : 6 :A/H[ 0]=0.00000/0.85065 A/H[ 1]=0.52573/0.52573 A/H[ 2]=0.85065/0.00000
Iterat : 7 :A/H[ 0]=0.00000/0.85065 A/H[ 1]=0.52573/0.52573 A/H[ 2]=0.85065/0.00000
(c) vectors are initialized to = 0.577350
Base : 0 :A/H[ 0]=0.57735/0.57735 A/H[ 1]=0.57735/0.57735 A/H[ 2]=0.57735/0.57735
Iterat : 1 :A/H[ 0]=0.00000/0.83205 A/H[ 1]=0.44721/0.55470 A/H[ 2]=0.89443/0.00000
Iterat : 2 :A/H[ 0]=0.00000/0.84800 A/H[ 1]=0.51450/0.53000 A/H[ 2]=0.85749/0.00000
Iterat : 3 :A/H[ 0]=0.00000/0.85027 A/H[ 1]=0.52410/0.52635 A/H[ 2]=0.85166/0.00000
Iterat : 4 :A/H[ 0]=0.00000/0.85059 A/H[ 1]=0.52549/0.52582 A/H[ 2]=0.85080/0.00000
Iterat : 5 :A/H[ 0]=0.00000/0.85064 A/H[ 1]=0.52570/0.52574 A/H[ 2]=0.85067/0.00000
Iterat : 6 :A/H[ 0]=0.00000/0.85065 A/H[ 1]=0.52573/0.52573 A/H[ 2]=0.85065/0.00000
Iterat : 7 :A/H[ 0]=0.00000/0.85065 A/H[ 1]=0.52573/0.52573 A/H[ 2]=0.85065/0.00000
```

# How to run
`% java hits iterations initialvalue filename`

for example:

`javac hits0206.java`

`java hits0206 15 -1 samplegraph.txt`
