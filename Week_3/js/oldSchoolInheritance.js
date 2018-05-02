function Hero(heroName = "Sorey", heroLevel = 99) {
    this.heroName = heroName;
    this.heroLevel = heroLevel;

}

Hero.prototype.sayHello = function () {
    console.log(`${this.heroName}, a level ${this.heroLevel} hero, says hello!`);
}

let hero = new Hero();
// hero.sayHello();

hero.nobodyElseCanUseThis = function() {
    console.log(`${this.heroName} says 'I'm the only one who can use this'`);
}

// let otherHero = new Hero("Barrett", 65);
// otherHero.nobodyElseCanUseThis();


function Shepard(heroName="Sorey", heroLevel=99, heroWeapon="The Sword of the Shepard"){
    // In order to effectively call 'super' in JS, we use
    Hero.call(this, heroName, heroLevel);
    this.heroWeapon = heroWeapon;
}

// use this to link an object with another Object's prototype
// To make the Shepard object effectively be a subclass of Hero, we use
Shepard.prototype = Object.create(Hero.prototype);
Shepard.prototype.attack = function () {
    console.log(`${this.heroName}, a level ${this.heroLevel} Shepard, attacks with ${this.heroWeapon} for ${Math.round(Math.random() * 100)} damage!`);
}

let shepard = new Shepard();
shepard.sayHello();
shepard.attack();

function Seraph(heroName="Mikleo", heroLevel=99, heroSpell="Aselia"){
    Hero.call(this, heroName, heroLevel);
    this.heroSpell = heroSpell;
}

// DONT FORGET TO LINK THE OBJECTS TOGETHER

Seraph.prototype = Object.create(Hero.prototype);
Seraph.prototype.cast = function() {
    console.log(`${this.heroName}, a level ${this.heroLevel} Seraph, attacks with ${this.heroSpell} for ${Math.round(Math.random() * 100)} damage!`);
}

let seraph = new Seraph();
seraph.sayHello();
seraph.cast();

