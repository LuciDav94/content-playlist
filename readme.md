First thing first I will try to divide in smaller chunks what I have to do: 
          1) - implement model structure, implement the service to return the playlist,
I will add some dummy data first in memory and play a bit with the structures until it matches well
          2) - implement read from a json file and map directly to model structure
          3) - trying to optimize my code, check if there are any problems
          4) - implement as a library

1) While implementing this I already started to question the solution I will go, especially if
I have to return a playlist which is composed by multiple objects, maybe I can do something to directly
compute the right order and maybe add into a MAP, so that by giving the identifier and the language the answer
I will return fast from a MAP, I will try to go a bit into this direction and adjust the models into this direction
   
  I implemented a first model of the code, some easy logic and now I want to do some tests to validate the solution.
  From the first test for my initial solution I see the running time is 32ms for M13,US case, I will review later 
how I can improve this. 
  I continue to validate my solution, implement tests to cover all possible scenarions and implement all cases
I think is relevant
 
2) After validating my initial solution I will continue with the read from file.
   Implemented read from file using jackson, now it starting to get more clearer
   
3) Now I will try to make a production ready go. As I saw in time whan I used a code from another
library it was really well documented, so I will do this and I will try to cover all the cases and describe everything
   I implemented tests, coverage 100% for all my code and documented my code
   I will think tomorrow of a solution to load the in-memory from JSON in an async way to not interface
 with program loading
   
4) Added <groupId>us.maretha</groupId> and <artifactId>content-playlist</artifactId>
and executed mvn clean install to install in the local .m2 
   Tested the library in another application and everything works ok.

I still have a few scenarios to think about:
  - I am not proud with the static load of the file, especially if it will be a really large file 
  - I am wondering how it works with a large file
  - I am not proud how I iterate over the prerolls and content's video but at least the content will 
be found in O(1)
    
I will try to investigate a bit more the load of the file in an async way, maybe using CompletableFuture?
I can go with the solution to load in an async way in-memory the file using this piece of code:
CompletableFuture.runAsync(StoreRead::initContentFile);
and add it to a static block, but than I will have to implement a bit differently.
I will have to wait for the CompletableFuture to finish so that the computePlaylist will return a value
after the content map is initialised. Depends on what strategy we want to go, normally it will be a DB
behind which already have the values, but for this case I will prefer to let the file to be loaded in the static  
block 


