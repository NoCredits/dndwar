      ' initialize the loop counter
      count = 4
      test = "hallo"
      t = {strength}
      h = {health} + 10
      f = {faceTo}
      print "strength="+t
      print "health="+h
      print "faceTo "+f
      faceTo NORTH
      move 10
      ' stop looping if we're done
top:  if count = 0 then end
      print test+", world!" 
      faceTo WEST

      move 10
      delay 1      
      ' decrement and restart the loop
      count = count - 1 
      goto top
end: