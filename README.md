# Readme
## Running the program
The main is in core.java
You might want to change the log4j.properties file to log somewhere of your choosing.

## Existing Logins
An existing admin login is  
Username: `admin`  
Password: `admins`

An existing player login is  
Username: `walkindude`  
Password: `walkinwalkin`

## Background
This was based on the card crafting system of Hearthstone, Blizzard Entertainment's collectible card game. It's been simplified in some places for ease of implementation.


## Instructions

You can use the admin account to approve accounts, lock or unlock accounts, and make more admin accounts. More user accounts can be created via "signup".

The "banked" resource is called "dust". You get this dust from turning spare cards that you have into dust using the "Dust_Extras" feature. Any more than two of a particular card is considered "spare".

You get cards by opening "card packs". In any given card pack you're going to have a high chance of getting a "common" quality card, a lower chance of getting a "rare", ditto for "epic", then "legendary". Each card pack draws from a particular "set", a batch of cards that Blizzard released at once. 

For the purposes of this program, I've made it so you can open up many packs at once without the ingame gold (or real life money) this would cost in Hearthstone. Opening up 80 packs or so from a given set will probably give you enough dust to craft anything you want, (after using the "dust extras" feature). That allows you to spend the dust, using the "craft card" feature. The rates to spend are listed in-app. Turning the duplicates into dust gets you less dust than it costs to craft a card, I didn't bother listing the rates in-app but they're

## Libraries used
I used org.json for json parsing, jbcrypt for password encryption, log4j for logging, math3 from apache commons for card rarity rates for pack opening, text-io for IO and jUnit for testing.

## Other things

user data is stored in /src/main/resources/users.json
card data is stored in /src/main/resources/cards.collectible.json
card data is modified from a file downloaded from hearthstonejson.com, and pack rates were taken from a hearthstone wiki.

