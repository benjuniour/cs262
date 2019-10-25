-- 8.1
-- a. Retrieve a list of all the games, ordered by date with the most recent game coming first. 
-- SELECT *
--   FROM game
-- ORDER BY time DESC
--   ;

-- b. Retrieve all the games that occurred in the past week.
-- SELECT *
--   FROM Game WHERE time >= date_trunc('week', CURRENT_TIMESTAMP - interval '1 week')
--   ;

-- c. Retrieve a list of players who have (non-NULL) names.
-- SELECT name
--   FROM player
-- WHERE name IS not NULL
-- ;

-- d. Retrieve a list of IDs for players who have some game score larger than 2000.
-- SELECT playerid
--   FROM playergame
-- WHERE score >= 2000
-- ;

-- e. Retrieve a list of players who have GMail accounts.
-- SELECT name
--   FROM Player WHERE emailaddress LIKE '%@gmail%'
--   ;

-- 8.2
-- a. Retrieve all “The King”’s game scores in decreasing order.
SELECT score
  FROM playergame, player
  WHERE playergame.playerid = player.id AND 
        name = 'The King'
  ORDER BY score DESC
  ;

-- b. Retrieve the name of the winner of the game played on 2006-06-28 13:20:00.
SELECT name
  FROM playergame, game, player
  WHERE playergame.gameid = game.id AND
        playergame.playerid = player.id AND
        time = '2006-06-28 13:20:00'
  ORDER BY score DESC
  LIMIT 1
  ;

-- c. So what does that P1.ID < P2.ID clause do in the last example query?
-- makes sure that the players that we are comparing are not the same person in different datatables

-- d. The query that joined the Player table to itself seems rather contrived. Can you think of a realistic situation in which you’d want to join a table to itself?
-- If you wanted to filter out duplicate names you would
-- join a table to itself to compare each of the values 
-- in the table with eachother.