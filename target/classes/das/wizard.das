      ' initialize the loop counter
      test = "Wizard Petra"
      n = {name}
      t = {health}
      print n +" health = "+t
      move 1
      count = 1
      ' stop looping if we're done
      E 
      S 1
top:  if count = 0 then end
      print "Hello, world! "  + test
            
      ' decrement and restart the loop
      count = count - 1 
      goto top

end: