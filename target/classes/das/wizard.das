      ' initialize the loop counter

      test = "Wizard Petra"
      n = {name}
      t = {health}  
      print n +" health = "+t
      move 1
      count = 1
      ' stop looping if we're done
      E 
      S 3
      N 1
      S
      gosub moi
      print "Na gosub"
      combat = {inCombat}
      print combat
      if combat = 0 then combat

      
top:  if count = 0 then end
      print "Hello, world! "  + test
            
      ' decrement and restart the loop
      count = count - 1 
      goto top
      
moi:
      print "in gosub Moi"
      gosub hoi
      print "na hoi"
      endgosub
hoi:
      print "in hoi"
      endgosub
combat: 
      print "IN COMBAT";
      goto top
end:

