      ' initialize the loop counter
      test = "Wizard Petra"
      t = {health}
      n = {name}
      print n+" health = "+t
      move 10
      count = 3
      ' stop looping if we're done
top:  if count = 0 then end
      print "Hello, world! "  + test
            
      ' decrement and restart the loop
      count = count - 1 
      goto top

end: