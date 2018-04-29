import json

with open("cards.collectible.json", 'r', encoding="utf-8") as inFile:
   with open("addCards.sql", 'w') as outFile:
      jsonCards = json.load(inFile)
      for n in jsonCards:
         n["name"] = n["name"].replace("'", "''")
         print("INSERT INTO Card (id, name, rarity, card_set)",
         " VALUES ('", n["id"], "','", n["name"], "','", n["rarity"], "','",
         n["set"], "');", sep="", file=outFile)