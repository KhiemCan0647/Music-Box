# My Personal Project

## A music box simulator

A music box, though just a small object, its design is exquisite 
and create beautiful sound as any musical instrument.
Music boxes are greatly different from others based on its brands,
materials, but what differ the most are: 
- The *numbers of different notes*: the number of tuned teeth of 
the comb, the more teeth the longer the comb and the cylinder. 

- The *length* of the music track:
the radius of the cylinder's base (or the circumference of the cylinder base) 
determines how many notes of the track 
it can play. 
- The *song* it plays: the pins which are placed along the height
of the cylinder hit different teeth of the comb. Placing one lines of 
pins after another creates a song. Multiple pins lined up
on 1 line of height allows makes the comb plays multiple different 
notes simultaneously<br>

A normal music box usually has a comb consisted of 20-30 notes, and a cylinder 
which allows the box to play 1 song only for 1-2 min. If you want a music
box with better sound quality, a lengthier song, or multiple songs, you have to
pay for deluxe music boxes, which have more playable notes and multiple cylinders. 
However, these are really expensive and not popular in many developing country, 
such as my homeland-Vietnam. As someone who had played a REUGE music box, I deem 
its music is extremely magnificent and that type of music box should be made as 
an instrument, which is free, user-friendly and not limited by 
cylinders...
This prompted me to create a music box simulator, allowing everyone to customize 
their own track, listening to it and enjoying the rolling pins hitting the 
comb's teeth as a real music box.

## User stories
- As a user, I want to insert notes to my track.
- As a user, I want to see all inserted note of my track.
- As a user, I want to delete a note from my track.
- As a user, I want to hear the sound of the note I just inserted.
- As a user, I want to listen to my whole track. 
- As a user, I want to save my track (all the notes I inserted) as a file.
- As a user, I want to load my saved track. 

## Advance User stories (which I don't want to do yet)
- As a user, I want each Note to have its own duration (the time gap
  between 2 notes). This means I need
  to add new parameter to Note: "Name", which includes: Semibreve(sb), Minim(m),
  Crotchet (c), Quaver (q), Semiquaver(sq). <br>
  I can do this by specifying each name has it own delay and execute the delay
  of the note when it is called by playTrack().
- As a user, I want to play multiple notes simultaneously. 
I can do this by not using delay in for loop of @override playTrack(). 
- As a user, I want to hear my song not just a consecutive 
list of single notes, but a list of 
tracks (a track may contain multiple notes playing if the user want to
play them simultaneously). <br>
I can do this by creating a new super class of Track: "Song" which has 
the method playSong(), which use a combination of @override playTrack(), which has 0 delay to
play tracks with more than 1 note inside, and an original playTrack() to
play tracks with only 1 note inside. <br>
  Still, I need to retain the duration of the longest note in a track before
  the next track is played. Consequently, both original and @override playSong() must have it delay for loop
  based on each track. This means a track needs a new parameter: "Beats" to
  determine the delay, which is equal to the longest note's duration inside
  the track.
