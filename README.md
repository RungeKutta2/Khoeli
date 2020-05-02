# Khoeli
Repo del motor de juegos.

# Modelo inicial

Setting:
-WelcomeMessage
-Character

Character:
-Name
-Gender
	Playable:
	-Health points
	-Items

	Non playable:
	-Description
	-Number
	-Speech
	-Triggers

Direction - ENUM // Deberíamos poner un alias a las const
-North
-South
-East
-West

Location:
-Name
-Gender
-Number
-Description
-Places

Place: //Suelo, pared
-Name
-Gender
-Number
-Items

Item:
-Name
-Gender
-Number
-Actions
-EffectsOver

Action:
-Name
-Synonims

Connection:
-Direction
-Location
-Obstacles

Trigger:
-Type
-Thing
-OnTrigger
-AfterTrigger

Endgame:
-Condition
-Action
-Thing
-Description
