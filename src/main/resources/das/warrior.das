      ' initialize the loop counter
      count = (1 + (1 -1))
      test = "hallo"
      t = {strength}
      h = {health} + 10
     
      print "strength="+t
      print "health="+h
      move 10
      ' stop looping if we're done
top:  if count = 0 then end
      print "Hello, world!" + test + test
            
      ' decrement and restart the loop
      count = count - 1 
      goto top
end: