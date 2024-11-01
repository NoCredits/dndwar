      west = "WEST"

      count = 2
      if count ! 2 then end

      test = "hallo"
      t = {strength}
      h = {health} + 10
      f = {faceTo}
      print "strength="+t
      print "health="+h
      print "faceTo "+f
      face "NORTH"
      move 2
      ' stop looping if we're done
top:  if count = 0 then end
      print test+", world!" 
      face west
      move count
      ' decrement and restart the loop
      count = count - 1 
      goto top
end: