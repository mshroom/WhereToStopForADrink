# Week report 3

I have removed all ready-made data structures from my project 
and replaced them with my own implementations. For my algorithms to work I needed a queue that supports
both first-in-first-out and last-in-first-out operations. I hade to make two queues, one for integers and
another for Node objects. I also wanted to make a more efficient queue by allowing it to grow in size if needed,
so that I could use it to replace ArrayLists.

I was positively surprised to see that after I added my own data structures, the algorithms did not get significantly
slower, judging by the time that the tests needed to run. Some tests ran actually a bit faster. I should make
some experiments with my queues to see how much the initial size of the queue effects the performance of
the algorithms. The size can be defined when creating the queue, so I could optimize it to be as effective
as possible.

I also created a simple text UI and added a possibility to test and compare the algorithms with test graphs. 
The testing and comparing features will be more interesting once I get more graphs and hopefully real data. 
That will be one of the things I need to concentrate on next week. I have tried to keep my code well tested and 
documented with Javadoc, but next week I also have to start writing the required testing and implementation documents.
